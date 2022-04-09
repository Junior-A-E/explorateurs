package process;

import java.util.ArrayList;

import configuration.GameConfiguration;
import element.Animal;
import element.Explorer;
import element.Treasure;
import map.Intersection;
import map.Map;
import strategy.EquipmentStrategy;
import strategy.SwordStrategy;
import strategy.ShieldStrategy;

/**
 * This class manages all explorers of the map.
 * Each instance of the class represents a explorer on the map.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo *
 */
public class ExplorerManager extends Thread{

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
    	
    	if(simulation.getStrat() == 0) {
    		doFirstStrategy();
    	}
    	else if (simulation.getStrat() == 1) {
    		doSecondStrategy();
    	}
    	else {
    		doThirdStrategy();
    	}
    }
    
    /**
     * Run the equipment strategy.
     */
    private void doFirstStrategy() {
		while(!finish && running) {
    		if(this.treasure == null) {
    			this.treasure = simulation.getNextTreasure();
    			System.out.println(treasure);
    			treasure.setTake(true);
    		}
    		
    		while (!finish && running) {
    			GameUtility.unitTime();
    			Intersection position = explorer.getPosition();
    			Intersection newPosition = equipmentStrategy.call(position,treasure);
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
		while(!finish && running) {
			if(this.treasure == null) {
    			this.treasure = simulation.getNextTreasure();
    			System.out.println(treasure);
    			treasure.setTake(true);
    		}
//			if(this.animal == null) {
//    			this.animal = simulation.getNextAnimal();
//    			System.out.println(animal);
//    			animal.setTake(true);
//    		}
    		
    		while (!finish && running) {
    			
    			GameUtility.unitTime();
    			Intersection position = explorer.getPosition(); 
    			Intersection newPosition = new Intersection(1, 1);
//    			if(explorer.getFree()==1) {
//    				//while(simulation.getTreasures().size()>0){
//    					System.out.println(explorer+"is freeNFGVOISE;BGVUIGOPRGBEWRGBI;EWTGTQWEUBOTGW8E9;ATQWEAGIUL;EW79SI");
//    					newPosition = strategy2.call(position,simulation.getTreasures().get(0));
//    				//}
//    				
//    				
//    			}
    			//else {
    				newPosition = swordStrategy.call(position,treasure);
    			//}
    			
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
		while(!finish && running) {
    		if(this.treasure == null) {
    			this.treasure = simulation.getNextTreasure();
    			System.out.println(treasure);
    			treasure.setTake(true);
    		}
    		
    		while (!finish && running) {
    			GameUtility.unitTime();
    			Intersection position = explorer.getPosition();
    			Intersection newPosition = shieldStrategy.call(position,treasure);
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
