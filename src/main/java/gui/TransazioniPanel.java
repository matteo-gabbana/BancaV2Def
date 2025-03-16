package gui;

import tools.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class TransazioniPanel extends JFrame {

  private final String username;
  private final JTextArea areaElencoTransazioni;

  public TransazioniPanel(MainPanel finestraPrincipale, String username) {

    this.username = username;

    setTitle("Storico Transazioni - " + username);
    setSize(720, 550);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(Color.WHITE);

    JLabel titoloLabel = getTitoloLabel();
    mainPanel.add(titoloLabel, BorderLayout.NORTH);

    areaElencoTransazioni = new JTextArea();
    areaElencoTransazioni.setFont(new Font("Arial", Font.PLAIN, 18));
    areaElencoTransazioni.setEditable(false);
    areaElencoTransazioni.setLineWrap(true);
    areaElencoTransazioni.setWrapStyleWord(true);
    areaElencoTransazioni.setBackground(Color.WHITE);
    areaElencoTransazioni.setForeground(Color.BLACK);

    JScrollPane scrollPane = new JScrollPane(areaElencoTransazioni);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    mainPanel.add(scrollPane, BorderLayout.CENTER);

    add(mainPanel);

    finestraPrincipale.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            dispose();
          }
        });

    aggiornaFinestra();

    setVisible(true);
  }

  private static JLabel getTitoloLabel() {
    JLabel titoloLabel = new JLabel("Registro Transazioni", SwingConstants.CENTER);
    titoloLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titoloLabel.setOpaque(true);
    titoloLabel.setForeground(new Color(0, 102, 204));
    titoloLabel.setBackground(new Color(230, 247, 255));
    titoloLabel.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 10),
            BorderFactory.createEmptyBorder(15, 0, 15, 0)));
    return titoloLabel;
  }

  public void aggiornaFinestra() {
    areaElencoTransazioni.setText("");
    Vector<String> transazioni = FileManager.getElencoTransazioni(username);

    for (String transazione : transazioni) {
      areaElencoTransazioni.append(transazione + "\n\n\n");
    }
  }
}
