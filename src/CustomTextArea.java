import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTextArea extends JTextArea implements MyObserver {
    public CustomTextArea(int x, int y){
        super(x, y);
    }

    private void parseClasses(List<Box> boxes){
        for(Box box : boxes) {
            this.append("class " + box.getName() + " {\n}\n");
        }
    }

    @Override
    public void update(MyObservable ob) {
        //The getBox call of umlComponents causes an error
//        List<Box> umlComponents = Blackboard.getBlackboard().getBoxList().stream().map(UMLComponent::getBox).collect(Collectors.toList());
//        this.setText("");
//        this.parseClasses(umlComponents);

    }
}
