package map;

import configuration.GameConfiguration;

/**
 * This class allows to define all the intersections of the map.
 * 
 * @author Junior Afatchawo
 */
public class Map {

    private Intersection[][] intersections;

    private int AbscisseStart = GameConfiguration.Abscisse_Start;
    private int OrdonneeStart = GameConfiguration.Ordonnee_Start;

    private int distance = (GameConfiguration.BLOCK_SIZE * (GameConfiguration.ABSCISSE_COUNT - 1)) + GameConfiguration.Abscisse_Start;

    private int abscisseCount;
    private int ordonneeCount;

    public Map(int abscisseCount, int ordonneeCount) {
        intersections = new Intersection[abscisseCount][ordonneeCount];
        this.abscisseCount = abscisseCount;
        this.ordonneeCount = ordonneeCount;

        for (int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
            for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
                intersections[abscisseIndex][ordonneeIndex] = new Intersection(AbscisseStart, OrdonneeStart);
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

    public Intersection getElementPosition(int x, int y) {
        int abscisse = (x - GameConfiguration.Abscisse_Start) / GameConfiguration.BLOCK_SIZE;
        int ordonnee = (y - GameConfiguration.Ordonnee_Start) / GameConfiguration.BLOCK_SIZE;
        return getIntersection(abscisse, ordonnee);
    }

    /**
     * Verify that the element position is on the left top of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnLeftTop(Intersection position) {
        return position.getAbscisse() == GameConfiguration.Abscisse_Start && position.getOrdonnee() == GameConfiguration.Ordonnee_Start;
    }

    /**
     * Verify that the element position is on the right top of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnRightTop(Intersection position) {
        int abscisse = position.getAbscisse();
        int ordonnee = position.getOrdonnee();
        return abscisse == distance && ordonnee == GameConfiguration.Ordonnee_Start;
    }

    /**
     * Verify that the element position is on the left bottom of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnLeftBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == GameConfiguration.Abscisse_Start && y == distance;
    }

    /**
     * Verify that the element position is on the right bottom of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnRightBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y == distance;
    }

    /**
     * Verify that the element position is on the top border of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnTopBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance && y == GameConfiguration.Ordonnee_Start;
    }

    /**
     * Verify that the element position is on the bottom border of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnBottomBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start <= x && x <= distance && y == distance;
    }

    /**
     * Verify that the element position is on the left border of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnLeftBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GameConfiguration.Abscisse_Start == x && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }

    /**
     * Verify that the element position is on the right border of the map
     * @param position Element position
     * @return boolean True is yes, false otherwise
     */
    public boolean isOnRightBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y >= GameConfiguration.Ordonnee_Start && y <= distance;
    }
}
