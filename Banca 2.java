import java.util.Random;
import java.util.Scanner;

public class Banca {

    private static double saldoConto = 0.0;
    private static double saldoTSLA = 0.0;
    private static double saldoNVDA = 0.0;
    private static double saldoAMZN = 0.0;
    private static double saldoAAPL = 0.0;

    private static double investimentoInizialeTSLA = 0.0;
    private static double investimentoInizialeNVDA = 0.0;
    private static double investimentoInizialeAMZN = 0.0;
    private static double investimentoInizialeAAPL = 0.0;

    private static int meseCorrente = 1;  // Mese corrente
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\nBenvenuto nella Banca!");
            System.out.println("Mese corrente: " + meseCorrente);
            System.out.println("1) Deposita denaro");
            System.out.println("2) Preleva denaro");
            System.out.println("3) Investi in azioni");
            System.out.println("4) Avanza il tempo di 1 mese");
            System.out.println("5) Visualizza saldo corrente");
            System.out.println("6) Visualizza investimenti in corso");
            System.out.println("7) Chiudi investimento");
            System.out.println("8) Esci");
            System.out.print("Scegli un'opzione: ");
            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    // Deposito denaro
                    System.out.print("Inserisci l'importo da depositare: ");
                    double deposito = scanner.nextDouble();
                    saldoConto += deposito;
                    System.out.println("Hai depositato: " + deposito + "€. Saldo attuale: " + String.format("%.2f", saldoConto) + "€");
                    break;

                case 2:
                    // Prelievo denaro
                    System.out.print("Inserisci l'importo da prelevare: ");
                    double prelievo = scanner.nextDouble();
                    if (prelievo <= saldoConto) {
                        saldoConto -= prelievo;
                        System.out.println("Hai prelevato: " + prelievo + "€. Saldo attuale: " + String.format("%.2f", saldoConto) + "€");
                    } else {
                        System.out.println("Saldo insufficiente per il prelievo.");
                    }
                    break;

                case 3:
                    // Investi in azioni senza leva
                    System.out.println("Su quale azione desideri investire?");
                    System.out.println("1) Tesla (TSLA)");
                    System.out.println("2) Nvidia (NVDA)");
                    System.out.println("3) Amazon (AMZN)");
                    System.out.println("4) Apple (AAPL)");
                    System.out.print("Scegli un'opzione: ");
                    int sceltaInvestimento = scanner.nextInt();

                    // L'utente inserisce l'importo da investire
                    double importoInvestimento = 0.0;
                    boolean investimentoValido = false;

                    // Assicuriamoci che l'importo sia almeno 5€
                    while (!investimentoValido) {
                        System.out.print("Quanto vuoi investire? (minimo 5€): ");
                        importoInvestimento = scanner.nextDouble();

                        if (importoInvestimento >= 5) {
                            investimentoValido = true;
                        } else {
                            System.out.println("L'importo minimo per l'investimento è 5€. Riprova.");
                        }
                    }

                    // Tassa del 1% sull'importo dell'investimento
                    double tassa = importoInvestimento * 0.01;
                    double importoEffettivo = importoInvestimento - tassa;
                    System.out.println("Tassa applicata (1%): " + String.format("%.2f", tassa) + "€. Importo effettivo investito: " + String.format("%.2f", importoEffettivo) + "€");

                    if (importoInvestimento <= saldoConto) {
                        saldoConto -= importoInvestimento;  // Rimuove l'importo dal saldo

                        // Aggiunge l'investimento nell'azione scelta
                        switch (sceltaInvestimento) {
                            case 1: // Tesla (TSLA)
                                saldoTSLA += importoEffettivo;
                                investimentoInizialeTSLA += importoEffettivo;  // Salva l'importo iniziale
                                System.out.println("Hai investito " + String.format("%.2f", importoEffettivo) + "€ in Tesla (TSLA).");
                                break;

                            case 2: // Nvidia (NVDA)
                                saldoNVDA += importoEffettivo;
                                investimentoInizialeNVDA += importoEffettivo;  // Salva l'importo iniziale
                                System.out.println("Hai investito " + String.format("%.2f", importoEffettivo) + "€ in Nvidia (NVDA).");
                                break;

                            case 3: // Amazon (AMZN)
                                saldoAMZN += importoEffettivo;
                                investimentoInizialeAMZN += importoEffettivo;  // Salva l'importo iniziale
                                System.out.println("Hai investito " + String.format("%.2f", importoEffettivo) + "€ in Amazon (AMZN).");
                                break;

                            case 4: // Apple (AAPL)
                                saldoAAPL += importoEffettivo;
                                investimentoInizialeAAPL += importoEffettivo;  // Salva l'importo iniziale
                                System.out.println("Hai investito " + String.format("%.2f", importoEffettivo) + "€ in Apple (AAPL).");
                                break;
                                
                            default:
                                System.out.println("Opzione non valida.");
                        }
                    } else {
                        System.out.println("Saldo insufficiente per l'investimento.");
                    }
                    break;

                case 4:
                    // Avanza il tempo di 1 mese
                    meseCorrente++;
                    aggiornaInvestimenti();
                    System.out.println("Il tempo è avanzato di un mese. Gli investimenti sono stati aggiornati.");
                    break;

                case 5:
                    // Visualizza saldo corrente
                    System.out.println("\nSaldo corrente:");
                    System.out.println("Saldo conto corrente: " + String.format("%.2f", saldoConto) + "€");
                    System.out.println("Investimenti:");
                    System.out.println("Tesla (TSLA): " + String.format("%.2f", saldoTSLA) + "€");
                    System.out.println("Nvidia (NVDA): " + String.format("%.2f", saldoNVDA) + "€");
                    System.out.println("Amazon (AMZN): " + String.format("%.2f", saldoAMZN) + "€");
                    System.out.println("Apple (AAPL): " + String.format("%.2f", saldoAAPL) + "€");
                    break;

                case 6:
                    // Visualizza investimenti correnti
                    System.out.println("\nInvestimenti in corso:");
                    if (saldoTSLA > 0) {
                        double guadagnoTSLA = saldoTSLA - investimentoInizialeTSLA;
                        System.out.println("Tesla (TSLA): " + String.format("%.2f", saldoTSLA) + "€ (Guadagno/Perdita: " + String.format("%.2f", guadagnoTSLA) + "€)");
                    }
                    if (saldoNVDA > 0) {
                        double guadagnoNVDA = saldoNVDA - investimentoInizialeNVDA;
                        System.out.println("Nvidia (NVDA): " + String.format("%.2f", saldoNVDA) + "€ (Guadagno/Perdita: " + String.format("%.2f", guadagnoNVDA) + "€)");
                    }
                    if (saldoAMZN > 0) {
                        double guadagnoAMZN = saldoAMZN - investimentoInizialeAMZN;
                        System.out.println("Amazon (AMZN): " + String.format("%.2f", saldoAMZN) + "€ (Guadagno/Perdita: " + String.format("%.2f", guadagnoAMZN) + "€)");
                    }
                    if (saldoAAPL > 0) {
                        double guadagnoAAPL = saldoAAPL - investimentoInizialeAAPL;
                        System.out.println("Apple (AAPL): " + String.format("%.2f", saldoAAPL) + "€ (Guadagno/Perdita: " + String.format("%.2f", guadagnoAAPL) + "€)");
                    }
                    if (saldoTSLA == 0 && saldoNVDA == 0 && saldoAMZN == 0 && saldoAAPL == 0) {
                        System.out.println("Nessun investimento in corso.");
                    }
                    break;

                case 7:
                    // Chiudi investimento
                    System.out.println("\nInvestimenti aperti:");
                    if (saldoTSLA > 0) {
                        System.out.println("1) Tesla (TSLA): " + String.format("%.2f", saldoTSLA) + "€");
                    }
                    if (saldoNVDA > 0) {
                        System.out.println("2) Nvidia (NVDA): " + String.format("%.2f", saldoNVDA) + "€");
                    }
                    if (saldoAMZN > 0) {
                        System.out.println("3) Amazon (AMZN): " + String.format("%.2f", saldoAMZN) + "€");
                    }
                    if (saldoAAPL > 0) {
                        System.out.println("4) Apple (AAPL): " + String.format("%.2f", saldoAAPL) + "€");
                    }
                    System.out.print("Quale investimento desideri chiudere? (inserisci il numero corrispondente o 0 per annullare): ");
                    int sceltaChiusura = scanner.nextInt();

                    if (sceltaChiusura == 1 && saldoTSLA > 0) {
                        double guadagnoTSLA = saldoTSLA - investimentoInizialeTSLA;
                        saldoConto += saldoTSLA;  // Rimborso dell'importo investito
                        saldoTSLA = 0;  // Chiude l'investimento
                        System.out.println("Hai chiuso l'investimento in Tesla. Guadagno/Perdita: " + String.format("%.2f", guadagnoTSLA) + "€.");

                    } else if (sceltaChiusura == 2 && saldoNVDA > 0) {
                        double guadagnoNVDA = saldoNVDA - investimentoInizialeNVDA;
                        saldoConto += saldoNVDA;  // Rimborso dell'importo investito
                        saldoNVDA = 0;  // Chiude l'investimento
                        System.out.println("Hai chiuso l'investimento in Nvidia. Guadagno/Perdita: " + String.format("%.2f", guadagnoNVDA) + "€.");

                    } else if (sceltaChiusura == 3 && saldoAMZN > 0) {
                        double guadagnoAMZN = saldoAMZN - investimentoInizialeAMZN;
                        saldoConto += saldoAMZN;  // Rimborso dell'importo investito
                        saldoAMZN = 0;  // Chiude l'investimento
                        System.out.println("Hai chiuso l'investimento in Amazon. Guadagno/Perdita: " + String.format("%.2f", guadagnoAMZN) + "€.");
                        
                    } else if (sceltaChiusura == 4 && saldoAAPL > 0) {
                        double guadagnoAAPL = saldoAAPL - investimentoInizialeAAPL;
                        saldoConto += saldoAAPL;  // Rimborso dell'importo investito
                        saldoAAPL = 0;  // Chiude l'investimento
                        System.out.println("Hai chiuso l'investimento in Apple. Guadagno/Perdita: " + String.format("%.2f", guadagnoAAPL) + "€.");

                    } else {
                        System.out.println("Opzione non valida o nessun investimento da chiudere.");
                    }
                    break;

                case 8:
                    System.out.println("Grazie per aver usato il nostro servizio!");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (scelta != 8);

        scanner.close();
    }

    // Metodo per aggiornare gli investimenti mensili
    
    
    private static void aggiornaInvestimenti() {
        saldoTSLA = aggiornaValoreInvestimento(saldoTSLA);
        saldoNVDA = aggiornaValoreInvestimento(saldoNVDA);
        saldoAMZN = aggiornaValoreInvestimento(saldoAMZN);
        saldoAAPL = aggiornaValoreInvestimento(saldoAAPL);
    }

    // Metodo per simulare l'aggiornamento del valore di un investimento
    
    
    private static double aggiornaValoreInvestimento(double saldo) {
        if (saldo > 0) {
        	
            double percentualeVariazione = rand.nextDouble() * 0.2 - 0.1; // Variazione tra -10% e +10%
            saldo += saldo * percentualeVariazione;
        }
        return saldo;
    }
}
