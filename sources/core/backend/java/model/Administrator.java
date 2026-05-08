package model;

import beans.Project;
import beans.Staff;

import java.util.HashSet;

public class Administrator extends Staff {
    private HashSet<Project> projects;
    public Administrator(String username, String password, String name, String surname, String photo) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setPhoto(photo);
        this.setType("admin"); // administrator is in administrative staff
    }

    /*
    * crea e definisce la struttura del progetto
    * inserisce il progetto, i WP e task
    * assegna il responsabile del progetto
    * -> stato del progetto creato
    * */


}
