package economia;

import static org.junit.jupiter.api.Assertions.*;

public class ContoCorrenteTest {

  @org.junit.Test
  public void testEffettuaDeposito() {
    ContoCorrente conto = new ContoCorrente(500.0);
    double deposito = conto.effettuaDeposito(200.0);

    assertEquals(200.0, deposito);
    assertEquals(700.0, conto.getSaldo());
  }

  @org.junit.Test
  public void testEffettuaPrelievo() {
    ContoCorrente conto = new ContoCorrente(500.0);
    double prelievo = conto.effettuaPrelievo(100.0);

    assertEquals(100.0, prelievo);
    assertEquals(400.0, conto.getSaldo());
  }

  @org.junit.Test
  public void testMostraSaldo() {
    ContoCorrente conto = new ContoCorrente(1000.0);
    assertEquals("Saldo conto corrente: $1000,00", conto.mostraSaldo());
  }
}
