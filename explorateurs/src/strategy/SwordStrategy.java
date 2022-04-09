package strategy;

import configuration.GameConfiguration;
import element.Animal;
import element.Explorer;
import element.Treasure;
import gui.MainGUI;
import map.Intersection;
import map.Map;
import process.Simulation;

/**
 * This class define all restrictions and movement for the Sword Strategy.
 * 
 * @author Mathis Da Cruz
 */
public class SwordStrategy {
	
	private Map map;
	private Explorer explorer;
	private Simulation simulation;
	
    public SwordStrategy(Map map, Explorer explorer, Simulation simulation) {
		this.map = map;
		this.explorer = explorer;
		this.simulation = simulation;
	}
    
    /**
     * Remove or add, treasure, animals or explorer's hp depend of the explorer's position.
     * @see Simulation belongTo(Intersection,ArrayList<Intersection>)
     * @param position Explorer's position
     * @param treasure
     * @return newPosition The new explorer's position
     */
    public Intersection call(Intersection position, Treasure treasure) {
    	System.out.println("NB TRESORS: "+simulation.getTreasures().size());
    	System.out.println("Listes: "+simulation.getTreasures());
        Intersection newPosition = moveExplorer(position,treasure);
        int is_delete = 0;
        Animal delete = new Animal(position);
        if(simulation.belongTo(position,simulation.getAnimalsIntersection())) {
            for(Animal animal: simulation.getAnimals()) {
                if(simulation.equalIntersection(animal.getPosition(), position)) {
                    is_delete = 1;
                    delete = animal;
                }
            }
        }
        if(simulation.belongTo(position,simulation.getTreasuresIntersection())) {       	
    		simulation.getTreasuresIntersection().remove(position);
			simulation.getTreasures().remove(treasure);
			MainGUI.valueGold += 50;
			explorer.setFree(1);	        
				
		}
        
        if(is_delete==1) {
            simulation.getAnimalsIntersection().remove(position);
            simulation.getAnimals().remove(delete);

            System.out.println("LIFE EXPLORER: "+explorer.getLife());
            if(explorer.getLife()==100) {
                explorer.setLife(50);
            }
            else {
                //explorer.setLife(0);
                simulation.getTreasures().add(treasure);
                simulation.getExplorers().remove(explorer);                            
            }
        }
        //System.out.println(simulation.getTreasures());
        return newPosition;
    }
    
    /**
     * Compare explorer's position and treasure's position to decide in which
     * orientation explorer move with random moves.
     * @param position Explorer's position
     * @param treasure
     * @return newPosition The new explorer's position
     */
    private Intersection moveExplorer(Intersection position, Treasure treasure) {
		Intersection treasurePosition = treasure.getPosition();
		int newAbscisse = treasurePosition.getAbscisse();
		int newOrdonnee = treasurePosition.getOrdonnee();
		if(position.getAbscisse() > treasurePosition.getAbscisse()) {
			newAbscisse = position.getAbscisse()-GameConfiguration.BLOCK_SIZE;
		}
		else if(position.getAbscisse() < treasurePosition.getAbscisse()) {
			newAbscisse = position.getAbscisse()+GameConfiguration.BLOCK_SIZE;
		}
		
		if(position.getOrdonnee() > treasurePosition.getOrdonnee()) {
			newOrdonnee = position.getOrdonnee()-GameConfiguration.BLOCK_SIZE;
		}
		else if(position.getOrdonnee() < treasurePosition.getOrdonnee()) {
			newOrdonnee = position.getOrdonnee()+GameConfiguration.BLOCK_SIZE;
		}
		
		Intersection result = new Intersection(newAbscisse,newOrdonnee);
		while(simulation.belongTo(result, simulation.getMountainsIntersection())) {
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
