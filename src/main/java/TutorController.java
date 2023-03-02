import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorController implements ActionListener {

    TutorApp host;

    public TutorController(TutorApp host){
        this.host = host;
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
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new TEMP_CodeToUMLPanel());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoUML);
            }
            case ("Code --> Metrics") -> {
                host.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
                new TEMP_CodeToMetricsWindow(host);
            }
            case ("UML --> Code") ->{
                host.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
                new TEMP_UMLToCodeWindow(host);
            }
            case ("View Progress") -> {
                //
            }
            case ("Back To Menu") -> {
                host.resetFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new MenuPanel(host.getUsername(), this));
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
            case ("Log Out") -> {
                TempSignInWindow.main(null);
                host.dispose();
            }

        }

    }
}
