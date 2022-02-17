package element;

import map.Intersection;

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
