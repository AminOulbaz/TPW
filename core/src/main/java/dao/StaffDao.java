package dao;

import beans.Staff;
import jakarta.enterprise.context.ApplicationScoped;
import model.StaffFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@ApplicationScoped
public class StaffDao extends GroundDao {
    //insert a new user into staff
    public void insert(Staff staff, String type) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into staff values(?,?,?,?,?,?)"
            );
            psmt.setString(1, staff.getUsername());
            psmt.setString(2,staff.getName());
            psmt.setString(2,staff.getSurname());
            psmt.setString(4, staff.getPassword());
            psmt.setString(5,staff.getPhoto());
            psmt.setString(6, type);

            psmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<Staff> findTechnicalStaff(){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from staff where type='TECH'");
            ResultSet rs = psmt.executeQuery();
            HashSet<Staff> staff = new HashSet<>();
            while(rs.next()) {
                staff.add(
                        StaffFactory.createStaff(
                                rs.getString("username"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("type")
                        )
                );
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Staff find(String username){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement("select * from staff where username=?");
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()) {
                return StaffFactory.createStaff(
                        username,
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("type")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
