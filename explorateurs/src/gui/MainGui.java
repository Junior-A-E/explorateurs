package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.GameConfiguration;
import map.Map;
import process.Simulation;

public class MainGui extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
    
    private static final Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 75);

    private GameDisplay board;
    private Simulation simulation;
    private JPanel gamePanel;
    private JPanel infoPanel;
    protected JLabel explorateurLabel = new JLabel("Explorateurs restants en vie :");
    protected JLabel tresorLabel = new JLabel("Trésors restants à trouver :");
    protected JButton menu = new JButton("Menu");

    private Map map;

    public MainGui() {
        super();
        
        initStyle();
        
        initLayout();
    }

    protected void initStyle() {
        setTitle("Exploration");
        
        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        explorateurLabel.setFont(font);
        tresorLabel.setFont(font);
        
        menu.setFont(BUTTON_FONT);
    }
    
    protected void initLayout() {
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        simulation = new Simulation();
        map = simulation.getMap();
        board = new GameDisplay(simulation, map);

        board.setPreferredSize(preferredSize);    
        
        
        JButton menu = new JButton(new ImageIcon("src/images/menu2.png"));
        menu.setMargin(new Insets(0,0,0,0));
        menu.addActionListener(new ActionMenu(this));        
        
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));
        infoPanel.add(explorateurLabel);
        infoPanel.add(tresorLabel);
        infoPanel.add(menu);

        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.add(board);
        gamePanel.add(infoPanel);
        

        contentPane.add(gamePanel, BorderLayout.WEST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setPreferredSize(preferredSize);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
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

    public void run() {
        while (true) {
            try {
                Thread.sleep(GameConfiguration.GAME_SPEED);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            simulation.nextRound();
            board.repaint();
        }
    }
}
