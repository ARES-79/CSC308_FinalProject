import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Assignment 01
 *
 * @author Andrew Estrada
 * @version 2.1
 * MethodDecorator - concrete decoration for methods
 */
public class MethodDec extends Decoration {
    private String methodName;
    private UMLComponent component;
    private int decWidth;
    private int decHeight;
    private ArrayList<String> methods;

    /**
     * MethodDecorator constructor
     * transfers necessary info from the component with appropriate edits
     *
     * @param method    - name of the method being added
     * @param component - component to be decorated
     */
    public MethodDec(String method, UMLComponent component) {
        this.methodName = method;
        this.methods = (ArrayList<String>) Arrays.stream(this.methodName.split("\n")).collect(Collectors.toList());
        this.component = component;
        super.setComponent(component);
        super.setNumVars(component.getNumVars());
        super.setTotalVars(component.getTotalVars());
        super.setNumMethods(component.getNumMethods() + 1);
        this.decWidth = component.getWidth() - 5;
        this.decHeight = Decoration.decHeight * this.methods.size();
    }

    /**
     * paintBox - overridden method to also paint a pink box with a method name
     * in the correct position
     *
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g) {
        this.methods = (ArrayList<String>) Arrays.stream(this.methodName.split("\n")).collect(Collectors.toList());
        this.methods.remove(0);
        int baseBoxX = component.getX();
        this.decHeight = Decoration.decHeight * this.methods.size();
        ((VarDec) this.component).getComponent().setNumMethods(this.methods.size());
        super.paintBox(g);
        super.paintBox(g);
        g.setColor(Color.PINK);
        if (super.getNumVars() == 0) {
            g.fillRect(baseBoxX - component.getWidth() / 2 + 2,
                    super.getY() - super.getHeight() / 2 + 25 + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX - methodName.length() * 5, super.getY() - super.getHeight() / 2 + 25 + ((super.getNumMethods() - 1) * decHeight) + 5 + 15);
        } else {
            g.fillRect(baseBoxX - component.getWidth() / 2 + 2,
                    super.getY() - super.getHeight() / 2 + 30 + ((VarDec) this.component).getDecHeight(),
                    decWidth, decHeight);
            g.setColor(Color.black);
            int baseHeight = 45;
            for (String method : methods) {
                g.drawString(method + "()", baseBoxX - method.length() * 5, super.getY() - super.getHeight() / 2 +
                        ((VarDec) this.component).getDecHeight() + baseHeight);
                baseHeight += Decoration.decHeight;
            }
        }
    }

    /**
     * setComponent - setter method to set the component
     * @param component - UMLComponent object which will become the component inside the decorator
     */
    @Override
    public void setComponent(UMLComponent component) {
        this.component = component;
    }
}
