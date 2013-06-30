package spacegame.Entity;

import java.awt.Color;
import spacegame.Abstract.PointFloat;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Explosion extends Entity {

    public int Frame = 0;
    public float Timer = 0;

    public Explosion(PointFloat p, PointFloat s) {
        super(p, s);
        this.exist = true;
        this.ImageID = 4;
        this.solid = false;
        color = new Color(255,0,0);
    }

    @Override
    public void tick(Game game) {
        Timer += game.getDelta();
        float grow = 0.5f;

        if (Timer > 1) {
            Frame += 1;
            Timer = 0;
            PointFloat newSize = new PointFloat(this.size.x() + grow, this.size.y() + grow);
            PointFloat newPos = new PointFloat(this.pos.x() - (grow / 2), this.pos.y() - (grow / 2));
            this.size = newSize;
            this.pos = newPos;
        }

        color = new Color(255, 0, 0, 255 - Frame);

        if (Frame > 200) {
            this.exist = false;
        }

    }
}
