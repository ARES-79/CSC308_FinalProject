import com.sun.source.tree.StatementTree;
import lombok.Getter;
import lombok.Setter;

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
public class Question {
    private int id;
    private String text;
    private String answer;
    private List<Hint> hints;
    double difficulty;

    public Question(int id, String text, String answer, List<Hint> hints, double difficulty){
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    boolean checkAnswer(String studentAnswer){
        return this.answer.strip().replace(" ", "").equalsIgnoreCase(studentAnswer.strip().replace(" ", ""));
    }
}
