package model;

import beans.Staff;

public class Collaborator extends Staff {

    public Collaborator(String username, String password, String name, String surname, String photo) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setPhoto(photo);
        this.setType("tech"); // collaborator is in technology staff
    }

    /*
    * esecuzione dei task assegnati con le ore previste
    * inserimento delle ore effettive
    * */
}
