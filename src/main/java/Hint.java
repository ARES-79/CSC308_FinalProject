import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 *
 * Hint object to read from the DB and display to the user
 */
@Getter
@Setter
public class Hint implements Serializable {
    String text;

    public Hint(String text){

        this.text = text;
    }

    public String getText() {
        return text;
    }
}
