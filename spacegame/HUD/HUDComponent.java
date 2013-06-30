package spacegame.HUD;

import java.awt.Graphics;
import java.util.ArrayList;
import spacegame.Abstract.PointInt;
import spacegame.Game;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class HUDComponent {
    PointInt pos;
    public ArrayList<BaseComponent> componentArray = new ArrayList<>();
    
    public void draw(Graphics g){
        
    }
    
    public void update(Game game){
        
    }
    
    public HUDComponent(PointInt pos){
        this.pos=pos;
    }
    
    public ArrayList<BaseComponent> getComponentArray(){
        return componentArray;
    }
    
    public void addComponent(BaseComponent c){
        componentArray.add(c);
    }
    
    public PointInt getPos(){return pos;}
    public void setPos(PointInt pos){this.pos=pos;}
}
