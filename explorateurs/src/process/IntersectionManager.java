package process;

import java.util.ArrayList;
import java.util.List;

import configuration.GameConfiguration;
import map.Intersection;
import map.Map;

/**
 * This class represents all elements's intersection (position) of the map.
 * It controls the entry and the exit of explorer for the intersection.
 * 
 * @author Junior Afatchawo
 * @author Mathis Da Cruz
 */
public class IntersectionManager {
	private Intersection position;
	private ExplorerManager occupyingExplorer = null;
	private List<Intersection> intersections = new ArrayList<Intersection>();
	private Map map;
	
	public IntersectionManager(Intersection intersection, Map map) {
		this.position = intersection;
		this.map = map;
		initAroundIntersections();
	}
	
	/**
	 * Initialize all intersections around elements wherever they are on the map.
	 */
	private void initAroundIntersections() {
		if(map.isOnLeftTop(position)) {
			leftTop(position);
		} else if (map.isOnRightTop(position)) {
            rightTop(position);
        } else if (map.isOnLeftBottom(position)) {
            leftBottom(position);
        } else if (map.isOnRightBottom(position)) {
            rightBottom(position);
        } else if (map.isOnTopBorder(position)) {
            topBorder(position);
        } else if (map.isOnBottomBorder(position)) {
            bottomBorder(position);
        } else if (map.isOnLeftBorder(position)) {
            leftBorder(position);
        } else if (map.isOnRightBorder(position)) {
            rightBorder(position);
        } else {
            normal(position);
        }
	}

	/**
	 * Find and add intersections around elements on the right top of the map.
	 * @param explorer's position
	 */
	private void rightTop(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position3 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position3);
	}

    /**
     * Find and add intersections around elements.
     * @param explorer's position
     */
	private void normal(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		Intersection position3 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		Intersection position4 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		intersections.add(position1);
		intersections.add(position3);
		intersections.add(position4);
		intersections.add(position5);
		
	}

    /**
     * Find and add intersections around elements on the right border of the map.
     * @param explorer's position
     */
	private void rightBorder(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		Intersection position3 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		Intersection position5 = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		intersections.add(position1);
		intersections.add(position3);
		intersections.add(position5);
	}
	
    /**
     * Find and add intersections around elements on the left border of the map.
     * @param explorer's position
     */
	private void leftBorder(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		Intersection position3 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		Intersection position5 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		intersections.add(position1);
		intersections.add(position3);
		intersections.add(position5);
	}

	/**
     * Find and add intersections around elements on the bottom border of the map.
     * @param explorer's position
     */
	private void bottomBorder(Intersection position2) {
		Intersection position1 =  map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position3 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position3);
		intersections.add(position5);
	}

	/**
     * Find and add intersections around elements on the top border of the map.
     * @param explorer's position
     */
	private void topBorder(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position3 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position3);
		intersections.add(position5);
	}

	/**
     * Find and add intersections around elements on the right bottom of the map.
     * @param explorer's position
     */
	private void rightBottom(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position5);
	}

	/**
     * Find and add intersections around elements on the left bottom of the map.
     * @param explorer's position
     */
	private void leftBottom(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position5);
	}

	/**
     * Find and add intersections around elements on the left top of the map.
     * @param explorer's position
     */
	private void leftTop(Intersection position2) {
		Intersection position1 = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
		Intersection position5 = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
		intersections.add(position1);
		intersections.add(position5);
	}

    /**
     * Tries to make a explorer enter into the intersection of the map. If the intersection is not free, the explorer should wait.
     * The "synchronized" keyword ensure unique access.
     * 
     * @param explorerManager the explorer asking for entry.
     */
	public synchronized void enter (ExplorerManager explorerManager) {
		if(occupyingExplorer != null) {
		    // The explorer asking for entry should wait until the intersection is freed by its occupying explorer.
			explorerManager.updatePosition(position);
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		// The explorer leaves and frees its previous intersection.
		IntersectionManager previousIntersectionManager = explorerManager.getIntersectionManager();
		previousIntersectionManager.exit();
		
		// The explorer entries into this intersection.
		explorerManager.setIntersectionManager(this);
		occupyingExplorer = explorerManager;
	}
	
    /**
     * Makes the occupying explorer leave and notifies the waiting explorer.
     */
	public synchronized void exit() {
		occupyingExplorer = null;
		
		// Notify the waiting explorer (which can enter into the intersection now).
		notify();
	}
	
	public boolean isFree() {
		return occupyingExplorer == null;
	}
	
	public int getAbscisse() {
		return position.getAbscisse();
	}
	
	public int getOrdonnee() {
		return position.getOrdonnee();
	}
	
	public List<Intersection> getIntersections() {
		return intersections;
	}
}
