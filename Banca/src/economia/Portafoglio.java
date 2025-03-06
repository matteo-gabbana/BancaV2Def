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

    public void depositaNelConto(double importo) {

        if (importo > 0 && importo < bilancio) {
            bilancio -= conto.deposita(importo);
        } else if (importo < 0) {
            System.out.println("Importo non valido. Riprova.");
        } else {
            System.out.println("Bilancio insufficiente per il deposito.");
        }
    }

    public void prelevaDalConto(double importo) {
        bilancio += conto.preleva(importo);
    }

    public void mostraBilancioPortafoglio() {
        System.out.println("Bilancio portafoglio: " + String.format("%.2f", bilancio) + "$.");
    }
}