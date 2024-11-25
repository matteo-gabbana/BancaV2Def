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
                	
                	
                    // Investi in azioni 
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
                        
                        
                        }
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

// DA CONTINUARE
                    

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
