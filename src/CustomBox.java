import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna, Mitashi Parikh
 * @version 1.0
 * Box variables-
 * String name, Int x, Int y, Int width, Int height
 */
public class CustomBox extends UMLComponent{
    int addedHeight = 0;

    /**
     * Box constructor
     * @param name - String name which will be the name of the new Box
     * @param x - int x while will be the x co-ordinate of the new Box
     * @param y - int y while will be the y co-ordinate of the new Box
     */
    public CustomBox(String name, int x, int y) {
        super.setName(name);
        super.setX(x);
        super.setY(y);
        if(name.length()*10 > getWidth()){
            super.setWidth(name.length()*10);
        }
    }


    /**
     * paintBox - overridden method to draw box to the screen
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g){
        int tempAddedHeight = (super.getNumVars() + super.getNumMethods()) * Decoration.decHeight == 0 ? 0 :
                (super.getNumVars() + super.getNumMethods()) * Decoration.decHeight;
        if(tempAddedHeight != this.addedHeight){
            super.setHeight(super.getHeight() - this.addedHeight + tempAddedHeight);
            this.addedHeight = tempAddedHeight;
        }
        g.setColor(Color.YELLOW);
        g.fillRect(super.getX() - super.getWidth()/2, super.getY() - super.getHeight()/2,
                super.getWidth(), super.getHeight());
        g.setColor(Color.black);
        g.drawString(this.getName(), super.getX()-super.getName().length()*4, super.getY() - super.getHeight()/2 + 20);

        for(Connection c: super.getConnections()){
            c.paintConnection(g);
        }
    }

    /**
     * setHeight - setter method to set the height of this box
     * @param height - int value to replace the current height
     */
    public void setHeight(int height){
        super.setHeight(height);
    }

}
