import java.awt.*;

public class TempDecoration extends UMLComponent{
    protected UMLComponent component;
    public static final int decHeight = 25;

    public void setComponent(UMLComponent component){
        this.component = component;
        super.setName(component.getName());
        super.setConnections(component.getConnections());
    }

    @Override
    public void paintBox(Graphics g) {
        if (component != null){
            component.paintBox(g);
        }
        for(Connection c: super.getConnections()){
            c.paintConnection(g);
        }
    }

    /**
     * getHeight - getter method to get the height of the component
     * @return int height of the component
     */
    public int getHeight() {
        return component.getHeight();
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

    public void setHeight(int height){
        component.setHeight(height);
    }

    @Override
    public void setVarY(int varY) {
        super.setVarY(varY);
        this.component.setVarY(varY);
    }

    @Override
    public void setMethodY(int methodY){
        super.setMethodY(methodY);
        this.component.setMethodY(methodY);
    }
}
