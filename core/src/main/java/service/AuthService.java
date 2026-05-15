package service;

import beans.Staff;
import dao.StaffDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import util.PasswordManager;

@ApplicationScoped
public class AuthService {

    @Inject
    private StaffDao dao;
    @Inject
    private PasswordManager manager;

    public AuthService() {}

    public Staff getStaffMember(String username){
        return dao.find(username);
    }

    public void register(String username, String password,
                         String name, String surname,
                         String photoLocation, String typeStaff){
        String hashed = manager.hashPassword(password);
        dao.insert(username, hashed, name, surname,photoLocation,typeStaff);
    }

    public void login(String username, String password) {
        Staff user = getStaffMember(username);
        if (user == null) throw new RuntimeException("no staff member found with this username "+username);
        if(!manager.checkPassword(password, user.getPassword())) throw new RuntimeException("wrong password");
    }
}
