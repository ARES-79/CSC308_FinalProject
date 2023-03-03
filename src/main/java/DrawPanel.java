import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0.1
 * DrawPanel - section of the application for drawing Classes
 */
@Setter
@Getter
public class DrawPanel extends JPanel implements MyObserver {

    /**
     * DrawPanel constructor
     *    creates a DrawPanelController and adds it as the MouseListener and MouseMotionListener
     */
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
            component.paintBox(g);
        }
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
