package model;

import beans.Staff;

public class AdministrativeStaff extends Staff {
    public AdministrativeStaff(String username, String name, String surname) {
        this.setUsername(username);
        this.setName(name);
        this.setSurname(surname);
        this.setType("ADMIN"); // administrator is in administrative staff
    }
}
