package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LoginManager {


  public static String[] effettuaLogin(String username, String password) {

    String[] datiUtente = FileManager.caricaUtente(username);

    if (datiUtente == null || !datiUtente[1].equals(password)) {
      return null;
    }

    return datiUtente;
  }

  public static String[] registraUtente(String username, String password) {

    if (FileManager.caricaUtente(username) != null) {
      return null;
    }

    String dataIniziale =
        LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN));

    FileManager.salvaUtente(username, password, 0.0, 0.0, dataIniziale, 0.0, 0.0, 0.0, 0.0);
    return new String[] {
      username, password, "0.0", "0.0", dataIniziale, "0.0", "0.0", "0.0", "0.0"
    };
  }
}
