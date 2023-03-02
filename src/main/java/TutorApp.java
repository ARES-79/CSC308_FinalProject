import javax.swing.*;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * View for the Menu for different types of tutoring subjects
 */
public class TutorApp extends JFrame {

    private final String username;
    private JPanel screenPanel;
    private JMenu file;
    private TutorController controller = new TutorController(this);


    public String getUsername() {
        return username;
    }

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
    public static void main(String[]args){
        System.out.println("Username: " + args[0] + "    Password: " + args[1]);
        Blackboard.getBlackboard().getDatabaseController().setUp();
        TutorApp menu = new TutorApp(args[0]);
        menu.setSize(800,600);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JMenuItem progress = new JMenuItem("View Progress");
        JMenuItem logOut = new JMenuItem("Log Out");

        file.add(progress);
        file.add(logOut);

        progress.addActionListener(controller);
        logOut.addActionListener(controller);

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
        JMenuItem progress = new JMenuItem("View Progress");
        JMenuItem backToMenu = new JMenuItem("Back To Menu");
        JMenuItem logOut = new JMenuItem("Log Out");

        file.add(progress);
        file.add(backToMenu);
        file.add(logOut);

        progress.addActionListener(controller);
        backToMenu.addActionListener(controller);
        logOut.addActionListener(controller);

        revalidate();
        repaint();
    }

    /**
     * Constructor
     */
    public TutorApp(String username) {
        super("My UML Tutor Menu");
        this.username  = username;

        //menu
        JMenuBar menuBar = new JMenuBar();
        resetFileMenu();
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(help);

        screenPanel = new MenuPanel(username, controller);
        add(screenPanel);

        //actionListeners
        about.addActionListener(controller);

    }

}
