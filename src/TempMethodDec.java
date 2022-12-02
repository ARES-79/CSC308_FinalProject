import java.awt.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 2.1
 * MethodDecorator - concrete decoration for methods
 */
public class TempMethodDec extends TempDecoration{
    private String methodName;
    private int decWidth;

    /**
     * MethodDecorator constructor
     *    transfers necessary info from the component with appropriate edits
     * @param method - name of the method being added
     * @param component - component to be decorated
     */
    public TempMethodDec(String method, UMLComponent component){
        this.methodName = method;
        super.setComponent(component);
        super.setNumVars(component.getNumVars());
        super.setTotalVars(component.getTotalVars());
        super.setNumMethods(component.getNumMethods() +1);
        this.decWidth = component.getWidth()-5;
    }

    /**
     * paintBox - overridden method to also paint a pink box with a method name
     *              in the correct position
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g){
        super.paintBox(g);
        int baseBoxX = component.getX();
        g.setColor(Color.PINK);
        if (super.getNumVars() == 0){
            g.fillRect(baseBoxX - component.getWidth()/2+2,
                    super.getY() - super.getHeight()/2 + 25 + ((super.getNumMethods()-1)* decHeight) + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX- methodName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getNumMethods()-1)* decHeight) + 5 + 15);
        } else{
            g.fillRect(baseBoxX - component.getWidth()/2+2,
                    super.getY() - super.getHeight()/2 + 25 + ((super.getTotalVars() + super.getNumMethods()-1)* decHeight) + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX- methodName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getTotalVars() + super.getNumMethods()-1)* decHeight) + 5 + 15);
        }
    }
}
