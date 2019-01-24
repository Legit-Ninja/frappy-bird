import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * the bottomPipe location is set based on the location of the scoreBox height and the topPipe's height.
 * bottomPipe() reads in a image and sets the image to the proper size of the JLabel in our gameWindow
 */
class bottomPipe extends GameObject {
    private URL bottomPipe;
    private ImageIcon bottomPipeIcon;
    private JLabel bottomPipeLabel;

    bottomPipe() {
        try {
            bottomPipe = getClass().getResource("/resources/green.jpg");
            bottomPipeIcon = new ImageIcon(bottomPipe);
            bottomPipeLabel = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            bottomPipeLabel.setBorder(border);
            bottomPipeLabel.setIcon(bottomPipeIcon);
            image = bottomPipeLabel;
        } catch (Exception ex) { ex.printStackTrace(); }
        image.setVisible(true);
    }
}
