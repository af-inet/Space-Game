package spacegame.Abstract;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacegame.App;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Time {

    public long elapsedTime;
    public long delta;
    public long lastTime;

    public Time() {
        delta = 0;
        lastTime = 0;
    }

    public void update() {
        compensate();
        lastTime = elapsedTime;
        elapsedTime = System.nanoTime();
        delta = elapsedTime - lastTime;
    }

    public long getFPS() {
        return TimeUnit.NANOSECONDS.toSeconds(delta * 1000);
    }

    public float getDelta() {
        float d;
        d = delta;
        d /= 1000000;
        return (float) d;
    }
    
    public void compensate(){
        if(getDelta()<30){
            sleep((int)(30-getDelta()));
        }
    }
    
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
