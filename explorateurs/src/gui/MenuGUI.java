package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 75);

    private JLabel jeu = new JLabel(" Explorateurs ");

    protected JButton start = new JButton("Start");
    protected JButton options = new JButton("Options");
    protected JButton regles = new JButton("Règles du jeu");

    public MenuGUI() {

        super("Explorateurs - Menu");

        initStyle();

        initLayout();

    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 120);
        jeu.setFont(font);
        jeu.setForeground(Color.decode("#ec9706"));

        start.setFont(BUTTON_FONT);
        options.setFont(BUTTON_FONT);
        regles.setFont(BUTTON_FONT);

    }

    protected void initLayout() {

        try {
            Image img = ImageIO.read(new File("src/images/background.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(new GridLayout(3, 1));
            jeu.setBounds(-10, -300, 5000, 850);
            imgGui.add(jeu);

            JPanel panel = new JPanel();
            imgGui.setLayout(null);
            imgGui.add(panel);

            JButton start = new JButton(new ImageIcon("src/images/start.png"));
            start.setBounds(240, 270, 500, 78);
            imgGui.add(start);
            start.addActionListener(new ActionStart(this));

            JButton options = new JButton(new ImageIcon("src/images/options.png"));
            options.setBounds(240, 400, 500, 78);
            imgGui.add(options);
//          options.addActionListener(new ActionOptions(this));

            JButton regles = new JButton(new ImageIcon("src/images/regles.png"));
            regles.setBounds(240, 530, 500, 78);
            imgGui.add(regles);
            regles.addActionListener(new ActionRegles(this));

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

    class ActionStart implements ActionListener {
        private JFrame frame;

        public ActionStart(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            MainGui game = new MainGui();
            Thread guiTread = new Thread(game);
            guiTread.start();
        }

    }

//    class ActionOptions implements ActionListener{
//        private JFrame frame;
//        
//        public ActionOptions(JFrame frame) {
//            this.frame = frame;
//        }
//        
//    //opening the option window
//        public void actionPerformed(ActionEvent e) {
//            frame.dispose();
//            new OptionsGUI();
//           
//        }
//        
//    }

    class ActionRegles implements ActionListener {
        private JFrame frame;

        public ActionRegles(JFrame frame) {
            this.frame = frame;
        }

        // opening the option window
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new ReglesGUI();
        }

    }
}