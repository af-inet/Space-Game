package spacegame.Entity;

import java.awt.Color;
import java.awt.Image;
import spacegame.Abstract.PointFloat;
import spacegame.Game;
import spacegame.Screen;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class Entity {

    public PointFloat pos;
    public PointFloat size;
    public PointFloat dir;
    public Boolean exist;
    public Boolean solid = false;
    public Boolean rotates = false;
    public Boolean destructable = false;
    public float angle=0;
    public int ImageID;
    public int shape;
    public float delta;
    public Color color = new Color(255, 255, 255);
    
    public void tick(Game game) {
        // Update code would go in here
    }
    
    public void draw(Game game,Screen screen){
        float x,y;
        Image img;
        x= +game.width / 2 - game.offset.x() + this.getX() * game.scale;
        y= +game.height / 2 - game.offset.y() + this.getY() * game.scale;
        if(!(x<game.width&&y<game.height&&x>0&&y>0)){return;} // if the entity is not within the screen bounds, skip drawing it.

        if (getImgID() > -1) {
            img = screen.rotate(screen.images[this.getImgID()],(int)this.getAngle());
            screen.dbImage.getGraphics().drawImage(img,
            (int) x * game.scale,
            (int) y * game.scale,
            (int) this.getSize().x() * game.scale,
            (int) this.getSize().y() * game.scale,
            screen.frm);
        }else{
            screen.dbImage.getGraphics().setColor(color);
            screen.dbImage.getGraphics().fillRect(
            (int) x * game.scale,
            (int) y * game.scale,
            (int) this.getSize().x() * game.scale,
            (int) this.getSize().y() * game.scale
            );
        }
    }

    public Entity() {
        //Make sure nothing is null (I don't like NPEs, leave me alone)
        shape = 0;
        exist = true;
        pos = new PointFloat(0,0);
        size = new PointFloat(0,0);
        ImageID = -1;
    }

    public Entity(PointFloat p) {
        shape = 0;
        exist = true;
        this.pos = p;
        size = new PointFloat(0,0);
        ImageID = -1;
    }

    public Entity(PointFloat p, PointFloat s) {
        shape = 0;
        exist = true;
        this.pos = p;
        this.size = s;
        ImageID = -1;
    }

    public int getImgID() {
        return ImageID;
    }

    public float getX() {
        return this.pos.x;
    }

    public float getY() {
        return this.pos.y;
    }

    public Boolean solid() {
        return solid;
    }

    public void setPos(float x, float y) {
        this.pos.x(x);
        this.pos.y(y);
    }

    public void setPos(PointFloat p) {
        this.pos.x(p.x());
        this.pos.y(p.y());
    }

    public void setSize(PointFloat s) {
        this.size.x(s.x());
        this.size.y(s.y());
    }

    public void solid(Boolean bool) {
        this.solid = bool;
    }

    public void setImgID(int ImgID) {
        this.ImageID = ImgID;
    }

    public int getShape() {
        return shape;
    }

    public PointFloat getSize() {
        return size;
    }

    public PointFloat getPos() {
        return new PointFloat(this.pos.x, this.pos.y);
    }

    public Boolean exist() {
        return exist;
    }

    public void exist(Boolean bool) {
        this.exist = bool;
    }

    public PointFloat center(){
        return new PointFloat(this.pos.x() + (this.size.x()/2),this.pos.y() + (this.size.y()/2));
    }
    
    public float dist(Entity e1, Entity e2, Boolean center) {
        float x1, y1, x2, y2;
        
        if(center==false){
            x1 = e1.getX();
            y1 = e1.getY();
            x2 = e2.getX();
            y2 = e2.getY();
        }else{
            x1 = e1.getX() + e1.getSize().x()/2;
            y1 = e1.getY() + e1.getSize().y()/2;
            x2 = e2.getX() + e2.getSize().x()/2;
            y2 = e2.getY() + e2.getSize().y()/2;  
        }
        
        return (float) Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    public float dist(PointFloat p1, PointFloat p2) {
        float x1, y1, x2, y2;
        x1 = p1.x();
        y1 = p1.y();
        x2 = p2.x();
        y2 = p2.y();
        return (float) Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    public Boolean collide(Entity ent) {
        Boolean bool = false;
        
        if(shape==0){
            float x1, y1, x2, y2;
            x1 = pos.x();
            y1 = pos.y();
            x2 = ent.getPos().x();
            y2 = ent.getPos().y();
            if (x1 > x2 && x1 < x2 + ent.getSize().x() && y1 > y2 && y1 < y2 + ent.getSize().y()) {
                bool = true;
            }
            if (x1 + size.x() > x2 && x1 + size.x() < x2 + ent.getSize().x() && y1 > y2 && y1 < y2 + ent.getSize().y()) {
                bool = true;
            }
            if (x1 > x2 && x1 < x2 + ent.getSize().x() && y1 + size.y() > y2 && y1 + size.y() < y2 + ent.getSize().y()) {
                bool = true;
            }
            if (x1 + size.x() > x2 && x1 + size.x() < x2 + ent.getSize().x() && y1 + size.y() > y2 && y1 + size.y() < y2 + ent.getSize().y()) {
                bool = true;
            }
        }
        
        if(shape==1){
            if(dist(this,ent,true)<c(size.x(),size.y())){
                bool=true;
            }
        }
        
        return bool;
    }
    
    public float c(float a, float b){
        return (float)Math.sqrt((a*a) + (b*b));
    }
    
    @Override
    public String toString() {
        String str = "";
        str += "Location ";
        str += Float.toString(pos.x());
        str += ",";
        str += Float.toString(pos.y());
        return str;
    }

    public Color getColor() {
        return color;
    }
    
    public PointFloat getDirection(){
        return dir;
    }
    
    public float getAngle() {
        return angle;
    }
    
    public Boolean destructable(){
        return destructable;
    }
}
