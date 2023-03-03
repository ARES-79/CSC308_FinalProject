import lombok.Getter;
import lombok.Setter;
/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 *
 * Hint object to read from the DB and display to the user
 */
@Getter
@Setter
public class Hint {
    String text;
    int rank; //rank is 1st hint, 2nd hint, etc,
    boolean visited; //if the student has used it or not

    public Hint(String text){

        this.text = text;
    }

    public String getText() {
        return text;
    }
}
