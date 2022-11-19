import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Box variables-
 * String name, Int x, Int y, Int width, Int height
 */
public class Box {
    private String name;
    private int x;
    private int y;
    private int width = 120;
    private int height = 60;

    public Box(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void paintBox(Graphics g){
        g.fillRect(this.x + width/2, this.y + height/2, this.width, this.height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
