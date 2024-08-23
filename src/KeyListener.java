import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    GameManager gm;
    public KeyListener(GameManager gm){
        this.gm = gm;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(gm.player.coordinates.y - gm.upwardsForce < 0){
                gm.player.coordinates.y = 0;
            }else{
                gm.player.coordinates.y -= gm.upwardsForce;
            }
        }else{
            if(gm.player.coordinates.y + gm.player.height + gm.downwardsForce > gm.ui.getHeight()){
                gm.player.coordinates.y = gm.ui.getHeight() - gm.player.height;
            }else{
                gm.player.coordinates.y += gm.downwardsForce;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
