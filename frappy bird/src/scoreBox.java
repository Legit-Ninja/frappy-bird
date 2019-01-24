import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * the scoreBox is the simplest of our objects, it is an invisible label.
 * When the bird makes contact with the scoreBox, the scoreTally variable goes up.
 * in scoreBox() we set the image with a border.
 */
class scoreBox extends GameObject
{
    scoreBox() {
        URL scoreBoxURL;
        try {
           scoreBoxURL = getClass().getResource("/resources/white.png");
            ImageIcon i = new ImageIcon(scoreBoxURL);
            JLabel l = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            l.setBorder(border);
            l.setIcon(i);
            image = l;
        } catch (Exception ex) {
        }
        image.setVisible(true);
    }
}
