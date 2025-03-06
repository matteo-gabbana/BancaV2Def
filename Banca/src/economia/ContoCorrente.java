package economia;

public class ContoCorrente {

    private double saldo;

    public ContoCorrente(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double deposita(double importo) {

        saldo += importo;
        System.out.println("Hai depositato: " + String.format("%.2f", importo) + "$. Saldo conto: " + String.format("%.2f", saldo) + "$.");
        return importo;
    }

    public double preleva(double importo) {

        if (importo > 0 && importo <= saldo) {
            saldo -= importo;
            System.out.println("Hai prelevato: " + String.format("%.2f", importo) + "$. Saldo conto: " + String.format("%.2f", saldo) + "$.");
            return importo;
        } else if (importo > saldo) {
            System.out.println("Saldo insufficiente per il prelievo.");
        } else {
            System.out.println("Importo non valido. Riprova.");
        }
        return 0;
    }

    public void mostraSaldo() {
        System.out.println("Saldo conto corrente: " + String.format("%.2f", saldo) + "$.");
    }
}
