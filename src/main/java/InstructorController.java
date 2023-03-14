import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorController implements ActionListener {

    private InstructorApp host;

    /**
     * Constructor
     *
     * @param host TutorApp JFrame host of the panel
     */
    public InstructorController(InstructorApp host){
        this.host = host;
    }
    /**
     * Gives functionality to the help menu
     * @param e the event notifying that an on screen element has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(host,
                    "Authors: \n\t- Andrew Estrada \n\t- Jamie Luna \n\t- Archie Jones\n\t- Mitashi Parikh",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getActionCommand().equals("Log Out")){
            TempSignInWindow.main(null);
            host.dispose();
        }
        else if(e.getActionCommand().equals("Back To Menu")){
            this.setScreenPanel(new InstructorPage(this));
        }
        else{
            Student student = Blackboard.getBlackboard().getDatabaseController().getStudentByUsername(e.getActionCommand());
            host.alternativeFileMenu();
            host.remove(host.getScreenPanel());
            host.setScreenPanel(createChart(student));
            host.add(host.getScreenPanel());
            host.revalidate();
            host.repaint();
        }
    }
    private ChartPanel createChart(Student s){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(s.getCodetoUML(), "Code To UML","");
        dataset.setValue(s.getUMLtoCode(), "UML To Code", "");
        dataset.setValue(s.getCodetoMetrics(), "Code To Metrics", "");
        dataset.setValue(s.getUMLtoMetrics(), "UML To Metrics", "");
        dataset.setValue(s.getOverallProficiency(), "Overall", "");
        JFreeChart chart = ChartFactory.createBarChart(s.getFirstName() + "'s proficiency", "Topic", "Proficiency", dataset, PlotOrientation.VERTICAL,true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 10);
        return new ChartPanel(chart);
    }
    public void setScreenPanel(JPanel nextPanel){
        host.alternativeFileMenu();
        host.remove(host.getScreenPanel());
        host.setScreenPanel( nextPanel );
        host.add(host.getScreenPanel());
        host.revalidate();
        host.repaint();
        Blackboard.getBlackboard().reset();
    }
}
