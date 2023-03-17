import javax.swing.*;
import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna, Andrew Estrada, Mitashi Parikh
 * @version 1.0
 *
 * Panel for questions related to translating UML to Code
 */
public class UMLtoCodePanel extends QuestionPanel {

    private JTextArea codeProblem = new CustomTextArea(30,30);

    /**
     * Constructor
     */
    public UMLtoCodePanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        DrawPanel east = new DrawPanel();
        east.removeMouseListener(east.getMouseListeners()[0]);
        east.removeMouseMotionListener(east.getMouseMotionListeners()[0]);
        Blackboard.getBlackboard().addObserver(east);

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the UML below into code:");

        codeProblem.setEditable(true);
        JScrollPane scroll = new JScrollPane (codeProblem,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll, BorderLayout.CENTER);
        leftCenter.setVisible (true);

        add(leftCenter, BorderLayout.EAST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        east.setBackground(Color.LIGHT_GRAY);
        Blackboard.getBlackboard().addObserver(east);
        centerPanel.add(east, BorderLayout.CENTER);

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

        centerPanel.add(instructionLabel, BorderLayout.NORTH);
        leftCenter.add(selectionToolBar, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        Blackboard.getBlackboard().drawUMLtoCodeBoxes(Blackboard.getBlackboard().getUMLtoCodeQuestions().get(0));
        Blackboard.getBlackboard().setCurrentQuestion(Blackboard.getBlackboard().getUMLtoCodeQuestions().get(0));
    }

    /**
     * Brings the next question to the screen or says the current question is the last
     */
    void showNextQuestion(){
        if(super.getQuestionButtonsModel().showNextQuestion(Blackboard.getBlackboard().getUMLtoCodeQuestions())){
            Blackboard.getBlackboard().drawUMLtoCodeBoxes(Blackboard.getBlackboard().getCurrentQuestion());
            codeProblem.setText("");
        }
    }

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    void submitPressed(){
        String studentAttempt = codeProblem.getText();
        if(super.getQuestionButtonsModel().submitPressed(studentAttempt))
            showNextQuestion();
    }

}
