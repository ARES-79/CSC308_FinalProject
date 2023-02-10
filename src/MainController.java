import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 01
 * @author Andrew Estrada, Jamie Luna, Mitashi Parikh
 * @version 1.0
 * MainController Class - class that connects GUI with the model
 */
public class MainController implements ActionListener {
    FileHandler FH;
    Component parentComponent;

    public MainController(Component parentComponent){
        this.parentComponent = parentComponent;
    }


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
                FH = new SaveModel();
                FH.saveLoadProject();
                Blackboard.getBlackboard().getStatusBar().setText("     Project Saved");}
            case ("Load") -> {
                FH = new LoadModel();
                FH.saveLoadProject();
                Blackboard.getBlackboard().getStatusBar().setText("     Project Loaded");}

            case ("About") -> {
                JOptionPane.showMessageDialog(parentComponent,
                    "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
            }

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
