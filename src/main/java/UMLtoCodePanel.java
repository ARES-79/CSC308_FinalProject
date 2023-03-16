import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UMLtoCodePanel extends JPanel implements ActionListener {
    //private CustomTextArea pairedText = new CustomTextArea(30,20);
    private JTextArea codeProblem = new CustomTextArea(30,30);
    QuestionButtonsModel questionButtonsModel = new QuestionButtonsModel();

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
//        leftCenter.add(instructionLabel, BorderLayout.NORTH);


        codeProblem.setEditable(true);
        JScrollPane scroll = new JScrollPane (codeProblem,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll, BorderLayout.CENTER);
        leftCenter.setVisible (true);

        add(leftCenter, BorderLayout.EAST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

//        CustomTextArea pairedText = new CustomTextArea(30,20);
        //MainController mC = new MainController(this, pairedText);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("Submit") -> {
                submitPressed();
            }
            case ("Next") -> {
                showNextQuestion();
            }
            case ("?") -> {
                questionButtonsModel.showHint();
            }
        }

    }

    void showNextQuestion(){
        if(questionButtonsModel.showNextQuestion(Blackboard.getBlackboard().getUMLtoCodeQuestions())){
            Blackboard.getBlackboard().drawUMLtoCodeBoxes(Blackboard.getBlackboard().getCurrentQuestion());
        }
    }

    void submitPressed(){
        String studentAttempt = codeProblem.getText();
        if(questionButtonsModel.submitPressed(studentAttempt))
            showNextQuestion();
    }

}
