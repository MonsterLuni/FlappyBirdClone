import java.awt.*;

public class Obstacle {
    boolean cleared;
    int width;
    int safeSpace;
    int ySafeSpace;
    int x;
    UI ui;
    public Obstacle(UI ui){
        this.ui = ui;
        width = (int) Math.floor(Math.random() * 50);
        if(width < 20){
            width = 20;
        }
        ySafeSpace = (int) Math.floor(Math.random() * ui.getHeight());
        safeSpace = ui.gm.safeSpaceObstacle;
        if(ySafeSpace < 50){
            ySafeSpace = 50;
        }
        else if(ySafeSpace + safeSpace > ui.getHeight()){
            ySafeSpace = ui.getHeight() - safeSpace - 50;
        }
        x = ui.getWidth() + 100;
    }
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x,0,width,ySafeSpace);
        g.fillRect(x,ySafeSpace + safeSpace,width,ui.getHeight() - (ySafeSpace + safeSpace));
    }
    public boolean checkIfAlive() {
        return x >= -50;
    }
    public void buff(){
        ui.gm.upwardsForce++;
        ui.gm.downwardsForce++;
    }
}
