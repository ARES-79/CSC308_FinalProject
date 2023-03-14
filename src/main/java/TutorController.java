import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TutorController implements ActionListener {

    private TutorApp host;

    /**
     * Constructor
     *
     * @param host TutorApp JFrame host of the panel
     */
    public TutorController(TutorApp host){
        this.host = host;
    }

    /**
     * updates what is seen on screen
     *
     * @param nextPanel the panel set tp take over the screen
     */
    public void setScreenPanel(JPanel nextPanel){
        host.alternativeFileMenu();
        host.remove(host.getScreenPanel());
        host.setScreenPanel( nextPanel );
        host.add(host.getScreenPanel());
        host.revalidate();
        host.repaint();
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
                JOptionPane.showMessageDialog(host,
                        "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);}
            case ("Code --> UML") -> {
                setScreenPanel(new TEMP_CodeToUMLPanel());
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoUML);
            }
            case ("Code --> Metrics") -> {
                setScreenPanel(new TEMP_CodeToMetricsPanel());
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
            }
            case ("UML --> Code") ->{
                // uml to code panel
            }
            case ("UML --> Metrics") -> {
                // uml to metrics panle
            }
            case ("Practice") -> {
                setScreenPanel(new PracticePanel());
            }
            case ("View Progress") -> {
                // progress panel
            }
            case ("Back To Menu") -> {
                setScreenPanel(new MenuPanel(this));
            }
            case ("Log Out") -> {
                TempSignInWindow.main(null);
                host.dispose();
            }

        }

    }
}
