import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Final Project
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class TEMP_CodeToMetricsPanel extends JPanel implements ActionListener {

    private final ArrayList<Question> questions = Blackboard.getBlackboard().getCodeToUMLQuestions();
    private Question currentQuestion = questions.get(0);
    private int hintIdx = 0;

    private JTextArea codeProblem = new JTextArea(30,30);
    private JTextField locA, elocA, llocA;

    public TEMP_CodeToMetricsPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Determine metrics for the code below:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);


        codeProblem.setText(questions.get(0).getText());
        codeProblem.setEditable(false);
        JScrollPane scroll = new JScrollPane (codeProblem,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll, BorderLayout.CENTER);
        leftCenter.setVisible (true);

        add(leftCenter, BorderLayout.WEST);

        //center
        // TODO: Connect this to the blackboard and a CustomTextArea
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        int hintCount = 0;
        switch (e.getActionCommand()) {
            case ("Submit") -> {
                submitPressed();
            }
            case ("Next") -> {
                showNextQuestion();
            }
            case ("?") -> {
                showHint();
            }
        }
    }

    void showNextQuestion(){
        if(questions.indexOf(currentQuestion) + 1 < questions.size()){
            currentQuestion = questions.get(questions.indexOf(currentQuestion) + 1);
            codeProblem.setText(currentQuestion.getText());
        } else {
            JOptionPane.showMessageDialog(this, "This is the last question!",
                    "", JOptionPane.WARNING_MESSAGE);
        }
    }

    void showHint(){
        if(hintIdx < currentQuestion.getHints().size()){
            JOptionPane.showMessageDialog(this, currentQuestion.getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
            hintIdx++;
        } else{
            hintIdx = 0;
            JOptionPane.showMessageDialog(this, currentQuestion.getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void submitPressed(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String locAnswer = locA.getText().trim();
        String elocAnswer = elocA.getText().trim();
        String llocAnswer = llocA.getText().trim();
        boolean allCorrect = true;
        String code = questions.get(questions.indexOf(currentQuestion)).getText();
        String message = "";

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
//            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    message, // + "You updated Code to UML proficiency is:" + s.getSubjectProficiency().get(SubjectType.CodetoUML),
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
