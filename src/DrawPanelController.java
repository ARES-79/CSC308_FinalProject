import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawPanelController implements MouseListener, MouseMotionListener {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x: " + e.getX() +
                               ", y: " + e.getY() );
        //Will have to include collision detection
        String input = showDialogueBox();
        System.out.println(input);
        if (input != null){
            System.out.print("A class named \"" + input +
                            "\" was created at (" + e.getX() +
                            ", " + e.getY() + ").");
        }else{
            System.out.print("User clicked(" + e.getX() +
                    ", " + e.getY() + "), " +
                    "but no class was created.");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragging code");
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
    public void mouseMoved(MouseEvent e) {

    }
}
