import javax.swing.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 0.0
 * DrawPanelModel - class that provides the logic for the DrawPanel
 */
public class DrawPanelModel {

    private boolean isFirstBoxPressed;
    private UMLComponent firstBoxPressed;
    private int x1, y1;

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
     * @return true if the click is withan an existing box, false otherwise
     */
    public boolean isInExistingBox(int x, int y){
        //iterate through list of UMLComponents in Blackboard
        for (UMLComponent c: Blackboard.getBlackboard().getBoxList()){
            if(c.checkCollision(x,y)){
//                dealWithBox(c);
                x1 = x;
                y1 = y;
                return true;
            } else{
                isFirstBoxPressed = false;
                firstBoxPressed = null;
            }
        }
        return false;
    }

    /**
     * dealWithBox - either creates connection or sets which box was pressed
     * @param boxPressed - class object that was selected on the screen
     */
    public void dealWithBox(UMLComponent boxPressed){
        //if isFirstBoxPressedLabel is set
            // we want to make a connection
            // between the first box pressed and new box pressed
            // add the connection to the Blackboard
            // set isFirstBoxPressed to false
            // set firstBoxPressed to null

        //if this is the first box
            // set the isFirstBoxPressed to true
            // set the firstBoxPressed to the input UMLComponent
    }

    /**
     * moveBox - update the position of the selected box
     * @param x - x coordinate of mouse on screen
     * @param y - y coordinate of mouse on screen
     */
    public void moveBox(int x, int y){
        // int baseX = firstUMLComponent.getX()
        // int baseY = firstUMlComponent.getY()
        // changedX = baseX + (x-x1)
        // changedY = baseY + (y-x1)
        // update firstUMLComponent coordinates
        // repaint/update
    }

    /**
     * released - method to be called when the mouse is released after dragging
     *            just resets it so no ox is selected
     */
    public void released(){
        // set isFirstBoxPressed to false
        // set firstBoxPressed to null
    }

}
