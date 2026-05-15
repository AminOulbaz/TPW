package dao;

import beans.*;
import dto.CollaborationDetailed;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.HashSet;

@ApplicationScoped
public class CollaborationDao extends GroundDao {
    //insert a new collaboration
    public void insert(Collaboration collaboration) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into task values(?,?,?,?,?)");
            psmt.setString(1, collaboration.getCollaborator());
            psmt.setInt(2, collaboration.getTask());
            psmt.setInt(3, collaboration.getMonth());
            psmt.setInt(4, collaboration.getExpectedHours());
            psmt.setInt(5, collaboration.getEffectiveHours());
            psmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //find project title associated on task where the collaborator is assigned for
    public HashSet<Project> findProjects(String collaborator) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(""+
                    "select p.title as project_title\n" +
                    "form collaboration as c\n" +
                    "join task as t on t.id=c.task\n" +
                    "join work_package as w on w.id=t.work_package\n" +
                    "join project as p on p.id=w.project\n" +
                    "where c.collaborator=?");
            psmt.setString(1, collaborator);
            ResultSet rs = psmt.executeQuery();
            HashSet<Project> projects = new HashSet<>();
            while(rs.next()) {
                Project project = new Project();
                project.setTitle(rs.getString("project_title"));
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //find work_package title associated on task where the collaborator is assigned for
    public HashSet<WorkPackage> findWorkPackages(String collaborator) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(""+
                    "select w.title as work_package_title\n" +
                    "form collaboration as c\n" +
                    "join task as t on t.id=c.task\n" +
                    "join work_package as w on w.id=t.work_package\n" +
                    "where c.collaborator=?");
            psmt.setString(1, collaborator);
            ResultSet rs = psmt.executeQuery();
            HashSet<WorkPackage> workPackages = new HashSet<>();
            while(rs.next()) {
                WorkPackage workPackage = new WorkPackage();
                workPackage.setTitle(rs.getString("work_package_title"));
                workPackages.add(workPackage);
            }
            return workPackages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //aggregates into CollaborationDetailed object the collaboration and the details of the task
    public HashSet<CollaborationDetailed> findDetails(String collaborator) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(""+
                    "select t.title, t.id, t.begin_month, t.end_month, c.expected_hours, c.effective_hours\n" +
                    "form collaboration as c\n" +
                    "join task as t on t.id=c.task\n" +
                    "where c.collaborator=?");
            psmt.setString(1, collaborator);

            ResultSet rs = psmt.executeQuery();
            HashSet<CollaborationDetailed> collaborationDetaileds = new HashSet<>();
            while(rs.next()) {
                CollaborationDetailed collaborationDetailed = new CollaborationDetailed();
                Task task = new Task();
                task.setTitle(rs.getString("title"));
                task.setId(rs.getInt("id"));
                task.setBeginMonth(rs.getInt("begin_month"));
                task.setEndMonth(rs.getInt("end_month"));
                collaborationDetailed.setTask(task);
                Collaboration collaboration = new Collaboration();
                collaboration.setCollaborator(collaborator);
                collaboration.setExpectedHours(rs.getInt("expected_hours"));
                collaboration.setExpectedHours(rs.getInt("effective_hours"));
                collaborationDetailed.setCollaboration(collaboration);
                collaborationDetaileds.add(collaborationDetailed);
            }
            return collaborationDetaileds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
