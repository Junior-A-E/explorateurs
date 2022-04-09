package element;

import map.Intersection;

/** 
 * This abstract class serves to parameter the elements of the map.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 * @author Nathan Chriqui 
 */
public abstract class MapElement {

    private Intersection position;

    public MapElement(Intersection position) {
        this.position = position;
    }

    public Intersection getPosition() {
        return position;
    }

    public void setPosition(Intersection position) {
        this.position = position;
    }

}
