import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Final Project
 * @author Jamie Luna, Andrew Estrada, Mitashi Parikh
 * @version 1.0
 *
 * Panel for questions related to translating Code to UML
 */
public class CodeToUMLPanel extends QuestionPanel {

    private JTextArea codeProblem = new JTextArea(30,20);

    /**
     * Constructor
     */
    public CodeToUMLPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Translate the code below to UML:");
        leftCenter.add(instructionLabel, BorderLayout.NORTH);


        Blackboard.getBlackboard().setCurrentQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions().get(0));
        codeProblem.setText(Blackboard.getBlackboard().getCurrentQuestion().getText());
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

    /**
     * Brings the next question to the screen or says the current question is the last
     */
    @Override
    void showNextQuestion(){
        if(super.getQuestionButtonsModel().showNextQuestion(Blackboard.getBlackboard().getCodeToUMLQuestions())){
            codeProblem.setText(Blackboard.getBlackboard().getCurrentQuestion().getText());
        }
    }

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    @Override
    void submitPressed(){
        Parser parser = new Parser();
        String studentAttempt = parser.parseClasses(Blackboard.getBlackboard().getBoxList()); //.sort(Comparator.comparing(UMLComponent::getName)));
        if (super.getQuestionButtonsModel().submitPressed(studentAttempt)){
            Blackboard.getBlackboard().setBoxList(new ArrayList<>());
            Blackboard.getBlackboard().updateData();
            showNextQuestion();
        }
    }
}
