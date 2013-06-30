package spacegame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class ImageLoader {

    Image[] image;

    public void load() {
        image = new Image[5];
        try {
            image[0] = ImageIO.read(this.getClass().getResource("res/img/Asteroid.png"));
            image[1] = ImageIO.read(this.getClass().getResource("res/img/Shuttle.png"));
            image[2] = ImageIO.read(this.getClass().getResource("res/img/Stars.png"));
            image[3] = ImageIO.read(this.getClass().getResource("res/img/Laser.png"));
            image[4] = ImageIO.read(this.getClass().getResource("res/img/Explosion.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image[] get() {
        return image;
    }
}
