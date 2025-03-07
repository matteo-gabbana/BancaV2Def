package gui;

import economia.ContoCorrente;
import economia.Portafoglio;
import java.awt.*;
import javax.swing.*;
import tools.DateManager;

public class MainPanel extends JFrame {

    // riga di testo per inserire un comando
    private JTextField commandField;

    /*
     * 6 bottoni
     * 1: Deposita denaro sul conto corrente
     * 2: Preleva denaro dal conto corrente
     * 3: Investi in azioni
     * 4: Visualizza investimenti in corso
     * 5: Chiudi investimento
     * 6: Avanza il tempo di 1 mese (+100$ nel portafoglio)
     * */
    private JButton depositaButton;
    private JButton prelevaButton;
    private JButton avanzaButton;
    private JButton investiButton;
    private JButton situazioneInvestimentiButton;
    private JButton chiudiInvestimentoButton;

    private String username;
    private DateManager dateManager;
    private ContoCorrente conto;
    private Portafoglio portafoglio;

    public MainPanel(String username, DateManager dateManager, ContoCorrente conto, Portafoglio portafoglio) {

        this.username = username;
        this.dateManager = dateManager;
        this.conto = conto;
        this.portafoglio = portafoglio;

        setTitle("Banca \"Morsli & Gabbana\"");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//        mainPanel.setBackground(new Color(102, 204, 255));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setOpaque(true);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 10),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        infoPanel.setBackground(new Color(230, 247, 255));

        JLabel usernameLabel = new JLabel("Username: " + username, SwingConstants.LEFT);
        JLabel dataLabel = new JLabel("Data: " + dateManager.getDataCorrente(), SwingConstants.LEFT);
        JLabel saldoLabel = new JLabel(conto.mostraSaldo(), SwingConstants.LEFT);
        JLabel bilancioLabel = new JLabel(portafoglio.mostraBilancioPortafoglio(), SwingConstants.LEFT);

        JLabel[] labels = {usernameLabel, dataLabel, saldoLabel, bilancioLabel};
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.BOLD, 25));
            label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
            infoPanel.add(label);
        }



        commandField = new JTextField();
        commandField.setFont(new Font("Arial", Font.PLAIN, 16));
        commandField.setBorder(BorderFactory.createTitledBorder("Inserisci comando"));
        commandField.setPreferredSize(new Dimension(850, 70));



        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        depositaButton = new JButton("Deposita");
        prelevaButton = new JButton("Preleva");
        investiButton = new JButton("Investi");
        situazioneInvestimentiButton = new JButton("Quadro Investimenti");
        chiudiInvestimentoButton = new JButton("Chiudi Investimento");
        avanzaButton = new JButton("Avanza Tempo");

        JButton[] buttons = {depositaButton, prelevaButton, avanzaButton, investiButton, situazioneInvestimentiButton, chiudiInvestimentoButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setPreferredSize(new Dimension(280, 90));
            button.setFocusPainted(false);
            buttonPanel.add(button);
        }

        depositaButton.addActionListener(e -> gestisciDeposito());
        prelevaButton.addActionListener(e -> gestisciPrelievo());
        avanzaButton.addActionListener(e -> gestisciAvanza());
        investiButton.addActionListener(e -> gestisciInvestimento());
        situazioneInvestimentiButton.addActionListener(e -> mostraQuadroInvestimenti());
        chiudiInvestimentoButton.addActionListener(e -> gestisciChiusuraInvestimento());

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.add(commandField, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.setBackground(Color.WHITE);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainPanel mainPanel = new MainPanel("Mario", new DateManager("7 marzo 2025"), new ContoCorrente(1000), new Portafoglio(new ContoCorrente(2000), 1000));
    }

    private void gestisciDeposito() {

        String input = JOptionPane.showInputDialog(null, "Inserisci l'importo da depositare nel conto corrente: ", "Deposito", JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                double importo = Double.parseDouble(input);
                // Aggiungi la logica per gestire il deposito
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Per favore, inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Input non valido o vuoto.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void gestisciPrelievo() {
    }

    private void gestisciAvanza() {
    }

    private void gestisciInvestimento() {
    }

    private void mostraQuadroInvestimenti() {
    }

    private void gestisciChiusuraInvestimento() {
    }

}
