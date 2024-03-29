package Model;

import Model.Decoration;
import Model.UMLComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 2.1
 * VariableDecorator - concrete decoration for variables
 */

public class VarDec extends Decoration {

    private String varName;
    private final int decWidth;
    private int decHeight;
    private ArrayList<String> vars;

    /**
     * VariableDecorator Constructor
     *      transfers necessary info from the component with appropriate edits
     * @param var - String name of the variable being added
     * @param component - Model.UMLComponent to be decorated
     */
    public VarDec(String var, UMLComponent component){
        this.varName = var;
        this.vars = new ArrayList<>();

        super.setComponent(component);
        super.setNumVars(component.getNumVars() +1);
        super.setTotalVars(component.getTotalVars() +1);
        super.setNumMethods(component.getNumMethods());
        this.decWidth = component.getWidth()-5;
        this.decHeight = Decoration.decHeight * this.vars.size();
    }

    /**
     * paintBox - overridden method to also paint a pink box with a variable name
     *              in the correct position
     * @param g - Graphics object used to create what is shown on screen
     */
    @Override
    public void paintBox(Graphics g){
        this.vars = (ArrayList<String>) Arrays.stream(this.varName.split("\n")).collect(Collectors.toList());
        this.vars.remove(0);
        int baseBoxX = component.getX();
        this.decHeight = Decoration.decHeight * this.vars.size();
        this.component.setNumVars(this.vars.size());
        super.paintBox(g);
        g.setColor(Color.PINK);
        g.fillRect(baseBoxX - component.getWidth()/2+2,
                super.getY() - super.getHeight()/2 + 25, decWidth, decHeight);
        g.setColor(Color.black);
        int baseHeight = 40;
        for(String var : vars){
            g.drawString(var, baseBoxX- var.length()*5, super.getY() - super.getHeight()/2 + baseHeight);
            baseHeight += Decoration.decHeight;
        }
    }

    /**
     * getDecHeight - getter method to get height of this decorator
     *
     * @return int decHeight which is the height of this decorator
     */
    public int getDecHeight() {
        return decHeight;
    }

    /**
     * getVarName - getter method to get the name of the variable of this decorator
     *
     * @return String varNAme, which is the name of the variable
     */
    public String getVarName() {
        return varName;
    }

    /**
     * setVarName - setter method to set varName to the string received as the parameter
     *
     * @param varName - String which will become the varNAme of this decorator
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }
}
