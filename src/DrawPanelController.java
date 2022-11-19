import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * DrawPanelController -
 */
public class DrawPanelController implements MouseListener, MouseMotionListener {
    private Stack<Box> stack = new Stack<>();

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("The center panel has been clicked");
        Box b = new Box("box 1", e.getX() + 60, e.getY() + 35);
        stack.add(b);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Stack<Box> getStack() {
        return stack;
    }

}
