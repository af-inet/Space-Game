package spacegame.Entity;

import java.util.Random;
import spacegame.Abstract.PointFloat;
import spacegame.Game;
import spacegame.Screen;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Asteroid extends Projectile {
        
    @Override
    public void tick(Game game) {
        
        if(this.dist(this, game.ply,true)>10000){
            this.exist(false);
            replace(game);
            return;
        }
        
        for (Entity ent : game.EntityArray) {
            if (ent != this) {  
                if (ent.solid()) {
                    if (collide(ent)) {
                        explode(game);
                        replace(game);
                        if(ent.destructable()){ent.exist(false);}
                        if(ent == game.ply){game.ply.hurt(10);}
                    }
                }
            }
        }

        delta = game.getDelta();
        move();
    }
    
    public void explode(Game game){
        this.exist=false;
        game.spawnEntity(new Explosion(this.getPos(), this.size));
    }
    
    public void replace(Game game){
        Asteroid e = new Asteroid(game.ply.getPos(), new PointFloat(32, 32), 0.1f);
        game.spawnEntity(e);
    }

    public Asteroid(PointFloat Position, PointFloat Size, float Speed) {
        super(Position, Size, Speed);
        this.solid = true;
        randomize();
        ImageID = 0;
        rotates=false;
        destructable=true;
        shape=1;
    }

    public final void randomize() {
        Random gen = new Random();
        PointFloat position = new PointFloat(
                this.pos.x()+(gen.nextFloat()*5000)-(gen.nextFloat()*5000), 
                this.pos.y()+(gen.nextFloat()*5000)-(gen.nextFloat()*5000)
                );
        while(!(dist(position,this.getPos())>2000)){
                position = new PointFloat(
                this.pos.x()+(gen.nextFloat()*5000)-(gen.nextFloat()*5000), 
                this.pos.y()+(gen.nextFloat()*5000)-(gen.nextFloat()*5000)
                );  
        }
        PointFloat target = new PointFloat(
                position.x()+(gen.nextFloat() * 10)-(gen.nextFloat() * 10), 
                position.y()+(gen.nextFloat() * 10)-(gen.nextFloat() * 10)
                );
        setPos(position);
        setTarget(target);
    }

    @Override
    public String toString() {
        String str = "";
        str += "Location ";
        str += Float.toString(pos.x());
        str += ",";
        str += Float.toString(pos.y());
        str += " Direction ";
        str += Float.toString(dir.x());
        str += ",";
        str += Float.toString(dir.y());
        return str;
    }
}
