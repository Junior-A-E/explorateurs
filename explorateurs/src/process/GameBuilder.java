package process;

import configuration.GameConfiguration;
import map.Map;

/**
 * Design pattern builder to build the map.
 * 
 * @author Junior Afatchawo
 */
public class GameBuilder {

    public static Map buildMap() {
        return new Map(GameConfiguration.ABSCISSE_COUNT, GameConfiguration.ORDONNEE_COUNT);
    }

}
