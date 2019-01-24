import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * the topPipe object is what all the other stagnant GameObject positions are based off of;
 *  the locations of the scoreBoxes and bottomPipes are based on this object's height.
 *  topPipe() reads in a image and sets the image to the proper size of the JLabel in our gameWindow
 */
class topPipe extends GameObject
{
    URL topPipeURL;
    topPipe()
    {
        try {
            topPipeURL = getClass().getResource("/resources/green.jpg");
            ImageIcon i = new ImageIcon(topPipeURL);
            JLabel l = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            l.setBorder(border);
            l.setIcon(i);
            image = l;
        } catch (Exception ex) {}
        topPipeHeight = rand.nextInt(500)+100;
    }
}

