package dao;

import beans.Project;
import beans.Staff;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.HashSet;

@ApplicationScoped
public class ProjectDao extends GroundDao {
    //insert a new project into db
    public Project create(Project project){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into project values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, project.getTitle());
            psmt.setString(2, project.getDescription());
            psmt.setInt(3, project.getPeriod());
            psmt.setString(4, project.getStatus());
            psmt.setString(5, project.getAdminUsername());
            psmt.setString(6, project.getManagerUsername());

            if(psmt.execute()){
                ResultSet rs = psmt.getGeneratedKeys();
                if(rs.next()){
                    project.setId(rs.getInt(1));
                    return project;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //update project status
    public void updateStatus(Integer projectId, String projectStatus) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "update project set status = ? where id = ?");
            psmt.setString(1, projectStatus);
            psmt.setInt(2, projectId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Project> findProjectsByStatus(String status) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from project where status = ?");
            psmt.setString(1, status);
            ResultSet rs = psmt.executeQuery();
            HashSet<Project> projects = new HashSet<>();
            while(rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setTitle(rs.getString("title"));
                project.setDescription(rs.getString("description"));
                project.setPeriod(rs.getInt("period"));
                project.setStatus(status);
                project.setAdminUsername(rs.getString("admin"));
                project.setManagerUsername(rs.getString("manager"));
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Project> findProjectsByManager(String managerUsername){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from project where manager=?");
            psmt.setString(1, managerUsername);
            ResultSet rs = psmt.executeQuery();
            HashSet<Project> projects = new HashSet<>();
            while(rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setTitle(rs.getString("title"));
                project.setDescription(rs.getString("description"));
                project.setPeriod(rs.getInt("period"));
                project.setStatus(rs.getString("status"));
                project.setAdminUsername(rs.getString("admin"));
                project.setManagerUsername(rs.getString("manager"));
                projects.add(project);
            }
            return projects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
