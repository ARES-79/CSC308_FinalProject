package Controller;

import Model.*;
import View.CustomTextArea;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Assignment 01
 * @author Andrew Estrada, Jamie Luna, Mitashi Parikh
 * @version 1.0
 * Controller.MainController Class - class that connects GUI with the model
 */
@Getter
@Setter
public class MainController implements ActionListener {
    private FileHandler FH;
    private Component parentComponent;
    private CustomTextArea textArea;

    /**
     * constructor
     *
     * @param parentComponent host frame of the controller
     * @param textArea Custom Text Area for the controller
     */
    public MainController(Component parentComponent, CustomTextArea textArea){
        this.parentComponent = parentComponent;
        this.textArea = textArea;
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
                Blackboard.getBlackboard().statusBarNewProject();}
            case ("Save") -> {
                FH = new SaveModel();
                FH.saveLoadProject();
                Blackboard.getBlackboard().statusBarProjectSaved();}
            case ("Load") -> {
                FH = new LoadModel();
                FH.saveLoadProject();
                Blackboard.getBlackboard().statusBarProjectLoaded();}

            case ("About") -> {
                JOptionPane.showMessageDialog(parentComponent,
                    "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            case ("Update") -> {
//                View.CustomTextArea textArea = parentComponent.getTextArea();
                textArea.parseText();
                Blackboard.getBlackboard().statusBarTextAreaUpdated();
            }

            case ("Association") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.ASSOCIATION);
            case ("Inheritance") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.INHERITANCE);
            case ("Composition") -> Blackboard.getBlackboard().setConnectionType(ConnectionType.COMPOSITION);
        }
    }
}
