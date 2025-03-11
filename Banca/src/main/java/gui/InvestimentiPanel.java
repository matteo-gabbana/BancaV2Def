package gui;

import economia.StocksManager;

import javax.swing.*;
import java.awt.*;

public class InvestimentiPanel extends JFrame {

    private MainPanel finestraPrincipale;

    private JLabel teslaLabel;
    private JLabel nvidiaLabel;
    private JLabel amazonLabel;
    private JLabel appleLabel;

    public InvestimentiPanel(MainPanel finestraPrincipale) {

        this.finestraPrincipale = finestraPrincipale;

        setTitle("Quadro Investimenti");
        setSize(380, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.setBackground(Color.WHITE);

        JLabel titoloLabel = new JLabel("Investimenti Attuali:", SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titoloLabel.setOpaque(true);
        titoloLabel.setForeground(new Color(0, 102, 204));
        titoloLabel.setBackground(new Color(230, 247, 255));
        titoloLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 10));
        mainPanel.add(titoloLabel);

        teslaLabel = new JLabel("", SwingConstants.CENTER);
        nvidiaLabel = new JLabel("", SwingConstants.CENTER);
        amazonLabel = new JLabel("", SwingConstants.CENTER);
        appleLabel = new JLabel("", SwingConstants.CENTER);

        JLabel[] labels = {teslaLabel, nvidiaLabel, amazonLabel, appleLabel};
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.BOLD, 22));
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            mainPanel.add(label);
        }

        add(mainPanel);

        finestraPrincipale.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

        aggiornaDisplayInvestimenti();
        setVisible(true);
    }

    public void aggiornaDisplayInvestimenti() {
        teslaLabel.setText("Tesla (TSLA): " + String.format("%.2f", StocksManager.getSaldoTSLA()) + "$");
        nvidiaLabel.setText("Nvidia (NVDA): " + String.format("%.2f", StocksManager.getSaldoNVDA()) + "$");
        amazonLabel.setText("Amazon (AMZN): " + String.format("%.2f", StocksManager.getSaldoAMZN()) + "$");
        appleLabel.setText("Apple (AAPL): " + String.format("%.2f", StocksManager.getSaldoAAPL()) + "$");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new InvestimentiPanel(null);
    }

}
