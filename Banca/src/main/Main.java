package main;

import economia.ContoCorrente;
import economia.Portafoglio;
import economia.StocksManager;
import tools.DateManager;
import tools.FileManager;
import tools.InputTools;
import tools.LoginManager;

public class Main {

    public static void main(String[] args) {

        String[] datiUtente = null;
        while (datiUtente == null) {
            datiUtente = LoginManager.loginORegistrazione();
        }

        String username = datiUtente[0];
        double saldoConto = Double.parseDouble(datiUtente[2]);
        double saldoPortafoglio = Double.parseDouble(datiUtente[3]);

        DateManager dateManager = new DateManager();
        ContoCorrente conto = new ContoCorrente(saldoConto);
        Portafoglio portafoglio = new Portafoglio(conto, saldoPortafoglio);

        int scelta;

        do {

            // Menu principale
            System.out.println("\n--- Menu Banca Morsli&Gabbana ---");
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
            System.out.println("\n");


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

                    System.out.println("Salvataggio dati utente...");
                    FileManager.salvaUtente(username, datiUtente[1], conto.getSaldo(), portafoglio.getBilancio());
                    System.out.println("Grazie per aver usato la Banca Morsli&Gabbana! Alla prossima.");
                    break;
                }
                default: {

                    System.out.println("Scelta non valida. Riprova.");
                    break;
                }
            }

        } while (scelta != 9);

    }
}