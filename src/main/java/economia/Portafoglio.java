package economia;

import tools.FileManager;

import javax.swing.*;

public class Portafoglio {

  private final ContoCorrente conto;
  private double bilancio;
  private boolean modalitaTest = false;

  public Portafoglio(ContoCorrente conto, double bilancio) {
    this.conto = conto;
    this.bilancio = bilancio;
  }

  public double getBilancio() {
    return bilancio;
  }

  public void setBilancio(double bilancio) {
    this.bilancio = bilancio;
  }

  public void setModalitaTest(boolean modalitaTest) {
    this.modalitaTest = modalitaTest;
    this.conto.setModalitaTest(modalitaTest);
  }

  public void depositaNelConto(double importo, String data, String username, ContoCorrente conto) {

    if (depositoValido(importo)) {
      effettuaDeposito(importo, data, username, conto);
    }
    if (!modalitaTest) {
      FileManager.salvaSituazioneBilanci(username, data, this, conto);
    }
  }

  private boolean depositoValido(double importo) {

    if (importo <= 0) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null, "Importo non valido. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
      }
      return false;
    }

    if (importo > bilancio) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null,
            "Bilancio insufficiente per il deposito.",
            "Bilancio Insufficiente",
            JOptionPane.ERROR_MESSAGE);
      }
      return false;
    }

    return true;
  }

  public void effettuaDeposito(double importo, String data, String username, ContoCorrente conto) {
    double importoDepositato = conto.deposita(importo, data, username);
    bilancio -= importoDepositato;
  }

  public void prelevaDalConto(double importo, String data, String username, ContoCorrente conto) {

    if (prelievoValido(importo, conto)) {
      effettuaPrelievo(importo, data, username, conto);
    }
    if (!modalitaTest) {
      FileManager.salvaSituazioneBilanci(username, data, this, conto);
    }
  }

  private boolean prelievoValido(double importo, ContoCorrente conto) {

    if (importo <= 0) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null, "Importo non valido. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
      }
      return false;
    }

    if (importo > conto.getSaldo()) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null,
            "Saldo insufficiente per il prelievo.",
            "Saldo Insufficiente",
            JOptionPane.ERROR_MESSAGE);
      }
      return false;
    }

    return true;
  }

  public void effettuaPrelievo(double importo, String data, String username, ContoCorrente conto) {
    double importoPrelevato = conto.preleva(importo, data, username);
    bilancio += importoPrelevato;
  }

  public String mostraBilancioPortafoglio() {
    return ("Bilancio portafoglio: $" + String.format("%.2f", bilancio));
  }
}
