import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.util.ArrayList;

public class QuestionButtonsModel {

    private int hintIdx = 0;
    private int questionIdx = 0;

    public int getQuestionIdx() {
        return questionIdx;
    }

    void showHint(){
        if(hintIdx < Blackboard.getBlackboard().getCurrentQuestion().getHints().size()){
            JOptionPane.showMessageDialog(null, Blackboard.getBlackboard().getCurrentQuestion().getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
            hintIdx++;
        } else{
            hintIdx = 0;
            JOptionPane.showMessageDialog(null, Blackboard.getBlackboard().getCurrentQuestion().getHints().get(hintIdx).getText(), "Hint #" + (hintIdx + 1), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    boolean showNextQuestion(ArrayList<Question> questions){
        if(questionIdx + 1 < questions.size()){
            questionIdx += 1;
            Blackboard.getBlackboard().setCurrentQuestion(questions.get(questionIdx));
            Blackboard.getBlackboard().reset();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "This is the last question!",
                    "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    boolean submitPressed(String studentAttempt) {
        if (Blackboard.getBlackboard().getCurrentQuestion().checkAnswer(studentAttempt)) {
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            JOptionPane.showMessageDialog(null,
                    Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is correct \nYour updated UML to Code proficiency is:" + s.getUmlToCode(),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            Blackboard.getBlackboard().reset();
            return true;
        } else {
            String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is incorrect.";
            if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "class") < StringUtils.countMatches(studentAttempt, "class")) {
                message += "\nHint: You have made too many classes!";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "class") > StringUtils.countMatches(studentAttempt, "class")) {
                message += "\nHint: You still need to make more classes";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), ";") > StringUtils.countMatches(studentAttempt, ";")) {
                message += "\nHint: Check if you have added all the required variables";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "()") > StringUtils.countMatches(studentAttempt, "()")) {
                message += "\nHint: Check if you have added all the required methods";
            } else if (!areClassNamesCorrect(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), studentAttempt)) {
                message += "\nHint: Are you naming your classes correctly?";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Incorrect Answer",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean areClassNamesCorrect(String correctAns, String studentAns){
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
