package main.gui;

import javax.swing.*;
import java.awt.*;

public class SceltaInvestimentoPanel extends JDialog {

    private String azioneSelezionata = null;
    private SceltaInvestimentoListener listener;

    public SceltaInvestimentoPanel(JFrame parent, SceltaInvestimentoListener listener, String messaggio) {

        super(parent, true);
        this.listener = listener;

        setTitle("Investi in Azioni");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titoloLabel = new JLabel(messaggio);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titoloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titoloLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        panel.add(titoloLabel);

        JButton teslaButton = new JButton("Tesla (TSLA)");
        JButton nvidiaButton = new JButton("Nvidia (NVDA)");
        JButton amazonButton = new JButton("Amazon (AMZN)");
        JButton appleButton = new JButton("Apple (AAPL)");
        JButton[] buttons = {teslaButton, nvidiaButton, amazonButton, appleButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setPreferredSize(new Dimension(240, 80));
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                if (listener != null) {
                    listener.onSceltaEffettuata(button.getText());
                }
                dispose();
            });
            panel.add(button);
            panel.add(Box.createVerticalStrut(20));
        }

        add(panel);
        setVisible(true);
    }

    public String getScelta() {
        return azioneSelezionata;
    }

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new SceltaInvestimentoPanel( scelta -> {
//            JOptionPane.showMessageDialog(null, "Hai scelto di investire in " + scelta, "Scelta Investimento", JOptionPane.INFORMATION_MESSAGE);
//        },"Su che azioni vuoi investire?");
//    }

}
