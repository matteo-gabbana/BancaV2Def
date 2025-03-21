package tools;

public class UsernameManager {

  public static final int MIN_USERNAME_LENGHT = 3;
  public static final int MAX_USERNAME_LENGHT = 25;

  public static boolean validitaCaratteriUsername(String username) {
    String expression = "^[a-zA-Z0-9]+$";
    return username.matches(expression);
  }

  public static boolean validitaLunghezzaUsername(String username) {
    return ((username.length() >= MIN_USERNAME_LENGHT)
        && (username.length() <= MAX_USERNAME_LENGHT));
  }
}
