import java.awt.*;

public class Decoration extends UMLComponent{

    protected UMLComponent component;

    public void setComponent(UMLComponent component){
        this.component = component;
    }

    @Override
    public void paintBox(Graphics g) {
        if (component != null){
            component.paintBox(g);
        }
    }
}
