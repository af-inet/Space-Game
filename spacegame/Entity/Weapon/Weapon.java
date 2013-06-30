package spacegame.Entity.Weapon;

import java.awt.Color;
import spacegame.Abstract.PointFloat;
import spacegame.Entity.Entity;
import spacegame.Entity.Projectile;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class Weapon extends Entity {

    int energy_cost = 30;
    int energy = 100;
    int max_energy = 100;
    
    int cooldown_time = 100;
    float cooldown_timer = 0;
    
    int energy_cooldown_time = 10;
    int energy_cooldown_timer = 0;
    
    
    public Weapon(Entity ent) {
        super(ent.getPos(), new PointFloat(4, 4));
        solid(false);
        color = new Color(0,0,0,0);
        ImageID = -1;
    }

    public void aim(PointFloat target) {
        
    }

    public int charge(){
        /*
        int i = (int)(100*(cooldown_timer/cooldown_time));
        if(i>100){
            return 100;
        }else{
            return i;
        }*/
        return energy;
    }
    
    public void fire(Game game) {
        
    PointFloat tar = new PointFloat(
            game.input.getMouse().x() - game.width / 2 + game.offset.x(), 
            game.input.getMouse().y() - game.height / 2 + game.offset.y());
    
        if(dist(tar,game.ply.center())>64)
        {
            Projectile pew = new Projectile(new PointFloat(10, 10), new PointFloat(16, 16), 0.5f,game.ply);
            pew.setPos(getPos());
            pew.setTarget(tar);
            pew.color = new Color(0, 0, 255);
            pew.solid(true);
            game.spawnEntity(pew);
            energy-=energy_cost;
            cooldown_timer=0;
        }
    }
    
    @Override
    public void tick(Game game) {
        setPos(new PointFloat(
                game.ply.getPos().x + (game.ply.getSize().x / 2)+ (game.ply.getDirection().x()*50), 
                game.ply.getPos().y + (game.ply.getSize().y / 2) + (game.ply.getDirection().y()*50)
                ));
                
        if (cooldown_timer > cooldown_time) {
            if (game.input.pressed && energy > energy_cost) {
                fire(game);
            }
        } else {
            cooldown_timer += game.getDelta();
        }
        
        if (energy_cooldown_timer > energy_cooldown_time && energy < max_energy) {
            energy+=1;
            energy_cooldown_timer=0;
        } else {
            energy_cooldown_timer += game.getDelta();
        } 
    }
}
