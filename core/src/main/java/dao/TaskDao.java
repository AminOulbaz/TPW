package dao;

import beans.Project;
import beans.Task;
import beans.WorkPackage;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.HashSet;

@ApplicationScoped
public class TaskDao extends GroundDao {
    //insert a new task and return the same task with id
    public Task insert(Task task) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into task values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, task.getTitle());
            psmt.setString(2, task.getDescription());
            psmt.setInt(3, task.getWorkPackageId());
            psmt.setInt(4, task.getBeginMonth());
            psmt.setInt(5, task.getEndMonth());
            psmt.setString(6, task.getOrderedId());

            if(psmt.execute()){
                ResultSet rs = psmt.getGeneratedKeys();
                if(rs.next()){
                    task.setId(rs.getInt(1));
                    return task;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Task> findByWorkPackage(int workPackageId) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from task where work_package=?");
            psmt.setInt(1, workPackageId);
            ResultSet rs = psmt.executeQuery();
            HashSet<Task> tasks = new HashSet<>();
            if(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setWorkPackageId(workPackageId);
                task.setBeginMonth(rs.getInt("begin_month"));
                task.setEndMonth(rs.getInt("end_month"));
                task.setOrderedId(rs.getString("ordered_id"));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
