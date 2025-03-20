package economia;

import static org.junit.jupiter.api.Assertions.*;

public class ContoCorrenteTest {

  @org.junit.Test
  public void testEffettuaDeposito() {
    ContoCorrente conto = new ContoCorrente(500.0);
    conto.setModalitaTest(true);
    double importo = conto.deposita(200.0, "25 dicembre 2025", "utenteTest");

    assertEquals(200.0, importo);
    assertEquals(700.0, conto.getSaldo());
  }

  @org.junit.Test
  public void testEffettuaPrelievo() {
    ContoCorrente conto = new ContoCorrente(500.0);
    conto.setModalitaTest(true);
    double prelievo = conto.preleva(200.0, "25 dicembre 2025", "utenteTest");

    assertEquals(-200.0, prelievo);
    assertEquals(300.0, conto.getSaldo());
  }

  @org.junit.Test
  public void testMostraSaldo() {
    ContoCorrente conto = new ContoCorrente(1000.0);
    assertEquals("Saldo conto corrente: $1000,00", conto.mostraSaldo());
  }
}
