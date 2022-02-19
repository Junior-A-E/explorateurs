package process;

import java.util.ArrayList;

import configuration.GameConfiguration;
import element.Explorer;
import map.Intersection;
import map.Map;

public class ExplorerManager {

    private Map map;
    private Simulation simulation;
    private volatile ArrayList<Intersection> explorersTurnIntersection = new ArrayList<Intersection>();

    public ExplorerManager(Map map, Simulation simulation) {
        super();
        this.map = map;
        this.simulation = simulation;
    }

    protected void moveExplorers() {
        for (Explorer explorer : simulation.getExplorers()) {
            // System.out.println(animal.getPosition().toString());
            Intersection position = explorer.getPosition();
            if (map.isOnLeftTop(position)) {
                leftTopMove(position, explorer);
            } else if (map.isOnRightTop(position)) {
                rightTopMove(position, explorer);
            } else if (map.isOnLeftBottom(position)) {
                leftBottomMove(position, explorer);
            } else if (map.isOnRightBottom(position)) {
                rightBottomMove(position, explorer);
            } else if (map.isOnTopBorder(position)) {
                topBorderMove(position, explorer);
            } else if (map.isOnBottomBorder(position)) {
                bottomBorderMove(explorer, position);
            } else if (map.isOnLeftBorder(position)) {
                leftBorderMove(explorer, position);
            } else if (map.isOnRightBorder(position)) {
                rightBorderMove(explorer, position);
            } else {
                normalMove(explorer, position);
            }
        }
    }

    private void normalMove(Explorer explorer, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.25) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.25 && percent <= 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.50 && percent <= 0.75) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void rightBorderMove(Explorer explorer, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.33) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.33 && percent <= 0.66) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.67) {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void leftBorderMove(Explorer explorer, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.33) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.33 && percent <= 0.66) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            } else if (percent > 0.67) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void bottomBorderMove(Explorer explorer, Intersection position) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.33) {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.33 && percent <= 0.66) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.67) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void topBorderMove(Intersection position, Explorer explorer) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.33) {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.33 && percent <= 0.66) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.67) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void rightBottomMove(Intersection position, Explorer explorer) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void leftBottomMove(Intersection position, Explorer explorer) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() - GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void rightTopMove(Intersection position, Explorer explorer) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse() - GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }

    private void leftTopMove(Intersection position, Explorer explorer) {
        Intersection[][] intersections = map.getIntersections();
        Intersection newPosition = intersections[0][0];
        do {
            double percent = Math.random();
            if (percent <= 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse() + GameConfiguration.BLOCK_SIZE, position.getOrdonnee());
            } else if (percent > 0.50) {
                newPosition = map.getElementPosition(position.getAbscisse(), position.getOrdonnee() + GameConfiguration.BLOCK_SIZE);
            }
        } while (simulation.isForbiddenForExplorers(newPosition));
        explorer.setPosition(newPosition);
    }
}
