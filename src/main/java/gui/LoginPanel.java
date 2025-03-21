package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tools.LoginManager;
import tools.PasswordManager;
import tools.UsernameManager;

public class LoginPanel extends JFrame {

  private final JTextField usernameField;
  private final JPasswordField passwordField;

  private LoginListener loginListener;

  public LoginPanel() {

    setTitle("Login e Registrazione");
    setSize(500, 530);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBackground(new Color(102, 204, 255));
    mainPanel.setOpaque(true);
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(new Color(102, 204, 255));
    titlePanel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 4));

    setLayout(new BorderLayout(10, 10));
    JPanel centerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    centerPanel.setBorder(new EmptyBorder(0, 0, -50, 0));

    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/logobanca1.png"));
    JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(22, 76, 119, 255)));

    JLabel usernameLabel = new JLabel("          Username: ");
    usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("          Password: ");
    passwordField = new JPasswordField();
    JButton loginButton = new JButton("Accedi");
    JButton registerButton = new JButton("Registrati");

    usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
    passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

    usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
    passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

    loginButton.setFont(new Font("Arial", Font.ITALIC, 16));
    registerButton.setFont(new Font("Arial", Font.ITALIC, 16));

    loginButton.addActionListener(e -> gestisciAccesso(true));
    registerButton.addActionListener(e -> gestisciAccesso(false));

    centerPanel.add(usernameLabel);
    centerPanel.add(usernameField);
    centerPanel.add(passwordLabel);
    centerPanel.add(passwordField);
    centerPanel.add(new JLabel(" "));
    centerPanel.add(new JLabel(" "));
    centerPanel.add(loginButton);
    centerPanel.add(registerButton);
    centerPanel.setBorder(new EmptyBorder(30, 25, -30, 25));

    add(imageLabel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);

    setVisible(true);
  }

  private void gestisciAccesso(boolean isLogin) {

    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());

    String[] datiUtente = null;
    if (!isLogin) {

      if (!checkValidityUsernameAndPasswordFields(username, password)) {
        return;
      }

      datiUtente = LoginManager.registraUtente(username, password);

      if (datiUtente == null) {
        mostraMessaggio(
            "Registrazione fallita!\nQuesto username è già in uso.",
            "Errore",
            JOptionPane.ERROR_MESSAGE);
      } else {
        mostraMessaggio(
            "Registrazione completata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        if (loginListener != null) {
          loginListener.onLoginCompleted(datiUtente);
        }
        dispose();
      }

    } else {

      datiUtente = LoginManager.effettuaLogin(username, password);

      if (datiUtente == null) {
        mostraMessaggio(
            "Login fallito!\nUsername o Password errati!", "Errore", JOptionPane.ERROR_MESSAGE);
      } else {
        mostraMessaggio(
            "Login effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        if (loginListener != null) {
          loginListener.onLoginCompleted(datiUtente);
        }
        dispose();
      }
    }
  }

  private boolean checkValidityUsernameAndPasswordFields(String username, String password) {

    if (username.isEmpty() || password.isEmpty()) {
      mostraMessaggio(
          "Registrazione fallita!\nUsername e Password non possono essere vuoti.",
          "Errore",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (!UsernameManager.validitaCaratteriUsername(username)) {
      mostraMessaggio(
          "Registrazione fallita!\nL'username contiene caratteri non validi.\nCaratteri consentiti: a-z, A-Z, 0-9",
          "Errore",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (!UsernameManager.validitaLunghezzaUsername(username)) {
      mostraMessaggio(
          "Registrazione fallita!\nL'username non rispetta una dimensione valida.\nLunghezza minima: "
              + UsernameManager.MIN_USERNAME_LENGHT
              + " caratteri.\nLunghezza massima: "
              + UsernameManager.MAX_USERNAME_LENGHT
              + " caratteri.",
          "Errore",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (!PasswordManager.validitaCaratteriPassword(password)) {
      mostraMessaggio(
          "Registrazione fallita!\nLa password contiene caratteri non validi.\nCaratteri consentiti: a-z, A-Z, 0-9, !, ?",
          "Errore",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (!PasswordManager.validitaLunghezzaPassword(password)) {
      mostraMessaggio(
          "Registrazione fallita!\nLa password non rispetta una dimensione valida.\nLunghezza minima: "
              + PasswordManager.MIN_PASSWORD_LENGHT
              + " caratteri.\nLunghezza massima: "
              + PasswordManager.MAX_PASSWORD_LENGHT
              + " caratteri.",
          "Errore",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    return true;
  }

  private void mostraMessaggio(String messaggio, String titolo, int tipoMessaggio) {
    JOptionPane.showMessageDialog(this, messaggio, titolo, tipoMessaggio);
  }

  public void addLoginListener(LoginListener listener) {
    this.loginListener = listener;
  }
}
