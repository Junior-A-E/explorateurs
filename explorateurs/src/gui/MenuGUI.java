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

/**
 * This class contains the Menu Frame (first enter in the game).
 * 
 * @author Nathan Chriqui
 */
public class MenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel jeuLabel = new JLabel("Explorateurs");

    protected JButton startButton = new JButton(new ImageIcon("src/images/start.png"));
    protected JButton reglesButton = new JButton(new ImageIcon("src/images/regles.png"));
    protected JButton exitButton = new JButton(new ImageIcon("src/images/quitter.png"));

    public MenuGUI() {
        super("Explorateurs - Menu");

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 120);
        jeuLabel.setFont(font);
        jeuLabel.setForeground(Color.decode("#ec9706"));
    }

    protected void initLayout() {
        try {
            Image img = ImageIO.read(new File("src/images/background.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            jeuLabel.setBounds(58, 20, 1200, 150);
            imgGui.add(jeuLabel);

            startButton.setBounds(300, 270, 400, 65);
            imgGui.add(startButton);
            startButton.addActionListener(new ActionStart(this));

            reglesButton.setBounds(300, 400, 400, 65);
            imgGui.add(reglesButton);
            reglesButton.addActionListener(new ActionRegles(this));

            exitButton.setBounds(300, 530, 400, 65);
            imgGui.add(exitButton);
            exitButton.addActionListener(new ActionExit(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 1000, 750);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ActionStart implements ActionListener {
        private JFrame frame;

        public ActionStart(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new StrategyGUI();
        }
    }

    class ActionRegles implements ActionListener {
        private JFrame frame;

        public ActionRegles(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new ReglesGUI();
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