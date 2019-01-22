import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class gameWindow extends JFrame{
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    public int scoreTally = 0;
   gameWindow()
   {
        SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        buildWindow();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
   }

   public void buildWindow()
   {
       this.add(spacePanel());
       this.add(pipePanel());
   }
   private JPanel spacePanel()
   {
       JPanel p = new JPanel();
       p.setSize ( 75, SCREEN_HEIGHT);
       return p;
   }
   private JPanel pipePanel()
   {
       JPanel p = new JPanel();
       p.setSize(75, SCREEN_HEIGHT);
       p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
           topPipe t = new topPipe();
           bottomPipe b = new bottomPipe();
           scoreBox s = new scoreBox();
           gameObjects.add(t);
           gameObjects.add(s);
           gameObjects.add(b);
           Box box = Box.createVerticalBox();
           p.add(t.getImage());
       (t.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);
           p.add(s.getImage());
       (s.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);
           p.add(t.getImage());
       (b.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);
           //p.add(box);
        return p;
   }



    public void GameOver() {
        JFrame end = new JFrame();
        JLabel score = new JLabel();
        score.setText("Score: "  + scoreTally);
        end.setSize(250,250);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setVisible(true);
    }
    public void scoreUp() { scoreTally++; }
    public boolean collision(GameObject g, GameObject gg) {
        boolean intersects = false;
        if ((g.getImage().getBounds()).intersects((gg.getImage().getBounds())) && gg.getClass() == topPipe.class) {     // checks to see if the two labels intersect
            intersects = true;
            GameOver();
        } else if ((g.getImage().getBounds()).intersects((gg.getImage().getBounds())) && gg.getClass() == bottomPipe.class) {
            intersects = true;
            GameOver();
        } else if ((g.getImage().getBounds()).intersects((gg.getImage().getBounds())) && gg.getClass() == scoreBox.class) {
            intersects = true;
            scoreUp();
        }
        return intersects;
    }
    public static void main(String[] args) {
        gameWindow g = new gameWindow();
        gameObjects.add(new bird(1));//gameObject


    }

}
