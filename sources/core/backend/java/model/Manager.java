package model;

import beans.Project;
import beans.Staff;

import java.util.HashSet;

public class Manager extends Staff {

    public Manager(String username, String password, String name, String surname, String photo) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setPhoto(photo);
        this.setType("tech"); // manager is in technology staff
    }

    /*
    * assegna i task e le ore previste per il loro completamento
    * definizione dei collaboratori che devono svolgere i task
    * -> stato del progetto assegnato
    * -> monitora il progetto, quindi i WP e i suoi task
    * -> se la somma delle ore lavorate, quelle effettive non è inferiore alle ore previste
    * -> manager può mettere il progetto nello stato di concluso
    * */
}
