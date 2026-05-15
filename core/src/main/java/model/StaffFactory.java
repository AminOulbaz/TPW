package model;

import beans.Staff;

public class StaffFactory {
    public static Staff createStaff(String type, String username, String name, String surname) {
        if ("ADMIN".equalsIgnoreCase(type)) {
            return new AdministrativeStaff(username, name, surname);
        } else if ("TECH".equalsIgnoreCase(type)) {
            return new TechnicalStaff(username, name, surname);
        }
        throw new IllegalArgumentException("Staff type is not recognized: " + type);
    }

    public static Staff createActiveStaff(UserSession user) {
        switch (user.getActiveRole()) {
            case "ADMIN":
                return new AdministrativeStaff(user.getUsername(), user.getName(), user.getSurname());
            case "MANAGER":
                TechnicalStaff manager = new TechnicalStaff(user.getUsername(), user.getName(), user.getSurname());
                manager.setRoleContext("MANAGER");
                return manager;
            case "COLLABORATOR":
                TechnicalStaff collaborator = new TechnicalStaff(user.getUsername(), user.getName(), user.getSurname());
                collaborator.setRoleContext("COLLABORATOR");
                return collaborator;
            default:
                throw new IllegalStateException("invalid role: " + user.getActiveRole());
        }
    }
}
