import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryCrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;


public class TutorController implements ActionListener {

    private TutorApp host;

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
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new TEMP_CodeToMetricsPanel());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
            }
            case ("UML --> Code") ->{
                // uml to code panel
            }
            case ("UML --> Metrics") -> {
                // uml to metrics panle
            }
            case ("Practice") -> {
                host.alternativeFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new PracticePanel());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
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
                host.resetFileMenu();
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new MenuPanel(this));
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
