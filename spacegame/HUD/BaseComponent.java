package spacegame.HUD;

import java.awt.Graphics;
import spacegame.Abstract.PointInt;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class BaseComponent {
    public PointInt pos=new PointInt(0,0);
    public Boolean exist=true;
    
    public BaseComponent(){
    }
    
    public BaseComponent(PointInt pos){
        this.pos=pos;
    }
    
    public void draw(Graphics g, HUDComponent h){
        g.drawString("Component Null", h.getPos().x()+pos.x(),h.getPos().y()+pos.y());
    }
    
    public PointInt getPos(){return pos;}
    public void setPos(PointInt pos){this.pos=pos;}
    public Boolean exist(){return exist;}

}
