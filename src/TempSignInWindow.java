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

    JTextField username;
    JTextField password;
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
        setLayout(new GridLayout(6,1));

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

        JPanel userPassPanel = new JPanel();
        userPassPanel.setLayout(new GridLayout(3,3));
        username = new JTextField("Username");
        password = new JTextField("Password");
        JButton submit = new JButton("Submit");
        for (int i =0; i < 9; i++){
            if(i == 1){userPassPanel.add(username);}
            else if(i == 4){userPassPanel.add(password);}
            else if(i == 7){userPassPanel.add(submit);}
            else{userPassPanel.add(new JLabel(""));}
        }
        add(userPassPanel);

        JLabel createAccountPrompt = new JLabel("New? Create an account:", SwingConstants.CENTER);
        add(createAccountPrompt);

        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new GridLayout(2,4));
        JButton createStudent = new JButton("Create Student Account");
        JButton createInstructor = new JButton("Create Instructor Account");
        for(int i = 0; i < 8; i++){
            if (i == 1){createButtonPanel.add(createStudent);}
            else if (i == 2){createButtonPanel.add(createInstructor);}
            else{createButtonPanel.add(new JLabel(""));}
        }
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
                Menu.main(new String[]{username.getText(), password.getText()});
                dispose();
            }
        }
    }
}
