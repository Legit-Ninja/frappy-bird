import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static javafx.application.Platform.exit;

public class gameWindow extends JFrame{
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    public static int scoreTally = 0;
    public static bird bird;
    public static int panelNum = 0;
    public static JLayeredPane lpane;
   gameWindow()
   {
       bird = new bird();
       gameObjects.add(bird);
        SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        buildWindow();
        this.addKeyListener(bird);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
   }

   public void buildWindow()
   {
       lpane = new JLayeredPane();
       lpane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
       lpane.setOpaque(true);
       lpane.add(spacePanel());
       lpane.add(pipePanel());
       lpane.add(spacePanel());
       lpane.add(pipePanel());
       bird.getImage().setBounds(75,300,50,50);
       lpane.add(bird.getImage(), JLayeredPane.PALETTE_LAYER);

       this.add(lpane);
       lpane.repaint();
   }
   private JPanel spacePanel()
   {
       JPanel space = new JPanel();
       space.setSize ( 150, SCREEN_HEIGHT);
       space.setBounds(panelNum*150, 0, 300, SCREEN_HEIGHT);
       panelNum += 2;
       return space;
   }
   private JPanel pipePanel()
   {
       JPanel p = new JPanel();
       panelNum++;
       p.setBounds(panelNum*150,0, 150, SCREEN_HEIGHT);
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
           p.add(b.getImage());
       (b.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);

           //p.add(box);
        return p;
   }



    public static void GameOver() {
       JFrame end = new JFrame();      //score window
        JLabel score = new JLabel();
        score.setText("Score: "  + scoreTally);
        end.setSize(250,250);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setVisible(true);
        exit();
   }
    public static void scoreUp() { scoreTally++; }
    public static boolean collide()
    {
        boolean intersects = false;
        for (int i= 0; i < gameObjects.size(); i++) {
            for (int j = 1; j < gameObjects.size(); j++) {

                if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && gameObjects.get(j).getClass() == topPipe.class) {     // checks to see if the two labels intersect
                    intersects = true;

                    //GameOver();
                } else if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && (gameObjects.get(j)).getClass() == bottomPipe.class) {
                    intersects = true;
                    //GameOver();
                    System.out.println("boom");
                } else if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && (gameObjects.get(j)).getClass() == scoreBox.class) {
                    scoreUp();
                }
            }
        }
        return intersects;
    }
    public static void main(String[] args) {
        gameWindow g = new gameWindow();

        System.out.println(gameObjects.get(0));

        Thread animation = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    bird.move();
                    collide();
                    try {
                        Thread.sleep(300);
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }
        });
        animation.start();

        // MAIN GAME LOOOOOOOOOOP
        /*JFrame end = new JFrame();      //score window
        JLabel score = new JLabel();
        score.setText("Score: "  + scoreTally);
        end.setSize(250,250);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setVisible(true); */

    }

}
