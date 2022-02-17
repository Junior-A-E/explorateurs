package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import configuration.GameConfiguration;
import element.Animal;
import map.Intersection;
import map.Map;
import process.GameUtility;
import process.Simulation;


public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	private Map map;
	private Simulation simulation;
	
	public GameDisplay(Simulation simulation, Map map) {
		this.simulation = simulation;
		this.map = map;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paint(map, g);
		printAnimals(g);
	}
	
	public void paint(Map map, Graphics g) {
		Intersection[][] intersections = map.getIntersections();	
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; ordonneeIndex < map.getOrdonneeCount(); ordonneeIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[map.getAbscisseCount()-1][ordonneeIndex];
			g.setColor(Color.BLACK);
			g.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; abscisseIndex < map.getAbscisseCount(); abscisseIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[abscisseIndex][map.getOrdonneeCount()-1];
			g.setColor(Color.BLACK);
			g.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}
	}
	
	public void printAnimals(Graphics g2) {
		for(Animal animal : simulation.getAnimals()) {
			Intersection position = animal.getPosition();
			g2.drawImage(GameUtility.readImage("src/images/animal.png"),position.getAbscisse(),position.getOrdonnee(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
		}
		
	}
}
