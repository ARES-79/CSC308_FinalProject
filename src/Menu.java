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
        setLayout(new GridLayout(5,1));

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        JMenuItem progress = new JMenuItem("View Progress");
        JMenuItem logOut = new JMenuItem("Log Out");

        JMenuItem about = new JMenuItem("About");
        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(help);
        file.add(progress);
        file.add(logOut);
        help.add(about);

        //Welcome Message
        JLabel welcome = new JLabel("Welcome to the UML tutor. Please select your subject.", SwingConstants.CENTER);

        add(welcome);

        //center
        JButton code_to_UML = new JButton("Code --> UML");
        JButton code_to_Metrics = new JButton("Code --> Metrics");
        JButton UML_to_Code = new JButton("UML --> Code");
        JButton UML_to_Metrics = new JButton("UML --> Metrics");

        add(code_to_UML);
        add(code_to_Metrics);
        add(UML_to_Code);
        add(UML_to_Metrics);

//        add(optionPanel, BorderLayout.CENTER);

        //actionListeners
        progress.addActionListener(this);
        logOut.addActionListener(this);
        about.addActionListener(this);
        code_to_UML.addActionListener(this);
        code_to_Metrics.addActionListener(this);
        UML_to_Code.addActionListener(this);
        UML_to_Metrics.addActionListener(this);
    }

    /**
     * Gives functionality to the help menu
     * @param e the event notifying that an on screen element has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){
            case ("About") -> {
                JOptionPane.showMessageDialog(this,
                    "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);}
            case ("Code --> UML") -> {
                this.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                new TempAfterMenuPage(this);
            }
            case ("Code --> Metrics") -> {
                this.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                new TempAfterMenuPageV2(this);
            }
            case ("Log Out") -> {
                TempSignInWindow.main(null);
                dispose();
            }

        }

    }


}
