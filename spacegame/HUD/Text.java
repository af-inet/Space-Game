package spacegame.HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import spacegame.Abstract.PointInt;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class Text extends BaseComponent {
    String str;
    Color color;
    Font font;
    
    public Text(PointInt pos,String str, Color color, Font font){
        this.pos=pos;
        this.str=str;
        this.color=color;
        this.font=font;
    }
    
    public void set(String str){
        this.str=str;
    }
    
    @Override
    public void draw(Graphics g, HUDComponent h){
        g.setColor(color);
        g.setFont(font);
        g.drawString(str, h.getPos().x()+getPos().x(),h.getPos().y()+getPos().y());
    }
}
