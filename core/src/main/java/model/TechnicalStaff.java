package model;

import beans.Staff;

public class TechnicalStaff extends Staff {
    // context initialized in login phase
    private String roleContext;

    public TechnicalStaff(String username, String name, String surname) {
        this.setUsername(username);
        this.setName(name);
        this.setSurname(surname);
        this.setType("TECH"); // manager and collaborator is in technical staff
    }

    public String getRoleContext() {
        return roleContext;
    }

    public void setRoleContext(String roleContext) {
        this.roleContext = roleContext;
    }

    public boolean isActingAsManager() {
        return "MANAGER".equals(this.roleContext);
    }
}
