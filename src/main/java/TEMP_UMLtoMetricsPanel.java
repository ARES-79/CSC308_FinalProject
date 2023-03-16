import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TEMP_UMLtoMetricsPanel extends JPanel implements ActionListener {
    private final ArrayList<Question> questions = Blackboard.getBlackboard().getUMLtoMetricsQuestions();
    private Question currentQuestion = questions.get(0);
    private int hintIdx = 0;

    private DrawPanel west = new DrawPanel();
    private JTextField numerator, denominator;

    /**
     * Constructor
     */
    public TEMP_UMLtoMetricsPanel(){
        super();
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

        CustomTextArea pairedText = new CustomTextArea(30,20);
        MainController mC = new MainController(this, pairedText);
        west.setBackground(Color.LIGHT_GRAY);
        Blackboard.getBlackboard().addObserver(west);
        centerPanel.add(west, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        Blackboard.getBlackboard().drawUMLtoCodeBoxes(questions.get(0));
        Blackboard.getBlackboard().removeObserver(west);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
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
            Blackboard.getBlackboard().reset();
            Blackboard.getBlackboard().drawUMLtoCodeBoxes(currentQuestion);
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
}
