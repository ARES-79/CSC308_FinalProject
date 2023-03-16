import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.2
 * Parent panel to all panels with a correct answer
 */
public abstract class QuestionPanel extends JPanel implements ActionListener {

    private ArrayList<Question> questions = new ArrayList<>();
    private Question currentQuestion;
    private int hintIdx = 0;

    /**
     * getter
     * @return questions retrieved for the panel type
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * setter
     * @param questions list of questions that correspond to the panel type
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * getter
     * @return current question which should be displayed on screen
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * setter
     * @param currentQuestion question to be displayed for students to answer
     */
    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    /**
     * setter
     * @param hintIdx index of hint to be displayed next
     */
    public void setHintIdx(int hintIdx) {
        this.hintIdx = hintIdx;
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
        if(hintIdx < currentQuestion.getHints().size()){
            JOptionPane.showMessageDialog(this, currentQuestion.getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
            hintIdx++;
        } else{
            hintIdx = 0;
            JOptionPane.showMessageDialog(this, currentQuestion.getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * overridden method for reacting to an onscreen event
     * @param e event that was triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case ("Submit") -> submitPressed();
            case ("Next") -> showNextQuestion();
            case ("?") -> showHint();
        }

    }

}
