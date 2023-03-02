import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hint {
    String text;
    int rank; //rank is 1st hint, 2nd hint, etc,
    boolean visited; //if the student has used it or not

    public Hint(String text){

        this.text = text;
    }
}
