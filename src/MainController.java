import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * MainController Class - class that connects GUI with the model
 */
public class MainController implements ActionListener {
    /**`
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for GUI components
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("New") -> Blackboard.getBlackboard().reset();
            case ("Save") -> new SaveModel();
            case ("Load") -> new LoadModel();

            case ("Update") -> System.out.println("The user has made changes that require an UPDATE");

            case ("Association") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.ASSOCIATION);
            case ("Inheritance") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.INHERITANCE);
            case ("Composition") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.COMPOSITION);
        }
    }
}
