package banca;

import economia.ContoCorrente;
import economia.Portafoglio;
import tools.DateManager;

public class Utente {

    private String username;
    private String password;
    private DateManager dateManager;
    private ContoCorrente conto;
    private Portafoglio portafoglio;

    public Utente(String username, String password, DateManager dateManager,ContoCorrente conto,Portafoglio portafoglio) {
        this.username = username;
        this.password = password;
        this.dateManager = dateManager;
        this.conto = conto;
        this.portafoglio = portafoglio;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DateManager getDateManager() {
        return dateManager;
    }

    public ContoCorrente getConto() {
        return conto;
    }

    public Portafoglio getPortafoglio() {
        return portafoglio;
    }
}
