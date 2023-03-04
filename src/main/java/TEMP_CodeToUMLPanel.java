import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Final Project
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class TEMP_CodeToUMLPanel extends JPanel implements ActionListener {

    private final String TEMPTEXT1 = """





                        class A {
                        
                        }
                        
                        """;

    private final String TEMPTEXT2 = """





                        class B {
                            A a;
                            C c;
                            method(){
                            
                            }
                        }
                        
                        """;

    //TODO: Load Question from DB
    //will come from Blackboard eventually:
    private Hint hint1 = new Hint("hint1");
    private Hint hint2 = new Hint("hint2");
    private Hint hint3 = new Hint("hint3");
    private ArrayList<Hint> hints_list = new ArrayList<Hint>(Arrays.asList(hint1, hint2, hint3));
    private Question question1 = new Question(1, TEMPTEXT1, TEMPTEXT1, hints_list, 1);
    private Question question2 = new Question(1, TEMPTEXT2, TEMPTEXT2, hints_list, 1);
    private ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question1, question2));
    private Question currentQuestion = question1;
    private int hintIdx = 0;

    private JTextArea codeProblem = new JTextArea(30,20);
    private DrawPanel east = new DrawPanel();

    public TEMP_CodeToUMLPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the code below to UML:");
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

        CustomTextArea pairedText = new CustomTextArea(30,20);
        MainController mC = new MainController(this, pairedText);
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
        Parser parser = new Parser();
        String answer = parser.parseClasses(Blackboard.getBlackboard().getBoxList()); //.sort(Comparator.comparing(UMLComponent::getName)));
        if(currentQuestion.checkAnswer(answer)){
            Blackboard.getBlackboard().setBoxList(new ArrayList<>());
            Blackboard.getBlackboard().updateData();
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
//            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    "Your answer is correct \nYou updated Code to UML proficiency is:" +s.getSubjectProficiency().get(SubjectType.CodetoUML),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            showNextQuestion();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Your answer is incorrect",
                    "Incorrect Answer",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
