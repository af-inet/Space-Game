package spacegame.HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import spacegame.Abstract.PointInt;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class HealthBar extends HUDComponent {
    Box border;
    Box background;
    Box bar;
    Box base;
    Text text;
    
    @Override
    public void draw(Graphics g){
        for (BaseComponent c : getComponentArray()) {
            c.draw(g,this);
        } 
    }
    
    public final void init(){ 
        base = new Box(
            new PointInt(0,-48),
            new PointInt(256,16+48),
            new Color(255,255,255,100)
        );
        
        border = new Box(
            new PointInt(0,0),
            new PointInt(256,16),
            new Color(255,255,255,150)
        );
        
        background = new Box(
            new PointInt(2,2),
            new PointInt(256-4,16-4),
            new Color(255,0,0,150)
        );
        
        bar = new Box(
            new PointInt(2,2),
            new PointInt(256-4,16-4),
            new Color(0,255,0,150)
        );
        
        text = new Text(
            new PointInt(2,-8),
            "Health: 100",
            new Color(0,0,0,150),
            new Font("System", Font.BOLD,40)
        );
        this.addComponent(base);
        this.addComponent(border);
        this.addComponent(background);
        this.addComponent(bar);
        this.addComponent(text);
    }
    
    @Override
    public void update(Game game){
        float v = 252;
        float w = ((float)game.ply.getHealth()/100);
        bar.setSize(new PointInt((int)(v*w),16-4));
        text.set("Health: " +Integer.toString(game.ply.getHealth()));
    }
    
    public HealthBar(PointInt pos){
        super(pos);
        init();
    }
    
    
}
