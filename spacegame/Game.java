package spacegame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacegame.Abstract.PointFloat;
import spacegame.Abstract.PointInt;
import spacegame.Entity.Asteroid;
import spacegame.Entity.Entity;
import spacegame.Entity.Player;
import spacegame.Entity.Weapon.Weapon;
import spacegame.HUD.AmmoBar;
import spacegame.HUD.HUDComponent;
import spacegame.HUD.HealthBar;

// Author David Hargat
// Email: DavidHargat@yahoo.com
public class Game {

    // The Declaration
    public ArrayList<Entity> EntityArray = new ArrayList<>();
    public ArrayList<HUDComponent> HUDArray = new ArrayList<>();
    public ArrayList<Entity> EntityToAddArray = new ArrayList<>();
    public float delta;
    public int toSpawn = 0;
    public int width;
    public int height;
    public int bFrame = 0;
    public int nextFrame = 0;
    public int maxFrames = 3;
    public PointFloat offset = new PointFloat();
    public Input input;
    public int scale = 1;
    
    //Two Arbitrary objects.
    public Player ply;
    public Weapon wep;
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        ply = new Player(new PointFloat(5000, 5000), new PointFloat(100, 100));
        
        wep = new Weapon(ply);

        EntityArray.add(ply);
        EntityArray.add(wep);
        HealthBar h = new HealthBar(new PointInt(32,height-128));
        AmmoBar a = new AmmoBar(new PointInt(32,height-64));
        HUDArray.add(h);
        HUDArray.add(a);
    }

    public void update(float delta) {
        this.delta = delta;
        offset = ply.getPos();

        ply.tick(this);
        wep.tick(this);

        for (Entity ent : EntityArray) {
            if (ent != ply && ent != wep) {
                if (ent.exist()) {
                    ent.tick(this);
                }
            }
        }

        cleanUp(EntityArray);

        for (Entity ent : EntityToAddArray) {
            EntityArray.add(ent);
        }

        EntityToAddArray.clear();
    }

    public void input(Input input) {
        this.input = input;
    }

    public void init() {
        for (int i = 0; i <= 100; i++) {
            Asteroid e = new Asteroid(new PointFloat((width / 2), (height / 2)), new PointFloat(32, 32), 0.1f);
            spawnEntity(e);
        }
    }

    public void spawnEntity(Entity ent) {
        EntityToAddArray.add(ent);
    }

    public ArrayList<Entity> getEntityArray() {
        return EntityArray;
    }

    public ArrayList<HUDComponent> getHUDArray() {
        return HUDArray;
    }
        
    public void cleanUp(ArrayList<Entity> array) {
        for (Iterator<Entity> iter = array.iterator(); iter.hasNext();) {
            Entity e = iter.next();
            if (!e.exist() && e != ply) {
                iter.remove();
            }
        }
    }

    public float getDelta() {
        return delta;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean within(Entity ent) {
        Boolean bool;
        float x1, y1;
        x1 = ent.getPos().x();
        y1 = ent.getPos().y();
        if (x1 > 0 && x1 < 0 + width - ent.getSize().x() && y1 > 0 && y1 < 0 + height - ent.getSize().y()) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }
}
