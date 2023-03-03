import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Final Project
 * @author Andrew Estrada
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

    public TEMP_CodeToUMLPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        //west
        JPanel leftCenter = new JPanel ();
        leftCenter.setLayout(new BorderLayout());

        JLabel codeLabel = new JLabel("Source Code:");
        codeLabel.setSize(400,100);
        leftCenter.add(codeLabel, BorderLayout.NORTH);

        JTextArea codeProblem = new JTextArea(30,20);
        codeProblem.setText(TEMPTEXT1);
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

        JButton nextQuestion = new JButton("Next");
        nextQuestion.setContentAreaFilled(false);
        JButton requestHint = new JButton("?");
        requestHint.setContentAreaFilled(false);
        requestHint.addActionListener(this);
        nextQuestion.addActionListener(this);

        selectionToolBar.add(association);
        selectionToolBar.add(inheritance);
        selectionToolBar.add(composition);
        selectionToolBar.add(Box.createHorizontalGlue());
        selectionToolBar.add(nextQuestion);
        selectionToolBar.add(requestHint);

        centerPanel.add(selectionToolBar, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        //possibly add a status bar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        int hintCount = 0;
        switch (e.getActionCommand()) {
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
                Hint hint1 = new Hint("hint1");
                Hint hint2 = new Hint("hint2");
                Hint hint3 = new Hint("hint3");
                ArrayList<Hint> hints = new ArrayList<>();
                hints.add(hint1);
                hints.add(hint2);
                hints.add(hint3);
                Question question = new Question(1, "Does this work?", "Maybe", hints, 1);
                hintCount ++;
                JOptionPane.showMessageDialog(this, question.getHints().get(hintCount).getText(), "Hint", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void addQuestionToScreen(Question question){
    }

    void showHint(Question question, int idx){
        JOptionPane.showMessageDialog(this, question.getHints().get(idx).getText(), "Hint", JOptionPane.INFORMATION_MESSAGE);
    }

}
