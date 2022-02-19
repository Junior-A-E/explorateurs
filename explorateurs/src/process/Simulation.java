package process;

import java.util.ArrayList;
import configuration.GameConfiguration;
import element.Animal;
import element.Explorer;
import element.Mountain;
import element.Treasure;
import map.Intersection;
import map.Map;

public class Simulation {
    private Map map;
    private volatile ArrayList<Intersection> occupied = new ArrayList<Intersection>();
    private volatile ArrayList<Explorer> explorers = new ArrayList<Explorer>();
    private volatile ArrayList<Animal> animals = new ArrayList<Animal>();
    private volatile ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    private volatile ArrayList<Mountain> mountains = new ArrayList<Mountain>();

    private volatile ArrayList<Intersection> explorersStartIntersection = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> treasuresIntersection = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> mountainsIntersection = new ArrayList<Intersection>();

    private volatile ArrayList<Intersection> forbiddenForAnimals = new ArrayList<Intersection>();
    private volatile ArrayList<Intersection> forbiddenForExplorers = new ArrayList<Intersection>();

    private AnimalManager animalManager;
    private ExplorerManager explorerManager;

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

    public Map getMap() {
        return map;
    }

    public Simulation() {
        map = GameBuilder.buildMap();
        Intersection[][] intersections = map.getIntersections();
        animalManager = new AnimalManager(map, this);
        explorerManager = new ExplorerManager(map, this);

        int numberOfExplorers = GameUtility.getRandomNumber(3, 5);
        for (int i = 0; i < numberOfExplorers; i++) {
            // Intersection position = intersections[0][0];
            // do {
            Intersection position = intersections[GameConfiguration.ABSCISSE_COUNT / 2][GameConfiguration.ORDONNEE_COUNT / 2];
            // }while(isOccupied(position));
            Explorer explorer = new Explorer(position);
            explorers.add(explorer);
            explorersStartIntersection.add(position);
        }
        System.out.println(explorersStartIntersection);

        int numberOfTreasures = GameUtility.getRandomNumber(explorers.size(), (explorers.size() * 2) - 1);
        for (int i = 0; i < numberOfTreasures; i++) {
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(0, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(0,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position));
            Treasure treasure = new Treasure(position);
            treasures.add(treasure);
            treasuresIntersection.add(position);
        }

        int numberOfChainMountains = GameUtility.getRandomNumber(6, 8);
        for (int i = 0; i < numberOfChainMountains; i++) {
            int numberOfMountains = GameUtility.getRandomNumber(1, 3);
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(0, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(0,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position) || isForbiddenForAnimals(position));
            Mountain mountain = new Mountain(position);
            mountains.add(mountain);
            mountainsIntersection.add(position);/*
                                                 * for(int j = 1; j < numberOfMountains; j++) { Intersection position2 =
                                                 * intersections[0][0]; do { position2 = map.getFreeNeighbour(position);
                                                 * }while(initialPositionCheck(position2)); Mountain mountain2 = new
                                                 * Mountain(position2); mountains.add(mountain2); }
                                                 */
        }

        int numberOfAnimals = GameUtility.getRandomNumber(5, 10);
        for (int i = 0; i < numberOfAnimals; i++) {
            Intersection position = intersections[0][0];
            do {
                position = intersections[GameUtility.getRandomNumber(0, GameConfiguration.ABSCISSE_COUNT - 2)][GameUtility.getRandomNumber(0,
                        GameConfiguration.ORDONNEE_COUNT - 2)];
            } while (initialPositionCheck(position));
            Animal animal = new Animal(position);
            animals.add(animal);
        }
    }

    public void nextRound() {
        animalManager.moveAnimals();
        explorerManager.moveExplorers();
    }

    public boolean belongTo(Intersection intersection, ArrayList<Intersection> array) {
        int exist = 0;
        for (Intersection inter : array) {
            if (inter.equals(intersection)) {
                exist = 1;
            }
        }
        return exist == 1;
    }

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

}
