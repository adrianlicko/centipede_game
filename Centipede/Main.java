import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Hra hra;
    private static JFrame frame;
    private static JLabel label;
    private static JPanel panel;
    private static JButton spustiHru;
    private static JButton obchod;

    public static void main(String[] args) {
        frame = new JFrame("Menu");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label = new JLabel("Centipede");
        label.setFont(new Font("Arial", Font.BOLD, 20)); // Nastavi font a velkost
        label.setHorizontalAlignment(JLabel.CENTER); // Zarovnaj na stred
        frame.getContentPane().add(label, BorderLayout.NORTH);

        // Vytvorte nový panel s GridBagLayout pre tlačidlá
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        spustiHru = new JButton("Spusti Hru");
        spustiHru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spustiHru();
                frame.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(spustiHru, gbc);

        obchod = new JButton("Obchod");
        obchod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obchod();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(obchod, gbc);

        // Umiestnite panel do stredu hlavného kontajnera
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void spustiHru() {
        hra = new Hra(10, 70);
        // Tu by ste mohli pridať ďalšie nastavenia alebo akcie po spustení hry
    }

    private static void obchod() {
        // Tu by ste mohli pridať kód pre obchod
    }
}
