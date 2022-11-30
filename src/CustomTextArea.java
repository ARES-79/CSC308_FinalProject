import javax.swing.*;
import java.lang.reflect.Array;
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
                            .replace("\n", "").split("\\W+"))
                    .collect(Collectors.toList());
            String boxName = splitClass.get(0);
            UMLComponent box = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(boxName))
                    .findFirst().orElse(new Box(boxName, 100, 100));
            int i = 1;
            while (i < splitClass.size()) {
                if(splitClass.get(i).equals("extends")){
                    i = getConnections(ConnectionType.ASSOCIATION, splitClass, i, s, (Box) box);
                }
                else if((splitClass.get(i).equals("implements"))){
                    i = getConnections(ConnectionType.INHERITANCE, splitClass, i, s, (Box )box);
                }
                i++;
            }

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

    private int getConnections(ConnectionType connectionType, ArrayList<String> splitClass, int i, String s, Box origin){
        String currentWord = splitClass.get(i);
        int currentWordIndex = s.indexOf(currentWord);
        char lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
        String endKeyword = currentWord.equals("extends") ? "implements" : "extends";
        while(lastChar != '{'){
            i++;
            currentWord = splitClass.get(i);
            currentWordIndex = s.indexOf(currentWord);
            lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
            if(currentWord.equals(endKeyword)){
                i--;
                break;
            }
            String finalCurrentWord = currentWord;
            System.out.println(currentWord);
            Box box2 = (Box) Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(finalCurrentWord))
                    .findFirst().orElse(null);
            if(box2 == null){
                System.out.println("Error creating connection, box2 does not exist");
            }
            else{
                Connection connection = new Connection(origin, box2, connectionType);
                origin.getConnections().add(connection);
            }
        }
        return i;
    }
}
