package tests.main.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import main.tools.DateManager;
import org.junit.jupiter.api.Test;

public class DateManagerTest {

  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("d MMMM yyyy", java.util.Locale.ITALIAN);

  @Test
  void testCostruttore_UsaDataAttualeSeDataSalvataEPassata() {
    String dataPassata = "1 gennaio 2000";
    LocalDate dataAttuale = LocalDate.now();

    DateManager dateManager = new DateManager(dataPassata);

    assertEquals(dataAttuale.format(DATE_FORMATTER), dateManager.getDataCorrente());
  }

  @Test
  void testCostruttore_UsaDataSalvataSeNelFuturoOPresente() {
    LocalDate dataFutura = LocalDate.now().plusDays(10);
    String dataFuturaString = dataFutura.format(DATE_FORMATTER);

    DateManager dateManager = new DateManager(dataFuturaString);

    assertEquals(dataFuturaString, dateManager.getDataCorrente());
  }

  @Test
  void testAvanzaDiUnMese() {
    String dataIniziale = "8 marzo 2025";
    DateManager dateManager = new DateManager(dataIniziale);

    dateManager.avanzaDiUnMese();

    String dataAttesa = "8 aprile 2025";
    assertEquals(dataAttesa, dateManager.getDataCorrente());
  }
}
