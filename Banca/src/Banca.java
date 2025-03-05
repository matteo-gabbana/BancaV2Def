import java.util.Random;
import java.util.Scanner;


  //Classe principale per il programma della Banca BinGian.

    // prova modifica per primo commit
    // questi commenti servono solo per provare il commit
 
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

    private static int meseCorrente = 1; // Mese corrente
    private static final Random RAND = new Random();

    
    
    //MAIN
    
    public static void main(String[] args) {
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
            scelta = leggiIntero(scanner);

            
            switch (scelta) {
                case 1:
                    depositaDenaro(scanner);
                    break;
                case 2:
                    prelevaDenaro(scanner);
                    break;
                case 3:
                    investiInAzioni(scanner);
                    break;
                case 4:
                    avanzaTempo();
                    break;
                case 5:
                    mostraSaldo();
                    break;
                case 6:
                    mostraInvestimenti();
                    break;
                case 7:
                    chiudiInvestimento(scanner);
                    break;
                case 8:
                    System.out.println("Grazie per aver usato la Banca BinGian! Alla prossima.");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        } while (scelta != 8);

        scanner.close();
    }

    
    
    // Deposito
    
    private static void depositaDenaro(Scanner scanner) {
        System.out.print("Inserisci l'importo da depositare: ");
        double deposito = leggiDouble(scanner);

        
        if (deposito > 0) {
            saldoConto += deposito;							//"%.2f" serve per aver solo 2 cifre dopo la virgola
            System.out.println("Hai depositato: " + String.format("%.2f", deposito) + "€. Saldo attuale: " + String.format("%.2f", saldoConto) + "€.");
        } else {
            System.out.println("Importo non valido. Riprova.");
        }
    }

    
     // Preleva
    
    private static void prelevaDenaro(Scanner scanner) {
        System.out.print("Inserisci l'importo da prelevare: ");
        double prelievo = leggiDouble(scanner);

        if (prelievo > 0 && prelievo <= saldoConto) {
            saldoConto -= prelievo;
            System.out.println("Hai prelevato: " + String.format("%.2f", prelievo) + "€. Saldo attuale: " + String.format("%.2f", saldoConto) + "€.");
        } else if (prelievo > saldoConto) {
            System.out.println("Saldo insufficiente per il prelievo.");
        } else {
            System.out.println("Importo non valido. Riprova.");
        }
    }

    
      // Investimenti -> TSLA, NVDA, AMZN, AAPL
  
    private static void investiInAzioni(Scanner scanner) {
        System.out.println("Su quale azione desideri investire?");
        System.out.println("1) Tesla (TSLA) (alto rischio)");
        System.out.println("2) Nvidia (NVDA) (medio rischio)");
        System.out.println("3) Amazon (AMZN) (basso rischio)");
        System.out.println("4) Apple (AAPL) (basso rischio)");
        System.out.print("Scegli un'opzione: ");
        int sceltaInvestimento = leggiIntero(scanner);

        double importoInvestimento = 0.0;

        
        // minimo da investire 5€
        
        while (importoInvestimento < 5) {
            System.out.print("Quanto vuoi investire? (minimo 5€): ");
            importoInvestimento = leggiDouble(scanner);
            if (importoInvestimento < 5) {
                System.out.println("L'importo minimo per l'investimento è 5€. Riprova.");
            }
        }

        
        if (importoInvestimento <= saldoConto) {
            double tassa = importoInvestimento * 0.01;
            double importoEffettivo = importoInvestimento - tassa;
            saldoConto -= importoInvestimento;

            
            switch (sceltaInvestimento) {		// scelta azione
            
                case 1:
                	
                    saldoTSLA += importoEffettivo;
                    investimentoInizialeTSLA += importoEffettivo;
                    System.out.println("Hai investito in Tesla (TSLA): " + String.format("%.2f", importoEffettivo) + "€.");
                    break;
                    
                case 2:
                	
                    saldoNVDA += importoEffettivo;
                    investimentoInizialeNVDA += importoEffettivo;
                    System.out.println("Hai investito in Nvidia (NVDA): " + String.format("%.2f", importoEffettivo) + "€.");
                    break;
                    
                case 3:
                	
                    saldoAMZN += importoEffettivo;
                    investimentoInizialeAMZN += importoEffettivo;
                    System.out.println("Hai investito in Amazon (AMZN): " + String.format("%.2f", importoEffettivo) + "€.");
                    break;
                    
                case 4:
                	
                    saldoAAPL += importoEffettivo;
                    investimentoInizialeAAPL += importoEffettivo;
                    System.out.println("Hai investito in Apple (AAPL): " + String.format("%.2f", importoEffettivo) + "€.");
                    break;
                    
                default:
                	
                    System.out.println("Opzione non valida.");
                    saldoConto += importoInvestimento; 		// annullazione in caso di errore
                    break;
            }
        } else {
            System.out.println("Saldo insufficiente per l'investimento.");
        }
    }

    // Avanza il tempo di un mese e aggiorna gli investimenti
     
    private static void avanzaTempo() {
        meseCorrente++;
        saldoTSLA = aggiornaValoreInvestimento(saldoTSLA, "TSLA");
        saldoNVDA = aggiornaValoreInvestimento(saldoNVDA, "NVDA");
        saldoAMZN = aggiornaValoreInvestimento(saldoAMZN, "AMZN");
        saldoAAPL = aggiornaValoreInvestimento(saldoAAPL, "AAPL");

        System.out.println("Tempo avanzato. Ora siamo al mese: " + meseCorrente);
    }

    
     // Aggiorna il valore di un investimento in base al rischio specifico
  
     
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
                
            default:		// non fa niente e non serve
            	
                variazione = 0; 
                break;
        }

        return saldo + (saldo * variazione);
    }

    
    
     //Mostra il saldo totale del conto
     
    private static void mostraSaldo() {
        System.out.println("Saldo disponibile: " + String.format("%.2f", saldoConto) + "€.");
    }

    
    
     //Mostra gli investimenti in corso
     
    private static void mostraInvestimenti() {
        System.out.println("Investimenti attuali:");
        System.out.println("Tesla (TSLA): " + String.format("%.2f", saldoTSLA) + "€.");
        System.out.println("Nvidia (NVDA): " + String.format("%.2f", saldoNVDA) + "€.");
        System.out.println("Amazon (AMZN): " + String.format("%.2f", saldoAMZN) + "€.");
        System.out.println("Apple (AAPL): " + String.format("%.2f", saldoAAPL) + "€.");
    }

    
     //Permette di chiudere un investimento
    
     
    private static void chiudiInvestimento(Scanner scanner) {
        System.out.println("Quale investimento vuoi chiudere?");
        System.out.println("1) Tesla (TSLA)");
        System.out.println("2) Nvidia (NVDA)");
        System.out.println("3) Amazon (AMZN)");
        System.out.println("4) Apple (AAPL)");
        System.out.print("Scegli un'opzione: ");
        int scelta = leggiIntero(scanner);

        double saldoRecuperato = 0.0;

        switch (scelta) {
            case 1:
                saldoRecuperato = saldoTSLA;
                saldoTSLA = 0.0;
                break;
            case 2:
                saldoRecuperato = saldoNVDA;
                saldoNVDA = 0.0;
                break;
            case 3:
                saldoRecuperato = saldoAMZN;
                saldoAMZN = 0.0;
                break;
            case 4:
                saldoRecuperato = saldoAAPL;
                saldoAAPL = 0.0;
                break;
            default:
                System.out.println("Opzione non valida.");
                return;
        }

        saldoConto += saldoRecuperato;
        System.out.println("Hai chiuso l'investimento e recuperato: " + String.format("%.2f", saldoRecuperato) + "€.");
    }

    
    
     //Legge un intero dall'input dell'utente, gestendo eventuali errori.

     
    private static int leggiIntero(Scanner scanner) {
        while (!scanner.hasNextInt()) { 					// Controlla se l'input non è un intero
            System.out.print("Input non valido. Inserisci un numero intero: ");
            scanner.next(); 
        }
        return scanner.nextInt(); // Restituisce l'intero valido
    }

    
    
      // stessa roba di prima ma double

     
    private static double leggiDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) { 					// controlla se l'input non è un double
            System.out.print("Input non valido. Inserisci un numero decimale: ");
            scanner.next(); 
        }
        return scanner.nextDouble(); // Restituisce il double valido
    }

}
