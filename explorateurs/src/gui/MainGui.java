package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import configuration.GameConfiguration;
import map.Map;
import process.AnimalManager;
import process.GameBuilder;
import process.Simulation;


public class MainGui extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
	
	private GameDisplay board;
	private Simulation simulation;
	private JPanel gamePanel;
	
	private Map map;
	
	public MainGui() {
		super();
		init();
	}
	
	private void init() {
		setTitle("Exploration");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		simulation = new Simulation();
		map = simulation.getMap();
		board = new GameDisplay(simulation,map);
		
		board.setPreferredSize(preferredSize);
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new FlowLayout());
		gamePanel.add(board);
		
		contentPane.add(gamePanel,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setPreferredSize(preferredSize);
		setVisible(true);
		setResizable(false);
	}
	
	public void run() {
		while(true) {
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
