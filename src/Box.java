import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Box variables-
 * String name, Int x, Int y, Int width, Int height
 */
public class Box extends UMLComponent{
    public Box(String name, int x, int y) {
        super.setName(name);
        super.setX(x);
        super.setY(y);
    }


    @Override
    public void paintBox(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(super.getX() - super.getWidth()/2, super.getY() - super.getHeight()/2,
                super.getWidth(), super.getHeight());
    }

}
