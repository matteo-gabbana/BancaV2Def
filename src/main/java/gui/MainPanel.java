package gui;

import banca.Utente;
import economia.ContoCorrente;
import economia.Portafoglio;
import economia.StocksManager;
import java.awt.*;
import javax.swing.*;
import tools.DateManager;
import tools.FileManager;

public class MainPanel extends JFrame {

  private final JTextField commandField;
  private final JLabel dataLabel;
  private final JLabel saldoLabel;
  private final JLabel bilancioLabel;
  private final String username;
  private final String password;
  private final DateManager dateManager;
  private final ContoCorrente conto;
  private final Portafoglio portafoglio;
  private InvestimentiPanel investimentiPanel;
  private TransazioniPanel transazioniPanel;
  private String sceltaInvestimento;

  public MainPanel(Utente utente) {

    this.username = utente.getUsername();
    this.password = utente.getPassword();
    this.dateManager = utente.getDateManager();
    this.conto = utente.getConto();
    this.portafoglio = utente.getPortafoglio();

    setTitle("Banca \"Morsli & Gabbana\"");
    setSize(900, 750); // height 650
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBackground(Color.WHITE);
    mainPanel.setOpaque(true);

    // pannello in alto con le informazioni
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new GridLayout(4, 1, 5, 5));
    infoPanel.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 10),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
    infoPanel.setBackground(new Color(230, 247, 255));

    JLabel usernameLabel = new JLabel("Username: " + username, SwingConstants.LEFT);
    dataLabel = new JLabel("Data: " + dateManager.getDataCorrente(), SwingConstants.LEFT);
    saldoLabel = new JLabel(conto.mostraSaldo(), SwingConstants.LEFT);
    bilancioLabel = new JLabel(portafoglio.mostraBilancioPortafoglio(), SwingConstants.LEFT);

    JLabel[] labels = {usernameLabel, dataLabel, saldoLabel, bilancioLabel};
    for (JLabel label : labels) {
      label.setFont(new Font("Arial", Font.BOLD, 25));
      label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
      infoPanel.add(label);
    }

    // riga di comando
    commandField = new JTextField();
    commandField.setFont(new Font("Arial", Font.PLAIN, 16));
    commandField.setBorder(BorderFactory.createTitledBorder("Inserisci comando"));
    commandField.setPreferredSize(new Dimension(850, 70));

    commandField.addActionListener(
        e -> {
          String comando = commandField.getText().trim().toLowerCase();

          switch (comando) {
            case "/deposita":
              gestisciDeposito();
              break;
            case "/preleva":
              gestisciPrelievo();
              break;
            case "/investi":
              gestisciInvestimento();
              break;
            case "/quadro":
              mostraQuadroInvestimenti();
              break;
            case "/chiudi":
              gestisciChiusuraInvestimento();
              break;
            case "/avanza":
              gestisciAvanza();
              break;
            case "/storico":
              mostraStoricoTransazioni();
              break;
            case "/esci":
              gestisciSalvaEdEsci();
              break;
            default:
              JOptionPane.showMessageDialog(
                  null, "Comando non riconosciuto!", "Errore", JOptionPane.ERROR_MESSAGE);
              break;
          }

          commandField.setText("");
        });

    // area con tutti i pulsanti
    JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
    buttonPanel.setBackground(Color.WHITE);

    JButton depositaButton = new JButton("Deposita");
    JButton prelevaButton = new JButton("Preleva");
    JButton investiButton = new JButton("Investi in Azioni");
    JButton situazioneInvestimentiButton = new JButton("Quadro Investimenti");
    JButton chiudiInvestimentoButton = new JButton("Chiudi Investimento");
    JButton avanzaButton = new JButton("Avanza Tempo");
    JButton transazioniButton = new JButton("Storico Transazioni");
    JButton salvaEdEsciButton = new JButton("Salva ed Esci");

    JButton[] buttons = {
      depositaButton,
      prelevaButton,
      avanzaButton,
      investiButton,
      situazioneInvestimentiButton,
      chiudiInvestimentoButton
    };
    for (JButton button : buttons) {
      button.setFont(new Font("Arial", Font.BOLD, 16));
      button.setPreferredSize(new Dimension(280, 90));
      button.setFocusPainted(false);
      buttonPanel.add(button);
    }

    JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    bottomButtonPanel.setBackground(Color.WHITE);
    bottomButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

    transazioniButton.setFont(new Font("Arial", Font.BOLD, 16));
    transazioniButton.setBackground(new Color(102, 178, 255));
    transazioniButton.setPreferredSize(new Dimension(280, 90));
    transazioniButton.setFocusPainted(false);
    bottomButtonPanel.add(transazioniButton);

    salvaEdEsciButton.setFont(new Font("Arial", Font.BOLD, 16));
    salvaEdEsciButton.setBackground(new Color(0, 102, 204)); // 51, 102, 255
    salvaEdEsciButton.setPreferredSize(new Dimension(280, 90));
    salvaEdEsciButton.setFocusPainted(false);
    bottomButtonPanel.add(salvaEdEsciButton, BorderLayout.CENTER);

    depositaButton.addActionListener(e -> gestisciDeposito());
    prelevaButton.addActionListener(e -> gestisciPrelievo());
    avanzaButton.addActionListener(e -> gestisciAvanza());
    investiButton.addActionListener(e -> gestisciInvestimento());
    situazioneInvestimentiButton.addActionListener(e -> mostraQuadroInvestimenti());
    chiudiInvestimentoButton.addActionListener(e -> gestisciChiusuraInvestimento());
    transazioniButton.addActionListener(e -> mostraStoricoTransazioni());
    salvaEdEsciButton.addActionListener(e -> gestisciSalvaEdEsci());

    JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    bottomPanel.add(commandField, BorderLayout.NORTH);
    bottomPanel.add(buttonPanel, BorderLayout.CENTER);
    bottomPanel.setBackground(Color.WHITE);
    bottomPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

    mainPanel.add(infoPanel, BorderLayout.NORTH);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    add(mainPanel);

    addWindowListener(
        new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            chiudiInvestimentiPanel();
          }
        });

    setVisible(true);
  }

  private void gestisciDeposito() {

    String input =
        JOptionPane.showInputDialog(
            null,
            "Inserisci l'importo da depositare nel conto corrente: ",
            "Deposito",
            JOptionPane.QUESTION_MESSAGE);

    if (input != null && !input.trim().isEmpty()) {
      try {
        double importo = Double.parseDouble(input);
        portafoglio.depositaNelConto(importo, dateManager.getDataCorrente(), username, conto);
        aggiornaInfoPanel();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            null, "Per favore, inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
      }
    } else if (input != null) {
      JOptionPane.showMessageDialog(
          null, "Input non valido.", "Errore", JOptionPane.WARNING_MESSAGE);
    }
  }

  private void gestisciPrelievo() {

    String input =
        JOptionPane.showInputDialog(
            null,
            "Inserisci l'importo da prelevare conto corrente: ",
            "Prelievo",
            JOptionPane.QUESTION_MESSAGE);

    if (input != null && !input.trim().isEmpty()) {
      try {
        double importo = Double.parseDouble(input);
        portafoglio.prelevaDalConto(importo, dateManager.getDataCorrente(), username, conto);
        aggiornaInfoPanel();
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            null, "Per favore, inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
      }
    } else if (input != null) {
      JOptionPane.showMessageDialog(
          null, "Input non valido.", "Errore", JOptionPane.WARNING_MESSAGE);
    }
  }

  private void gestisciAvanza() {
    StocksManager.avanzaTempo(dateManager, portafoglio);
    FileManager.salvaSituazioneBilanci(username, dateManager.getDataCorrente(), portafoglio, conto);
    JOptionPane.showMessageDialog(
        null,
        "Tempo avanzato. Data attuale: " + dateManager.getDataCorrente(),
        "Transizione al mese successivo",
        JOptionPane.INFORMATION_MESSAGE);
    aggiornaInfoPanel();
    dataLabel.setText("Data: " + dateManager.getDataCorrente());

    if (investimentiPanel != null && investimentiPanel.isDisplayable()) {
      investimentiPanel.aggiornaDisplayInvestimenti();
    }
  }

  private void gestisciInvestimento() {

    new SceltaInvestimentoPanel(
        this,
        scelta -> {
          sceltaInvestimento = scelta;
          JOptionPane.showMessageDialog(
              null,
              "Hai scelto di investire in " + scelta,
              "Scelta Investimento",
              JOptionPane.INFORMATION_MESSAGE);

          if (StocksManager.isInvestimentoAttivo(sceltaInvestimento)) {
            JOptionPane.showMessageDialog(
                null,
                "Hai gia' un investimento attivo su questi stocks!",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
          } else {
            String input =
                JOptionPane.showInputDialog(
                    null,
                    "Inserisci la somma da investire (minimo 5$): ",
                    "Cifra Investimento",
                    JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
              try {
                double importoInvestimento = Double.parseDouble(input);
                StocksManager.effettuaInvestimento(
                    conto,
                    sceltaInvestimento,
                    importoInvestimento,
                    dateManager.getDataCorrente(),
                    username,
                    portafoglio);
                aggiornaInfoPanel();
                if (investimentiPanel != null && investimentiPanel.isDisplayable()) {
                  investimentiPanel.aggiornaDisplayInvestimenti();
                }
              } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Per favore, inserisci un numero valido.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
              }
            } else if (input != null) {
              JOptionPane.showMessageDialog(
                  null, "Input non valido.", "Errore", JOptionPane.WARNING_MESSAGE);
            }
          }
        },
        "Su che azioni vuoi investire?");
  }

  private void mostraQuadroInvestimenti() {
    if (investimentiPanel == null || !investimentiPanel.isDisplayable()) {
      investimentiPanel = new InvestimentiPanel(this);
    }
  }

  private void mostraStoricoTransazioni() {
    if (transazioniPanel == null || !transazioniPanel.isDisplayable()) {
      transazioniPanel = new TransazioniPanel(this, username);
    }
  }

  private void gestisciChiusuraInvestimento() {

    new SceltaInvestimentoPanel(
        this,
        scelta -> {
          sceltaInvestimento = scelta;
          JOptionPane.showMessageDialog(
              null,
              "Hai scelto di chiudere l'investimento in " + scelta,
              "Chiusura Investimento",
              JOptionPane.INFORMATION_MESSAGE);

          StocksManager.chiudiInvestimento(
              sceltaInvestimento, conto, dateManager.getDataCorrente(), username, portafoglio);
          aggiornaInfoPanel();
          if (investimentiPanel != null && investimentiPanel.isDisplayable()) {
            investimentiPanel.aggiornaDisplayInvestimenti();
          }
        },
        "Che investimento vuoi chiudere?");
  }

  private void gestisciSalvaEdEsci() {
    FileManager.salvaUtente(
        username,
        password,
        conto.getSaldo(),
        portafoglio.getBilancio(),
        dateManager.getDataCorrente(),
        StocksManager.getSaldoTSLA(),
        StocksManager.getSaldoNVDA(),
        StocksManager.getSaldoAMZN(),
        StocksManager.getSaldoAAPL());
    FileManager.scriviTransazioni(username);
    JOptionPane.showMessageDialog(
        null, "Dati personali salvati con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
    chiudiInvestimentiPanel();
    dispose();
  }

  private void aggiornaInfoPanel() {
    saldoLabel.setText(conto.mostraSaldo());
    bilancioLabel.setText(portafoglio.mostraBilancioPortafoglio());
  }

  private void chiudiInvestimentiPanel() {
    if (investimentiPanel != null && investimentiPanel.isDisplayable()) {
      investimentiPanel.dispose();
      // investimentiPanel = null;
    }
  }
}
