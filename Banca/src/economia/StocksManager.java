package economia;

import java.util.Random;
import tools.DateManager;
import tools.FileManager;

import javax.swing.*;

public class StocksManager {

  private static final Random RAND = new Random();

  private static double saldoTSLA = 0.0;
  private static double saldoNVDA = 0.0;
  private static double saldoAMZN = 0.0;
  private static double saldoAAPL = 0.0;

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

  // ULTIMA COSA DA FARE RIMASTA
  public static void mostraInvestimenti() {

    System.out.println("Investimenti attuali:");
    System.out.println(
        "Tesla (TSLA): " + String.format("%.2f", StocksManager.getSaldoTSLA()) + "$.");
    System.out.println(
        "Nvidia (NVDA): " + String.format("%.2f", StocksManager.getSaldoNVDA()) + "$.");
    System.out.println(
        "Amazon (AMZN): " + String.format("%.2f", StocksManager.getSaldoAMZN()) + "$.");
    System.out.println(
        "Apple (AAPL): " + String.format("%.2f", StocksManager.getSaldoAAPL()) + "$.");
  }

  public static void effettuaInvestimento(
      ContoCorrente conto,
      String sceltaInvestimento,
      double importoInvestimento,
      String data,
      String username) {

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

      FileManager.salvaTransazione(
          username,
          data,
          "Investimento in " + sceltaInvestimento + ": " + String.format("%.2f", investimento) + "$.");
    }
  }

  private static double investiInAzioni(double importoInvestimento, double saldoConto) {

    if (importoInvestimento < 5) {
      JOptionPane.showMessageDialog(null, "L'importo minimo per l'investimento e' di 5$", "Errore", JOptionPane.ERROR_MESSAGE);
      return 0;
    }

    if (importoInvestimento > saldoConto) {
      JOptionPane.showMessageDialog(null, "Saldo insufficiente per l'investimento.", "Errore", JOptionPane.ERROR_MESSAGE);
      return 0;
    }

    double tassa = importoInvestimento * 0.01;
    JOptionPane.showMessageDialog(null, "Importo investito dopo le tasse: " + String.format("%.2f", (importoInvestimento - tassa)), "Valore Importo Investimento post-tasse", JOptionPane.INFORMATION_MESSAGE);
    return importoInvestimento - tassa;

//    switch (sceltaInvestimento) {
//      case 1:
//        System.out.println(
//            "Hai investito in Tesla (TSLA): " + String.format("%.2f", importoEffettivo) + "$.");
//        return importoEffettivo;
//
//      case 2:
//        System.out.println(
//            "Hai investito in Nvidia (NVDA): " + String.format("%.2f", importoEffettivo) + "$.");
//        return importoEffettivo;
//
//      case 3:
//        System.out.println(
//            "Hai investito in Amazon (AMZN): " + String.format("%.2f", importoEffettivo) + "$.");
//        return importoEffettivo;
//
//      case 4:
//        System.out.println(
//            "Hai investito in Apple (AAPL): " + String.format("%.2f", importoEffettivo) + "$.");
//        return importoEffettivo;
//
//      default:
//        System.out.println("Opzione non valida.");
//        return 0;
  }

  private static double aggiornaValoreInvestimento(double saldo, String tipoAzione) {

    double variazione = 0;
    switch (tipoAzione) {
      case "TSLA":
        variazione = RAND.nextDouble() * 0.5 - 0.25; // tra -25% e +25%
        break;
      case "NVDA":
        variazione = RAND.nextDouble() * 0.2 - 0.1; // tra -10% e +10%
        break;
      case "AMZN":
      case "AAPL":
        variazione = RAND.nextDouble() * 0.1 - 0.05; // tra -5% e +5%
        break;
    }

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
      String scelta, ContoCorrente conto, String data, String username) {

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

    FileManager.salvaTransazione(
        username,
        data,
        "Chiusura investimento in "
            + scelta
            + ": "
            + String.format("%.2f", saldoRecuperato)
            + "$ recuperati.");

    JOptionPane.showMessageDialog(null, "Hai chiuso l'investimento in " + scelta + " e recuperato: " + String.format("%.2f", saldoRecuperato) + "$.", "Chiusura Investimento", JOptionPane.INFORMATION_MESSAGE);
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
