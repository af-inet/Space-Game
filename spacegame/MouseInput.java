package spacegame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

// Author: David Hargat
// Email: DavidHargat@yahoo.com
public class MouseInput extends MouseAdapter implements MouseMotionListener {

    int x;
    int y;
    Boolean pressed = false;

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==1){pressed = true;}
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==1){pressed = false;}
    }
}
