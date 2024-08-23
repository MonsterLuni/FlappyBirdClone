import javax.swing.*;
import java.awt.*;

public class GameManager {
    public int score = 0;
    public KeyListener kl;
    public UI ui;
    public int FPS = 60;
    public int currentFPS;
    public int screenHeight = 600;
    public int screenWidth = 600;
    public Player player;
    public boolean gameIsRunning = true;

    // GAME START SETTINGS
    public int gravity = 2;
    public int upwardsForce = 40;
    public int downwardsForce = 40;
    public int maxObstacles = 3;
    public int minMilliSecondsBetweenObstacles = 2000;
    public int obstacleSpeed = 2;
    public int safeSpaceObstacle = 150;


    public void start(){
        ui = new UI(this);
        kl = new KeyListener(this);
        JFrame frame = new JFrame("CheapFlappyBirdClone");
        ui.paintComponents(ui.getGraphics());
        ui.setBackground(Color.red);
        ui.addKeyListener(kl);
        ui.setFocusable(true);
        frame.add(ui);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        player = new Player(new Point(200,ui.getHeight()/2 - 25),Color.RED,50,50);
        framelimiter();

    }
    public void framelimiter() {
        long lastTime = System.currentTimeMillis();
        while (gameIsRunning) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastTime;

            if (elapsedTime >= 1000 / FPS) {
                ui.repaint();
                currentFPS = (int) (1000 / elapsedTime);
                lastTime = currentTime;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void endGame() {
        gameIsRunning = false;
        System.out.println("Game has ended you ded");
    }
}

