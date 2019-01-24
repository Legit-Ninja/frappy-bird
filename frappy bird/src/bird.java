import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;



/** bird Class
 * the bird object is the most complex of our objects
 * it has a position, (x,y) that is changed based on user key inputs (keyPressed) and a velocity.
 */
class bird extends GameObject implements KeyListener
{
    int x, y;

    /**
     * bird() sets the image we want with a border so the user can see the bounds, sets the initial velocity, and sets the initial position.
     */
    bird()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();      //makes addedLabel ans stores it
        try {
            URL url = getClass().getResource("/resources/Bird_norm.png");
            Image img = toolkit.getImage(url);
            img = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
            ImageIcon i = new ImageIcon(img);
            JLabel l = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            l.setBorder(border);
            l.setIcon(i);
            image = l;
        } catch (Exception ex) {}
        vy = 9.0;
        vx = 9.0;  // how fast is this
        x = 75; y = 300;
    }

    /**
     * The ASCII KeyCode for the UP arrow is 40, so if the user presses that key, the velocity is set to positive 10.
     * Else, if they press the DOWN arrow (ASCII 38) the velocity will be set to negative 10.
     * @param k KeyEvent check
     */
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == 40) {
            vy = 10;
            //move();
        } else if (k.getKeyCode() == 38){
            vy = -10;//is this a good number?
            // move();
        }
    }
    public void keyReleased(KeyEvent k) {}
    public void keyTyped (KeyEvent k) {}

    /**
     * Changes the location of the bird object based on the bird's current x and the bird's velocity
     */
    public void move()
    {
        (this.getImage()).setLocation(x += (int)vx, y += (int)vy);
    }

}
