package View;

import Controller.MainController;
import Model.Blackboard;

import javax.swing.*;
import java.awt.*;

/**
 * Final Project
 * @author Andrew Estrada, Jamie Luna
 * @version 1.3
 * View.UMLApp Class - a UML application GUI framework
 * DEPRECATED - old 308 application
 */
public class UMLApp extends JFrame {

    private CustomTextArea textArea;
    private StatusBar south = new StatusBar();

    public CustomTextArea getTextArea() {
        return textArea;
    }

    /**
     * Main creates a new View.UMLApp window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        UMLApp window = new UMLApp();
        window.setSize(800,600);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * View.UMLApp constructor
     * Uses inheritance from JFrame to create a window with the elements
     * necessary for a GUI with UML functionality.
     */
    public UMLApp() {
        super("My UML App");
        setLayout(new BorderLayout());

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem newB = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        JMenuItem about = new JMenuItem("About");
        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(help);
        file.add(newB);
        file.add(save);
        file.add(load);
        help.add(about);

        //west
        JPanel leftCenter = new JPanel ();
//        textArea = Model.Blackboard.getBlackboard().getCustomTextArea();
        textArea = new CustomTextArea(30,20);
        MainController mC = new MainController(this, textArea);
        Blackboard.getBlackboard().addObserver(textArea);
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll);
        leftCenter.setVisible (true);
        add(leftCenter, BorderLayout.WEST);

        //center
        DrawPanel east = new DrawPanel();
        Blackboard.getBlackboard().addObserver(east);
        east.setBackground(Color.LIGHT_GRAY);
        add(east, BorderLayout.CENTER);

        //north
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,3));

        JButton update = new JButton("Update");
        update.setSize(400,100);

        JPanel selectionPanel = new JPanel();
        JRadioButton association = new JRadioButton("Association");
        JRadioButton inheritance = new JRadioButton("Inheritance");
        JRadioButton composition = new JRadioButton("Composition");
        association.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(association);
        group.add(inheritance);
        group.add(composition);

        selectionPanel.setLayout(new GridLayout(1,2));
        selectionPanel.add(association);
        selectionPanel.add(inheritance);

        northPanel.add(update);
        northPanel.add(selectionPanel);
        northPanel.add(composition);
        add(northPanel, BorderLayout.NORTH);

        Blackboard.getBlackboard().addObserver(south);
        add(south, BorderLayout.SOUTH);

        //actionListeners

        newB.addActionListener(mC);
        save.addActionListener(mC);
        load.addActionListener(mC);

        about.addActionListener(mC);

        update.addActionListener(mC);

        association.addActionListener(mC);
        inheritance.addActionListener(mC);
        composition.addActionListener(mC);
    }
}
