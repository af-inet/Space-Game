package spacegame.Entity;

import spacegame.Abstract.PointFloat;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Projectile extends Entity {
    float speed;

    @Override
    public void tick(Game game) {
        this.delta = game.getDelta();
        if (dist(game.ply, this, true) > 1500) {
            exist(false);
        }
        move();
        rotates=true;
        angle = (int) (360 + 90 + Math.toDegrees(Math.atan2((double) dir.y(), (double) dir.x())) % 360);
    }

    public void move() {
        if (delta < 100f && delta > 0f) {
            pos.x(pos.x() + (dir.x() * speed * delta));
            pos.y(pos.y() + (dir.y() * speed * delta));
        }
    }

    public final void setTarget(PointFloat tar) {
        float d;
        d = dist(this.getPos(), tar);
        dir.x(dir.x+(tar.x() - pos.x) / d);
        dir.y(dir.y+(tar.y() - pos.y) / d);
    }

    public final void setDir(PointFloat dir) {
        this.dir = dir;
    }

    public Projectile() {
        super();
        speed = 1f;
        ImageID = -1;
        dir = new PointFloat(0, 0);
    }

    public Projectile(PointFloat pos, PointFloat size, float speed) {
        super(pos, size);
        this.speed = speed;
        ImageID = 3;
        dir = new PointFloat(0, 0);
        destructable=true;
    }
    
    public Projectile(PointFloat pos, PointFloat size, float speed, Entity ent) {
        super(pos, size);
        this.speed = speed;
        ImageID = 3;
        dir = ent.getDirection();
        destructable=true;
    }
}
