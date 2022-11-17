import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {

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

    }

}
