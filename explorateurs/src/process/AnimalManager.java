package process;

import configuration.GameConfiguration;
import element.Animal;
import map.Intersection;
import map.Map;

/**
 * This class manages all animals of the map.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 * @author Nathan Chriqui
 */
public class AnimalManager {

    private Map map;
    private Simulation simulation;

    public AnimalManager(Map map, Simulation simulation) {
        this.simulation = simulation;
        this.map = map;
    }

    /**
     * Verify if animals are on card ends and move them in case.
     */
    protected void moveAnimals() {
        for (Animal animal : simulation.getAnimals()) {

            Intersection position = animal.getPosition();
            if (map.isOnLeftTop(position)) {
                leftTopMove(position, animal);
            } else if (map.isOnRightTop(position)) {
                rightTopMove(position, animal);
            } else if (map.isOnLeftBottom(position)) {
                leftBottomMove(position, animal);
            } else if (map.isOnRightBottom(position)) {
                rightBottomMove(position, animal);
            } else if (map.isOnTopBorder(position)) {
                topBorderMove(position, animal);
            } else if (map.isOnBottomBorder(position)) {
                bottomBorderMove(animal, position);
            } else if (map.isOnLeftBorder(position)) {
                leftBorderMove(animal, position);
            } else if (map.isOnRightBorder(position)) {
                rightBorderMove(animal, position);
            } else {
                normalMove(animal, position);
            }
        }
    }

    /**
     * Define animal's normal random move.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void normalMove(Animal animal, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.25) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.25 && percent <= 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.50 && percent <= 0.75) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on right border of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void rightBorderMove(Animal animal, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.33) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.33 && percent <= 0.66) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.67) {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on left border of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void leftBorderMove(Animal animal, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.33) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.33 && percent <= 0.66) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        } else if (percent > 0.67) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on bottom border of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void bottomBorderMove(Animal animal, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.33) {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.33 && percent <= 0.66) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.67) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on top border of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void topBorderMove(Intersection position, Animal animal) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.33) {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.33 && percent <= 0.66) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.67) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on right bottom of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void rightBottomMove(Intersection position, Animal animal) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on left bottom of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void leftBottomMove(Intersection position, Animal animal) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on right top of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void rightTopMove(Intersection position, Animal animal) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

    /**
     * Define animal's random move when animal is on left top of the map.
     * 
     * @param animal   The animal to move
     * @param position The position of the animal to move
     */
    private void leftTopMove(Intersection position, Animal animal) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];

        double percent = Math.random();
        if (percent <= 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
        } else if (percent > 0.50) {
            newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
        }

        animal.setPosition(newPosition);
    }

}