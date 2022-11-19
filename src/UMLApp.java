import javax.swing.*;
import java.awt.*;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.3
 * UMLApp Class - a UML application GUI framework
 */
public class UMLApp extends JFrame {

    private JTextArea textArea;
    private JLabel south;
    /**
     * Main creates a new UMLApp window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        UMLApp window = new UMLApp();
        window.setSize(800,600);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * UMLApp constructor
     * Uses inheritance from JFrame to create a window with the elements
     * necessary for a GUI with UML functionality.
     */
    public UMLApp() {
        super("My UML App");
        setLayout(new BorderLayout());
        MainController mC = new MainController();

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem newB = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        JMenuItem name1 = new JMenuItem("Andrew Estrada");
        JMenuItem name2 = new JMenuItem("Jamie Luna");
        JMenuItem name3 = new JMenuItem("Mitashi Parikh");
        JMenuItem name4 = new JMenuItem("Archie Jones");
        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(help);
        file.add(newB);
        file.add(save);
        file.add(load);
        help.add(name1);
        help.add(name2);
        help.add(name3);
        help.add(name4);

        //west
        JPanel leftCenter = new JPanel ();
        textArea = new JTextArea (30,20);
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll);
        leftCenter.setVisible (true);
        add(leftCenter, BorderLayout.WEST);

        //center
        DrawPanel east = new DrawPanel();
        east.setBackground(Color.GRAY);
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

        //south
        south = new JLabel("   Temp Label");
        add(south, BorderLayout.SOUTH);

        //actionListeners
        newB.addActionListener(mC);
        save.addActionListener(mC);
        load.addActionListener(mC);

        update.addActionListener(mC);

        association.addActionListener(mC);
        inheritance.addActionListener(mC);
        composition.addActionListener(mC);
    }
}
