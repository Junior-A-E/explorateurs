package process;

import java.util.ArrayList;

import configuration.GameConfiguration;
import element.Animal;
import map.Intersection;
import map.Map;

public class Simulation {
	private Map map;
	private volatile ArrayList<Animal> animals = new ArrayList<Animal>();
	private AnimalManager animalManager;
	
	public ArrayList<Animal> getAnimals() {
		return animals;
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
	}
	
	public void nextRound() {
		animalManager.moveAnimals();	
	}

}
