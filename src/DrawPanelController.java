import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.3
 * DrawPanelController - class that connects the panel GUI with its model
 */
public class DrawPanelController implements MouseListener, MouseMotionListener {

    private DrawPanelModel dpModel = new DrawPanelModel();

    /**
     * mousePressed - overridden method that gives the option to create a new class box
     *      if the mouse is clicked where there is not a box
     *      OR sends the information to be handled if there is a box
     * @param e - MouseEvent object that contains data about the mouse within the panel
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("x: " + e.getX() +
                ", y: " + e.getY() );
        if(!dpModel.isInExistingBox(e.getX(),e.getY())) {
            String input = dpModel.showDialogueBox();
            System.out.println(input);
            if (input != null) {
                System.out.print("A class named \"" + input +
                        "\" was created at (" + e.getX() +
                        ", " + e.getY() + ").");
                Box newBox = new Box(input, e.getX(), e.getY());
                Blackboard.getBlackboard().appendBoxList(newBox);

                //UMLComponent decBox = newBox;
                //if (input.equals("A")){
                //    decBox = new TempVarDec("var1", newBox);
                //}
                //else if (input.equals("B")){
                //    decBox = new TempMethodDec("method1", newBox);
                //}
                //else if (input.equals("C")){
                //    decBox = new TempMethodDec("method1", new TempVarDec("var1", newBox) );
                //}
                //else if (input.equals("D")){
                //    decBox = new TempVarDec( "var2", new TempMethodDec("method1", new TempVarDec("var1", newBox) ));
                //}
                //else if (input.equals("E")){
                //    decBox = new TempMethodDec( "method2", new TempMethodDec("method1", new TempVarDec("var1", newBox) ));
                //}
                //Blackboard.getBlackboard().appendBoxList(decBox);
                Blackboard.getBlackboard().getStatusBar().setText("     A new class was created.");
                Blackboard.getBlackboard().updateData();
            } else {
                System.out.print("User clicked(" + e.getX() +
                        ", " + e.getY() + "), " +
                        "but no class was created.");
                Blackboard.getBlackboard().getStatusBar().setText("     The user clicked the screen but no class was created.");
            }
        }
    }

    /**
     * mouseDragged - overridden method that moves a box when it is selected and dragged
     * @param e - MouseEvent object that contains data about the mouse within the panel
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(dpModel.isFirstBoxPressed()){
            dpModel.moveBox(e.getX(),e.getY());
        }
    }

    /**
     * mouseReleased - overridden method that sends information to the model
     * @param e - MouseEvent object that contains data about the mouse within the panel
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        dpModel.released(e.getX(),e.getY());
    }

    /**
     * Overridden methods not in use - not meant to do anything when that action is performed
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
