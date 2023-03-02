import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
        return this.answer.equals(studentAnswer);
    }
}
