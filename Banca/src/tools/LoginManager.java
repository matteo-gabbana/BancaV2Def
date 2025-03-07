package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class LoginManager {

    public static String[] loginORegistrazione() {

        Scanner input = new Scanner(System.in);
        System.out.println("\nBenvenuto nella Banca Morsli&Gabbana!");
        System.out.println("1) Login");
        System.out.println("2) Registrati");
        System.out.print("Scegli un'opzione: ");
        int scelta = InputTools.inserireIntero();

        while (scelta != 1 && scelta != 2) {
            System.out.println("Scelta non valida.");
            System.out.print("Scegli un'opzione: ");
            scelta = InputTools.inserireIntero();
        }

        System.out.print("Inserisci username: ");
        String username = input.nextLine();
        System.out.print("Inserisci password: ");
        String password = input.nextLine();

        if (scelta == 1) {
            return effettuaLogin(username, password);
        } else {
            return registraUtente(username, password);
        }
    }

    public static String[] effettuaLogin(String username, String password) {

        String[] datiUtente = FileManager.caricaUtente(username);

        if (datiUtente == null || !datiUtente[1].equals(password)) {
            System.out.println("Username o password errati.");
            return null;
        }

        System.out.println("Login effettuato con successo!");
        return datiUtente;
    }

    public static String[] registraUtente(String username, String password) {

        if (FileManager.caricaUtente(username) != null) {
            System.out.println("Questo username è già in uso.");
            return null;
        }

        String dataIniziale = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN));

        FileManager.salvaUtente(username, password, 0.0, 0.0, dataIniziale, 0.0, 0.0, 0.0, 0.0);
        System.out.println("Registrazione completata con successo!");
        return new String[]{username, password, "0.0", "0.0", dataIniziale, "0.0", "0.0", "0.0", "0.0"};
    }
}
