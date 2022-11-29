import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTextArea extends JTextArea implements MyObserver {
    public CustomTextArea(int x, int y) {
        super(x, y);
    }

    private void parseClasses(List<UMLComponent> boxes) {
        for (UMLComponent box : boxes) {
            this.append("class " + box.getName() + " {\n}\n");
        }
    }

    public void parseText() {
        String[] text = this.getText().split("class", -1);
        for (String s : Arrays.copyOfRange(text, 1, text.length)) {
            ArrayList<String> splitClass = (ArrayList<String>) Arrays.stream(s.strip()
                            .replace("\n", "").split(" "))
                    .collect(Collectors.toList());
            System.out.println(s.strip().replace("\n", ""));
            System.out.println(splitClass);
            String boxName = splitClass.get(0);
            UMLComponent box = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(boxName))
                    .findFirst().orElse(new Box(boxName, 100, 100));
            int i = 0;
            while (i < splitClass.size()) {
//                System.out.println(splitClass[i]);
//                if (splitClass[i + 1].equals("(") && !splitClass[i].equals("method")) {
//                    classMethods.add(splitClass[i]);
//                }
                i++;
            }
//            for (String method : classMethods) {
//                System.out.println(method);
//            }


            if (!Blackboard.getBlackboard().getBoxList().contains(box)) {
                Blackboard.getBlackboard().appendBoxList(box);
            }
            Blackboard.getBlackboard().notifying();
        }
    }

    @Override
    public void update(MyObservable ob) {
        //The getBox call of umlComponents causes an error
        this.setText("");
        this.parseClasses(Blackboard.getBlackboard().getBoxList());

    }
}
