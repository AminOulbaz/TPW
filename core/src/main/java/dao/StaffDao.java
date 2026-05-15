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
    public void insert(String username, String password,
                       String name, String surname,
                       String photoLocation, String typeStaff) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(
                    "insert into staff values(?,?,?,?,?,?)"
            );
            psmt.setString(1, username);
            psmt.setString(2, name);
            psmt.setString(2, surname);
            psmt.setString(4, password);
            psmt.setString(5, photoLocation);
            psmt.setString(6, typeStaff);

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
                        rs.getString("type"),
                        username,
                        rs.getString("name"),
                        rs.getString("surname")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
