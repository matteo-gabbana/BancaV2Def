package libreria;

import java.util.Scanner;


// Classe libro
class Libro {
    private String titolo;
    private String autore;
    private String genere;
    private int pagine;

    
    // Costruttore per inizializzare un libro
    public Libro(String titolo, String autore, String genere, int pagine) {
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.pagine = pagine;
    }
    

    // campi del libro
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getPagine() {
        return pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }
    

    // rappresentare il libro come stringa
    
    public String toString() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Genere: " + genere + ", Pagine: " + pagine;
    }
}


// rappresenta lo scaffale che contiene libri

class Scaffale {
	
    private Libro[] libri; // Array libri
    private int totale;    // Numero totale di libri attualmente nello scaffale

    
    
    // inizializzare lo scaffale 
    
    public Scaffale(int max) {
        libri = new Libro[max];
        totale = 0;
    }

    
    
    // Metodo per aggiungere un libro allo scaffale
    
    public void aggiungi(Libro libro) {
        if (totale >= libri.length) {
            System.out.println("Scaffale pieno."); // Messaggio se non c'è spazio
            return;
        }
        
        
        libri[totale] = libro; // Aggiunge il libro all'array
        totale++;              // Incrementa il contatore dei libri
        System.out.println("Libro aggiunto.");
    }

    
    
    // Metodo per rimuovere un libro dallo scaffale
    
    public void rimuovi(int posizione) {
        if (posizione < 0 || posizione >= totale) {
            System.out.println("Posizione non valida."); // Controllo se vale l'indice
            return;
        }
        
        
        // Sposta i libri successivi per riempire lo scaffale
        
        for (int i = posizione; i < totale - 1; i++) {
            libri[i] = libri[i + 1];
        }
        
        libri[totale - 1] = null; // Libera l'ultimo spazio
        totale--;                 // Decrementa il contatore
        System.out.println("Libro rimosso.");
    }

    
    
    // modificare i dati di un libro
    
    public void modifica(int posizione, String titolo, String autore, String genere, Integer pagine) {
        if (posizione < 0 || posizione >= totale) {
            System.out.println("Posizione non valida."); // Controllo se vale l'indice
            return;
        }
        
        Libro libro = libri[posizione];
        
        
        
        // Modifica solo quello che vuoi
        
        if (titolo != "" && titolo != null) libro.setTitolo(titolo);
        if (autore != "" && autore != null) libro.setAutore(autore);
        if (genere != "" && genere != null) libro.setGenere(genere);
        if (pagine != null) libro.setPagine(pagine);

        System.out.println("Libro modificato.");
    }
    

    
    // Metodo per mostrare tutti i libri nello scaffale
    
    public void mostra() {
        if (totale == 0) {
            System.out.println("Scaffale vuoto.");
            return;
        }
        for (int i = 0; i < totale; i++) {
            System.out.println((i + 1) + ". " + libri[i]); // Stampa ogni libro con il suo indice
        }
    }

    
    
    // Metodo per ottenere il numero totale di libri nello scaffale
    
    public int getTotale() {
        return totale;
    }
}



// PRINCIPALE - gestisce l'interazione con l'utente

public class Libreria {
    private Scaffale scaffale;  
    private Scanner scanner;    

    
    
    // Costruttore per inizializzare la libreria con uno scaffale di una certa dimensione
    
    public Libreria(int dimensione) {
        scaffale = new Scaffale(dimensione);
        scanner = new Scanner(System.in);
    }

    
    
    // avvio
   
    public void avvia() {
    	
        int scelta; // quiello che vuoe l'utente

        
        do {
            // menù
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Aggiungi libro");
            System.out.println("2 - Rimuovi libro");
            System.out.println("3 - Modifica libro");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");
            scelta = leggiNumero(); // scelta utente

            switch (scelta) {
                case 1:
                    aggiungiLibro();
                    break;
                case 2:
                    rimuoviLibro();
                    break;
                case 3:
                    modificaLibro();
                    break;
                case 0:
                    System.out.println("Uscita.");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
            
        } while (scelta != 0);
        
    }

    
    
    // Metodo per aggiungere un libro
    
    private void aggiungiLibro() {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Autore: ");
        String autore = scanner.nextLine();
        System.out.print("Genere: ");
        String genere = scanner.nextLine();
        System.out.print("Pagine: ");
        int pagine = leggiNumero();

        
        scaffale.aggiungi(new Libro(titolo, autore, genere, pagine));
    }
    
    

    // Metodo per rimuovere un libro
    
    private void rimuoviLibro() {
        if (scaffale.getTotale() == 0) {
            System.out.println("Scaffale vuoto."); // Messaggio se non ci sono libri
            return;
        }
        

        System.out.print("Numero libro da rimuovere: ");
        int numero = leggiNumero();

        scaffale.rimuovi(numero - 1); // Converte il numero dell'utente in indice
    }

    
    
    // Metodo per modificare un libro
    
    private void modificaLibro() {
        if (scaffale.getTotale() == 0) {
            System.out.println("Scaffale vuoto.");
            return;
        }
        

        System.out.print("Numero libro da modificare: ");
        int numero = leggiNumero();
        

        
        // Chiede i nuovi dati per il libro
        
        System.out.println("Inserisci i nuovi dati (lascia vuoto per mantenere):");
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Autore: ");
        String autore = scanner.nextLine();
        System.out.print("Genere: ");
        String genere = scanner.nextLine();
        System.out.print("Pagine: ");
        String pagineInput = scanner.nextLine();
        Integer pagine = pagineInput == "" ? null : stringaANumero(pagineInput);
        

        scaffale.modifica(numero - 1, titolo, autore, genere, pagine);
    }
    

    
    // legge numnero dell utente
    
    private int leggiNumero() {
        while (true) {
            String input = scanner.nextLine();
            int numero = stringaANumero(input);
            if (numero != -1) {
                return numero;
            }
            System.out.print("Inserisci un numero valido: ");
        }
    }

    
    
    // converte stringa in int
    
    private int stringaANumero(String input) {
        int risultato = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                return -1; // Se il carattere non è un numero, ritorna -1
            }
            risultato = risultato * 10 + (input.charAt(i) - '0'); // fa il numero type shi
        }
        return risultato;
    }


    
    
    // main 
    
    public static void main(String[] args) {
        Libreria libreria = new Libreria(100); // libreria scaffale di 100 libri
        libreria.avvia(); // Avvia tutto
    }
}
