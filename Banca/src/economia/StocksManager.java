package economia;

import tools.DateManager;
import tools.FileManager;

import java.util.Random;

public class StocksManager {

    private static final Random RAND = new Random();

    private static double saldoTSLA = 0.0;
    private static double saldoNVDA = 0.0;
    private static double saldoAMZN = 0.0;
    private static double saldoAAPL = 0.0;

    public static double getSaldoTSLA() {
        return saldoTSLA;
    }

    public static double getSaldoAAPL() {
        return saldoAAPL;
    }

    public static double getSaldoAMZN() {
        return saldoAMZN;
    }

    public static double getSaldoNVDA() {
        return saldoNVDA;
    }

    public static void setSaldoTSLA(double saldoTSLA) {
        StocksManager.saldoTSLA = saldoTSLA;
    }

    public static void setSaldoNVDA(double saldoNVDA) {
        StocksManager.saldoNVDA = saldoNVDA;
    }

    public static void setSaldoAMZN(double saldoAMZN) {
        StocksManager.saldoAMZN = saldoAMZN;
    }

    public static void setSaldoAAPL(double saldoAAPL) {
        StocksManager.saldoAAPL = saldoAAPL;
    }

    public static void mostraInvestimenti() {

        System.out.println("Investimenti attuali:");
        System.out.println("Tesla (TSLA): " + String.format("%.2f", StocksManager.getSaldoTSLA()) + "$.");
        System.out.println("Nvidia (NVDA): " + String.format("%.2f", StocksManager.getSaldoNVDA()) + "$.");
        System.out.println("Amazon (AMZN): " + String.format("%.2f", StocksManager.getSaldoAMZN()) + "$.");
        System.out.println("Apple (AAPL): " + String.format("%.2f", StocksManager.getSaldoAAPL()) + "$.");
    }

    public static void effettuaInvestimento(ContoCorrente conto, int sceltaInvestimento, double importoInvestimento, String data, String username) {

        double investimento = StocksManager.investiInAzioni(sceltaInvestimento, importoInvestimento, conto.getSaldo());
        String nomeAzione = "";

        if (investimento > 0) {
            conto.setSaldo(conto.getSaldo() - investimento);
            switch (sceltaInvestimento) {
                case 1:
                    StocksManager.setSaldoTSLA(StocksManager.getSaldoTSLA() + investimento);
                    nomeAzione = "Tesla (TSLA)";
                    break;
                case 2:
                    StocksManager.setSaldoNVDA(StocksManager.getSaldoNVDA() + investimento);
                    nomeAzione = "Nvidia (NVDA)";
                    break;
                case 3:
                    StocksManager.setSaldoAMZN(StocksManager.getSaldoAMZN() + investimento);
                    nomeAzione = "Amazon (AMZN)";
                    break;
                case 4:
                    StocksManager.setSaldoAAPL(StocksManager.getSaldoAAPL() + investimento);
                    nomeAzione = "Apple (AAPL)";
                    break;
            }

            FileManager.salvaTransazione(username, data, "Investimento in " + nomeAzione + ": " + String.format("%.2f", investimento) + "$.");
        }
    }

    private static double investiInAzioni(int sceltaInvestimento, double importoInvestimento, double saldoConto) {

        if (importoInvestimento < 5) {
            System.out.println("L'importo minimo per l'investimento è di 5$.");
            return 0;
        }

        if (importoInvestimento > saldoConto) {
            System.out.println("Saldo insufficiente per l'investimento.");
            return 0;
        }

        double tassa = importoInvestimento * 0.01;
        double importoEffettivo = importoInvestimento - tassa;

        switch (sceltaInvestimento) {
            case 1:
                System.out.println("Hai investito in Tesla (TSLA): " + String.format("%.2f", importoEffettivo) + "$.");
                return importoEffettivo;

            case 2:
                System.out.println("Hai investito in Nvidia (NVDA): " + String.format("%.2f", importoEffettivo) + "$.");
                return importoEffettivo;

            case 3:
                System.out.println("Hai investito in Amazon (AMZN): " + String.format("%.2f", importoEffettivo) + "$.");
                return importoEffettivo;

            case 4:
                System.out.println("Hai investito in Apple (AAPL): " + String.format("%.2f", importoEffettivo) + "$.");
                return importoEffettivo;

            default:
                System.out.println("Opzione non valida.");
                return 0;
        }
    }

    private static double aggiornaValoreInvestimento(double saldo, String tipoAzione) {

        double variazione;
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
            default:
                variazione = 0;
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

        System.out.println("Tempo avanzato. Data attuale: " + dateManager.getDataCorrente());
    }

    public static void chiudiInvestimento(int scelta, ContoCorrente conto, String data, String username) {

        double saldoRecuperato = 0.0;
        String nomeAzione = "";

        switch (scelta) {
            case 1:
                saldoRecuperato = StocksManager.getSaldoTSLA();
                StocksManager.setSaldoTSLA(0.0);
                nomeAzione = "Tesla (TSLA)";
                break;
            case 2:
                saldoRecuperato = StocksManager.getSaldoNVDA();
                StocksManager.setSaldoNVDA(0.0);
                nomeAzione = "Nvidia (NVDA)";
                break;
            case 3:
                saldoRecuperato = StocksManager.getSaldoAMZN();
                StocksManager.setSaldoAMZN(0.0);
                nomeAzione = "Amazon (AMZN)";
                break;
            case 4:
                saldoRecuperato = StocksManager.getSaldoAAPL();
                StocksManager.setSaldoAAPL(0.0);
                nomeAzione = "Apple (AAPL)";
                break;
            default:
                System.out.println("Opzione non valida.");
                return;
        }

        conto.setSaldo(conto.getSaldo() + saldoRecuperato);

        FileManager.salvaTransazione(username, data, "Chiusura investimento in " + nomeAzione + ": " + String.format("%.2f", saldoRecuperato) + "$ recuperati.");

        System.out.println("Hai chiuso l'investimento e recuperato: " + String.format("%.2f", saldoRecuperato) + "$.");
    }
}
