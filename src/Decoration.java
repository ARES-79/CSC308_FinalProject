import java.awt.*;
import java.util.ArrayList;

/**
 * 308 Final Project
 * @author Mitashi Parikh,
 * @version 1.0
 * Decoration - class which extends UMLComponent and follows the decorated pattern to add 'methods' variables to a Box
 */
public class Decoration extends UMLComponent{
    String text = "Decoration";
    protected UMLComponent component;
    private final int decHeight = 15;

    /**
     * setComponent - Setter method which sets component
     * @param component UMLComponent to be set
     */
    public void setComponent(UMLComponent component){
        this.component = component;
        super.setHeight(20);
    }

    /**
     * paintComponent - overridden method to draw the decorations on the DrawPanel
     * @param g - Graphics object used by the parent to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g) {
        if (component != null){
            component.paintBox(g);
            if (component instanceof Box){
                int baseBoxX = component.getX();
                int baseBoxY = component.getY();
                g.setColor(Color.YELLOW);
                g.fillRect(baseBoxX - component.getWidth()/2, baseBoxY + component.getHeight()/2, component.getWidth(), component.getHeight());
                g.setColor(Color.PINK);
                g.fillRect(baseBoxX - component.getWidth()/2+2, baseBoxY + component.getHeight()/2+2, component.getWidth()-5, component.getHeight()-5);
                g.setColor(Color.black);
                g.drawString(text, baseBoxX- text.length()*5, baseBoxY + component.getHeight()+2);
            }
        }
    }

    /**
     * getName - getter method to get the Name of the component
     * @return String name of the component
     */
    public String getName() {
        return component.getName();
    }

    /**
     * getX - getter method to get the x of the component
     * @return int x of the component
     */
    public int getX() {
        return component.getX();
    }

    /**
     * getY - getter method to get the Y of the component
     * @return int y of the component
     */
    public int getY() {
        return component.getY();
    }

    /**
     * getWidth - getter method to get the width of the component
     * @return int width of the component
     */
    public int getWidth() {
        return component.getWidth();
    }

    /**
     * getHeight - getter method to get the height of the component
     * @return int height of the component
     */
    public int getHeight() {
        return component.getHeight() + super.getHeight();
    }

    /**
     * getConnections - getter method to get the list of Connections of the component
     * @return ArrayList</Connections> list of connections of the component
     */
    public ArrayList<Connection> getConnections() {
        return component.getConnections();
    }

    /**
     * setX - setter method to set the x of the component
     * @param x int x value to set to the x of the component
     */
    public void setX(int x) {
        component.setX(x);
    }

    /**
     * setY - setter method to set the y of the component
     * @param y int y value to set to the y of the component
     */
    public void setY(int y){
        component.setY(y);
    }

    /**
     * setName - setter method to set the name of the component
     * @param name String name value to set to the name of the component
     */
    public void setName(String name) {
        component.setName(name);
    }

    /**
     * setWidth - setter method to set the width of the component
     * @param width int width value to set to the width of the component
     */
    public void setWidth(int width) {
        component.setWidth(width);
    }
}
