import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * MainController Class - class that connects GUI with the model
 */
public class MainController implements ActionListener {
    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for GUI components
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("New") -> System.out.println("The user has chosen to create a NEW UML");
            case ("Save") -> System.out.println("The user has chosen to SAVE their UML");
            case ("Load") -> System.out.println("The user has chosen to LOAD an existing UML");

            case ("Update") -> System.out.println("The user has made changes that require an UPDATE");

            case ("Association") -> System.out.println("ASSOCIATION connection chosen");
            case ("Inheritance") -> System.out.println("INHERITANCE connection chosen");
            case ("Composition") -> System.out.println("COMPOSITION connection chosen");
        }
    }
}
