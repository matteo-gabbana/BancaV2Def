package main.banca;

import main.economia.ContoCorrente;
import main.economia.Portafoglio;
import main.economia.StocksManager;
import main.gui.LoginPanel;
import main.gui.MainPanel;
import javax.swing.*;
import main.tools.DateManager;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginPanel loginPanel = new LoginPanel();

        String[] datiUtente;
        do {
            while (loginPanel.isVisible()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            datiUtente = loginPanel.getDatiUtente();
            if (datiUtente == null) {
                JOptionPane.showMessageDialog(null, "Login o registrazione fallita", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } while (datiUtente == null);

        String username = datiUtente[0];
        String password = datiUtente[1];

        double saldoConto = Double.parseDouble(datiUtente[2]);
        double bilancioPortafoglio = Double.parseDouble(datiUtente[3]);
        ContoCorrente conto = new ContoCorrente(saldoConto);
        Portafoglio portafoglio = new Portafoglio(conto, bilancioPortafoglio);

        String dataSalvata = datiUtente[4];
        DateManager dateManager = new DateManager(dataSalvata);

        StocksManager.setSaldoTSLA(Double.parseDouble(datiUtente[5]));
        StocksManager.setSaldoNVDA(Double.parseDouble(datiUtente[6]));
        StocksManager.setSaldoAMZN(Double.parseDouble(datiUtente[7]));
        StocksManager.setSaldoAAPL(Double.parseDouble(datiUtente[8]));

        MainPanel mainPanel = new MainPanel(username, password, dateManager, conto, portafoglio);

    }
}