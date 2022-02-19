package map;

import java.util.ArrayList;

import configuration.GameConfiguration;
import process.GameUtility;


public class Map {
	
	private Intersection[][] intersections;

	private int AbscisseStart = GameConfiguration.Abscisse_Start;
	private int OrdonneeStart = GameConfiguration.Ordonnee_Start;
	
	private int distance = (GameConfiguration.BLOCK_SIZE *(GameConfiguration.ABSCISSE_COUNT-1)) + GameConfiguration.Abscisse_Start;
	
	private int abscisseCount;
	private int ordonneeCount;
	
	public Map(int abscisseCount , int ordonneeCount) {
		intersections = new Intersection[abscisseCount][ordonneeCount];
		this.abscisseCount = abscisseCount;
		this.ordonneeCount = ordonneeCount;
		
		for(int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
				intersections[abscisseIndex][ordonneeIndex] = new Intersection(AbscisseStart,OrdonneeStart);	
				OrdonneeStart += GameConfiguration.BLOCK_SIZE;
			}
			OrdonneeStart = GameConfiguration.Ordonnee_Start;
			AbscisseStart += GameConfiguration.BLOCK_SIZE;
		}
	}
	
	public int getAbscisseCount() {
		return abscisseCount;
	}

	public void setAbscisseCount(int abscisseCount) {
		this.abscisseCount = abscisseCount;
	}

	public int getOrdonneeCount() {
		return ordonneeCount;
	}

	public void setOrdonneeCount(int ordonneeCount) {
		this.ordonneeCount = ordonneeCount;
	}

	public Intersection[][] getIntersections() {
		return intersections;
	}

	public void setIntersections(Intersection[][] intersections) {
		this.intersections = intersections;
	}

	public int getAbscisseStart() {
		return AbscisseStart;
	}

	public void setAbscisseStart(int abscisseStart) {
		AbscisseStart = abscisseStart;
	}

	public int getOrdonneeStart() {
		return OrdonneeStart;
	}

	public void setOrdonneeStart(int ordonneeStart) {
		OrdonneeStart = ordonneeStart;
	}
	
	public Intersection getIntersection(int abscisse, int ordonnee) {
		return intersections[abscisse][ordonnee];
	}

	public Intersection getElementPosition(int x , int y) {
		int abscisse = (x-GameConfiguration.Abscisse_Start)/ GameConfiguration.BLOCK_SIZE;
		int ordonnee = (y-GameConfiguration.Ordonnee_Start)/ GameConfiguration.BLOCK_SIZE;
		return getIntersection(abscisse, ordonnee);
	}
	
	public boolean isOnLeftTop(Intersection position) {
        return position.getAbscisse() == GameConfiguration.Abscisse_Start && position.getOrdonnee() == GameConfiguration.Ordonnee_Start;
    }

    public boolean isOnRightTop(Intersection position) {
        int abscisse = position.getAbscisse();
        int ordonnee = position.getOrdonnee();
        return abscisse == distance  && ordonnee == GameConfiguration.Ordonnee_Start;
    }
    
    public boolean isOnLeftBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == GameConfiguration.Abscisse_Start &&  y == distance; 
    }

    public boolean isOnRightBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y == distance;
    }

    public boolean isOnTopBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance  && y == GameConfiguration.Ordonnee_Start;
    }

    public boolean isOnBottomBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance  && y == distance;
    }
    
    public boolean isOnLeftBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start == x  && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }
    
    public boolean isOnRightBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }
    
 /*   
    public Intersection getFreeNeighbour(Intersection intersection) {
    	 ArrayList<Intersection> neighbours = new ArrayList<Intersection>();
		int n = 0;
		int x = intersection.getAbscisse();
		int y = intersection.getOrdonnee();
				
		int xl = intersection.getAbscisse()-(GameConfiguration.BLOCK_SIZE);
		int xr = intersection.getAbscisse()+(GameConfiguration.BLOCK_SIZE);
				
		int yu = intersection.getOrdonnee()-(GameConfiguration.BLOCK_SIZE);
		int yd = intersection.getOrdonnee()+(GameConfiguration.BLOCK_SIZE);
				
		if(!isOnLeftBorder(intersection)) {
			Intersection left = getElementPosition(xl, y);
			if(!isOccupied(left)) {
				neighbours.add(left);
				n++;
			}
		}
		
		if(!isOnRightBorder(intersection)) {
			Intersection right = getElementPosition(xr, y); 
			if(!isOccupied(right)) {
				neighbours.add(right);
				n++;
			}
		}
		
		if(!isOnTopBorder(intersection)) {
			Intersection up = getElementPosition(x, yu);
			if(!isOccupied(up)) {
				neighbours.add(up);
				n++;
			}
		}
		
		if(!isOnBottomBorder(intersection)) {
			Intersection down = getElementPosition(x, yd);
			if(!isOccupied(down)) {
				neighbours.add(down);
				n++;
			}
		}	
		Intersection keep = new Intersection(0,0);
		int count = 0;
		int sort = GameUtility.getRandomNumber(count, n);
		for(Intersection i : neighbours) {
			if(count == sort) {
				keep = i;
			}	
			count ++;
		}
		return keep;
		}
*/
}
