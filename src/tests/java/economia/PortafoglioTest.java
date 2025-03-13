package economia;

import main.economia.Portafoglio;
import main.economia.ContoCorrente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PortafoglioTest {

    @Test
    public void testDepositaNelConto_ImportoValido() {
        ContoCorrente conto = new ContoCorrente(200.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        portafoglio.depositaNelConto(50.0, "2025-03-08", "utente", conto);
        assertEquals(50.0, portafoglio.getBilancio());
    }

    @Test
    public void testDepositaNelConto_ImportoNegativo() {
        ContoCorrente conto = new ContoCorrente(200.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        portafoglio.depositaNelConto(-50.0, "2025-03-08", "utente", conto);
        assertEquals(100.0, portafoglio.getBilancio());
    }

    @Test
    public void testDepositaNelConto_BilancioInsufficiente() {
        ContoCorrente conto = new ContoCorrente(200.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        portafoglio.depositaNelConto(150.0, "2025-03-08", "utente", conto);
        assertEquals(100.0, portafoglio.getBilancio());
    }

    @Test
    public void testPrelevaDalConto_ImportoValido() {
        ContoCorrente conto = new ContoCorrente(200.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        portafoglio.prelevaDalConto(50.0, "2025-03-08", "utente", conto);
        assertEquals(150.0, portafoglio.getBilancio());
    }

    @Test
    public void testPrelevaDalConto_SaldoInsufficiente() {
        ContoCorrente conto = new ContoCorrente(50.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        portafoglio.prelevaDalConto(150.0, "2025-03-08", "utente", conto);
        assertEquals(100.0, portafoglio.getBilancio());
    }

    @Test
    public void testMostraBilancioPortafoglio() {
        ContoCorrente conto = new ContoCorrente(200.0);
        Portafoglio portafoglio = new Portafoglio(conto, 100.0);
        assertEquals("Bilancio portafoglio: $100,00", portafoglio.mostraBilancioPortafoglio());
    }
}


