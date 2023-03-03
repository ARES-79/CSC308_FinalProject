import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * Final Project
 * @author Andrew Estrada, Mitashi Parikh
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class TEMP_CodeToUMLPanel extends JPanel implements ActionListener {

    private final String TEMPTEXT = """





                        class A {
                        
                        }
                        
                        """;
    //TODO: Load Question from DB
    private Question question = new Question(1, TEMPTEXT, TEMPTEXT, null, 1);

    public TEMP_CodeToUMLPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the code below to UML:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);

        JTextArea codeProblem = new JTextArea(30,20);
        codeProblem.setText(TEMPTEXT);
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
        selectionToolBar.add(submit);
        selectionToolBar.add(nextQuestion);
        selectionToolBar.add(requestHint);

        centerPanel.add(selectionToolBar, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        //possibly add a status bar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("Submit") -> {
                submitPressed();
            }
            case ("Next") -> {
//                Hint hint1 = new Hint("hint1");
//                Hint hint2 = new Hint("hint2");
//                Hint hint3 = new Hint("hint3");
//                ArrayList<Hint> hints = new ArrayList<>();
//                hints.add(hint1);
//                hints.add(hint2);
//                hints.add(hint3);
//                Question question = new Question("Does this work?", "Maybe", hints, 1);
//                JLabel testQ = new JLabel(question.getText());
//                System.out.println(question.getText());
            }
            case ("?") -> {
//                Hint hint1 = new Hint("hint1");
//                Hint hint2 = new Hint("hint2");
//                Hint hint3 = new Hint("hint3");
//                ArrayList<Hint> hints = new ArrayList<>();
//                hints.add(hint1);
//                hints.add(hint2);
//                hints.add(hint3);
//                Question question = new Question("Does this work?", "Maybe", hints, 1);

            }
        }
    }

    void addQuestionToScreen(Question question){

    }

    void submitPressed(){
        Parser parser = new Parser();
        String answer = parser.parseClasses(Blackboard.getBlackboard().getBoxList()); //.sort(Comparator.comparing(UMLComponent::getName)));
        if(question.checkAnswer(answer)){
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
//            s.updateProficiency();
            JOptionPane.showMessageDialog(this,
                    "Your answer is correct \nYou updated Code to UML proficiency is:" +s.getSubjectProficiency().get(SubjectType.CodetoUML),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Your answer is incorrect",
                    "Incorrect Answer",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
