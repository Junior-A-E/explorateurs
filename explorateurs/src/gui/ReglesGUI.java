package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReglesGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 75);

    private JLabel regles = new JLabel(" Règles du jeu ");
    private JTextArea regle1 = new JTextArea();
    private JTextArea regle2 = new JTextArea();
    private JTextArea regle3 = new JTextArea();

    protected JButton menu = new JButton("Menu");

    public ReglesGUI() {

        super("Explorateurs - Règles");

        initStyle();

        initLayout();

    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 80);
        regles.setFont(font);
        regles.setForeground(Color.decode("#ec9706"));

        Font fontList = new Font(Font.DIALOG, Font.CENTER_BASELINE, 30);
        regle1.setFont(fontList);
        regle1.setForeground(Color.decode("#000000"));
        regle2.setFont(fontList);
        regle2.setForeground(Color.decode("#000000"));
        regle3.setFont(fontList);
        regle3.setForeground(Color.decode("#000000"));

        menu.setFont(BUTTON_FONT);

    }

    protected void initLayout() {

        try {
            Image img = ImageIO.read(new File("src/images/background_regles.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(new GridLayout(2, 1));
            regles.setBounds(140, -300, 4000, 850);
            imgGui.add(regles);

            JPanel panel = new JPanel();
            imgGui.setLayout(null);
            imgGui.add(panel);

            regle1.setText("L'objectif de ce jeu est de récupérer au plus vite tous\nles trésors présents sur la carte en minimisant des\npertes.");
            regle1.setBounds(150, 200, 2000, 140);
            regle1.setEditable(false);
            regle1.setOpaque(false);
            imgGui.add(regle1);
            JLabel puce1 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce1.setBounds(108, 210, 22, 22);
            imgGui.add(puce1);

            regle2.setText(
                    "L'utilisateur peut, avant chaque partie paramétrer\nl'effectif ainsi que l'équipement de chacun des\nexplorateurs en utilisant l'argent contenu\ndans sa bourse.");
            regle2.setBounds(150, 330, 2000, 150);
            regle2.setEditable(false);
            regle2.setOpaque(false);
            imgGui.add(regle2);
            JLabel puce2 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce2.setBounds(108, 340, 22, 22);
            imgGui.add(puce2);

            regle3.setText("La partie se termine dès que tous les trésors\nont été trouvés ou dès que tous les explorateurs\nsont morts.");
            regle3.setBounds(150, 490, 2000, 140);
            regle3.setEditable(false);
            regle3.setOpaque(false);
            imgGui.add(regle3);
            JLabel puce3 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce3.setBounds(108, 500, 22, 22);
            imgGui.add(puce3);

            JButton menu = new JButton(new ImageIcon("src/images/menu.png"));
            menu.setBounds(240, 650, 500, 78);
            imgGui.add(menu);
            menu.addActionListener(new ActionMenu(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 1000, 800);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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