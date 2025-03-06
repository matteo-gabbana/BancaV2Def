package economia;

public class Portafoglio {

    private double bilancio;
    private ContoCorrente conto;

    public Portafoglio(ContoCorrente conto, double bilancio) {
        this.conto = conto;
        this.bilancio = bilancio;
    }

    public void setBilancio(double bilancio) {
        this.bilancio = bilancio;
    }

    public double getBilancio() {
        return bilancio;
    }

//    public void depositaNelConto(double importo) {
//
//        if (importo > 0 && importo < bilancio) {
//            bilancio -= conto.deposita(importo);
//        } else if (importo < 0) {
//            System.out.println("Importo non valido. Riprova.");
//        } else {
//            System.out.println("Bilancio insufficiente per il deposito.");
//        }
//    }

    public void depositaNelConto(double importo, String data, String username) {

        if (importo <= 0) {
            System.out.println("Importo non valido. Riprova.");
            return;
        }

        if (importo > bilancio) {
            System.out.println("Bilancio insufficiente per il deposito.");
            return;
        }

        double bilancioPrecedente = bilancio;
        double importoDepositato = conto.deposita(importo, data, username);
        bilancio -= importoDepositato;

        //FileManager.salvaTransazione(username, data, "Trasferimento al conto: -" + String.format("%.2f", importo) + "$. Bilancio precedente: " + String.format("%.2f", bilancioPrecedente) + "$, Bilancio attuale: " + String.format("%.2f", bilancio) + "$.");
    }

//    public void prelevaDalConto(double importo) {
//        bilancio += conto.preleva(importo);
//    }

    public void prelevaDalConto(double importo, String data, String username) {

        double bilancioPrecedente = bilancio;
        double importoPrelevato = conto.preleva(importo, data, username);
        bilancio += importoPrelevato;

        //FileManager.salvaTransazione(username, data, "Prelievo dal conto: +" + String.format("%.2f", importoPrelevato) + "$. Bilancio precedente: " + String.format("%.2f", bilancioPrecedente) + "$, Bilancio attuale: " + String.format("%.2f", bilancio) + "$.");
    }

    public void mostraBilancioPortafoglio() {
        System.out.println("Bilancio portafoglio: " + String.format("%.2f", bilancio) + "$.");
    }
}