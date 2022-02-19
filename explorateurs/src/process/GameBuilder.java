package process;

import configuration.GameConfiguration;
import map.Map;

public class GameBuilder {

    public static Map buildMap() {
        return new Map(GameConfiguration.ABSCISSE_COUNT, GameConfiguration.ORDONNEE_COUNT);
    }

}
