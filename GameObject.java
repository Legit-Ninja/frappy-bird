import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GameObject {


    protected JLabel image;
    protected double vx, vy;
    protected int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    protected int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    GameObject()
    {
        image = new JLabel();   //very important data field
        vx = 0.0;
        vy = 0.0;
    }
    public void GameOver() {   /*GO IN GUI*/}
    public void scoreUp() { /*GO IN GUI */}
    public JLabel getImage() { return image;}
    public boolean collision(GameObject g) {
        boolean intersects = false;
        if ((g.getImage().getBounds()).intersects((this.getImage().getBounds())) && this.getClass() == topPipe.class) {     // checks to see if the two labels intersect
            intersects = true;
            GameOver();
        }
        else if ((g.getImage().getBounds()).intersects((this.getImage().getBounds())) && this.getClass() == scoreBox.class) {
            intersects = true;
            scoreUp();
        }
        return intersects;
    }

}
class bird extends GameObject implements KeyListener
{

    bird(int difficulty )
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();      //makes addedLabel ans stores it
        try {
            URL imgurl = getClass().getResource("/resources/Bird2-1.png.png");
            Image img = toolkit.getImage(imgurl);
            img = img.getScaledInstance(75, SCREEN_HEIGHT/3, Image.SCALE_SMOOTH);
            ImageIcon i = new ImageIcon(img);
            JLabel l = new JLabel();
            l.setIcon(i);
            image = l;
        } catch (Exception ex) {}
        vy = difficulty;
        vx = 1.0;  // how fast is this
    }
    public void keyPressed( KeyEvent k) {}      //need to implement at some point, maybe call move()?
    public void keyReleased(KeyEvent k_) {}
    public void keyTyped (KeyEvent k) {}
    public void move( ) {} //need to implement later on

}

class scoreBox extends GameObject
{
    scoreBox() {
        URL imgurl;
        Toolkit toolkit = Toolkit.getDefaultToolkit();      //makes addedLabel ans stores it
        try {
            imgurl = getClass().getResource("/resources/pipe_down.png");
            Image img = toolkit.getImage(imgurl);
            img = img.getScaledInstance(75, SCREEN_HEIGHT/3, Image.SCALE_SMOOTH);
            ImageIcon i = new ImageIcon(img);
            JLabel l = new JLabel();
            l.setIcon(i);
            image = l;
        } catch (Exception ex) {}
        image.setVisible(false);
    }
}
class topPipe extends GameObject
{
    URL imgurl;
    topPipe()
        {
            Toolkit toolkit = Toolkit.getDefaultToolkit();      //makes addedLabel ans stores it
            try {
                imgurl = getClass().getResource("/resources/pipe_down.png");
                Image img = toolkit.getImage(imgurl);
                img = img.getScaledInstance(75, SCREEN_HEIGHT/3, Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img);
                JLabel l = new JLabel();
                l.setIcon(i);
                image = l;
            } catch (Exception ex) {}
    }

}
class bottomPipe extends GameObject {
        private URL bottomPipe;
        private Image bottomPipeimg;
        private ImageIcon bottomPipeIcon;
        private JLabel bottomPipeLabel;

    bottomPipe() {

        Toolkit tk = Toolkit.getDefaultToolkit();
        bottomPipe = getClass().getResource("/resources/pipe_up.png");
        bottomPipeimg = tk.getImage(bottomPipe);
        bottomPipeimg = bottomPipeimg.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        bottomPipeIcon = new ImageIcon(bottomPipeimg);
        bottomPipeLabel = new JLabel(bottomPipeIcon);
        image = bottomPipeLabel;
    }
}



