package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.GameConfiguration;
import map.Intersection;
import map.Map;
import process.ExplorerManager;
import process.GameUtility;
import process.IntersectionManager;
import process.Simulation;

/**
 * This class contains the Game Frame.
 * 
 * @author Junior Afatchawo
 * @author Mathis Da Cruz
 * @author Nathan Chriqui
 */
public class MainGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

    private static final Font BUTTON_FONT = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 75);

    private int strat = 0;

    private GameDisplay board;
    private Simulation simulation;
    private JPanel gamePanel;
    private JPanel infoPanel;
    private JPanel explorateursPanel;
    private JPanel tresorsPanel;
    private JPanel goldPanel;
    private JPanel menuPanel;
    protected JLabel explorateurLabel = new JLabel("Explorateurs en vie :");
    protected JLabel explorateurValue = new JLabel("");
    protected JLabel tresorLabel = new JLabel("Tr�sors � trouver :");
    protected JLabel tresorValue = new JLabel("");
    protected JLabel goldLabel = new JLabel("Bourse d'or :");
    protected JLabel goldValue = new JLabel("");
    protected JButton menuButton = new JButton(new ImageIcon("src/images/menu2.png"));

    public int valueTreasures;
    public int valueExplorers;
    public static int valueGold;

    public int initTreasures;
    public int initExplorers;
    public int initGold;

    public int endTreasures;
    public int endExplorers;
    public int endGold;

    private Map map;

    public MainGUI(int strat) {
        super();

        this.strat = strat;

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        setTitle("Exploration");

        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        explorateurLabel.setFont(font);
        explorateurValue.setFont(font);
        tresorLabel.setFont(font);
        tresorValue.setFont(font);
        goldLabel.setFont(font);
        goldValue.setFont(font);

        menuButton.setFont(BUTTON_FONT);
    }

    protected void initLayout() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        simulation = new Simulation(strat);
        map = simulation.getMap();
        board = new GameDisplay(simulation, map);

        board.setPreferredSize(preferredSize);

        valueTreasures = simulation.getTreasures().size();
        initTreasures = simulation.getTreasures().size();
        initGold = valueGold;

        valueExplorers = simulation.getExplorers().size();
        initExplorers = simulation.getExplorers().size();

        menuPanel = new JPanel();
        menuButton.setOpaque(false);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setPreferredSize(new Dimension(270, 53));
        menuPanel.add(menuButton);
        menuButton.addActionListener(new ActionMenu(this));

        goldValue.setText("" + valueGold);
        goldPanel = new JPanel();
        goldPanel.setLayout(new FlowLayout());
        goldPanel.add(goldLabel);
        goldPanel.add(goldValue);

        explorateurValue.setText("" + valueExplorers);
        explorateursPanel = new JPanel();
        explorateursPanel.setLayout(new FlowLayout());
        explorateursPanel.add(explorateurLabel);
        explorateursPanel.add(explorateurValue);

        tresorValue.setText("" + valueTreasures);
        tresorsPanel = new JPanel();
        tresorsPanel.setLayout(new FlowLayout());
        tresorsPanel.add(tresorLabel);
        tresorsPanel.add(tresorValue);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1));
        infoPanel.add(goldPanel);
        infoPanel.add(explorateursPanel);
        infoPanel.add(tresorsPanel);
        infoPanel.add(menuPanel);

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
            GameConfiguration.NUMBER_TREASURES = 0;
            new MenuGUI();
        }

    }

    private void updateScore() {
        goldValue.setText("" + valueGold);
        explorateurValue.setText("" + valueExplorers);
        tresorValue.setText("" + valueTreasures);
    }

    public void run() {
        while (GameConfiguration.NUMBER_TREASURES != 0) {
            GameUtility.unitTime();
            Intersection position = new Intersection(GameConfiguration.Abscisse_Start, GameConfiguration.Ordonnee_Start);
            IntersectionManager firstIntersectionManager = simulation.getIntersectionManagersByPosition(position);
            System.out.println(firstIntersectionManager);
            if (firstIntersectionManager.isFree()) {
                ExplorerManager nextExplorer = simulation.getNextExplorer();
                if (nextExplorer != null) {
                    System.out.println(nextExplorer);
                    nextExplorer.setBlockManager(firstIntersectionManager);
                    firstIntersectionManager.enter(nextExplorer);

                    nextExplorer.setRunning(true);

                    // This is the Thread start.
                    nextExplorer.start();
                }
            }
            board.repaint();
            valueTreasures = simulation.getTreasures().size();
            valueExplorers = simulation.getExplorers().size();
            updateScore();
            // Tous les tr�sors r�cup�r�s et tous les explorateurs sont vivants
            if (valueTreasures == 0 && valueExplorers == initExplorers) {
                GameConfiguration.NUMBER_TREASURES = 0;
                endTreasures = initTreasures;
                endExplorers = 0;
                endGold = valueGold - initGold;            
                this.dispose();
                new EndGUI(strat, 0, endExplorers, endTreasures, endGold);
                // Tous les explorateurs morts et aucun tr�sor n'a �t� r�cup�r�
            } else if (valueExplorers == 0 && valueTreasures == initTreasures) {
                GameConfiguration.NUMBER_TREASURES = 0;
                endTreasures = 0;
                endExplorers = initExplorers;
                endGold = 0;
                this.dispose();
                new EndGUI(strat, 1, endExplorers, endTreasures, endGold);
            }
            // Des explorateurs sont morts et des tr�sors ont �t� r�cup�r�s
//            else if(valueExplorers < initExplorers && valueTreasures < initTreasures && !move) {
//                GameConfiguration.NUMBER_TREASURES = 0;
//                this.dispose();
//                endTreasures = initTreasures - valueTreasures;
//                endExplorers = initExplorers - valueExplorers;
//                endGold = valueGold - initGold;
//                new EndGUI(strat, 2, endExplorers, endTreasures, endGold);
//            }
            // Tous les explorateurs sont morts mais ils ont r�cup�r� des tr�sors
//              else if(valueExplorers == 0 && valueTreasures < initTreasures && !move) {
//                GameConfiguration.NUMBER_TREASURES = 0;
//                endTreasures = initTreasures - valueTreasures;
//                endExplorers = initExplorers;
//                endGold = valueGold - initGold;
//                this.dispose();
//                new EndGUI(strat, 3, endExplorers, endTreasures, endGold);    
            // Tous les tr�sors ont �t� r�cup�r�s mais des explorateurs sont morts
//            } else if(valueTreasures == 0 && valueExplorers < initExplorers && !move) {
//                GameConfiguration.NUMBER_TREASURES = 0;
//                endTreasures = initTreasures;
//                endExplorers = initExplorers - valueExplorers;
//                endGold = valueGold - initGold;
//                this.dispose();
//                new EndGUI(strat, 4, endExplorers, endTreasures, endGold);                
//            }
        }
    }
}
