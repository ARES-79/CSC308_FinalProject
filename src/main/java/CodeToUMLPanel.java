import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Final Project
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class CodeToUMLPanel extends JPanel implements ActionListener {

    private JTextArea codeProblem = new JTextArea(30,20);
    private DrawPanel east = new DrawPanel();
    QuestionButtonsModel questionButtonsModel = new QuestionButtonsModel();

    public CodeToUMLPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the code below to UML:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);


        codeProblem.setText(Blackboard.getBlackboard().getCodeToUMLQuestions().get(0).getText());
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

        Blackboard.getBlackboard().setCurrentQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions().get(0));
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
                questionButtonsModel.showHint();
            }
        }
    }

    void showNextQuestion(){
        if(questionButtonsModel.showNextQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions())){
            codeProblem.setText(Blackboard.getBlackboard().getCurrentQuestion().getText());
        }
    }


    void submitPressed(){
        Parser parser = new Parser();
        String studentAttempt = parser.parseClasses(Blackboard.getBlackboard().getBoxList()); //.sort(Comparator.comparing(UMLComponent::getName)));
        if (questionButtonsModel.submitPressed(studentAttempt)){
            Blackboard.getBlackboard().updateData();
            showNextQuestion();
        }
    }
}
