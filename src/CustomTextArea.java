import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class CustomTextArea extends JTextArea implements MyObserver {
    public CustomTextArea(int x, int y){
        super(x, y);
    }

    private void parseClasses(List<UMLComponent> boxes){
        for(UMLComponent box : boxes) {
            this.append("class " + box.getName() + " {\n}\n");
        }
    }

    public void parseText(){
        String[] text = this.getText().split("class", -1);
        for(String s : Arrays.copyOfRange(text, 1, text.length )){
            s = s.strip();
            String boxName = s.split(" ",2)[0];
            UMLComponent box = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(boxName))
                    .findFirst().orElse(null);
            if(box != null){
                System.out.println(box);
            }
            else{
                Box newBox = new Box(boxName, 100, 100);
                Blackboard.getBlackboard().getBoxList().add(newBox);
                Blackboard.getBlackboard().notifying();
            }
        }
    }

    @Override
    public void update(MyObservable ob) {
        //The getBox call of umlComponents causes an error
        this.setText("");
        this.parseClasses(Blackboard.getBlackboard().getBoxList());

    }
}
