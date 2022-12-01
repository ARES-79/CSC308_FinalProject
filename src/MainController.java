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
    SaveModel saveModel = new SaveModel();
    LoadModel loadModel = new LoadModel();

    /**`
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for GUI components
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ("New") -> Blackboard.getBlackboard().reset();
            case ("Save") -> saveModel.saveProject();
            case ("Load") -> loadModel.loadProject();

            case ("Update") -> {
                CustomTextArea textArea = Blackboard.getBlackboard().getCustomTextArea();
                textArea.parseText();
            }

            case ("Association") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.ASSOCIATION);
            case ("Inheritance") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.INHERITANCE);
            case ("Composition") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.COMPOSITION);
        }
    }
}
