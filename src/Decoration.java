import java.awt.*;
import java.util.ArrayList;

public class Decoration extends UMLComponent{
    String text = "Decoration";
    protected UMLComponent component;
    int height = 20;

    public void setComponent(UMLComponent component){
        this.component = component;
    }

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

    public String getName() {
        return component.getName();
    }

    public int getX() {
        return component.getX();
    }

    public int getY() {
        return component.getY();
    }

    public int getWidth() {
        return component.getWidth();
    }

    public int getHeight() {
        return component.getHeight() + height;
    }

    public ArrayList<Connection> getConnections() {
        return component.getConnections();
    }

    public void setX(int x) {
        component.setX(x);
    }

    public void setY(int y){
        component.setY(y);
    }

    public void setName(String name) {
        component.setName(name);
    }

    public void setWidth(int width) {
        component.setWidth(width);
    }
}
