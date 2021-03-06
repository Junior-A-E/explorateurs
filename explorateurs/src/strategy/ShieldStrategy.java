package strategy;

import configuration.GameConfiguration;
import element.Explorer;
import element.Treasure;
import gui.MainGUI;
import map.Intersection;
import map.Map;
import process.Simulation;

/**
 * This class define all restrictions and movement for the Shield Strategy.
 * 
 * @author Mathis Da Cruz
 */
public class ShieldStrategy {

    private Map map;
    private Explorer explorer;
    private Simulation simulation;

    public ShieldStrategy(Map map, Explorer explorer, Simulation simulation) {
        this.map = map;
        this.explorer = explorer;
        this.simulation = simulation;
    }

    /**
     * Remove or add, treasure or explorer's hp depend of the explorer's position.
     * 
     * @see Simulation belongTo(Intersection,ArrayList<Intersection>)
     * @param position Explorer's position
     * @param treasure
     * @return newPosition The new explorer's position
     */
    public Intersection call(Intersection position, Treasure treasure) {

        Intersection newPosition = moveExplorer(position, treasure);

        if (simulation.belongTo(newPosition, simulation.getAnimalsIntersection())) {

            explorer.setLife(explorer.getLife() - 25);
            if (explorer.getLife() == 0) {
                simulation.getTreasures().add(treasure);
                simulation.getExplorers().remove(explorer);
                System.out.println("");
            }
        }
        if (simulation.belongTo(newPosition, simulation.getTreasuresIntersection())) {
            simulation.getTreasuresIntersection().remove(newPosition);
            simulation.getTreasures().remove(treasure);
            MainGUI.valueGold += 50;
            explorer.setFree(1);
        }
        

        return newPosition;
    }

    /**
     * Compare explorer's position and treasure's position to decide in which
     * orientation explorer move with random moves.
     * 
     * @param position Explorer's position
     * @param treasure
     * @return newPosition The new explorer's position
     */
    private Intersection moveExplorer(Intersection position, Treasure treasure) {
        Intersection treasurePosition = treasure.getPosition();
        int newAbscisse = treasurePosition.getAbscisse();
        int newOrdonnee = treasurePosition.getOrdonnee();
        if (position.getAbscisse() > treasurePosition.getAbscisse()) {
            newAbscisse = position.getAbscisse() - GameConfiguration.BLOCK_SIZE;
        } else if (position.getAbscisse() < treasurePosition.getAbscisse()) {
            newAbscisse = position.getAbscisse() + GameConfiguration.BLOCK_SIZE;
        }

        if (position.getOrdonnee() > treasurePosition.getOrdonnee()) {
            newOrdonnee = position.getOrdonnee() - GameConfiguration.BLOCK_SIZE;
        } else if (position.getOrdonnee() < treasurePosition.getOrdonnee()) {
            newOrdonnee = position.getOrdonnee() + GameConfiguration.BLOCK_SIZE;
        }

        Intersection result = new Intersection(newAbscisse, newOrdonnee);
        while (simulation.belongTo(result, simulation.getMountainsIntersection())) {
            double percent = Math.random();
            if (percent <= 0.25) {
                result = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.25 && percent <= 0.50) {
                result = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.50 && percent <= 0.75) {
                result = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else {
                result = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            }
        }

        return result;
    }
}
