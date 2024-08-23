import java.awt.*;

public class Player {
    Point coordinates;
    Color color;
    int width;
    int height;
    public Player(Point point,Color color,int width, int height){
        this.coordinates = point;
        this.color = color;
        this.height = height;
        this.width = width;
    }
}
