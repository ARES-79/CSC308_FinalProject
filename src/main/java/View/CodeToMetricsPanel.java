package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * View.CodeToMetricsPanel
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 *
 * Panel for questions related to calculating LOC metrics
 */
public class CodeToMetricsPanel extends QuestionPanel {

    private JTextArea codeProblem = new JTextArea(30,30);
    private JTextField locA, elocA, llocA;

    /**
     * Constructor
     */
    public CodeToMetricsPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Determine metrics for the code below:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);

        Blackboard.getBlackboard().setCurrentQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions().get(0));
        codeProblem.setText(Blackboard.getBlackboard().getCurrentQuestion().getText());
        codeProblem.setEditable(false);
        JScrollPane scroll = new JScrollPane (codeProblem,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll, BorderLayout.CENTER);
        leftCenter.setVisible (true);

        add(leftCenter, BorderLayout.WEST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JToolBar selectionToolBar = new JToolBar();
        JButton submit = new JButton("Submit");
        submit.setContentAreaFilled(false);
        JButton nextQuestion = new JButton("Next");
        nextQuestion.setContentAreaFilled(false);
        JButton requestHint = new JButton("?");
        requestHint.setContentAreaFilled(false);

        selectionToolBar.add(Box.createHorizontalGlue());
        selectionToolBar.add(submit);
        selectionToolBar.add(nextQuestion);
        selectionToolBar.add(requestHint);

        centerPanel.add(selectionToolBar, BorderLayout.NORTH);

        JPanel metricsPanel = new JPanel();
        metricsPanel.setLayout(new GridLayout(7,1));

        metricsPanel.add(new JLabel(""));
        JPanel locQ = new JPanel();
        locQ.add(new JLabel("What is the count of LOC for the code?"));
        locA = new JTextField();
        locA.setColumns(3);
        locQ.add(locA);
        metricsPanel.add(locQ);

        metricsPanel.add(new JLabel(""));

        JPanel elocQ = new JPanel();
        elocQ.add(new JLabel("What is the count of eLOC for the code?"));
        elocA = new JTextField();
        elocA.setColumns(3);
        elocQ.add(elocA);
        metricsPanel.add(elocQ);

        metricsPanel.add(new JLabel(""));

        JPanel llocQ = new JPanel();
        llocQ.add(new JLabel("What is the count of lLOC for the code?"));
        llocA = new JTextField();
        llocA.setColumns(3);
        llocQ.add(llocA);
        metricsPanel.add(llocQ);

        metricsPanel.add(new JLabel(""));

        centerPanel.add(metricsPanel, BorderLayout.CENTER);


        add(centerPanel, BorderLayout.CENTER);

        //Action Listeners
        requestHint.addActionListener(this);
        nextQuestion.addActionListener(this);
        submit.addActionListener(this);
    }

    /**
     * Brings the next question to the screen or says the current question is the last
     */
    void showNextQuestion(){
        if(super.getQuestionButtonsModel().showNextQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions())){
            codeProblem.setText(Blackboard.getBlackboard().getCurrentQuestion().getText());
        }
    }

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    void submitPressed(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String locAnswer = locA.getText().trim();
        String elocAnswer = elocA.getText().trim();
        String llocAnswer = llocA.getText().trim();
        boolean allCorrect = true;
        String code = Blackboard.getBlackboard().getCurrentQuestion().getText();
        String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ",\n";

        if( locAnswer.equals( String.valueOf(calculator.totalLOC(code)) )){
            message += "Your LOC answer is correct. \n";
        }
        else{
            message += "Your LOC answer is incorrect. \n";
            allCorrect = false;
        }
        if( elocAnswer.equals( String.valueOf(calculator.totalELOC(code)) )){
            message += "Your eLOC answer is correct. \n";
        }
        else{
            message += "Your eLOC answer is incorrect. \n";
            allCorrect = false;
        }
        if( llocAnswer.equals( String.valueOf(calculator.totalLLOC(code)) )){
            message += "Your lLOC answer is correct. \n";
        }
        else{
            message += "Your LOC answer is incorrect. \n";
            allCorrect = false;
        }
        if(allCorrect){
            Blackboard.getBlackboard().setBoxList(new ArrayList<>());
            Blackboard.getBlackboard().updateData();
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            message += "Your updated Code to Metrics proficiency is:" + s.getCodeToMetrics();
            JOptionPane.showMessageDialog(this,
                    message,
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            locA.setText("");
            elocA.setText("");
            llocA.setText("");
            showNextQuestion();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    message,
                    "Incorrect Answer",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
