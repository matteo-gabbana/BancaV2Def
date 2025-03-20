package economia;

import javax.swing.*;
import tools.FileManager;

public class ContoCorrente {

  private double saldo;
  private boolean modalitaTest = false;

  public ContoCorrente(double saldo) {
    this.saldo = saldo;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public void setModalitaTest(boolean modalitaTest) {
    this.modalitaTest = modalitaTest;
  }

  public double deposita(double importo, String data, String username) {
    return effettuaTransazione(importo, data, username, true);
  }

  public double preleva(double importo, String data, String username) {
    return effettuaTransazione(-importo, data, username, false);
  }

  private double effettuaTransazione(
      double importo, String data, String username, boolean isDeposito) {

    double saldoPrecedente = saldo;
    saldo += importo;

    if (!modalitaTest) {

      String messaggio = getMessaggio(importo, isDeposito, saldoPrecedente);

      FileManager.salvaTransazione(username, data, messaggio);

      String tipo;
      if (isDeposito) {
        tipo = "Deposito";
      } else {
        tipo = "Prelievo";
      }
      mostraMessaggio(tipo + " eseguito con successo.");
    }

    return importo;
  }

  private String getMessaggio(double importo, boolean isDeposito, double saldoPrecedente) {
    String tipoTransazione;
    if (isDeposito) {
      tipoTransazione = "Deposito effettuato: +";
    } else {
      tipoTransazione = "Prelievo effettuato: -";
    }

    String messaggio =
        tipoTransazione
            + String.format("%.2f", Math.abs(importo))
            + "$. "
            + "Saldo precedente: "
            + String.format("%.2f", saldoPrecedente)
            + "$, "
            + "Saldo attuale: "
            + String.format("%.2f", saldo)
            + "$.";
    return messaggio;
  }

  private void mostraMessaggio(String messaggio) {
    JOptionPane.showMessageDialog(null, messaggio, "Successo", JOptionPane.INFORMATION_MESSAGE);
  }

  public String mostraSaldo() {
    return ("Saldo conto corrente: $" + String.format("%.2f", saldo));
  }
}
