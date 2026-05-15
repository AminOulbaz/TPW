package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {

    // Genera un hash sicuro per la password
    public static String hashPassword(String passwordChiaro) {
        return BCrypt.hashpw(passwordChiaro, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String passwordChiaro, String passwordHashata) {
        try {
            return BCrypt.checkpw(passwordChiaro, passwordHashata);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String passwordUtente = "root";

        // --- FASE DI REGISTRAZIONE ---
        String hashDaSalvareNelDB = hashPassword(passwordUtente);
        System.out.println("Hash da salvare: " + hashDaSalvareNelDB);

        // --- FASE DI LOGIN ---
        String passwordInseritaDallUtente = "root";

        if (checkPassword(passwordInseritaDallUtente, hashDaSalvareNelDB)) {
            System.out.println("Login effettuato con successo!");
        } else {
            System.out.println("Password errata.");
        }
    }
}
