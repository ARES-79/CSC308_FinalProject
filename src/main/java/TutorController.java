import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
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
                host.alternativeFileMenu();
                setScreenPanel(new TEMP_CodeToUMLPanel());
                Blackboard.getBlackboard().reset();
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoUML);
            }
            case ("Code --> Metrics") -> {
                host.alternativeFileMenu();
                setScreenPanel(new TEMP_CodeToMetricsPanel());
                Blackboard.getBlackboard().reset();
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
            }
            case ("UML --> Code") ->{
                // uml to code panel
                host.alternativeFileMenu();
                setScreenPanel(new UMLtoCodePanel());
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
            }
            case ("UML --> Metrics") -> {
                // uml to metrics panel
                host.alternativeFileMenu();
                setScreenPanel(new TEMP_UMLtoMetricsPanel());
                Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoMetrics);
            }
            case ("Practice") -> {
                host.alternativeFileMenu();
                setScreenPanel(new PracticePanel());
                Blackboard.getBlackboard().reset();
            }
            case ("View Progress") -> {
                host.alternativeFileMenu();
                setScreenPanel(createChart());
            }
            case ("Back To Menu") -> {
                host.resetFileMenu();
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
        dataset.setValue(s.getCodeToUML(), "Code To UML","");
        dataset.setValue(s.getUmlToCode(), "UML To Code", "");
        dataset.setValue(s.getCodeToMetrics(), "Code To Metrics", "");
        dataset.setValue(s.getUmlToMetrics(), "UML To Metrics", "");
        dataset.setValue(s.getOverallProficiency(), "Overall", "");
        JFreeChart chart = ChartFactory.createBarChart("Topic Proficiency", "Topic", "Proficiency", dataset, PlotOrientation.VERTICAL,true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 10);
        return new ChartPanel(chart);
    }
}
