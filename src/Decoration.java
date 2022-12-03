import java.awt.*;

public class Decoration extends UMLComponent {
    protected UMLComponent component;
    public static final int decHeight = 25;

    public void setComponent(UMLComponent component) {
        this.component = component;
        super.setName(component.getName());
        super.setX(component.getX());
        super.setY(component.getY());
        super.setConnections(component.getConnections());
        this.component.setHeight(component.getHeight() + decHeight);
        super.setHeight(this.component.getHeight());
    }

    /**
     * paintBox - overridden method to draw component to the screen
     * follows decorator pattern
     *
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g) {
        super.setNumVars(this.getNumVars());
        if (component != null) {
            component.paintBox(g);
        }
        for (Connection c : super.getConnections()) {
            c.paintConnection(g);
        }
    }

    /**
     * getHeight - getter method to get the height of the component
     *
     * @return int height of the component
     */
    public int getHeight() {
        return component.getHeight();
    }

    /**
     * getX - getter method to get the x of the component
     *
     * @return int x of the component
     */
    public int getX() {
        return component.getX();
    }

    /**
     * getY - getter method to get the Y of the component
     *
     * @return int y of the component
     */
    public int getY() {
        return component.getY();
    }

    /**
     * setX - setter method to set the x of the component
     *
     * @param x int x value to set to the x of the component
     */
    public void setX(int x) {
        component.setX(x);
    }

    /**
     * setY - setter method to set the y of the component
     *
     * @param y int y value to set to the y of the component
     */
    public void setY(int y) {
        component.setY(y);
    }

    public void setHeight(int height) {
        component.setHeight(height);
    }

    /**
     * setTotalVars - overridden setter method to not only update the
     * current UMLComponent but the nested one's as well
     *
     * @param totalVars - int value to replace current totalVars value
     */
    @Override
    public void setTotalVars(int totalVars) {
        super.setTotalVars(totalVars);
        component.setTotalVars(totalVars);
    }
    public UMLComponent getComponent() {
        return component;
    }

}
