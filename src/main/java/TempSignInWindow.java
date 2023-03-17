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

    private JPanel screenPanel;

    public JPanel getScreenPanel() {
        return screenPanel;
    }

    public void setScreenPanel(JPanel screenPanel) {
        this.screenPanel = screenPanel;
    }

    /**
     * creates a new Sign in window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        Blackboard.getBlackboard().getDatabaseController().setUp();
        TempSignInWindow signInWindow = new TempSignInWindow();
        signInWindow.setSize(800,600);
        signInWindow.setVisible(true);
        signInWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Blackboard.getBlackboard().getDatabaseController().shutDown();
                System.exit(0);
            }
        });
    }

    public TempSignInWindow(){
        super("My UML Tutor Sign In");

        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About");
        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(help);
        help.add(about);

        screenPanel = createStartScreen();
        add(screenPanel);

        //action listeners
        about.addActionListener(this);
    }

    /**
     *
     * @return a JPanel with the buttons to select you as a student or an Instructor
     */
    public JPanel createStartScreen(){
        JPanel startScreen = new JPanel();
        startScreen.setLayout(new GridLayout(6,1));

        startScreen.add(new JLabel(""));
        startScreen.add(new JLabel("Welcome to the UML Tutor Application", SwingConstants.CENTER));
        startScreen.add(new JLabel("Are you a Student or and Instructor?", SwingConstants.CENTER));


        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new GridLayout(2,4));
        JButton student = new JButton("Student");
        JButton instructor = new JButton("Instructor");
        for(int i = 0; i < 8; i++){
            if (i == 1){createButtonPanel.add(student);}
            else if (i == 2){createButtonPanel.add(instructor);}
            else{createButtonPanel.add(new JLabel(""));}
        }
        student.addActionListener(this);
        instructor.addActionListener(this);
        startScreen.add(createButtonPanel);
        return startScreen;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){
            case ("About") ->{
                JOptionPane.showMessageDialog(this,
                        "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            case ("Instructor") ->{
                remove(this.getScreenPanel());
                this.setScreenPanel(new InstructorSignInPanel(this));
                add(this.getScreenPanel());
                revalidate();
                repaint();
            }
            case  ("Student") -> {
                remove(this.getScreenPanel());
                this.setScreenPanel(new StudentSignInPanel(this));
                add(this.getScreenPanel());
                revalidate();
                repaint();
            }
        }
    }

}
