package economia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tools.DateManager;

public class StocksManagerTest {

  @Test
  public void testSetAndGetStockSaldo() {
    StocksManager.setSaldoTSLA(100.0);
    StocksManager.setSaldoNVDA(200.0);
    StocksManager.setSaldoAMZN(300.0);
    StocksManager.setSaldoAAPL(400.0);
    assertEquals(100.0, StocksManager.getSaldoTSLA());
    assertEquals(200.0, StocksManager.getSaldoNVDA());
    assertEquals(300.0, StocksManager.getSaldoAMZN());
    assertEquals(400.0, StocksManager.getSaldoAAPL());
  }

  @Test
  public void testEffettuaInvestimento_TSLA() { // uso tesla, ma vale per tutti alla fine
    // non faccio il test di investiInAzioni, perché viene richiamato da effettuaInvestimento
    ContoCorrente conto = new ContoCorrente(1000.0);
    StocksManager.setSaldoTSLA(0.0);
    StocksManager.setModalitaTest(true);

    StocksManager.effettuaInvestimento(
        conto, "Tesla (TSLA)", 100.0, "2025-03-08", "utente", new Portafoglio(conto, 0));
    assertEquals(
        99.0, StocksManager.getSaldoTSLA()); // controlla che ho investito bene (100$ - 1 di tasse)
    assertEquals(
        900.0, conto.getSaldo()); // controlla che il conto sia sceso di 100, dopo l'investimento
  }

  @Test
  public void testChiudiInvestimento_TSLA() {
    ContoCorrente conto = new ContoCorrente(500.0);
    StocksManager.setSaldoTSLA(150.0);
    StocksManager.setModalitaTest(true);

    StocksManager.chiudiInvestimento(
        "Tesla (TSLA)", conto, "2025-03-08", "utente", new Portafoglio(conto, 0));
    assertEquals(0.0, StocksManager.getSaldoTSLA());
    assertEquals(650.0, conto.getSaldo());
  }

  @Test
  public void testIsInvestimentoAttivo() {
    StocksManager.setSaldoTSLA(0.0);
    assertFalse(StocksManager.isInvestimentoAttivo("Tesla (TSLA)"));

    StocksManager.setSaldoNVDA(50.0);
    assertTrue(StocksManager.isInvestimentoAttivo("Nvidia (NVDA)"));
  }

  @Test
  public void
      testAvanzaTempo() { // non testo aggiorna valore investimento, tanto è richiamato da
                          // avanzaTempo
    DateManager dateManager = new DateManager("8 marzo 2025");
    ContoCorrente conto = new ContoCorrente(500.0);
    Portafoglio portafoglio = new Portafoglio(conto, 100.0);
    double bilancioIniziale = portafoglio.getBilancio();

    StocksManager.avanzaTempo(dateManager, portafoglio);
    assertEquals(
        bilancioIniziale + 100,
        portafoglio.getBilancio()); // dopo un mese dovrei avere 100 euro in più
  }
}
