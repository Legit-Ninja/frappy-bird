import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class gameWindow extends JFrame{
   JPanel pipPanel;
   JPanel spacePanel;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
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
       Toolkit toolkit = Toolkit.getDefaultToolkit();      //makes addedLabel ans stores it
       try {
           URL imgurl = new URL("http://pixelartmaker.com/art/2bcb80ed5003ccd.png");
           Image img = toolkit.getImage(imgurl);
           img = img.getScaledInstance(75, SCREEN_HEIGHT/3, Image.SCALE_SMOOTH);
           ImageIcon i = new ImageIcon(img);
           JLabel l = new JLabel();
           l.setIcon(i);
           topPipe t = new topPipe();
           bottomPipe b = new bottomPipe();
           scoreBox s = new scoreBox();
           gameObjects.add(t);
           gameObjects.add(s);
           gameObjects.add(b);
           p.add(t.getImage());
           p.add(s.getImage());
           p.add(t.getImage());
           p.setLayout(new GridLayout(1,3));


       } catch (Exception ex) {}
        return p;
   }




    public static void main(String[] args) {
        gameWindow g = new gameWindow();

    }

}
