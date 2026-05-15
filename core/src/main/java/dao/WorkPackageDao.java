package dao;

import beans.Project;
import beans.WorkPackage;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.HashSet;

@ApplicationScoped
public class WorkPackageDao extends GroundDao {
    //insert a new workpackage and return the same workpackage with id
    public WorkPackage insert(WorkPackage workPackage) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into work_package values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, workPackage.getTitle());
            psmt.setString(2, workPackage.getDescription());
            psmt.setInt(3, workPackage.getProjectId());
            psmt.setInt(4, workPackage.getBeginMonth());
            psmt.setInt(5, workPackage.getEndMonth());
            psmt.setString(6, workPackage.getOrderedId());

            if(psmt.execute()){
                ResultSet rs = psmt.getGeneratedKeys();
                if(rs.next()){
                    workPackage.setId(rs.getInt(1));
                    return workPackage;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<WorkPackage> findAll() {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from work_package");
            ResultSet rs = psmt.executeQuery();
            HashSet<WorkPackage> workPackages = new HashSet<>();
            if(rs.next()) {
                WorkPackage workPackage = new WorkPackage();
                workPackage.setId(rs.getInt("id"));
                workPackage.setTitle(rs.getString("title"));
                workPackage.setDescription(rs.getString("description"));
                workPackage.setProjectId(rs.getInt("project"));
                workPackage.setBeginMonth(rs.getInt("begin_month"));
                workPackage.setEndMonth(rs.getInt("end_month"));
                workPackage.setOrderedId(rs.getString("ordered_id"));
                workPackages.add(workPackage);
            }
            return workPackages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
