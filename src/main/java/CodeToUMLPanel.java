import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Final Project
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class CodeToUMLPanel extends QuestionPanel {

    private JTextArea codeProblem = new JTextArea(30,20);

    public CodeToUMLPanel(){
        super();
        super.setQuestions(Blackboard.getBlackboard().getCodeToUMLQuestions());
        super.setCurrentQuestion(super.getQuestions().get(0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the code below to UML:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);


        codeProblem.setText(super.getCurrentQuestion().getText());
        codeProblem.setEditable(false);
        JScrollPane scroll = new JScrollPane (codeProblem,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftCenter.add(scroll, BorderLayout.CENTER);
        leftCenter.setVisible (true);

        add(leftCenter, BorderLayout.WEST);

        //center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        CustomTextArea pairedText = new CustomTextArea(30,20);
        MainController mC = new MainController(this, pairedText);
        DrawPanel east = new DrawPanel();
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
        JButton submit = new JButton("Submit");
        submit.setContentAreaFilled(false);
        JButton nextQuestion = new JButton("Next");
        nextQuestion.setContentAreaFilled(false);
        JButton requestHint = new JButton("?");
        requestHint.setContentAreaFilled(false);
        requestHint.addActionListener(this);
        nextQuestion.addActionListener(this);
        submit.addActionListener(this);

        selectionToolBar.add(association);
        selectionToolBar.add(inheritance);
        selectionToolBar.add(composition);
        selectionToolBar.add(Box.createHorizontalGlue());
        selectionToolBar.add(newButton);
        selectionToolBar.add(submit);
        selectionToolBar.add(nextQuestion);
        selectionToolBar.add(requestHint);

        centerPanel.add(selectionToolBar, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        //possibly add a status bar
        //Action Listeners
        newButton.addActionListener(mC);
        association.addActionListener(mC);
        inheritance.addActionListener(mC);
        composition.addActionListener(mC);
    }

    @Override
    void showNextQuestion(){
        int current_index = super.getQuestions().indexOf(super.getCurrentQuestion());
        if(current_index + 1 < super.getQuestions().size()){
            Blackboard.getBlackboard().reset();
            super.setCurrentQuestion(super.getQuestions().get(current_index + 1));
            codeProblem.setText(super.getCurrentQuestion().getText());
            super.setHintIdx(0);
        } else {
            JOptionPane.showMessageDialog(this, "This is the last question!",
                    "", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    void submitPressed(){
        Parser parser = new Parser();
        String studentAttempt = parser.parseClasses(Blackboard.getBlackboard().getBoxList()); //.sort(Comparator.comparing(UMLComponent::getName)));
        if(super.getCurrentQuestion().checkAnswer(studentAttempt)){
            Blackboard.getBlackboard().setBoxList(new ArrayList<>());
            Blackboard.getBlackboard().updateData();
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is correct \nYour updated Code to UML proficiency is:" +s.getCodeToUML(),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            showNextQuestion();
        }
        else{
            String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is incorrect.";
            if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "class") < Blackboard.getBlackboard().getBoxList().size()){
                message += "\nHint: You have made too many classes!";
            }
            else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "class") > Blackboard.getBlackboard().getBoxList().size()){
                message += "\nHint: You still need to make more classes";
            }
            else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), ";") > StringUtils.countMatches(studentAttempt, ";")) {
                message += "\nHint: Check if you have added all the required variables";
            }
            else if (StringUtils.countMatches(super.getCurrentQuestion().getAnswer(), "()") > StringUtils.countMatches(studentAttempt, "()")){
                message += "\nHint: Check if you have added all the required methods";
            }
            else if (!areClassNamesCorrect(super.getCurrentQuestion().getAnswer(), studentAttempt)){
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
