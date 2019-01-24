import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The gameWindow class makes a window to start the game.
 */
public class gameWindow extends JFrame{
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    public static int scoreTally = 0;
    public static bird bird;
    public static int panelNum = 0;
    public static JLayeredPane lpane;

    /**
     * Constructor pulls up the window and starts the game.
     */
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

    /**
     * Builds the images on the screen and puts them in their desired locations.
     */
   public void buildWindow()
   {
       lpane = new JLayeredPane();
       lpane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
       lpane.setOpaque(true);
       spacePanel();
       pipePanel();
       spacePanel();
       pipePanel();
       spacePanel();
       pipePanel();
       spacePanel();
       pipePanel();
       bird.getImage().setBounds(75,300,50,50);
       lpane.add(bird.getImage(), JLayeredPane.PALETTE_LAYER);

       this.add(lpane);
       lpane.repaint();
   }

    /**
     * Creates a spacePanel (empty space between pipe columns).
     */
   private void spacePanel()
   {
       lpane.setBounds(panelNum*150, 0, 300, SCREEN_HEIGHT);
       panelNum += 2;
   }

    /**
     * Creates a pipePanel (randomly sets pipes at a location and scoreBoxes accordingly).
     * This is all based on screen height, so it will work on any machine.
     */
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

    /**
     * Called when the bird collides with a pipe; outputs score in a new window.
     */
    public static void GameOver() {
       JFrame end = new JFrame();      //score window
        JLabel score = new JLabel();
        score.setText("You Lose! Score: "  + scoreTally);
        end.add(score);
        end.setSize(250,250);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setVisible(true);
        try {
            Thread.sleep(58987);
        }catch( Exception ex ) {}
   }

    /**
     * called when the bird makes contact with the scoreBox object; counts up the score and if the
     * player reached the end, opens a window with the winning line.
     */
    public static void scoreUp() {
       scoreTally++;
       if (scoreTally > 79)
       {
           JFrame win = new JFrame();      //score window
           JLabel score = new JLabel();
           score.setText("YOU WIN! Pete is v proud :)");
           win.add(score);
           win.setSize(250,250);
           win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           win.setVisible(true);
           try {
               Thread.sleep(58987);
           }catch( Exception ex ) {}
       }
   }

    /**
     * Called continuously throughout each step of the game to make sure our bird hasn't collided with any of the other game objects
     * @return bool if the bird is colliding with another gameObject or not. Very useful for testing.
     */
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
    /**
     * Creates the start window, and when the button is clicked, begins the game.
     */
    public static void main(String[] args) {
       JFrame startMenu = new JFrame("Welcome To FlappyPete");         //creates the start menu
       JLabel peteBird = new JLabel();
       JPanel p = new JPanel(new BorderLayout());
       JButton start = new JButton("Start!");
       //makes petebirdLabel
        try {
            ImageIcon i = new ImageIcon("Z://CS-372-1-JavaAppDev/frappy bird/src/resources/TitleScreen.PNG");
            peteBird.setIcon(i);
            peteBird.setSize(100,100);
            p.add(peteBird, BorderLayout.CENTER);

        } catch (Exception ex) {}
        p.add(peteBird, BorderLayout.CENTER);
        p.add(start, BorderLayout.PAGE_START);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //main game loop
                gameWindow g = new gameWindow();
                Thread animation = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            bird.move();
                            collide();
                            try {
                                Thread.sleep(60);
                            } catch (Exception ex) { ex.printStackTrace(); }
                        }
                    }
                });
                animation.start();
            }
        });
        startMenu.add(p);
        startMenu.setSize(1000,1000);
        startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startMenu.setVisible(true);


    }

}
