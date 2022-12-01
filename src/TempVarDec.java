import java.awt.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 2.1
 * VariableDecorator - concrete decoration for variables
 */
public class TempVarDec extends TempDecoration{

    private String varName;
    private int decWidth;

    /**
     * VariableDecorator Constructor
     *      transfers necessary info from the component with appropriate edits
     * @param var - String name of the variable being added
     * @param component - UMLComponent to be decorated
     */
    public TempVarDec(String var, UMLComponent component){
        this.varName = var;
        super.setComponent(component);
        super.setNumVars(component.getNumVars() +1);
        super.setTotalVars(component.getTotalVars() +1);
        super.setNumMethods(component.getNumMethods());
        this.decWidth = component.getWidth()-5;
    }

    /**
     * paintBox - overridden method to also paint a pink box with a variable name
     *              in the correct position
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g){
        super.paintBox(g);
        int baseBoxX = component.getX();
        g.setColor(Color.PINK);
        g.fillRect(baseBoxX - component.getWidth()/2+2,
                super.getY() - super.getHeight()/2 + 25 + ((super.getNumVars()-1)* decHeight), decWidth, decHeight);
        g.setColor(Color.black);
        g.drawString(varName, baseBoxX- varName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getNumVars()-1)* decHeight)+ 15);

    }
}
