package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * This is the End Frame of the Game
 * 
 * @author Nathan Chriqui
 */
public class EndGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private int strat;
    private int explorers;
    private int gold;

    private JLabel endLabel = new JLabel("Fin d'exploration");

    JTextArea affichage = new JTextArea();

    private int end;

    protected JButton restartButton = new JButton(new ImageIcon("src/images/rejouer.png"));
    protected JButton changeButton = new JButton(new ImageIcon("src/images/change_strategie.png"));
    protected JButton menuButton = new JButton(new ImageIcon("src/images/menu.png"));
    protected JButton exitButton = new JButton(new ImageIcon("src/images/quitter.png"));

    public EndGUI(int strat, int end, int explorers, int treasures, int gold) {
        super("Explorateurs - Fin d'exploration");
        this.strat = strat;
        this.end = end;
        this.explorers = explorers;
        this.gold = gold;

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 80);
        endLabel.setFont(font);
        endLabel.setForeground(Color.decode("#ec9706"));

        affichage.setEditable(false);
        affichage.setOpaque(false);
        affichage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 38));
        affichage.setForeground(Color.WHITE);
    }

    protected void initLayout() {
        try {
            Image img = ImageIO.read(new File("src/images/background_end.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            endLabel.setBounds(80, -15, 1200, 150);
            imgGui.add(endLabel);

            // Meilleure fin
            if (end == 0) {
                affichage.setText("Exploration réussie !\nVous avez récupéré un joli\nbutin de " + gold + " pièces d'or sans\nla moindre perte !");
                // Pire fin
            } else if (end == 1) {
                affichage.setText("Exploration échouée...\nVous avez perdu tous vos\nexplorateurs et récupéré 0 pièce d'or.");
                // Des trésors ont été récup + des explorateurs sont morts
            } else if (end == 2) {
                affichage.setText("Exploration partiellement réussie !\nVous avez récupéré un joli\nbutin de " + gold + " pièces d'or\nen perdant "
                        + explorers + " explorateurs");
                // Des trésors ont été récup + tout les explorateurs sont morts
            } else if (end == 3) {
                affichage.setText("Exploration échouée...\nVous avez perdu tous vos\nexplorateurs mais récupéré " + gold + " pièces d'or.");
                // Tout les trésor récup + quelques explorateurs sont morts
            } else {
                affichage.setText("Exploration partiellement réussie !\nVous avez récupéré un joli\nbutin de " + gold + " pièces d'or\nen perdant "
                        + explorers + " explorateurs");
            }

            affichage.setBounds(100, 120, 820, 250);
            imgGui.add(affichage);

            restartButton.setBounds(300, 360, 400, 65);
            imgGui.add(restartButton);
            restartButton.addActionListener(new ActionRestart(this));

            changeButton.setBounds(300, 460, 400, 65);
            imgGui.add(changeButton);
            changeButton.addActionListener(new ActionChange(this));

            menuButton.setBounds(300, 560, 400, 65);
            imgGui.add(menuButton);
            menuButton.addActionListener(new ActionMenu(this));

            exitButton.setBounds(300, 660, 400, 65);
            imgGui.add(exitButton);
            exitButton.addActionListener(new ActionExit(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 1000, 800);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ActionRestart implements ActionListener {
        private JFrame frame;

        public ActionRestart(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainGUI game = new MainGUI(strat);
            Thread guiTread = new Thread(game);
            guiTread.start();
        }
    }

    class ActionChange implements ActionListener {
        private JFrame frame;

        public ActionChange(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new StrategyGUI();
        }
    }

    class ActionMenu implements ActionListener {
        private JFrame frame;

        public ActionMenu(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MenuGUI();
        }

    }

    class ActionExit implements ActionListener {
        private JFrame frame;

        public ActionExit(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}