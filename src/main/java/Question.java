import com.sun.source.tree.StatementTree;
import lombok.Getter;
import lombok.Setter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 *
 * Question object to hold information about each of the questions
 *
 */

@Getter
@Setter
public class Question implements Serializable {
    private int id;
    private String text;
    private String answer;
    private List<Hint> hints;
    double difficulty;
    private ArrayList<UMLComponent> UML;

    public Question(int id, String text, String answer, List<Hint> hints, double difficulty){
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    public Question(int id, ArrayList<UMLComponent> UML, String answer, List<Hint> hints, double difficulty){
        this.id = id;
        this.UML = UML;
        this.answer = answer;
        this.difficulty = difficulty;
        this.hints = hints;
    }

    boolean checkAnswer(String studentAnswer){
        String studentAns = studentAnswer.strip().replace(" ", "").replace("\n", "").replace("\t", "");
        String correctAns = answer.strip().replace(" ", "").replace("\n", "").replace("\t", "");
        return correctAns.equalsIgnoreCase(studentAns);
    }

}
