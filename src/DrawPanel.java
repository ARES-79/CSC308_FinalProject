import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class DrawPanel extends JPanel{ // implements Observer

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
        //will include drawing classes
        //will include drawing connections
    }

    /**
     * temporary version of the update method
     * @param observable
     */
    public void update(Object observable) {
        repaint();
    }
}
