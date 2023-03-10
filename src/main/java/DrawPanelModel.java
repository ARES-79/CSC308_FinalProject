import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.3
 * DrawPanelModel - class that provides the logic for the DrawPanel
 */
@Getter
@Setter
public class DrawPanelModel {

    private boolean isFirstBoxPressed;
    private UMLComponent firstBoxPressed;
    private int x1, y1, baseX, baseY, baseVarY, baseMethodY;

    /**
     * showDialogueBox - create a dialogue box to prompt the user to enter a class name
     * @return String input by the user or null if nothing was entered or the user clicked cancel
     */
    public String showDialogueBox(){
        String s = (String) JOptionPane.showInputDialog(
                null,
                "What would you like to name your new class?\n"
                        + "Name: ",
                "Create a class",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        //If a string was returned
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return null;
    }

    /**
     *  isInExistingBox - checks if where the user clicked is within a box already
     *          on the screen
     * @param x - x coordinate of mouse location
     * @param y - y coordinate of mouse location
     * @return true if the click is within an existing box, false otherwise
     */
    public boolean isInExistingBox(int x, int y){
        for (UMLComponent c: Blackboard.getBlackboard().getBoxList()){
            if(c.checkCollision(x,y)) {
                System.out.println("Collision found");
                x1 = x;
                y1 = y;
                baseX = c.getX();
                baseY = c.getY();
                dealWithBox(c);
                return true;
            }
        }
        this.isFirstBoxPressed = false;
        firstBoxPressed = null;
        return false;
    }

    public UMLComponent returnClickedBox(int x, int y){
        for (UMLComponent c: Blackboard.getBlackboard().getBoxList()){
            if(c.checkCollision(x,y)) {
                return c;
            }
        }
        return null;
    }

    /**
     * dealWithBox - either creates connection or sets which box was pressed
     * @param boxPressed - class object that was selected on the screen
     */
    public void dealWithBox(UMLComponent boxPressed){
        System.out.println("Dealing with Box, isFirstBoxPressed: " + this.isFirstBoxPressed);
        if(this.isFirstBoxPressed && firstBoxPressed != boxPressed
                && !firstBoxPressed.checkConnection(boxPressed)){
            firstBoxPressed.addConnection(boxPressed, Blackboard.getBlackboard().getConnectionType());
            Blackboard.getBlackboard().updateData();
            this.isFirstBoxPressed = false;
            firstBoxPressed = null;
        } else{
            this.isFirstBoxPressed = true;
            firstBoxPressed = boxPressed;
        }
    }

    /**
     * isFirstBoxPressed - getter for isFirstBoxPressed
     * @return true is a box has already been selected
     */
    public boolean isFirstBoxPressed() {
        return this.isFirstBoxPressed;
    }

    /**
     * moveBox - update the position of the selected box
     * @param x - x coordinate of mouse on screen
     * @param y - y coordinate of mouse on screen
     */
    public void moveBox(int x, int y){

        firstBoxPressed.setX(baseX + (x-x1));
        firstBoxPressed.setY(baseY + (y-y1));
        Blackboard.getBlackboard().updateData();
    }

    /**
     * released - method to be called when the mouse is released after dragging
     *            just resets it so no ox is selected
     */
    public void released(int finalX, int finalY){

        System.out.println("before release isFirstBoxPressed: " + this.isFirstBoxPressed);
        System.out.println("x diff: " + (finalX - x1) + ", y diff: " + (finalY - y1));
        if(!(-15 < finalX - x1  &&
                finalX - x1 < 15 &&
            -15 < finalY - y1 &&
                finalY - y1 < 15)) {
            this.isFirstBoxPressed = false;
            firstBoxPressed = null;
        }
        System.out.println("released, isFirstBoxPressed: " + this.isFirstBoxPressed);
    }

}
