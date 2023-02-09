import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * View for the Menu for different types of tutoring subjects
 */
public class Menu extends JFrame implements ActionListener {

    /**
     * creates a new Menu window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        Menu menu = new Menu();
        menu.setSize(800,600);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Constructor
     */
    public Menu() {
        super("My UML Tutor Menu");
        setLayout(new BorderLayout());

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About");
        setJMenuBar(menuBar);

        menuBar.add(help);
        help.add(about);

        //north
        JLabel welcome = new JLabel("Welcome to the UML tutor. Please select your subject.");

        add(welcome, BorderLayout.NORTH);

        //center
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(4,1));

        JButton code_to_UML = new JButton("Code --> UML");
        JButton code_to_Metrics = new JButton("Code --> Metrics");
        JButton UML_to_Code = new JButton("UML --> Code");
        JButton UML_to_Metrics = new JButton("UML --> Metrics");

        optionPanel.add(code_to_UML);
        optionPanel.add(code_to_Metrics);
        optionPanel.add(UML_to_Code);
        optionPanel.add(UML_to_Metrics);

        add(optionPanel, BorderLayout.CENTER);

        //actionListeners
        about.addActionListener(this);

    }

    /**
     * Gives functionality to the help menu
     * @param e the event notifying that an on screen element has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(this,
                    "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
