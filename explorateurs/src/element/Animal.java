package element;

import map.Intersection;

/**
 * The class to define the animals.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 * @author Nathan Chriqui
 */
public class Animal extends MapElement {
	private boolean take = false;
	
    public boolean isTake() {
		return take;
	}

	public void setTake(boolean take) {
		this.take = take;
	}

    public Animal(Intersection position) {
        super(position);
        // TODO Auto-generated constructor stub
    }
}
