package spacegame.Abstract;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class PointFloat {
    public float x, y;

    public PointFloat() {
        this.x = 0;
        this.y = 0;
        
    }

    public PointFloat(float x, float y) {
        this();
        this.x = x;
        this.y = y;
    }

    public void set(PointFloat p){
        this.x = p.x();
        this.y = p.y();
    }
    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public void x(float x) {
        this.x = x;
    }

    public void y(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + Float.toString(x) + "," + Float.toString(y) + ")";
    }
}