import javax.swing.*;
import java.awt.event.*;

public class GameObject {

    protected JLabel image;
    protected double vx, vy;

    GameObject()
    {
        image = new JLabel();   //very important data field
        vx = 0.0;
        vy = 0.0;
    }
    public JLabel getImage() { return image;}
    public boolean collision(GameObject  g) {
        boolean contains = false;
        if ((g.getImage().getBounds()).contains((this.getImage().getBounds()))) ;       // checks to see if the two labels intersect
        {
            contains = true;
        }
        return contains;
    }

}
class bird extends GameObject implements KeyListener
{

    bird(JLabel bird, int difficulty )
    {
        image = bird;
        vy = difficulty;
        vx = 1;  // how fast is this
    }
    public void keyPressed( KeyEvent k) {}      //need to implement at some point, maybe call move()?
    public void keyReleased(KeyEvent k_) {}
    public void keyTyped (KeyEvent k) {}
    public void move( ) {} //need to implement later on

}

class scoreBox extends GameObject
{
    scoreBox(JLabel l) {
        image = l;
        image.setVisible(false);
    }
}


