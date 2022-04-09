package process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import configuration.GameConfiguration;
import element.Animal;
import element.Explorer;
import element.Mountain;
import element.Treasure;
import map.Intersection;
import map.Map;

/**
 * The simulation management class. Its contains and prepares all {@link IntersectionManager} and all {@link ExplorerManager}.
 * 
 * The class has also some basic operations needed by the explorer simulation.
 * 
 * @author Junior Afatchawo
 * @author Mathis Da Cruz
 * @author Nathan Chriqui
 */
public class Simulation {
    private Map map;
    private int strat;

	private volatile ArrayList<Intersection> occupied = new ArrayList<Intersection>();
    private volatile ArrayList<Explorer> explorers = new ArrayList<Explorer>();
    private volatile ArrayList<Animal> animals = new ArrayList<Animal>();
    private volatile ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    private volatile ArrayList<Mountain> mountains = new ArrayList<Mountain>();

    private volatile ArrayList<Intersection> explorersStartIntersection = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> treasuresIntersection = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> mountainsIntersection = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> animalsIntersection = new ArrayList<Intersection>();

	private volatile ArrayList<Intersection> forbiddenForAnimals = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> forbiddenForExplorers = new ArrayList<Intersection>();

    private volatile ArrayList<IntersectionManager> intersectionManagers = new ArrayList<IntersectionManager>();
	private volatile HashMap<Intersection, IntersectionManager> intersectionManagersByPosition = new HashMap<Intersection, IntersectionManager>();
	
	private ArrayList<ExplorerManager> explorerManagers = new ArrayList<ExplorerManager>();

	public IntersectionManager getIntersectionManagersByPosition(Intersection position) {
		Collection<IntersectionManager> values = intersectionManagersByPosition.values();
		for(IntersectionManager i : values) {
			if(i.getAbscisse() == position.getAbscisse() && i.getOrdonnee() == position.getOrdonnee()) {
				return i;
			}
		}
		return null;
	}


    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public ArrayList<Mountain> getMountains() {
        return mountains;
    }

    public ArrayList<Intersection> getExplorersStartIntersection() {
        return explorersStartIntersection;
    }

    public ArrayList<Intersection> getTreasuresIntersection() {
        return treasuresIntersection;
    }

    public ArrayList<Intersection> getMountainsIntersection() {
        return mountainsIntersection;
    }

    public ArrayList<Intersection> getForbiddenForAnimals() {
        return forbiddenForAnimals;
    }
    
    public ArrayList<ExplorerManager> getExplorerManagers(){
    	return explorerManagers;
    }

    public Map getMap() {
        return map;
    }

    /**
     * Build the map and add his components.
     * @param strat Number of the strat
     */
    public Simulation(int strat) {
        map = GameBuilder.buildMap();
        this.strat = strat;
        Intersection[][] intersections = map.getIntersections();
        //animalManager = new AnimalManager(map, this);
        
        for (int i = 0 ; i <GameConfiguration.ABSCISSE_COUNT;i++) {
        	for(int j = 0; j<GameConfiguration.ORDONNEE_COUNT;j++) {
        		Intersection position = intersections[i][j];
        		IntersectionManager intersectionManager = new IntersectionManager(position, map);
        		intersectionManagers.add(intersectionManager);
        		intersectionManagersByPosition.put(position,intersectionManager);
        	}
        }
        
        int numberOfExplorers = GameUtility.getRandomNumber(3, 5);
        System.out.println(numberOfExplorers);
        for (int i = 0; i < numberOfExplorers; i++) {
            Intersection position = intersections[0][0];
            Explorer explorer = new Explorer(position);
            ExplorerManager explorerManager = new ExplorerManager(map, this, explorer);
            explorerManagers.add(explorerManager);
            explorers.add(explorer);
            explorersStartIntersection.add(position);
        }

        int numberOfTreasures = numberOfExplorers;
        GameConfiguration.NUMBER_TREASURES = numberOfTreasures;
        System.out.println("Game : "+ GameConfiguration.NUMBER_TREASURES);
        for (int i = 0; i < numberOfTreasures; i++) {
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(2, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(2,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position) || (position.getAbscisse()==position.getOrdonnee()));
            Treasure treasure = new Treasure(position);
            treasures.add(treasure);
            treasuresIntersection.add(position);
            //System.out.println(treasuresIntersection);
        }

        int numberOfChainMountains = GameUtility.getRandomNumber(6, 8);
        for (int i = 0; i < numberOfChainMountains; i++) {
            int numberOfMountains = GameUtility.getRandomNumber(1, 3);
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(2, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(2,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position) || isForbiddenForAnimals(position));
            Mountain mountain = new Mountain(position);
            mountains.add(mountain);
            mountainsIntersection.add(position);
        }

        int numberOfAnimals = GameUtility.getRandomNumber(25, 30);
        for (int i = 0; i < numberOfAnimals; i++) {
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(2, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(2,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position) || (position.getAbscisse()==position.getOrdonnee()));
            Animal animal = new Animal(position);
            animals.add(animal);
            animalsIntersection.add(position);
        }
    }

   /* public void nextRound() {
        animalManager.moveAnimals();
        explorerManager.moveExplorers();
    }*/

    public boolean isOccupied(Intersection find) {
        occupied.addAll(explorersStartIntersection);
        occupied.addAll(treasuresIntersection);
        occupied.addAll(mountainsIntersection);
        return belongTo(find, occupied);
    }

    public boolean isForbiddenForAnimals(Intersection find) {
        forbiddenForAnimals.addAll(explorersStartIntersection);
        forbiddenForAnimals.addAll(treasuresIntersection);
        forbiddenForAnimals.addAll(mountainsIntersection);
        return belongTo(find, forbiddenForAnimals);
    }

    public boolean isForbiddenForAnimalsAfterDeparture(Intersection find) {
        forbiddenForAnimals.addAll(treasuresIntersection);
        forbiddenForAnimals.addAll(mountainsIntersection);
        return belongTo(find, forbiddenForAnimals);
    }

    public boolean isForbiddenForExplorers(Intersection find) {
        forbiddenForExplorers.addAll(mountainsIntersection);
        forbiddenForExplorers.addAll(explorersStartIntersection);
        return belongTo(find, forbiddenForExplorers);
    }

    public boolean isExplorerStart(Intersection find) {
        return belongTo(find, explorersStartIntersection);
    }

    /**
     * Verify if an intersection is in the start zone
     * @return True if yes, false otherwise
     */
    public boolean isInStartZone(Intersection find) {
        // System.out.println(map.getElementPosition(find.getAbscisse(),
        // find.getOrdonnee()));
        // find = map.getElementPosition(find.getAbscisse(), find.getOrdonnee());
        int inside = 0;
        if (find.getAbscisse() >= 8 * GameConfiguration.BLOCK_SIZE && find.getAbscisse() <= 10 * GameConfiguration.BLOCK_SIZE
                && find.getOrdonnee() >= 8 * GameConfiguration.BLOCK_SIZE && find.getOrdonnee() <= 10 * GameConfiguration.BLOCK_SIZE) {
            inside = 1;
        }
        return inside == 1;
    }

    public boolean initialPositionCheck(Intersection position) {
        return isOccupied(position) || isExplorerStart(position) || isInStartZone(position);
    }

    /**
     * Get explorer who is not running.
     * @return The explorer or null if no explorer is find
     */
	public ExplorerManager getNextExplorer() {
		System.out.println(explorerManagers);
		for (ExplorerManager explorerManager : explorerManagers) {
			System.out.println(explorerManager);
			if (!explorerManager.isRunning()) {
				return explorerManager;
			}
		}
		return null;
	}

	/**
	 * Get treasure not already taken.
	 * @return The treasure or null if no treasure is find
	 */
	public Treasure getNextTreasure() {
		for(Treasure treasure : treasures) {
			if(!treasure.isTake()) {
				return treasure;		
			}
		}
		return null;
	}
	
	/**
     * Get animal not already taken.
     * @return The animal or null if no animal is find
     */
	public Animal getNextAnimal() {
		for(Animal animal : animals) {
			if(!animal.isTake()) {
				return animal;		
			}
		}
		return null;
	}
	
    public int getStrat() {
		return strat;
	}
    
    public ArrayList<Intersection> getAnimalsIntersection() {
		return animalsIntersection;
	}


    /**
     * Search and tell if the position (intersection) of an element is in the same intersection of an another element.
     * @param intersection Intersection of the element to be compared
     * @param array Lists who contain all element's intersection of the map
     * @return True if they are in the same position, false otherwise.
     */
	public boolean belongTo(Intersection intersection, ArrayList<Intersection> array) {
        int exist = 0;
        for (Intersection inter : array) {
            if (equalIntersection(inter,intersection)) {
                exist = 1;
            }
        }
        return exist == 1;
    }
	
    /**
     * Compare two intersections.
     * @param i1 The first intersection
     * @param i2 The second intersection
     * @return True if they are in the same position, false otherwise.
     */
    public boolean equalIntersection(Intersection i1, Intersection i2) {
    	return i1.getAbscisse() == i2.getAbscisse() && i1.getOrdonnee() == i2.getOrdonnee();
    }

}
