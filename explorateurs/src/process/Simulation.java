package process;

import java.util.ArrayList;
import configuration.GameConfiguration;
import element.Animal;
import element.Mountain;
import element.Treasure;
import map.Intersection;
import map.Map;

public class Simulation {
	private Map map;
	private volatile ArrayList<Animal> animals = new ArrayList<Animal>();
	private volatile ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	private volatile ArrayList<Mountain> mountains = new ArrayList<Mountain>();
	private AnimalManager animalManager;
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public ArrayList<Treasure> getTreasures() {
		return treasures;
	}

	public ArrayList<Mountain> getMountains() {
		return mountains;
	}

	public Map getMap() {
		return map;
	}

	public Simulation() {
		map = GameBuilder.buildMap();
		Intersection[][] intersections = map.getIntersections();
		animalManager = new AnimalManager(map, this);
		
		int numberOfAnimals = GameUtility.getRandomNumber(5,10);
		for(int i = 0; i < numberOfAnimals; i++) {
			Intersection position = intersections[GameUtility.getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][GameUtility.getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Animal animal = new Animal(position);
			animals.add(animal);
		}
	
		int numberOfTreasures = GameUtility.getRandomNumber(1,5);
		for(int i = 0; i < numberOfTreasures; i++) {
			Intersection position = intersections[GameUtility.getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][GameUtility.getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Treasure treasure = new Treasure(position);
			treasures.add(treasure);
		}

		int numberOfMountains = GameUtility.getRandomNumber(5,8);
		for(int i = 0; i < numberOfMountains; i++) {
			Intersection position = intersections[GameUtility.getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][GameUtility.getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Mountain mountain = new Mountain(position);
			mountains.add(mountain);
		}
	}
	
	public void nextRound() {
		animalManager.moveAnimals();	
	}

}
