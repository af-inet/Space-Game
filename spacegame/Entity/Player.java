package spacegame.Entity;

import spacegame.Abstract.PointFloat;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Player extends Entity {

    int health=100;
    float speed;
    int[] keys;
    PointFloat mousePos = new PointFloat();
    PointFloat vel = new PointFloat();
    
    public Player(PointFloat pos, PointFloat size) {
        super(pos, size);
        dir = new PointFloat();
        speed = 1f;
        solid(true);
        ImageID = 1;
        shape=1;
        rotates=true;
    }

    @Override
    public void tick(Game game) {
        this.keys = game.input.getKeys();
        this.mousePos = game.input.getMouse();

        setDirection(game);
        handleInput();
        move();
        if(1!=keys[4]){
            applyDrag();
        }
        //boundBox(10000,10000);
        //boundBox(game.getWidth(),game.getHeight());
    }

    
    public void setDirection(Game game) {
        float dirX, dirY;
        float d;

        d = dist(mousePos, new PointFloat(game.width / 2 + (game.ply.size.x()) / 2, game.height / 2 + (game.ply.size.y()) / 2));
        //d=d*2;

        if (d > 0) {
            dirX = (mousePos.x() - (game.width / 2 + (game.ply.size.x() / 2))) / d;
            dirY = (mousePos.y() - (game.height / 2 + (game.ply.size.y() / 2))) / d;
        } else {
            dirX = 0;
            dirY = 0;
        }

        dir = new PointFloat(dirX, dirY);
        angle = (int) (360 + 90 + Math.toDegrees(Math.atan2((double) dir.y * d, (double) dir.x * d)) % 360);
    }

    public void applyDrag() {
        vel.y(vel.y() / 1.05f);
        vel.x(vel.x() / 1.05f);

        if (Math.abs(vel.y()) < 0.1f && Math.abs(vel.y()) != 0) {
            vel.y(0);
        }
        if (Math.abs(vel.x()) < 0.1f && Math.abs(vel.x()) != 0) {
            vel.x(0);
        }
    }

    public void boundBox(int x, int y) {
        if (pos.x() < 0) {
            pos.x(x);
        }
        if (pos.x() > x) {
            pos.x(0);
        }
        if (pos.y() < 0) {
            pos.y(y);
        }
        if (pos.y() > y) {
            pos.y(0);
        }
    }

    public void handleInput() {
        vel = new PointFloat(vel.x() + (dir.x() * speed * keys[0]), vel.y() + (dir.y() * speed * keys[0]));
        vel = new PointFloat(vel.x() - (dir.x() * speed/2 * keys[1]), vel.y() - (dir.y() * speed/2 * keys[1]));
    }

    public void move() {
        this.pos = new PointFloat(pos.x() + vel.x(), pos.y() + vel.y());
        //System.out.println(vel.toString());
    }
    
    public void hurt(int dmg){
        if(health-dmg>0){
            health = health - dmg;
        }else{
            health = 0;
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int health){
        this.health=health;
    }

}
