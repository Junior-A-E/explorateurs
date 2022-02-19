package process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtility {

    public static Image readImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("-- Can not read the image file ! --");
            return null;
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

}
