package banca;

import economia.ContoCorrente;
import economia.Portafoglio;
import economia.StocksManager;
import gui.LoginPanel;
import gui.MainPanel;
import javax.swing.*;
import tools.DateManager;
import tools.FileManager;

public class Main {

  public static void main(String[] args) {

    impostaLookAndFeel();

    LoginPanel loginPanel = new LoginPanel();

    loginPanel.addLoginListener(
        datiUtente -> {
          String username = datiUtente[0];
          String password = datiUtente[1];
          double saldoConto = Double.parseDouble(datiUtente[2]);
          double bilancioPortafoglio = Double.parseDouble(datiUtente[3]);
          String dataSalvata = datiUtente[4];

          ContoCorrente conto = new ContoCorrente(saldoConto);
          Portafoglio portafoglio = new Portafoglio(conto, bilancioPortafoglio);
          DateManager dateManager = new DateManager(dataSalvata);

          impostaSaldiAzioni(datiUtente);

          Utente utente = new Utente(username, password, dateManager, conto, portafoglio);
          FileManager.salvaSituazioneBilanci(
              username, dateManager.getDataCorrente(), portafoglio, conto);

          new MainPanel(utente);
        });
  }

  private static void impostaLookAndFeel() {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void impostaSaldiAzioni(String[] datiUtente) {
    StocksManager.setSaldoTSLA(Double.parseDouble(datiUtente[5]));
    StocksManager.setSaldoNVDA(Double.parseDouble(datiUtente[6]));
    StocksManager.setSaldoAMZN(Double.parseDouble(datiUtente[7]));
    StocksManager.setSaldoAAPL(Double.parseDouble(datiUtente[8]));
  }
}
