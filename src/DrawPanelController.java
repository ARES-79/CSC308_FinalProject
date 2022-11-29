import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * DrawPanelController - class that connects the panel GUI with its model
 */
public class DrawPanelController implements MouseListener, MouseMotionListener {

    private DrawPanelModel dpModel = new DrawPanelModel();

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
                Blackboard.getBlackboard().appendBoxList(new Box(input, e.getX(), e.getY()));
                Blackboard.getBlackboard().updateData();
            } else {
                System.out.print("User clicked(" + e.getX() +
                        ", " + e.getY() + "), " +
                        "but no class was created.");
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dpModel.isFirstBoxPressed()){
            dpModel.moveBox(e.getX(),e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dpModel.released();
        System.out.println("Dragged and released.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click at x: " + e.getX() +
                ", y: " + e.getY() );
        if(!dpModel.isInExistingBox(e.getX(),e.getY())) {
            String input = dpModel.showDialogueBox();
            System.out.println(input);
            if (input != null) {
                System.out.print("A class named \"" + input +
                        "\" was created at (" + e.getX() +
                        ", " + e.getY() + ").");
                Blackboard.getBlackboard().appendBoxList(new Box(input, e.getX(), e.getY()));
                Blackboard.getBlackboard().updateData();
            } else {
                System.out.print("User clicked(" + e.getX() +
                        ", " + e.getY() + "), " +
                        "but no class was created.");
            }
        }
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
