package tools;

import java.io.*;

public class FileManager {

    private static final String PATH_CARTELLA_UTENTI = "fileUtenti/";
    private static File cartellaUtenti = new File(PATH_CARTELLA_UTENTI);

    private static final String PATH_CARTELLA_REGISTRO = "registroTransazioni/";
    private static final String PATH_REGISTRO = PATH_CARTELLA_REGISTRO + "registro.txt";
    private static File registroTransazioni = new File(PATH_REGISTRO);

    public static void salvaTransazione(String username, String data, String transazione) {

        File cartella = new File(PATH_CARTELLA_REGISTRO);
        if (!cartella.exists()) {
            cartella.mkdirs();
        }

        if (!registroTransazioni.exists()) {
            try {
                registroTransazioni.createNewFile();
            } catch (IOException e) {
                System.out.println("Errore nella creazione del registro!");
                return;
            }
        }

        try {
            FileWriter writer = new FileWriter(registroTransazioni, true);
            writer.write(username + " | " + data + "\n");
            writer.write(transazione);
            writer.write("\n\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio della transazione");
        }
    }

    public static void salvaUtente(String username, String password, double saldoConto, double bilancioPortafoglio, String data, double saldoTSLA, double saldoNVDA, double saldoAMZN, double saldoAAPL) {

        if (!cartellaUtenti.exists()) {
            cartellaUtenti.mkdirs();
        }

        try {
            File fileUtente = new File(PATH_CARTELLA_UTENTI + username + ".txt");
            FileWriter writer = new FileWriter(fileUtente);
            writer.write(username + "\n");
            writer.write(password + "\n");
            writer.write(saldoConto + "\n");
            writer.write(bilancioPortafoglio + "\n");
            writer.write(data + "\n");
            writer.write(saldoTSLA + "\n");
            writer.write(saldoNVDA + "\n");
            writer.write(saldoAMZN + "\n");
            writer.write(saldoAAPL + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei dati dell'utente.");
        }
    }

    public static String[] caricaUtente(String username) {

        File fileUtente = new File(PATH_CARTELLA_UTENTI + username + ".txt");

        if (!fileUtente.exists()) {
            return null;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileUtente));
            String nome = reader.readLine();
            String password = reader.readLine();
            String saldoConto = reader.readLine();
            String bilancioPortafoglio = reader.readLine();
            String data = reader.readLine();
            String saldoTSLA = reader.readLine();
            String saldoNVDA = reader.readLine();
            String saldoAMZN = reader.readLine();
            String saldoAAPL = reader.readLine();
            reader.close();

            return new String[]{nome, password, saldoConto, bilancioPortafoglio, data, saldoTSLA, saldoNVDA, saldoAMZN, saldoAAPL};
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dei dati dell'utente.");
            return null;
        }
    }
}
