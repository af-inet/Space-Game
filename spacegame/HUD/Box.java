package spacegame.HUD;

import java.awt.Color;
import java.awt.Graphics;
import spacegame.Abstract.PointInt;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class Box extends BaseComponent {
    PointInt size;
    Color color;
    
    @Override
    public void draw(Graphics g,HUDComponent h){
        g.setColor(color);
        g.fillRect(h.getPos().x()+pos.x(), h.getPos().y()+pos.y(), size.x(), size.y());
    }
    
    public Box(PointInt pos, PointInt size){
        this.pos=pos;
        this.size=size;
        color=new Color(255,255,255);
    } 
    
    public Box(PointInt pos, PointInt size, Color color){
        this.pos=pos;
        this.size=size;
        this.color=color;
    }
    
    public PointInt getSize(){return size;}
    public Color getColor(){return color;}
    
    public void setSize(PointInt size){this.size=size;}
    public void setColor(Color color){this.color=color;}
}
