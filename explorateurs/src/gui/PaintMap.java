package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import configuration.GameConfiguration;
import element.Animal;
import element.Grass;
import element.Mountain;
import element.Treasure;
import map.Intersection;
import map.Map;
import process.GameUtility;

public class PaintMap {
	
	public void paint(Map map, Graphics g) {
		Intersection[][] intersections = map.getIntersections();
		
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; ordonneeIndex < map.getOrdonneeCount(); ordonneeIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[map.getAbscisseCount()-1][ordonneeIndex];
			g.setColor(Color.BLACK);
			g.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}

		/*Creation of the vertical lines for the goban using drawLine method*/
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; abscisseIndex < map.getAbscisseCount(); abscisseIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[abscisseIndex][map.getOrdonneeCount()-1];
			g.setColor(Color.BLACK);
			g.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}
	}
	
	public void printGrass(Graphics g2 , Grass grass) {
		Intersection position = grass.getPosition();
		g2.drawImage(GameUtility.readImage("src/images/forest.png"),position.getAbscisse(),position.getOrdonnee(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	
	public void printAnimal(Graphics g2 , Animal animal) {
		Intersection position = animal.getPosition();
		g2.drawImage(GameUtility.readImage("src/images/animal.png"),position.getAbscisse(),position.getOrdonnee(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	
//	public void removAnimal(Graphics g2 , Animal animal) {
//		Intersection position = animal.getPosition();
//		g2.remove();
//	}

	public void printTreasure(Graphics g2 , Treasure treasure) {
		Intersection position = treasure.getPosition();
		g2.drawImage(GameUtility.readImage("src/images/treasure.png"),position.getAbscisse(),position.getOrdonnee(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
	public void printMountain(Graphics g2 , Mountain moutain) {
		Intersection position = moutain.getPosition();
		g2.drawImage(GameUtility.readImage("src/images/mountain.png"),position.getAbscisse(),position.getOrdonnee(),GameConfiguration.BLOCK_SIZE,GameConfiguration.BLOCK_SIZE,null);
	}
}
