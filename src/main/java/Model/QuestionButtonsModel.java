package Model;

import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.util.ArrayList;

/**
 * QuestionsButtonsModel
 * @author Andrew Estrada, Mitashi Parikh, Jamie Luna
 * @version 1.0
 *
 * Model class for the 'Next' and 'Submit' button functionalities
 */
public class QuestionButtonsModel {

    private int questionIdx = 0;


    /**
     * Brings the next question to the screen or says the current question is the last
     */
    public boolean showNextQuestion(ArrayList<Question> questions){
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

    /**
     * Checks student answer, gives messages and changes question if correct
     */
    public boolean submitPressed(String studentAttempt) {

        if (Blackboard.getBlackboard().getCurrentQuestion().checkAnswer(studentAttempt)) {
            Student s = (Student) Blackboard.getBlackboard().getCurrentUser();
            s.updateProficiency();
            JOptionPane.showMessageDialog(null,
                    Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is correct \nYour updated UML to Code proficiency is:" + getSubProficiency(s),
                    "Correct Answer",
                    JOptionPane.INFORMATION_MESSAGE);
            Blackboard.getBlackboard().reset();
            return true;
        } else {
            String message = Blackboard.getBlackboard().getCurrentUser().getFirstName() + ", your answer is incorrect.";
            if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "class") < StringUtils.countMatches(studentAttempt, "class")) {
                message += "\nModel.Hint: You have made too many classes!";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "class") > StringUtils.countMatches(studentAttempt, "class")) {
                message += "\nModel.Hint: You still need to make more classes";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), ";") > StringUtils.countMatches(studentAttempt, ";")) {
                message += "\nModel.Hint: Check if you have added all the required variables";
            } else if (StringUtils.countMatches(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), "()") > StringUtils.countMatches(studentAttempt, "()")) {
                message += "\nModel.Hint: Check if you have added all the required methods";
            } else if (!areClassNamesCorrect(Blackboard.getBlackboard().getCurrentQuestion().getAnswer(), studentAttempt)) {
                message += "\nModel.Hint: Are you naming your classes correctly?";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Incorrect Answer",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private float getSubProficiency(Student s) {
        if (Blackboard.getBlackboard().getCurrentSubject() == SubjectType.UMLtoCode) {
            return s.getUmlToCode();
        } else if (Blackboard.getBlackboard().getCurrentSubject() == SubjectType.CodetoUML) {
            return s.getCodeToUML();
        } else if (Blackboard.getBlackboard().getCurrentSubject() == SubjectType.CodetoMetrics) {
            return s.getCodeToMetrics();
        } else if (Blackboard.getBlackboard().getCurrentSubject() == SubjectType.UMLtoMetrics) {
            return s.getUmlToMetrics();
        }
        return 0;
    }

    /**
     * Checks if the names of the classes in the students answer match the actual answer
     */
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
