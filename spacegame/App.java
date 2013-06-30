package spacegame;

import java.applet.Applet;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacegame.Abstract.Time;

// Author David Hargat
// Email: DavidHargat@yahoo.com

/* NOTES
 * 6/22/2013
 * Scale does not work yet
 * Resources are embedded within the jar
 * 6/30/2013
 * Working on implementing Applet...
 * 
 */

public class App extends Applet {    
    Boolean running = true;
    Time time = new Time();
    public int width = 1200;
    public int height = width / 16 * 10;
    public static int scale = 1;

    @Override
    public void start(){
        run();
    }
    
    @Override
    public void stop(){
        running=false;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScale() {
        return scale;
    }

    public final void run() {
        Screen screen = new Screen(width, height, scale);
        Game game = new Game(width, height);
        game.init();
        
        while (running) {
            time.update();
            game.input(screen.getInput());
            game.update(time.getDelta());
            screen.render(game);
        }
        
    }

    public static void main(String[] args) {
        App app;
        app = new App();
        app.run();
    }
}
