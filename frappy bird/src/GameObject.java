import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * our GameObject class is the parent of all our pipe/box/bird classes
 * it has a JLabel to display what it is and a 0 velocity of the object (which is only used in bird)
 */
public class GameObject {
    public Random rand = new Random(System.currentTimeMillis());
    protected int topPipeHeight;
    protected JLabel image;
    protected double vx, vy;
    protected int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    protected int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * simply makes a new JLabel, sets the velocities to 0, and decides where our pipes are going to be located
     */
    GameObject()
    {
        topPipeHeight = rand.nextInt(300)+400;
        image = new JLabel();
        vx = 0.0;
        vy = 0.0;
    }

    /**
     * Used all the time when drawing to UI
     * @return the desired image
     */
    public JLabel getImage() { return image;}

    /**
     * Allows us to keep the random number so we can set the proper locations for our images without them overlapping.
     */
    public int getTopPipeHeight() {
        return topPipeHeight;
    }

    /**
     * Called to set the location of each column so they are slightly different
     */
    public void resetTopPipeHeight() {
        topPipeHeight = rand.nextInt(200) + 400;
    }
}



