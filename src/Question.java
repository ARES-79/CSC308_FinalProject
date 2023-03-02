import java.util.List;

public class Question {
    private String text;
    private String answer;
    private List<Hint> hints;
    double difficulty;

    public Question(String text, String answer, List<Hint> hints, double difficulty){
        this.text = text;
        this.answer = answer;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public double getDifficulty() {
        return difficulty;
    }

    boolean checkAnswer(String studentAnswer){
        return this.answer.equals(studentAnswer);
    }
}
