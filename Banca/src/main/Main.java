package main;

import economia.ContoCorrente;
import economia.Portafoglio;
import economia.StocksManager;
import tools.DateManager;
import tools.InputTools;

import java.util.Scanner;


//Classe principale per il programma della Banca BinGian.

public class Main {

    public static void main(String[] args) {

        DateManager dateManager = new DateManager();
        ContoCorrente conto = new ContoCorrente();
        Portafoglio portafoglio = new Portafoglio(conto);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nella Banca BinGian!");

        // Login finto
        System.out.print("Inserisci username: ");
        scanner.nextLine();
        System.out.print("Inserisci password: ");
        scanner.nextLine();
        System.out.println("Login effettuato con successo!");

        int scelta;

        do {

            // Menu principale
            System.out.println("\n--- Menu Banca BinGian ---");
            System.out.println("Data attuale: " + dateManager.getDataCorrente());
            System.out.println("1) Deposita denaro sul conto corrente");
            System.out.println("2) Preleva denaro dal conto corrente");
            System.out.println("3) Investi in azioni");
            System.out.println("4) Avanza il tempo di 1 mese (+100$ nel portafoglio)");
            System.out.println("5) Visualizza saldo del conto corrente");
            System.out.println("6) Visualizza investimenti in corso");
            System.out.println("7) Chiudi investimento");
            System.out.println("8) Visualizza bilancio del portafoglio");
            System.out.println("9) Esci");
            System.out.print("Scegli un'opzione: ");
            scelta = InputTools.inserireIntero();
            System.out.println();


            switch (scelta) {
                case 1: {

                    System.out.print("Inserisci l'importo da depositare nel conto corrente: ");
                    double importo = InputTools.inserireDouble();
                    portafoglio.depositaNelConto(importo);
                    break;
                }
                case 2: {

                    System.out.print("Inserisci l'importo da prelevare dal conto corrente: ");
                    double importo = InputTools.inserireDouble();
                    portafoglio.prelevaDalConto(importo);
                    break;
                }
                case 3: {

                    System.out.println("Su quale azione desideri investire?");
                    System.out.println("1) Tesla (TSLA) (alto rischio)");
                    System.out.println("2) Nvidia (NVDA) (medio rischio)");
                    System.out.println("3) Amazon (AMZN) (basso rischio)");
                    System.out.println("4) Apple (AAPL) (basso rischio)");
                    System.out.print("Scegli un'opzione: ");
                    int sceltaInvestimento = InputTools.inserireIntero();
                    while (sceltaInvestimento < 1 || sceltaInvestimento > 4) {
                        System.out.println("Scelta non valida!");
                        System.out.print("Scegli un'opzione: ");
                        sceltaInvestimento = InputTools.inserireIntero();
                    }
                    System.out.print("Quanto vuoi investire? (minimo 5$): ");
                    double importoInvestimento = InputTools.inserireDouble();

                    StocksManager.effettuaInvestimento(conto, sceltaInvestimento, importoInvestimento);
                    break;
                }
                case 4: {

                    StocksManager.avanzaTempo(dateManager, portafoglio);
                    break;
                }
                case 5: {

                    conto.mostraSaldo();
                    break;
                }
                case 6: {

                    StocksManager.mostraInvestimenti();
                    break;
                }
                case 7: {

                    System.out.println("Quale investimento vuoi chiudere?");
                    System.out.println("1) Tesla (TSLA)");
                    System.out.println("2) Nvidia (NVDA)");
                    System.out.println("3) Amazon (AMZN)");
                    System.out.println("4) Apple (AAPL)");
                    System.out.print("Scegli un'opzione: ");
                    int sceltaChiusura = InputTools.inserireIntero();

                    StocksManager.chiudiInvestimento(sceltaChiusura, conto);
                    break;
                }
                case 8: {

                    portafoglio.mostraBilancioPortafoglio();
                    break;
                }
                case 9: {

                    System.out.println("Grazie per aver usato la Banca BinGian! Alla prossima.");
                    break;
                }
                default: {

                    System.out.println("Scelta non valida. Riprova.");
                    break;
                }
            }

        } while (scelta != 9);

        scanner.close();
    }
}

//----------------------------------------------------------------------------------------
//
//                        TUTTE LE VECCHIE ROBE DI GIAN E BINCO
//
//----------------------------------------------------------------------------------------


// Deposito

//    private static void depositaDenaro(Scanner scanner) {
//
//        System.out.print("Inserisci l'importo da depositare: ");
//        double deposito = InputTools.inserireDouble();
//
//        if (deposito > 0) {
//            saldoConto += deposito;
//            System.out.println("Hai depositato: " + String.format("%.2f", deposito) + "$. Saldo attuale: " + String.format("%.2f", saldoConto) + "$.");
//        } else {
//            System.out.println("Importo non valido. Riprova.");
//        }
//    }


// Preleva

//    private static void prelevaDenaro(Scanner scanner) {
//
//        System.out.print("Inserisci l'importo da prelevare: ");
//        double prelievo = InputTools.inserireDouble();
//
//        if (prelievo > 0 && prelievo <= saldoConto) {
//            saldoConto -= prelievo;
//            System.out.println("Hai prelevato: " + String.format("%.2f", prelievo) + "$. Saldo attuale: " + String.format("%.2f", saldoConto) + "$.");
//        } else if (prelievo > saldoConto) {
//            System.out.println("Saldo insufficiente per il prelievo.");
//        } else {
//            System.out.println("Importo non valido. Riprova.");
//        }
//    }


// Investimenti -> TSLA, NVDA, AMZN, AAPL

//    private static void investiInAzioni(Scanner scanner) {
//
//        System.out.println("Su quale azione desideri investire?");
//        System.out.println("1) Tesla (TSLA) (alto rischio)");
//        System.out.println("2) Nvidia (NVDA) (medio rischio)");
//        System.out.println("3) Amazon (AMZN) (basso rischio)");
//        System.out.println("4) Apple (AAPL) (basso rischio)");
//
//        System.out.print("Scegli un'opzione: ");
//        int sceltaInvestimento = InputTools.inserireIntero();
//        while (sceltaInvestimento < 1 || sceltaInvestimento > 4) {
//            System.out.println("Scelta non valida!");
//            System.out.print("Scegli un'opzione: ");
//            sceltaInvestimento = InputTools.inserireIntero();
//        }
//
//        double importoInvestimento = 0.0;
//        while (importoInvestimento < 5) {
//            System.out.print("Quanto vuoi investire? (minimo 5$): ");
//            importoInvestimento = InputTools.inserireDouble();
//            if (importoInvestimento < 5) {
//                System.out.println("L'importo minimo per l'investimento è di 5$. Riprova.");
//            }
//        }
//
//        double investimento = StocksManager.investiInAzioni(sceltaInvestimento, importoInvestimento, saldoConto);
//
//        if (investimento > 0) {
//
//            saldoConto -= investimento;
//
//            switch (sceltaInvestimento) {
//                case 1: /*saldoTSLA += investimento;*/
//                    StocksManager.setSaldoTSLA(StocksManager.getSaldoTSLA() + investimento); break;
//                case 2: /*saldoNVDA += investimento;*/
//                    StocksManager.setSaldoNVDA(StocksManager.getSaldoNVDA() + investimento); break;
//                case 3: /*saldoAMZN += investimento;*/
//                    StocksManager.setSaldoAMZN(StocksManager.getSaldoAMZN() + investimento); break;
//                case 4: /*saldoAAPL += investimento;*/
//                    StocksManager.setSaldoAAPL(StocksManager.getSaldoAAPL() + investimento); break;
//            }
//        }

// ############### ROBA DI GIAN E BINCO ###############
//        if (importoInvestimento <= saldoConto) {
//
//            double tassa = importoInvestimento * 0.01;
//            double importoEffettivo = importoInvestimento - tassa;
//            saldoConto -= importoInvestimento;
//
//
//            switch (sceltaInvestimento) {
//
//                case 1:
//                    saldoTSLA += importoEffettivo;
//                    investimentoInizialeTSLA += importoEffettivo;
//                    System.out.println("Hai investito in Tesla (TSLA): " + String.format("%.2f", importoEffettivo) + "$.");
//                    break;
//
//                case 2:
//                    saldoNVDA += importoEffettivo;
//                    investimentoInizialeNVDA += importoEffettivo;
//                    System.out.println("Hai investito in Nvidia (NVDA): " + String.format("%.2f", importoEffettivo) + "$.");
//                    break;
//
//                case 3:
//                    saldoAMZN += importoEffettivo;
//                    investimentoInizialeAMZN += importoEffettivo;
//                    System.out.println("Hai investito in Amazon (AMZN): " + String.format("%.2f", importoEffettivo) + "$.");
//                    break;
//
//                case 4:
//                    saldoAAPL += importoEffettivo;
//                    investimentoInizialeAAPL += importoEffettivo;
//                    System.out.println("Hai investito in Apple (AAPL): " + String.format("%.2f", importoEffettivo) + "$.");
//                    break;
//
//                default:
//                    System.out.println("Opzione non valida.");
//                    saldoConto += importoInvestimento;
//                    break;
//            }
//        } else {
//            System.out.println("Saldo insufficiente per l'investimento.");
//        }


// Avanza il tempo di un mese e aggiorna gli investimenti

//    private static void avanzaTempo(DateManager dateManager) {
//
//        dateManager.avanzaDiUnMese();
//        saldoTSLA = StocksManager.aggiornaValoreInvestimento(saldoTSLA, "TSLA");
//        saldoNVDA = StocksManager.aggiornaValoreInvestimento(saldoNVDA, "NVDA");
//        saldoAMZN = StocksManager.aggiornaValoreInvestimento(saldoAMZN, "AMZN");
//        saldoAAPL = StocksManager.aggiornaValoreInvestimento(saldoAAPL, "AAPL");
//
//        System.out.println("Tempo avanzato. Data attuale: " + dateManager.getDataCorrente());
//    }


// ############### ROBA DI GIAN E BINCO ###############
// Aggiorna il valore di un investimento in base al rischio specifico

//    private static double aggiornaValoreInvestimento(double saldo, String tipoAzione) {
//        double variazione;
//
//        switch (tipoAzione) {
//
//            case "TSLA":
//
//                variazione = RAND.nextDouble() * 0.5 - 0.25; // tra -25% e +25%
//                break;
//
//            case "NVDA":
//
//                variazione = RAND.nextDouble() * 0.2 - 0.1; // tra -10% e +10%
//                break;
//
//            case "AMZN":
//            case "AAPL":
//
//                variazione = RAND.nextDouble() * 0.1 - 0.05; // tra -5% e +5%
//                break;
//
//            default:        // non fa niente e non serve
//
//                variazione = 0;
//                break;
//        }
//
//        return saldo + (saldo * variazione);
//    }


//Mostra il saldo totale del conto

//    private static void mostraSaldo() {
//        System.out.println("Saldo disponibile: " + String.format("%.2f", saldoConto) + "$.");
//    }


//Mostra gli investimenti in corso

//    private static void mostraInvestimenti() {
//        System.out.println("Investimenti attuali:");
//        System.out.println("Tesla (TSLA): " + String.format("%.2f", StocksManager.getSaldoTSLA()) + "$.");
//        System.out.println("Nvidia (NVDA): " + String.format("%.2f", StocksManager.getSaldoNVDA()) + "$.");
//        System.out.println("Amazon (AMZN): " + String.format("%.2f", StocksManager.getSaldoAMZN()) + "$.");
//        System.out.println("Apple (AAPL): " + String.format("%.2f", StocksManager.getSaldoAAPL()) + "$.");
//    }


//Permette di chiudere un investimento


//    private static void chiudiInvestimento(Scanner scanner) {
//        System.out.println("Quale investimento vuoi chiudere?");
//        System.out.println("1) Tesla (TSLA)");
//        System.out.println("2) Nvidia (NVDA)");
//        System.out.println("3) Amazon (AMZN)");
//        System.out.println("4) Apple (AAPL)");
//        System.out.print("Scegli un'opzione: ");
//        int scelta = InputTools.inserireIntero();
//
//        double saldoRecuperato = 0.0;
//
//        switch (scelta) {
//            case 1:
//                saldoRecuperato = StocksManager.getSaldoTSLA();
//                StocksManager.setSaldoTSLA(0.0);
//                break;
//            case 2:
//                saldoRecuperato = StocksManager.getSaldoNVDA();
//                StocksManager.setSaldoNVDA(0.0);
//                break;
//            case 3:
//                saldoRecuperato = StocksManager.getSaldoAMZN();
//                StocksManager.setSaldoAMZN(0.0);
//                break;
//            case 4:
//                saldoRecuperato = StocksManager.getSaldoAAPL();
//                StocksManager.setSaldoAAPL(0.0);
//                break;
//            default:
//                System.out.println("Opzione non valida.");
//                return;
//        }
//
//        saldoConto += saldoRecuperato;
//        System.out.println("Hai chiuso l'investimento e recuperato: " + String.format("%.2f", saldoRecuperato) + "$.");
//    }

// ############### ROBA DI GIAN E BINCO ###############
//    //Legge un intero dall'input dell'utente, gestendo eventuali errori.
//
//
//    private static int leggiIntero(Scanner scanner) {
//        while (!scanner.hasNextInt()) {                    // Controlla se l'input non è un intero
//            System.out.print("Input non valido. Inserisci un numero intero: ");
//            scanner.next();
//        }
//        return scanner.nextInt(); // Restituisce l'intero valido
//    }
//
//
//    // stessa roba di prima ma double
//
//
//    private static double leggiDouble(Scanner scanner) {
//        while (!scanner.hasNextDouble()) {                    // controlla se l'input non è un double
//            System.out.print("Input non valido. Inserisci un numero decimale: ");
//            scanner.next();
//        }
//        return scanner.nextDouble(); // Restituisce il double valido
//    }
