import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;

public class UI extends JPanel {
    GameManager gm;
    ArrayList<Obstacle> obstacles = new ArrayList<>(6);
    public UI(GameManager gm){
        this.gm = gm;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        if(gm.player.coordinates.y + gm.player.height + gm.gravity > gm.ui.getHeight()){
            gm.player.coordinates.y = gm.ui.getHeight() - gm.player.height;
        }else{
            gm.player.coordinates.y += gm.gravity;
        }
        updateObstacles(g);
        drawPlayer(g);
        drawScore(g);
        updateSettings();
    }
    private void updateSettings(){}
    private void drawScore(Graphics g){
        g.setFont(new Font("Arial", Font.PLAIN,50));
        g.drawString(String.valueOf(gm.score),50,50);
    }
    private void drawPlayer(Graphics g){
        g.setColor(gm.player.color);
        g.fillRect(gm.player.coordinates.x,gm.player.coordinates.y,gm.player.width,gm.player.height);
    }
    private long lastObstacleTime = 0;
    private void updateObstacles(Graphics g){
        for(int i = 0; i < obstacles.size(); i++){
            if(obstacles.get(i).checkIfAlive()){
                obstacles.get(i).draw(g,Color.GREEN);
                if(doSquaresTouch(obstacles.get(i).x,0,obstacles.get(i).width,obstacles.get(i).ySafeSpace,gm.player.coordinates.x,gm.player.coordinates.y,gm.player.width,gm.player.height) || doSquaresTouch(obstacles.get(i).x,obstacles.get(i).ySafeSpace + obstacles.get(i).safeSpace,obstacles.get(i).width,getHeight() - (obstacles.get(i).ySafeSpace + obstacles.get(i).safeSpace),gm.player.coordinates.x,gm.player.coordinates.y,gm.player.width,gm.player.height)){
                    gm.endGame();
                    obstacles.get(i).draw(g,Color.RED);
                }
                if(!obstacles.get(i).cleared && doSquaresTouch(obstacles.get(i).x + obstacles.get(i).width,obstacles.get(i).ySafeSpace,obstacles.get(i).width,obstacles.get(i).safeSpace,gm.player.coordinates.x,gm.player.coordinates.y,gm.player.width,gm.player.height)){
                    gm.score++;
                    obstacles.get(i).cleared = true;
                }
            }else{
                obstacles.get(i).buff();
                obstacles.remove(obstacles.get(i));
            }
            obstacles.get(i).x -= (int) (gm.obstacleSpeed + (double) ((gm.obstacleSpeed * gm.score) / 10));
        }
        if(obstacles.size() < gm.maxObstacles){
            long currentTime = Instant.now().toEpochMilli();
            if (currentTime - lastObstacleTime >= gm.minMilliSecondsBetweenObstacles) {
                obstacles.add(new Obstacle(this));
                lastObstacleTime = currentTime;
            }
        }
    }
    private static boolean doSquaresTouch(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        int right1 = x1 + width1;
        int bottom1 = y1 + height1;

        int right2 = x2 + width2;
        int bottom2 = y2 + height2;

        boolean horizontalTouch = (x1 < right2 && right1 > x2);
        boolean verticalTouch = (y1 < bottom2 && bottom1 > y2);

        return horizontalTouch && verticalTouch;
    }
}

