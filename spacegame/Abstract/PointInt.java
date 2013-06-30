package spacegame.Abstract;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class PointInt {
    public int x, y;

    public PointInt() {
        this.x = 0;
        this.y = 0;
    }

    public PointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(PointInt p){
        this.x = p.x();
        this.y = p.y();
    }
    
    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void x(int x) {
        this.x = x;
    }

    public void y(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
    }
}