package process;

import element.Explorer;
import element.Treasure;
import map.Intersection;
import map.Map;
import strategy.EquipmentStrategy;
import strategy.SwordStrategy;
import strategy.ShieldStrategy;

/**
 * This class manages all explorers of the map. Each instance of the class
 * represents a explorer on the map.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 */
public class ExplorerManager extends Thread {

    private Simulation simulation;
    private IntersectionManager intersectionManager;
    private Explorer explorer;

    private EquipmentStrategy equipmentStrategy;
    private SwordStrategy swordStrategy;
    private ShieldStrategy shieldStrategy;

    /**
     * The explorer has finished his exploration.
     */
    private boolean finish = false;

    /**
     * The explorer departs from the start point.
     */
    private boolean running = false;
    private Treasure treasure;

    public ExplorerManager(Map map, Simulation simulation, Explorer explorer) {
        super();
        this.simulation = simulation;
        this.explorer = explorer;
        equipmentStrategy = new EquipmentStrategy(map, explorer, simulation);
        swordStrategy = new SwordStrategy(map, explorer, simulation);
        shieldStrategy = new ShieldStrategy(map, explorer, simulation);
    }

    @Override
    public void run() {

        if (simulation.getStrat() == 0) {
            doFirstStrategy();
        } else if (simulation.getStrat() == 1) {
            doSecondStrategy();
        } else {
            doThirdStrategy();
        }
    }

    /**
     * Run the equipment strategy.
     */
    private void doFirstStrategy() {
        while (!finish && running) {
            if (this.treasure == null) {
                this.treasure = simulation.getNextTreasure();
                treasure.setTake(true);
            }

            while (!finish && running) {
                GameUtility.unitTime();
                Intersection position = explorer.getPosition();
                Intersection newPosition = equipmentStrategy.call(position, treasure);
                explorer.setPosition(newPosition);
                IntersectionManager nextIntersectionManager = simulation.getIntersectionManagersByPosition(newPosition);
                nextIntersectionManager.enter(this);
            }
        }
    }

    /**
     * Run the sword strategy.
     */
    private void doSecondStrategy() {
        while (!finish && running) {
            if (this.treasure == null) {
                this.treasure = simulation.getNextTreasure();
                treasure.setTake(true);
            }

            while (!finish && running) {

                GameUtility.unitTime();
                Intersection position = explorer.getPosition();
                Intersection newPosition = new Intersection(1, 1);

                newPosition = swordStrategy.call(position, treasure);

                explorer.setPosition(newPosition);
                IntersectionManager nextIntersectionManager = simulation.getIntersectionManagersByPosition(newPosition);
                nextIntersectionManager.enter(this);
            }
        }

    }

    /**
     * Run the shield strategy.
     */
    private void doThirdStrategy() {
        while (!finish && running) {
            if (this.treasure == null) {
                this.treasure = simulation.getNextTreasure();
                treasure.setTake(true);
            }

            while (!finish && running) {
                GameUtility.unitTime();
                Intersection position = explorer.getPosition();
                Intersection newPosition = shieldStrategy.call(position, treasure);
                explorer.setPosition(newPosition);
                IntersectionManager nextIntersectionManager = simulation.getIntersectionManagersByPosition(newPosition);
                nextIntersectionManager.enter(this);
            }
        }

    }

    public void updatePosition(Intersection position) {
        explorer.setPosition(position);
    }

    public IntersectionManager getIntersectionManager() {
        return intersectionManager;
    }

    public void setIntersectionManager(IntersectionManager intersectionManager2) {
        this.intersectionManager = intersectionManager2;
    }

    public void setRunning(boolean running) {
        this.running = running;

    }

    public void setBlockManager(IntersectionManager intersectionManager) {
        this.intersectionManager = intersectionManager;
    }

    public boolean isRunning() {
        return running;
    }
}
