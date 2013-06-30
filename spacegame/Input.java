package spacegame;

import spacegame.Abstract.PointFloat;

// Author: David Hargat
// Email: DavidHargat@yahoo.com

public class Input {

    public int[] keys;
    public PointFloat mouse = new PointFloat();
    public Boolean pressed;

    public Input(PointFloat mouse, Boolean pressed, int[] keys) {
        this.mouse = mouse;
        this.keys = keys;
        this.pressed = pressed;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public void setMouse(PointFloat mouse) {
        this.mouse = mouse;
    }

    public int[] getKeys() {
        return keys;
    }

    public PointFloat getMouse() {
        return mouse;
    }
}
