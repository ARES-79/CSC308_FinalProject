import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * DrawPanel - section of the application for drawing Classes
 */
public class DrawPanel extends JPanel implements MyObserver{

    public DrawPanel(){
        DrawPanelController dpc = new DrawPanelController();
        addMouseListener(dpc);
        addMouseMotionListener(dpc);
    }

    /**
     * paintComponent - overridden method to draw the shapes to the window
     * @param g - Graphics object used by the parent to create what is shown on screen
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(UMLComponent component : Blackboard.getBlackboard().getBoxList()){
            component.getBox().paintBox(g);
        }
        //will include drawing classes
        //will include drawing connections
    }

    /**
     * update - Overridden method to update drawPanel
     * @param ob- MyObservable object sending the update
     */
    @Override
    public void update(MyObservable ob) {
        repaint();
    }
}
