import lombok.Getter;
import lombok.Setter;
import javax.swing.*;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * View for the Menu for different types of tutoring subjects
 */
@Getter
@Setter
public class InstructorApp extends JFrame {

    private JPanel screenPanel;
    private JMenu file;
    private InstructorController instructorController = new InstructorController(this);


    public JPanel getScreenPanel() {
        return screenPanel;
    }

    public void setScreenPanel(JPanel screenPanel) {
        this.screenPanel = screenPanel;
    }

    /**
     * creates a new Menu window and allows it to be seen and closed properly.
     * args[0] : username of user
     * args[1] : password of user
     */
    public static void main(){
        Blackboard.getBlackboard().getDatabaseController().setUp();
        InstructorApp instructorApp = new InstructorApp();
        instructorApp.setSize(800,600);
        instructorApp.setVisible(true);
        instructorApp.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Blackboard.getBlackboard().getDatabaseController().shutDown();
                System.exit(0);
            }
        });
    }

    /**
     * Resets the FileMenu to options available for the Menu Window
     */
    public void resetFileMenu(){
        //menu
        if (file != null){
            file.removeAll();
        } else{
            file = new JMenu("File");
        }
        JMenuItem logOut = new JMenuItem("Log Out");

        file.add(logOut);

        logOut.addActionListener(instructorController);

        revalidate();
        repaint();
    }

    /**
     * Sets the FileMenu to options available for Problem Windows
     */
    public void alternativeFileMenu(){
        //menu
        if (file != null){
            file.removeAll();
        } else{
            file = new JMenu("File");
        }
        JMenuItem backToMenu = new JMenuItem("Back To Menu");
        JMenuItem logOut = new JMenuItem("Log Out");

        file.add(backToMenu);
        file.add(logOut);

        backToMenu.addActionListener(instructorController);
        logOut.addActionListener(instructorController);

        revalidate();
        repaint();
    }

    /**
     * Constructor
     */
    public InstructorApp() {
        super("Instructor Menu");

        //menu
        JMenuBar menuBar = new JMenuBar();
        resetFileMenu();
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(help);

        this.screenPanel = new InstructorPage(instructorController);
        add(this.screenPanel);
    }

}
