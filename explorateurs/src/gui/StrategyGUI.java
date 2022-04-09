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
 * This class serves to define the Frame for the choice of the strategy.
 * 
 * @author Nathan Chriqui
 */
public class StrategyGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel strategyLabel = new JLabel("Choissisez");
    private JLabel strategy2Label = new JLabel("une stratégie");

    protected JButton attackButton = new JButton(new ImageIcon("src/images/offensive.png"));
    protected JButton defenseButton = new JButton(new ImageIcon("src/images/defensive.png"));
    protected JButton adventureButton = new JButton(new ImageIcon("src/images/aventuriere.png"));
    protected JButton menuButton = new JButton(new ImageIcon("src/images/menu.png"));

    public StrategyGUI() {
        super("Explorateurs - Stratégie");

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 85);
        strategyLabel.setFont(font);
        strategyLabel.setForeground(Color.decode("#ec9706"));

        strategy2Label.setFont(font);
        strategy2Label.setForeground(Color.decode("#ec9706"));
    }

    protected void initLayout() {
        try {
            Image img = ImageIO.read(new File("src/images/strategy.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            strategyLabel.setBounds(235, 30, 700, 80);
            imgGui.add(strategyLabel);

            strategy2Label.setBounds(160, 105, 700, 100);
            imgGui.add(strategy2Label);

            adventureButton.setBounds(300, 260, 400, 65);
            imgGui.add(adventureButton);
            adventureButton.addActionListener(new ActionAdventure(this));

            attackButton.setBounds(300, 390, 400, 65);
            imgGui.add(attackButton);
            attackButton.addActionListener(new ActionAttack(this));

            defenseButton.setBounds(300, 520, 400, 65);
            imgGui.add(defenseButton);
            defenseButton.addActionListener(new ActionDefense(this));

            menuButton.setBounds(300, 650, 400, 65);
            imgGui.add(menuButton);
            menuButton.addActionListener(new ActionMenu(this));

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

    class ActionAdventure implements ActionListener {
        private JFrame frame;

        public ActionAdventure(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainGUI game = new MainGUI(0);
            Thread guiTread = new Thread(game);
            guiTread.start();
        }
    }

    class ActionAttack implements ActionListener {
        private JFrame frame;

        public ActionAttack(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainGUI game = new MainGUI(1);
            Thread guiTread = new Thread(game);
            guiTread.start();
        }
    }

    class ActionDefense implements ActionListener {
        private JFrame frame;

        public ActionDefense(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainGUI game = new MainGUI(2);
            Thread guiTread = new Thread(game);
            guiTread.start();
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
}