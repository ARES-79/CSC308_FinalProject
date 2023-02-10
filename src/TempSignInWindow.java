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
public class TempSignInWindow extends JFrame implements ActionListener {

    /**
     * creates a new Sign in window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        TempSignInWindow signInWindow = new TempSignInWindow();
        signInWindow.setSize(800,600);
        signInWindow.setVisible(true);
        signInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public TempSignInWindow(){
        super("My UML Tutor Sign In");
        setLayout(new GridLayout(7,1));

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About");
        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(help);
        help.add(about);

        add(new JLabel(""));

        JLabel welcome = new JLabel(
                "Welcome to the UML tutor.\nPlease sign in or create an account.",
                    SwingConstants.CENTER);
        add(welcome);
        JTextField username = new JTextField("Username");
        JTextField password = new JTextField("Password");
        add(username);
        add(password);

        JButton submit = new JButton("Submit");
        add(submit);

        JLabel createAccountPrompt = new JLabel("New? Create an account:", SwingConstants.CENTER);
        add(createAccountPrompt);

        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new GridLayout(1,2));
        JButton createStudent = new JButton("Create Student Account");
        JButton createInstructor = new JButton("Create Instructor Account");

        createButtonPanel.add(createStudent);
        createButtonPanel.add(createInstructor);

        add(createButtonPanel);

        //action listeners
        about.addActionListener(this);
        submit.addActionListener(this);
        createStudent.addActionListener(this);
        createInstructor.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case("Submit") -> {
                Menu.main(null);
                dispose();
            }
        }
    }
}
