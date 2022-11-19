import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * DrawPanel -
 */
public class DrawPanel extends JPanel {
    private final DrawPanelController drawPanelController;
//    private Stack<Box> stack = new Stack<>();

    public DrawPanel(DrawPanelController drawPanelController) {
        super();
        this.drawPanelController = drawPanelController;
        this.addMouseListener(drawPanelController);
        this.addMouseMotionListener(drawPanelController);
    }

    public void paintComponent(Graphics g){
        //sets the background
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());

        //test box for sizing
        g.fillRect(100, 100, 125, 75);

        for (Box b : drawPanelController.getStack()){
            g.setColor(Color.YELLOW);
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}
