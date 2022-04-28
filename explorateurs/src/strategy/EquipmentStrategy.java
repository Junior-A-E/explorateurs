package strategy;

import configuration.GameConfiguration;
import element.Explorer;
import element.Treasure;
import gui.MainGUI;
import map.Intersection;
import map.Map;
import process.Simulation;

/**
 * This class define all restrictions and movement for the Equipement Strategy.
 * 
 * @author Mathis Da Cruz
 */
public class EquipmentStrategy extends Strategy {

    private Explorer explorer;
    private Simulation simulation;

    public EquipmentStrategy(Map map, Explorer explorer, Simulation simulation) {
        this.explorer = explorer;
        this.simulation = simulation;
    }

    /**
     * Remove or add, treasure depend of the explorer's position.
     * 
     * @see Simulation belongTo(Intersection,ArrayList<Intersection>)
     * @param position Explorer's position
     * @param treasure
     * @return newPosition The new explorer's position
     */
    public Intersection call(Intersection position, Treasure treasure) {
        Intersection newPosition = moveExplorer(position, treasure);
        if (simulation.belongTo(position, simulation.getTreasuresIntersection())) {
            simulation.getTreasuresIntersection().remove(position);
            simulation.getTreasures().remove(treasure);
            MainGUI.valueGold += 50;
        }
        if (simulation.belongTo(position, simulation.getAnimalsIntersection())) {
            simulation.getTreasures().add(treasure);
            simulation.getExplorers().remove(explorer);
        }
        return newPosition;

    }

    /**
     * Compare explorer's position and treasure's position to decide in which
     * orientation explorer move.
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

        return new Intersection(newAbscisse, newOrdonnee);
    }

}
