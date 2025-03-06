package tools;

import java.io.*;

public class FileManager {

    private static final String PATH_CARTELLA = "fileUtenti/";

    public static void salvaUtente(String username, String password, double saldoConto, double bilancioPortafoglio) {

        try {
            File fileUtente = new File(PATH_CARTELLA + username + ".txt");
            FileWriter writer = new FileWriter(fileUtente);
            writer.write(username + "\n");
            writer.write(password + "\n");
            writer.write(saldoConto + "\n");
            writer.write(bilancioPortafoglio + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei dati dell'utente.");
        }
    }

    public static String[] caricaUtente(String username) {

        File fileUtente = new File(PATH_CARTELLA + username + ".txt");

        if (!fileUtente.exists()) {
            return null;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileUtente));
            String nome = reader.readLine();
            String password = reader.readLine();
            String saldoConto = reader.readLine();
            String bilancioPortafoglio = reader.readLine();
            reader.close();

            return new String[]{nome, password, saldoConto, bilancioPortafoglio};
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dei dati dell'utente.");
            return null;
        }
    }
}
