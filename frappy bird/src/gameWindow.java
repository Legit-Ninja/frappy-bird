import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;


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
       spacePanel();
       pipePanel();
       spacePanel();
       pipePanel();
       bird.getImage().setBounds(75,300,50,50);
       lpane.add(bird.getImage(), JLayeredPane.PALETTE_LAYER);

       this.add(lpane);
       lpane.repaint();
   }
   private void spacePanel()
   {
       lpane.setBounds(panelNum*150, 0, 300, SCREEN_HEIGHT);
       panelNum += 2;
   }
   private void pipePanel()
   {
       int tHeight;
       panelNum++;
           topPipe t = new topPipe();
           bottomPipe b = new bottomPipe();
           scoreBox s = new scoreBox();
           t.resetTopPipeHeight();
           tHeight = t.getTopPipeHeight();
           gameObjects.add(t);
           t.getImage().setBounds(panelNum*150, 0, 150, tHeight);
           gameObjects.add(s);
           s.getImage().setBounds(panelNum*150, tHeight, 150, 100);
           gameObjects.add(b);
           b.getImage().setBounds(panelNum*150, tHeight+100, 150, (SCREEN_HEIGHT-tHeight)-100);   //randomizes the pipe's locations
           lpane.add(t.getImage());
       (t.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);
           lpane.add(s.getImage());
       (s.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);
           lpane.add(b.getImage());
       (b.getImage()).setAlignmentX(Component.CENTER_ALIGNMENT);

   }



    public static void GameOver() {
       JFrame end = new JFrame();      //score window
        JLabel score = new JLabel();
        score.setText("Score: "  + scoreTally);
        end.add(score);
        end.setSize(250,250);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setVisible(true);
        try {
            Thread.sleep(58987);
        }catch( Exception ex ) {}
   }
    public static void scoreUp() { scoreTally++; }
    public static boolean collide()
    {
        boolean intersects = false;
        for (int i= 0; i < gameObjects.size(); i++) {
            for (int j = 1; j < gameObjects.size(); j++) {
                intersects = false;
                if (i == j) {}
                else if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && gameObjects.get(j).getClass() == topPipe.class) {     // checks to see if the two labels intersect
                    intersects = true;
                    GameOver();
                } else if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && (gameObjects.get(j)).getClass() == bottomPipe.class) {
                    intersects = true;
                    GameOver();
                } else if (((gameObjects.get(i)).getImage().getBounds()).intersects(((gameObjects.get(j)).getImage().getBounds())) && (gameObjects.get(j)).getClass() == scoreBox.class) {
                    intersects = true;
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
                        Thread.sleep(100);
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }
        });
        animation.start();


    }

}
