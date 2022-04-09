package element;

import map.Intersection;

/**
 * The class to define the treasures.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 * @author Nathan Chriqui
 */
public class Treasure extends MapElement {
	private boolean take = false;
	
    public boolean isTake() {
		return take;
	}

	public void setTake(boolean take) {
		this.take = take;
	}

	public Treasure(Intersection position) {
        super(position);
        // TODO Auto-generated constructor stub
	}  
    
}
