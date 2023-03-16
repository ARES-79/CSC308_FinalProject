import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * UMLtoMetricsPanel
 * @author Andrew Estrada
 * @version 1.0
 *
 * Panel for questions related to calculating Instability metrics
 */
public class TEMP_UMLtoMetricsPanel extends QuestionPanel {

    private DrawPanel west = new DrawPanel();
    private JTextField numerator, denominator;

    /**
     * Constructor
     */
    public TEMP_UMLtoMetricsPanel(){
        super();
        super.setQuestions(Blackboard.getBlackboard().getUMLtoMetricsQuestions());
        super.setCurrentQuestion(super.getQuestions().get(0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());


        west.removeMouseListener(west.getMouseListeners()[0]);
        west.removeMouseMotionListener(west.getMouseMotionListeners()[0]);
        Blackboard.getBlackboard().addObserver(west);

        //east
        JPanel rightCenter = new JPanel ();
        rightCenter.setLayout(new BorderLayout());

        JToolBar selectionToolBar = new JToolBar();

        JButton submit = new JButton("Submit");
        submit.setContentAreaFilled(false);
        JButton nextQuestion = new JButton("Next");
        nextQuestion.setContentAreaFilled(false);
        JButton requestHint = new JButton("?");
        requestHint.setContentAreaFilled(false);
        requestHint.addActionListener(this);
        nextQuestion.addActionListener(this);
        submit.addActionListener(this);

        selectionToolBar.add(Box.createHorizontalGlue());
        selectionToolBar.add(submit);
        selectionToolBar.add(nextQuestion);
        selectionToolBar.add(requestHint);

        rightCenter.add(selectionToolBar, BorderLayout.NORTH);

        JPanel studentWorkPanel = new JPanel();
        studentWorkPanel.setLayout(new GridLayout(4,1));
        studentWorkPanel.add(new JLabel(""));

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new GridLayout(2,1));
        instructionPanel.add(new JLabel("Calculate the Instability for class A.", SwingConstants.CENTER));
        instructionPanel.add(new JLabel("Give the numerator and denominator.", SwingConstants.CENTER));

        studentWorkPanel.add(instructionPanel);

        JPanel promptAnswerPanel = new JPanel();
        promptAnswerPanel.setLayout(new GridLayout(1,4));
        promptAnswerPanel.add(new JLabel(""));
        promptAnswerPanel.add(new JLabel("Instability = "));

        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(2,1));
        numerator = new JTextField();
        numerator.setColumns(3);
        answerPanel.add(numerator);
        denominator = new JTextField();
        denominator.setColumns(3);
        answerPanel.add(denominator);

        promptAnswerPanel.add(answerPanel);
        promptAnswerPanel.add(new JLabel(""));
        studentWorkPanel.add(promptAnswerPanel);

        rightCenter.add(studentWorkPanel, BorderLayout.CENTER);

        add(rightCenter, BorderLayout.EAST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        west.setBackground(Color.LIGHT_GRAY);
        Blackboard.getBlackboard().addObserver(west);
        centerPanel.add(west, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        Blackboard.getBlackboard().drawUMLtoCodeBoxes(super.getCurrentQuestion());
        Blackboard.getBlackboard().removeObserver(west);
    }

    /**
     * Brings the next question to the screen or says the current question is the last
     */
    @Override
    void showNextQuestion(){
        int current_index = super.getQuestions().indexOf(super.getCurrentQuestion());
        if(current_index + 1 < super.getQuestions().size()){
            super.setCurrentQuestion(super.getQuestions().get(current_index + 1));
            Blackboard.getBlackboard().reset();
            Blackboard.getBlackboard().drawUMLtoCodeBoxes(super.getCurrentQuestion());
            super.setHintIdx(0);
        } else {
            JOptionPane.showMessageDialog(this, "This is the last question!",
                    "", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    @Override
    void submitPressed(){
        String[] answerPair = super.getCurrentQuestion().getAnswer().split(",");
        boolean allCorrect = true;
        String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ",\n";
        if( numerator.getText().strip().equals(answerPair[0]) ){
            message += "Your numerator answer is correct. \n";
        }
        else{
            message += "Your narrator answer is incorrect. \n";
            allCorrect = false;
        }
        if( denominator.getText().strip().equals(answerPair[1]) ){
            message += "Your denominator answer is correct. \n";
        }
        else{
            message += "Your denominator answer is incorrect. \n";
            allCorrect = false;
        }
        if(allCorrect){
            Blackboard.getBlackboard().setBoxList(new ArrayList<>());
            Blackboard.getBlackboard().updateData();
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is correct \nYour updated Code to UML proficiency is:" +s.getCodeToUML(),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            numerator.setText("");
            denominator.setText("");
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
