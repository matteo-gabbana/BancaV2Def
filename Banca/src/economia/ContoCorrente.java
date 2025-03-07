package economia;

import tools.FileManager;

import javax.swing.*;

public class ContoCorrente {

  private double saldo;

  public ContoCorrente(double saldo) {
    this.saldo = saldo;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  //    public double deposita(double importo) {
  //
  //        saldo += importo;
  //        System.out.println("Hai depositato: " + String.format("%.2f", importo) + "$. Saldo
  // conto: " + String.format("%.2f", saldo) + "$.");
  //        return importo;
  //    }

  public double deposita(double importo, String data, String username) {

    double saldoPrecedente = saldo;
    saldo += importo;

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

    JOptionPane.showMessageDialog(null, "Deposito eseguito con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
    return importo;
  }

  //    public double preleva(double importo) {
  //
  //        if (importo > 0 && importo <= saldo) {
  //            saldo -= importo;
  //            System.out.println("Hai prelevato: " + String.format("%.2f", importo) + "$. Saldo
  // conto: " + String.format("%.2f", saldo) + "$.");
  //            return importo;
  //        } else if (importo > saldo) {
  //            System.out.println("Saldo insufficiente per il prelievo.");
  //        } else {
  //            System.out.println("Importo non valido. Riprova.");
  //        }
  //        return 0;
  //    }

  public double preleva(double importo, String data, String username) {

    double saldoPrecedente = saldo;
    saldo -= importo;

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

    JOptionPane.showMessageDialog(null, "Prelievo eseguito con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
    return importo;
  }

  public String mostraSaldo() {
    return ("Saldo conto corrente: $" + String.format("%.2f", saldo));
  }
}
