package tools;

public class PasswordManager {

  public static final int MIN_PASSWORD_LENGHT = 7;
  public static final int MAX_PASSWORD_LENGHT = 20;

  public static boolean validitaCaratteriPassword(String password) {
    String expression = "^[a-zA-Z0-9!?]+$";
    return password.matches(expression);
  }

  public static boolean validitaLunghezzaPassword(String password) {
    return ((password.length() >= MIN_PASSWORD_LENGHT)
        && (password.length() <= MAX_PASSWORD_LENGHT));
  }
}
