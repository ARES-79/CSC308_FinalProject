import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UMLtoCodePanel extends QuestionPanel {

    //private CustomTextArea pairedText = new CustomTextArea(30,20);
    private JTextArea codeProblem = new CustomTextArea(30,30);

    public UMLtoCodePanel(){
        super();
        super.setQuestions(Blackboard.getBlackboard().getUMLtoCodeQuestions());
        super.setCurrentQuestion(super.getQuestions().get(0));
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

        Blackboard.getBlackboard().drawUMLtoCodeBoxes(super.getCurrentQuestion());
    }

    @Override
    void showNextQuestion(){
        int current_index = super.getQuestions().indexOf(super.getCurrentQuestion());
        if( current_index + 1 < super.getQuestions().size()){
            super.setCurrentQuestion(super.getQuestions().get(current_index+1));
            Blackboard.getBlackboard().reset();
            Blackboard.getBlackboard().drawUMLtoCodeBoxes(super.getCurrentQuestion());
            super.setHintIdx(0);
        } else {
            JOptionPane.showMessageDialog(this, "This is the last question!",
                    "", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    void submitPressed(){
        String studentAttempt = codeProblem.getText();
        if (super.getCurrentQuestion().checkAnswer(codeProblem.getText())){
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is correct \nYour updated UML to Code proficiency is:" +s.getUmlToCode(),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            showNextQuestion();
            Blackboard.getBlackboard().reset();
        } else {

                String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is incorrect.";
                if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "class") < StringUtils.countMatches(studentAttempt, "class")) {
                    message += "\nHint: You have made too many classes!";
                } else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "class") > StringUtils.countMatches(studentAttempt, "class")) {
                    message += "\nHint: You still need to make more classes";
                } else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), ";") > StringUtils.countMatches(studentAttempt, ";")) {
                    message += "\nHint: Check if you have added all the required variables";
                } else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "()") > StringUtils.countMatches(studentAttempt, "()")) {
                    message += "\nHint: Check if you have added all the required methods";
                } else if (!areClassNamesCorrect(super.getCurrentQuestion().getAnswer(), studentAttempt)) {
                    message += "\nHint: Are you naming your classes correctly?";
                }
                JOptionPane.showMessageDialog(this,
                        message,
                        "Incorrect Answer",
                        JOptionPane.ERROR_MESSAGE);
            }

    }

    boolean areClassNamesCorrect(String correctAns, String studentAns){
        correctAns = correctAns.trim().replace("\n", " ").replace("\t", "");
        studentAns = studentAns.trim().replace("\n", " ");
        String[] correctAnswer = correctAns.split(" ");
        String[] studentAnswer = studentAns.split(" ");
        ArrayList<String> correctClasses = new ArrayList<>();
        ArrayList<String> studentClasses = new ArrayList<>();
        for (int i = 0; i < correctAnswer.length-1; i++){
            if(correctAnswer[i].equals("class")){
                correctClasses.add(correctAnswer[i+1]);
            }
        }
        for (int i = 0; i < studentAnswer.length-1; i++){
            if(studentAnswer[i].equals("class")){
                studentClasses.add(studentAnswer[i+1]);
            }
        }
        return correctClasses.equals(studentClasses);
    }
}
