package tests.main.economia;


import main.economia.ContoCorrente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContoCorrenteTest {

    @Test
    void testDeposita() {
        ContoCorrente conto = new ContoCorrente(500.0);
        double deposito = conto.deposita(200.0, "2025-03-08", "utente");

        assertEquals(200.0, deposito);
        assertEquals(700.0, conto.getSaldo());
    }

    @Test
    void testPreleva() {
        ContoCorrente conto = new ContoCorrente(500.0);
        double prelievo = conto.preleva(100.0, "2025-03-08", "utente");

        assertEquals(100.0, prelievo);
        assertEquals(400.0, conto.getSaldo());
    }

    @Test
    void testMostraSaldo() {
        ContoCorrente conto = new ContoCorrente(1000.0);
        assertEquals("Saldo conto corrente: $1000,00", conto.mostraSaldo());
    }
}

