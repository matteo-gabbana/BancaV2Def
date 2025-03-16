package economia;

import gui.TransazioniPanel;
import tools.FileManager;

import javax.swing.*;

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

    double saldoPrecedente = saldo;
    importo = effettuaDeposito(importo);

    FileManager.salvaTransazione(
        username,
        data,
        "Deposito effettuato: +"
            + String.format("%.2f", importo)
            + "$. Saldo precedente: "
            + String.format("%.2f", saldoPrecedente)
            + "$, Saldo attuale: "
            + String.format("%.2f", saldo)
            + "$.");

    if (!modalitaTest) {
      JOptionPane.showMessageDialog(null, "Deposito eseguito con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }

    return importo;
  }

  public double effettuaDeposito(double importo) {
    saldo += importo;
    return importo;
  }

  public double preleva(double importo, String data, String username) {

    double saldoPrecedente = saldo;
    importo = effettuaPrelievo(importo);

    FileManager.salvaTransazione(
        username,
        data,
        "Prelievo effettuato: -"
            + String.format("%.2f", importo)
            + "$. Saldo precedente: "
            + String.format("%.2f", saldoPrecedente)
            + "$, Saldo attuale: "
            + String.format("%.2f", saldo)
            + "$.");

    if (!modalitaTest) {
      JOptionPane.showMessageDialog(null, "Prelievo eseguito con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }

    return importo;
  }

  public double effettuaPrelievo(double importo) {
    saldo -= importo;
    return importo;
  }

  public String mostraSaldo() {
    return ("Saldo conto corrente: $" + String.format("%.2f", saldo));
  }
}
