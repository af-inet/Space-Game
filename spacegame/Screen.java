package spacegame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import spacegame.Abstract.PointFloat;
import spacegame.Entity.Entity;
import spacegame.HUD.BaseComponent;
import spacegame.HUD.HUDComponent;
import spacegame.MouseInput;

// Author David Hargat
// Email: DavidHargat@yahoo.com
public class Screen implements Runnable {

    public JFrame frm;
    public Image dbImage;
    public int width;
    public int height;
    public int scale;
    public PointFloat staticOffset;
    public Image[] images;
    Input input;
    KeyInput keyInput = new KeyInput();
    MouseInput mouseInput = new MouseInput();
    boolean done = true;
    ImageLoader imageLoader = new ImageLoader();
    
    public void run() {
    }

    public Input getInput() {
        return new Input(
                new PointFloat(mouseInput.x, mouseInput.y),
                mouseInput.pressed,
                keyInput.getKeys());
    }

    public Screen(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;

        frm = new JFrame();
        Dimension d = new Dimension(width, height);
        frm.setSize(d);
        frm.setTitle("Space Game");
        //frm.setResizable(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
        frm.addKeyListener(keyInput);
        frm.addMouseMotionListener(mouseInput);
        frm.addMouseListener(mouseInput);
        
        dbImage = frm.createImage(width, height);
        staticOffset = new PointFloat((float)((width/2) * (scale-1)), (float)((height/2) * (scale-1)));
        imageLoader.load();
        images = imageLoader.get();
    }

    public void drawImage() {
        frm.getGraphics().drawImage(dbImage, 0, 0, frm);
    }

    public void images(Image[] images) {
        this.images = images;
    }

    public void render(Game game) {
        dbImage = frm.createImage(game.getWidth(), game.getHeight());
        Graphics g = dbImage.getGraphics();

        drawBackground(game, g);

        for (Entity e : game.getEntityArray()) {
            e.draw(game, this);
        }

        for (HUDComponent h : game.getHUDArray()) {
            h.update(game);
            h.draw(g);
        }
        
        g.setColor(Color.white);
        g.drawString((int) game.ply.getPos().x() + ", " + (int) game.ply.getPos().y(), 64, 64);
                    
        drawImage();
    }

    public Image rotate(Image image, int ang) {
        BufferedImage img = (BufferedImage)image;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        //out.getGraphics().fillRect(0,0, img.getWidth(),img.getHeight());
        double rotationRequired = Math.toRadians(ang);
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 2;

        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        op.filter(img, out);
        
        return (Image)out;

    }

    public void setColor(int ID, Graphics g) {
        switch (ID) {
            case 0:
                g.setColor(new Color(255, 255, 255));
                break;
            case 1:
                g.setColor(new Color(255, 0, 0));
                break;
            case 2:
                g.setColor(new Color(0, 255, 0));
                break;
            default:
                g.setColor(new Color(255, 255, 255));
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Please don't look at the code below (I'm sorry)
    
    public void drawBackground(Game game, Graphics g) {
        // I did what I had to do...
        int w = images[2].getWidth(frm) * 1;
        int h = images[2].getHeight(frm) * 1;
        int ix = (int) ((game.offset.x() / 10) / w);
        int iy = (int) ((game.offset.y() / 10) / h);
        int x = (-(int) (game.offset.x() / 10) + (w * ix));
        int y = (-(int) (game.offset.y() / 10) + (h * iy));

        // God forgive me for this messy code.
        g.drawImage(images[2], x, y, w, h, frm); // Center 
        g.drawImage(images[2], x + (w), y, w, h, frm); //right
        g.drawImage(images[2], x - (w), y, w, h, frm); //left
        //inner edge
        g.drawImage(images[2], x, y - (h), w, h, frm); //Up
        g.drawImage(images[2], x, y + (h), w, h, frm); //Down
        g.drawImage(images[2], x + (w), y - (h), w, h, frm); //Upper right
        g.drawImage(images[2], x - (w), y - (h), w, h, frm); //Upper left
        g.drawImage(images[2], x + (w), y + (h), w, h, frm); //Lower Right
        g.drawImage(images[2], x - (w), y + (h), w, h, frm); //Lower Left
        // outer edge
        g.drawImage(images[2], x + (w * 2), y, w, h, frm); //right 2
        g.drawImage(images[2], x - (w * 2), y, w, h, frm); //left 2
        g.drawImage(images[2], x, y - (h * 2), w, h, frm); //Up 2
        g.drawImage(images[2], x, y + (h * 2), w, h, frm); //Down 2 
        g.drawImage(images[2], x + (w * 2), y - (h * 1), w, h, frm); //Upper 1 right 2
        g.drawImage(images[2], x - (w * 2), y - (h * 1), w, h, frm); //Upper 1 left 2
        g.drawImage(images[2], x + (w * 2), y + (h * 1), w, h, frm); //Lower 1 Right 2
        g.drawImage(images[2], x - (w * 2), y + (h * 1), w, h, frm); //Lower 1 Left 2
        g.drawImage(images[2], x + (w * 1), y - (h * 2), w, h, frm); //Upper 2 right 1
        g.drawImage(images[2], x - (w * 1), y - (h * 2), w, h, frm); //Upper 2 left 1
        g.drawImage(images[2], x + (w * 1), y + (h * 2), w, h, frm); //Lower 2 Right 1
        g.drawImage(images[2], x - (w * 1), y + (h * 2), w, h, frm); //Lower 2 Left 1
    }
}
