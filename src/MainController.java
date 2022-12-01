import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Assignment 01
 * @author Andrew Estrada, Jamie Luna
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
            case ("New") -> {
                Blackboard.getBlackboard().reset();
                Blackboard.getBlackboard().getStatusBar().setText("     New Project Created");}
            case ("Save") -> {
                saveModel.saveProject();
                Blackboard.getBlackboard().getStatusBar().setText("     Project Saved");}
            case ("Load") -> {
                loadModel.loadProject();
                Blackboard.getBlackboard().getStatusBar().setText("     Project Saved");}

            case ("Update") -> {
                CustomTextArea textArea = Blackboard.getBlackboard().getCustomTextArea();
                textArea.parseText();
                Blackboard.getBlackboard().getStatusBar().setText("     Screen updated based on text written.");
            }

            case ("Association") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.ASSOCIATION);
            case ("Inheritance") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.INHERITANCE);
            case ("Composition") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.COMPOSITION);
        }
    }
}
