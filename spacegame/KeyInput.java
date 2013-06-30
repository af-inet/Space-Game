package spacegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class KeyInput implements KeyListener {

    int w, s, a, d, shift;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode;
        int bool = 1;
        keyCode = e.getKeyCode();
        
        if(keyCode == e.VK_W) w = bool;
        if(keyCode == e.VK_A) a = bool;
        if(keyCode == e.VK_S) s = bool;
        if(keyCode == e.VK_D) d = bool;
        if(keyCode == e.VK_SHIFT) shift = bool;
    }

    public int[] getKeys() {
        // Current Key Array is:
        // w,s,a,d,shift
        int[] keyArray = new int[5];
        keyArray[0] = w;
        keyArray[1] = s;
        keyArray[2] = a;
        keyArray[3] = d;
        keyArray[4] = shift;
        return keyArray;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode;
        int bool = 0;
        keyCode = e.getKeyCode();
        
        if(keyCode == e.VK_W) w = bool;
        if(keyCode == e.VK_A) a = bool;
        if(keyCode == e.VK_S) s = bool;
        if(keyCode == e.VK_D) d = bool;
        if(keyCode == e.VK_SHIFT) shift = bool;
    }
}
