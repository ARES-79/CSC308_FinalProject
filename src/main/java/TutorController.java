import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


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
        Blackboard.getBlackboard().reset();
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
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new UMLtoCodePanel());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
            }
            case ("UML --> Metrics") -> {
                // uml to metrics panle
            }
            case ("Practice") -> {
                setScreenPanel(new PracticePanel());
            }
            case ("View Progress") -> {
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(createChart());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
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

    private ChartPanel createChart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Student s = (Student)Blackboard.getBlackboard().getCurrentUser();
        dataset.setValue(s.getCodetoUML(), "Code To UML","");
        dataset.setValue(s.getUMLtoCode(), "UML To Code", "");
        dataset.setValue(s.getCodetoMetrics(), "Code To Metrics", "");
        dataset.setValue(s.getUMLtoMetrics(), "UML To Metrics", "");
        dataset.setValue(s.getOverallProficiency(), "Overall", "");
        JFreeChart chart = ChartFactory.createBarChart("Topic Proficiency", "Topic", "Proficiency", dataset, PlotOrientation.VERTICAL,true, true, false);
        return new ChartPanel(chart);
    }
}
