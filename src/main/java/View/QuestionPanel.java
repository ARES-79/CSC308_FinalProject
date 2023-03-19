package View;

import Model.Blackboard;
import Model.QuestionButtonsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View.QuestionPanel
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.2
 *
 * Parent panel to all panels with a correct answer
 */
public abstract class QuestionPanel extends JPanel implements ActionListener {
    private QuestionButtonsModel questionButtonsModel = new QuestionButtonsModel();
    private int hintIdx = 0;

    /**
     * getter
     * @return class object for question button model
     */
    public QuestionButtonsModel getQuestionButtonsModel() {
        return questionButtonsModel;
    }

    /**
     * Brings the next question to the screen
     */
    abstract void showNextQuestion();

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    abstract void submitPressed();

    /**
     * Shows question hints to students
     */
    void showHint(){
        if(hintIdx < Blackboard.getBlackboard().getCurrentQuestion().getHints().size()){
            JOptionPane.showMessageDialog(this, Blackboard.getBlackboard().getCurrentQuestion().getHints().get(hintIdx).getText(), "Model.Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
            hintIdx++;
        } else{
            hintIdx = 0;
            JOptionPane.showMessageDialog(this, Blackboard.getBlackboard().getCurrentQuestion().getHints().get(hintIdx).getText(), "Model.Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * overridden method for reacting to an onscreen event
     * @param e event that was triggered
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("Submit") -> submitPressed();
            case ("Next") -> showNextQuestion();
            case ("?") -> showHint();
        }
    }

}
