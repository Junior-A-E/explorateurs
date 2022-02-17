package map;

import java.util.ArrayList;
import java.util.List;

import configuration.GameConfiguration;
import element.Animal;
import element.Mountain;
import element.Treasure;


public class Map {
	
	private Intersection[][] intersections;

	private int AbscisseStart = GameConfiguration.Abscisse_Start;
	private int OrdonneeStart = GameConfiguration.Ordonnee_Start;
	
	private int distance = (GameConfiguration.BLOCK_SIZE *(GameConfiguration.ABSCISSE_COUNT-1)) + GameConfiguration.Abscisse_Start;
	
	private int abscisseCount;
	private int ordonneeCount;
	
	//private List<Grass> grass = new ArrayList<Grass>();
	private List<Treasure> treasures = new ArrayList<Treasure>();
	private List<Mountain> mountains = new ArrayList<Mountain>();
	private List<Animal> animals = new ArrayList<Animal>();
	
	public Map(int abscisseCount , int ordonneeCount) {
		intersections = new Intersection[abscisseCount][ordonneeCount];
		this.abscisseCount = abscisseCount;
		this.ordonneeCount = ordonneeCount;
		
		for(int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
				intersections[abscisseIndex][ordonneeIndex] = new Intersection(AbscisseStart,OrdonneeStart);	
				OrdonneeStart += GameConfiguration.BLOCK_SIZE;
			}
			OrdonneeStart = GameConfiguration.Ordonnee_Start;
			AbscisseStart += GameConfiguration.BLOCK_SIZE;
		}
		//initAnimals();
		//initGrass();
		//initMountains();
		//initTreasures();
	}
	
	public int getAbscisseCount() {
		return abscisseCount;
	}

	public void setAbscisseCount(int abscisseCount) {
		this.abscisseCount = abscisseCount;
	}

	public int getOrdonneeCount() {
		return ordonneeCount;
	}

	public void setOrdonneeCount(int ordonneeCount) {
		this.ordonneeCount = ordonneeCount;
	}

	public Intersection[][] getIntersections() {
		return intersections;
	}

	public void setIntersections(Intersection[][] intersections) {
		this.intersections = intersections;
	}

	public int getAbscisseStart() {
		return AbscisseStart;
	}

	public void setAbscisseStart(int abscisseStart) {
		AbscisseStart = abscisseStart;
	}

	public int getOrdonneeStart() {
		return OrdonneeStart;
	}

	public void setOrdonneeStart(int ordonneeStart) {
		OrdonneeStart = ordonneeStart;
	}
	
	public Intersection getIntersection(int abscisse, int ordonnee) {
		return intersections[abscisse][ordonnee];
	}

	/*public List<Grass> initGrass() {
		for(int abscisseIndex = 0; abscisseIndex < GameConfiguration.ABSCISSE_COUNT-1; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < GameConfiguration.ORDONNEE_COUNT-1; ordonneeIndex++) {
				Grass gras = new Grass(intersections[abscisseIndex][ordonneeIndex]);
				grass.add(gras);
				OrdonneeStart += GameConfiguration.BLOCK_SIZE;
			}
			OrdonneeStart = 100;
			AbscisseStart += GameConfiguration.BLOCK_SIZE;
		}
		return grass;
	}*/

	public List<Treasure> initTreasures() {
		int numberOfTreasures = getRandomNumber(1,5);
		for(int i = 0; i < numberOfTreasures; i++) {
			Intersection position = intersections[getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Treasure treasure = new Treasure(position);
			treasures.add(treasure);
		}
		return treasures;
	}

	public List<Mountain> initMountains() {
		int numberOfMountains = getRandomNumber(5,8);
		for(int i = 0; i < numberOfMountains; i++) {
			Intersection position = intersections[getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Mountain mountain = new Mountain(position);
			mountains.add(mountain);
		}
		return mountains;
	}

	public List<Animal> initAnimals() {
		int numberOfAnimals = getRandomNumber(5,8);
		for(int i = 0; i < numberOfAnimals; i++) {
			Intersection position = intersections[getRandomNumber(0,GameConfiguration.ABSCISSE_COUNT-2)][getRandomNumber(0,GameConfiguration.ORDONNEE_COUNT-2)];
			Animal animal = new Animal(position);
			animals.add(animal);
		}
		System.out.println(animals);
		return animals;
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
	
	public Intersection getElementPosition(int x , int y) {
		int abscisse = (x-GameConfiguration.Abscisse_Start)/ GameConfiguration.BLOCK_SIZE;
		int ordonnee = (y-GameConfiguration.Ordonnee_Start)/ GameConfiguration.BLOCK_SIZE;
		return getIntersection(abscisse, ordonnee);
	}
	
	public boolean isOnLeftTop(Intersection position) {
        return position.getAbscisse() == GameConfiguration.Abscisse_Start && position.getOrdonnee() == GameConfiguration.Ordonnee_Start;
    }

    public boolean isOnRightTop(Intersection position) {
        int abscisse = position.getAbscisse();
        int ordonnee = position.getOrdonnee();
        return abscisse == distance  && ordonnee == GameConfiguration.Ordonnee_Start;
    }
    
    public boolean isOnLeftBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == GameConfiguration.Abscisse_Start &&  y == distance; 
    }

    public boolean isOnRightBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y == distance;
    }

    public boolean isOnTopBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance  && y == GameConfiguration.Ordonnee_Start;
    }

    public boolean isOnBottomBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance  && y == distance;
    }
    
    public boolean isOnLeftBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start == x  && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }
    
    public boolean isOnRightBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }
}
