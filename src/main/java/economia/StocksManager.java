package economia;

import java.util.Random;
import javax.swing.*;
import tools.DateManager;
import tools.FileManager;

public class StocksManager {

  private static final Random RAND = new Random();

  private static double saldoTSLA = 0.0;
  private static double saldoNVDA = 0.0;
  private static double saldoAMZN = 0.0;
  private static double saldoAAPL = 0.0;

  private static boolean modalitaTest = false;

  public static void setModalitaTest(boolean modalitaTest) {
    StocksManager.modalitaTest = modalitaTest;
  }

  public static double getSaldoTSLA() {
    return saldoTSLA;
  }

  public static void setSaldoTSLA(double saldoTSLA) {
    StocksManager.saldoTSLA = saldoTSLA;
  }

  public static double getSaldoAAPL() {
    return saldoAAPL;
  }

  public static void setSaldoAAPL(double saldoAAPL) {
    StocksManager.saldoAAPL = saldoAAPL;
  }

  public static double getSaldoAMZN() {
    return saldoAMZN;
  }

  public static void setSaldoAMZN(double saldoAMZN) {
    StocksManager.saldoAMZN = saldoAMZN;
  }

  public static double getSaldoNVDA() {
    return saldoNVDA;
  }

  public static void setSaldoNVDA(double saldoNVDA) {
    StocksManager.saldoNVDA = saldoNVDA;
  }

  private static double investiInAzioni(double importoInvestimento, double saldoConto) {

    if (importoInvestimento < 5) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null,
            "L'importo minimo per l'investimento e' di 5$",
            "Errore",
            JOptionPane.ERROR_MESSAGE);
      }
      return 0;
    }

    if (importoInvestimento > saldoConto) {
      if (!modalitaTest) {
        JOptionPane.showMessageDialog(
            null, "Saldo insufficiente per l'investimento.", "Errore", JOptionPane.ERROR_MESSAGE);
      }
      return 0;
    }

    double tassa = importoInvestimento * 0.01;
    if (!modalitaTest) {
      JOptionPane.showMessageDialog(
          null,
          "Importo investito dopo le tasse: "
              + String.format("%.2f", (importoInvestimento - tassa)),
          "Valore Importo Investimento post-tasse",
          JOptionPane.INFORMATION_MESSAGE);
    }
    return importoInvestimento - tassa;
  }

  public static void effettuaInvestimento(
      ContoCorrente conto,
      String sceltaInvestimento,
      double importoInvestimento,
      String data,
      String username,
      Portafoglio portafoglio) {

    double investimento = StocksManager.investiInAzioni(importoInvestimento, conto.getSaldo());

    if (investimento > 0) {
      switch (sceltaInvestimento) {
        case "Tesla (TSLA)":
          StocksManager.setSaldoTSLA(StocksManager.getSaldoTSLA() + investimento);
          break;
        case "Nvidia (NVDA)":
          StocksManager.setSaldoNVDA(StocksManager.getSaldoNVDA() + investimento);
          break;
        case "Amazon (AMZN)":
          StocksManager.setSaldoAMZN(StocksManager.getSaldoAMZN() + investimento);
          break;
        case "Apple (AAPL)":
          StocksManager.setSaldoAAPL(StocksManager.getSaldoAAPL() + investimento);
          break;
      }

      conto.setSaldo(conto.getSaldo() - importoInvestimento);

      if (!modalitaTest) {

        FileManager.salvaSituazioneBilanci(username, data, portafoglio, conto);

        FileManager.salvaTransazione(
            username,
            data,
            "Investimento in "
                + sceltaInvestimento
                + ": "
                + String.format("%.2f", investimento)
                + "$.");
      }
    }
  }

  private static double aggiornaValoreInvestimento(double saldo, String tipoAzione) {

    double variazione =
        switch (tipoAzione) {
          case "TSLA" -> RAND.nextDouble() * 0.5 - 0.25; // tra -25% e +25%
          case "NVDA" -> RAND.nextDouble() * 0.2 - 0.1; // tra -10% e +10%
          case "AMZN", "AAPL" -> RAND.nextDouble() * 0.1 - 0.05; // tra -5% e +5%
          default -> 0;
        };

    return saldo + (saldo * variazione);
  }

  public static void avanzaTempo(DateManager dateManager, Portafoglio portafoglio) {

    dateManager.avanzaDiUnMese();
    portafoglio.setBilancio(portafoglio.getBilancio() + 100);

    saldoTSLA = StocksManager.aggiornaValoreInvestimento(saldoTSLA, "TSLA");
    saldoNVDA = StocksManager.aggiornaValoreInvestimento(saldoNVDA, "NVDA");
    saldoAMZN = StocksManager.aggiornaValoreInvestimento(saldoAMZN, "AMZN");
    saldoAAPL = StocksManager.aggiornaValoreInvestimento(saldoAAPL, "AAPL");
  }

  public static void chiudiInvestimento(
      String scelta, ContoCorrente conto, String data, String username, Portafoglio portafoglio) {

    double saldoRecuperato = 0.0;

    switch (scelta) {
      case "Tesla (TSLA)":
        saldoRecuperato = StocksManager.getSaldoTSLA();
        StocksManager.setSaldoTSLA(0.0);
        break;
      case "Nvidia (NVDA)":
        saldoRecuperato = StocksManager.getSaldoNVDA();
        StocksManager.setSaldoNVDA(0.0);
        break;
      case "Amazon (AMZN)":
        saldoRecuperato = StocksManager.getSaldoAMZN();
        StocksManager.setSaldoAMZN(0.0);
        break;
      case "Apple (AAPL)":
        saldoRecuperato = StocksManager.getSaldoAAPL();
        StocksManager.setSaldoAAPL(0.0);
        break;
    }

    conto.setSaldo(conto.getSaldo() + saldoRecuperato);

    if (!modalitaTest) {
      JOptionPane.showMessageDialog(
          null,
          "Hai chiuso l'investimento in "
              + scelta
              + " e recuperato: "
              + String.format("%.2f", saldoRecuperato)
              + "$.",
          "Chiusura Investimento",
          JOptionPane.INFORMATION_MESSAGE);

      FileManager.salvaSituazioneBilanci(username, data, portafoglio, conto);

      FileManager.salvaTransazione(
          username,
          data,
          "Chiusura investimento in "
              + scelta
              + ": "
              + String.format("%.2f", saldoRecuperato)
              + "$ recuperati.");
    }
  }

  public static boolean isInvestimentoAttivo(String sceltaInvestimento) {

    switch (sceltaInvestimento) {
      case "Tesla (TSLA)":
        if (StocksManager.getSaldoTSLA() != 0) {
          return true;
        }
        break;
      case "Nvidia (NVDA)":
        if (StocksManager.getSaldoNVDA() != 0) {
          return true;
        }
        break;
      case "Amazon (AMZN)":
        if (StocksManager.getSaldoAMZN() != 0) {
          return true;
        }
        break;
      case "Apple (AAPL)":
        if (StocksManager.getSaldoAAPL() != 0) {
          return true;
        }
        break;
    }
    return false;
  }
}
