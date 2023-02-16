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
                host.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                new TEMP_CodeToUMLWindow(host);
            }
            case ("Code --> Metrics") -> {
                host.setVisible(false);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                new TEMP_CodeToMetricsWindow(host);
            }
            case ("UML --> Code") ->{
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new TEMP_UMLToCodePanel());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
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
