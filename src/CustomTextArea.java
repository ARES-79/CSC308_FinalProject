import javax.swing.*;
import java.util.List;

public class CustomTextArea extends JTextArea {
    public CustomTextArea(int x, int y){
        super(x, y);
    }

    public void parseClasses(List<BoxTempClass> boxes){
        for(BoxTempClass box : boxes) {
            this.append("class " + box.getName() + " {\n}\n");
        }
    }
}
