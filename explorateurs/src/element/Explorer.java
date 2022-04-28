package element;

import map.Intersection;

/**
 * The class to define the explorers.
 * 
 * @author Mathis Da Cruz
 * @author Junior Afatchawo
 * @author Nathan Chriqui
 */
public class Explorer extends MapElement {
	
	private int life = 100;
	private int free = 0;
	private int shield = 0;
	private int sword = 0;

	public Explorer(Intersection position) {
        super(position);
    }
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getSword() {
		return sword;
	}

	public void setSword(int sword) {
		this.sword = sword;
	}	
}
