package process;

import configuration.GameConfiguration;
import element.Animal;
import map.Intersection;
import map.Map;

public class AnimalManager {

	private Map map;
	private Simulation simulation;
	
	public AnimalManager(Map map , Simulation simulation) {
		this.simulation = simulation;
		this.map = map;
	}

	protected void moveAnimals() {
		for (Animal animal : simulation.getAnimals() ) {
			System.out.println(animal.getPosition().toString());
			Intersection position = animal.getPosition();
			if(map.isOnLeftTop(position)) {
				leftTopMove(position,animal);
			}
			else if (map.isOnRightTop(position)) {
				rightTopMove(position,animal);
			}
			else if (map.isOnLeftBottom(position)) {
				leftBottomMove(position,animal);
			}
			else if (map.isOnRightBottom(position)) {
				rightBottomMove(position,animal);
			}
			else if (map.isOnTopBorder(position)) {
				topBorderMove(position,animal);
			}
			else if (map.isOnBottomBorder(position)) {
				bottomBorderMove(animal,position);
			}
			else if (map.isOnLeftBorder(position)) {
				leftBorderMove(animal,position);
			}
			else if (map.isOnRightBorder(position)) {
				rightBorderMove(animal,position);
			}
			else {
				normalMove(animal,position);
			}
		}
	}

	private void normalMove(Animal animal, Intersection position) {
		double percent = Math.random();
		if(percent <= 0.25) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(),position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.25 && percent <= 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.50 && percent <= 0.75) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
	}

	private void rightBorderMove(Animal animal, Intersection position) {
		double percent = Math.random();
		if(percent <= 0.33) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(),position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.33 && percent <= 0.66) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.67) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		
	}

	private void leftBorderMove(Animal animal, Intersection position) {
		double percent = Math.random();
		if(percent <= 0.33) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(),position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.33 && percent <= 0.66) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
		else if(percent > 0.67) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
	}

	private void bottomBorderMove(Animal animal, Intersection position) {
		double percent = Math.random();
		if(percent <= 0.33) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.33 && percent <= 0.66) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.67) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
	}

	private void topBorderMove(Intersection position, Animal animal) {
		double percent = Math.random();
		if(percent <= 0.33) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.33 && percent <= 0.66) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.67) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
	}

	private void rightBottomMove(Intersection position, Animal animal) {
		double percent = Math.random();
		if(percent <= 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
	}

	private void leftBottomMove(Intersection position, Animal animal) {
		double percent = Math.random();
		if(percent <= 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()-GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}	
	}

	private void rightTopMove(Intersection position, Animal animal) {
		double percent = Math.random();
		if(percent <= 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()-GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}
	}

	private void leftTopMove(Intersection position, Animal animal) {
		double percent = Math.random();
		if(percent <= 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse()+GameConfiguration.BLOCK_SIZE,position.getOrdonnee());
			animal.setPosition(newPosition);
		}
		else if(percent > 0.50) {
			Intersection newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee()+GameConfiguration.BLOCK_SIZE);
			animal.setPosition(newPosition);
		}	
	}	


}