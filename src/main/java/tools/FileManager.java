package tools;

import economia.ContoCorrente;
import economia.Portafoglio;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class FileManager {

  private static final String PATH_CARTELLA_UTENTI = "fileUtenti/";
  private static final String PATH_CARTELLA_REGISTRI = "registroTransazioni/";
  private static final String PATH_CARTELLA_DATI_BILANCIO = "datiBilancio/";
  private static final File cartellaUtenti = new File(PATH_CARTELLA_UTENTI);

  private static final Vector<String> elencoTransazioni = new Vector<>();

  public static void salvaSituazioneBilanci(
      String username, String data, Portafoglio portafoglio, ContoCorrente conto) {

    File cartella = new File(PATH_CARTELLA_DATI_BILANCIO);
    if (!cartella.exists()) {
      cartella.mkdirs();
    }

    File datiBilancioUtente = new File(PATH_CARTELLA_DATI_BILANCIO + "dati-" + username + ".csv");

    if (!datiBilancioUtente.exists()) {
      try {
        datiBilancioUtente.createNewFile();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(
            null,
            "Errore nella creazione del file .csv dei bilanci!",
            "Errore",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
    }

    try {
      FileWriter writer = new FileWriter(datiBilancioUtente, true);
      writer.write(data + ";" + conto.getSaldo() + ";" + portafoglio.getBilancio());
      writer.write("\n");
      writer.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null, "Errore nel salvataggio dei bilanci", "Errore", JOptionPane.WARNING_MESSAGE);
    }
  }

  public static void salvaTransazione(String username, String data, String transazione) {

    String operazione = "";
    operazione += username + " | " + data + "\n";
    operazione += transazione;
    operazione += "\n\n";

    elencoTransazioni.add(operazione);
  }

  public static void scriviTransazioni(String username) {

    File cartella = new File(PATH_CARTELLA_REGISTRI);
    if (!cartella.exists()) {
      cartella.mkdirs();
    }

    File registroUtente = new File(PATH_CARTELLA_REGISTRI + "registro-" + username + ".txt");

    if (!registroUtente.exists()) {
      try {
        registroUtente.createNewFile();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(
            null, "Errore nella creazione del registro!", "Errore", JOptionPane.WARNING_MESSAGE);
        return;
      }
    }

    for (String transazione : elencoTransazioni) {
      try {
        FileWriter writer = new FileWriter(registroUtente, true);
        writer.write(transazione);
        writer.close();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(
            null,
            "Errore nel salvataggio della transazione",
            "Errore",
            JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  public static void salvaUtente(
      String username,
      String password,
      double saldoConto,
      double bilancioPortafoglio,
      String data,
      double saldoTSLA,
      double saldoNVDA,
      double saldoAMZN,
      double saldoAAPL) {

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
      JOptionPane.showMessageDialog(
          null,
          "Errore nel salvataggio dei dati dell'utente.",
          "Errore",
          JOptionPane.WARNING_MESSAGE);
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

      return new String[] {
        nome,
        password,
        saldoConto,
        bilancioPortafoglio,
        data,
        saldoTSLA,
        saldoNVDA,
        saldoAMZN,
        saldoAAPL
      };
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null,
          "Errore nel caricamento dei dati dell'utente.",
          "Errore",
          JOptionPane.WARNING_MESSAGE);
      return null;
    }
  }

  public static Vector<String> getElencoTransazioni(String username) {

    Vector<String> transazioni = new Vector<>();
    File registroUtente = new File(PATH_CARTELLA_REGISTRI + "registro-" + username + ".txt");

    if (!registroUtente.exists()) {
      return transazioni;
    }

    try {

      BufferedReader reader = new BufferedReader(new FileReader(registroUtente));
      StringBuilder operazione = new StringBuilder();
      String linea;

      while ((linea = reader.readLine()) != null) {
        if (linea.trim().isEmpty()) {
          transazioni.add(operazione.toString().trim());
          operazione.setLength(0);
        } else {
          operazione.append(linea).append("\n");
        }
      }

      if (!operazione.isEmpty()) {
        transazioni.add(operazione.toString().trim());
      }

    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null, "Errore nel caricamento delle transazioni", "Errore", JOptionPane.WARNING_MESSAGE);
    }

    return transazioni;
  }
}
