import javax.swing.*;
import java.awt.*;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 *
 * Panel that allows students to practice changing both code and UML
 */
public class PracticePanel  extends JPanel{
    CustomTextArea textArea;
    DrawPanel east;
    CodeMetricCalculator codeCalc;

    /**
     * Constructor
     */
    public PracticePanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel allLeft = new JPanel();
        allLeft.setLayout(new BorderLayout());

        JButton update = new JButton("Update");
        allLeft.add(update, BorderLayout.NORTH);

        JPanel leftCenter = new JPanel ();
        textArea = new CustomTextArea(30,20);
        MainController mC = new MainController(this, textArea);
        Blackboard.getBlackboard().addObserver(textArea);
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll);
        leftCenter.setVisible (true);
        add(leftCenter, BorderLayout.WEST);
        allLeft.add(leftCenter, BorderLayout.CENTER);

        add(allLeft, BorderLayout.WEST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        east = new DrawPanel();
        east.setBackground(Color.LIGHT_GRAY);
        Blackboard.getBlackboard().addObserver(east);
        centerPanel.add(east, BorderLayout.CENTER);

        JToolBar selectionToolBar = new JToolBar();
        JRadioButton association = new JRadioButton("Association");
        JRadioButton inheritance = new JRadioButton("Inheritance");
        JRadioButton composition = new JRadioButton("Composition");
        association.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(association);
        group.add(inheritance);
        group.add(composition);

        JButton newButton = new JButton("Reset");
        newButton.setActionCommand("New");
        newButton.setContentAreaFilled(false);

        selectionToolBar.add(association);
        selectionToolBar.add(inheritance);
        selectionToolBar.add(composition);
        selectionToolBar.add(Box.createHorizontalGlue());
        selectionToolBar.add(newButton);

        centerPanel.add(selectionToolBar, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        //South
        JTextArea south = new JTextArea("\t LOC: 0\t\t eLOC: 0\t\t lLOC: 0");
        codeCalc = new CodeMetricCalculator(south, textArea);
        Blackboard.getBlackboard().addObserver(codeCalc);
        add(south, BorderLayout.SOUTH);

        //Action Listeners
        update.addActionListener(mC);
        newButton.addActionListener(mC);
        association.addActionListener(mC);
        inheritance.addActionListener(mC);
        composition.addActionListener(mC);
    }

    public void tearDown(){
        Blackboard.getBlackboard().removeObserver(textArea);
        Blackboard.getBlackboard().removeObserver(east);
        Blackboard.getBlackboard().removeObserver(codeCalc);
    }
}
