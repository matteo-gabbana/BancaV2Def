package tools;

import java.util.Scanner;

public class InputTools {

  public static int inserireIntero() {

    Scanner input = new Scanner(System.in);

    int n = 0;
    boolean ok;

    do {
      ok = true;
      try {
        String s = input.nextLine();
        n = Integer.parseInt(s);
      } catch (NumberFormatException e) {
        System.out.print("\nErrore! Inserire un numero valido: ");
        ok = false;
      }
    } while (!ok);

    return n;
  }

  public static double inserireDouble() {

    Scanner input = new Scanner(System.in);

    double n = 0;
    boolean ok;

    do {
      ok = true;
      try {
        String s = input.nextLine();
        n = Double.parseDouble(s);
      } catch (NumberFormatException e) {
        System.out.print("\nErrore! Inserire un numero valido: ");
        ok = false;
      }
    } while (!ok);

    return n;
  }
}
