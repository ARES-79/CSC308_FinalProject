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
            List<String> inheritenceCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.INHERITANCE))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            List<String> extendCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.ASSOCIATION))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            String text = "class " + box.getName();
            if(inheritenceCons.size() > 0){
                text += " implements " + String.join(" ", inheritenceCons);
            }
            if(extendCons.size() > 0){
                text += " extends " + String.join(" ", extendCons);
            }
            text += " {\n}\n";
            this.append(text);
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
                if (splitClass.get(i).equals("extends")) {
                    i = getConnections(ConnectionType.ASSOCIATION, splitClass, i, s, (Box) box);
                } else if ((splitClass.get(i).equals("implements"))) {
                    i = getConnections(ConnectionType.INHERITANCE, splitClass, i, s, (Box) box);
                } else {
                    String currentWord = splitClass.get(i);
                    int currentWordIndex = s.indexOf(currentWord);
                    char lastChar = s.charAt(currentWordIndex + currentWord.length());
                    if(lastChar == '\n'){
                        System.out.println("This class have a variable " + currentWord);
                    }
                    else if(lastChar == '(' && !currentWord.equals("method")){
                        System.out.println("This class has a method " + currentWord);
                    }
                    else {
                        lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
                        while(lastChar != '}'){
                            i++;
                            currentWord = splitClass.get(i);
                            currentWordIndex = s.indexOf(currentWord);
                            lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
                            this.createConnection((Box) box, currentWord, ConnectionType.COMPOSITION);
                        }
                    }
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

    private int getConnections(ConnectionType connectionType, ArrayList<String> splitClass, int i, String s, Box origin) {
        String currentWord = splitClass.get(i);
        int currentWordIndex = s.indexOf(currentWord);
        char lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
        String endKeyword = currentWord.equals("extends") ? "implements" : "extends";
        while (lastChar != '{') {
            i++;
            currentWord = splitClass.get(i);
            currentWordIndex = s.indexOf(currentWord);
            lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
            if (currentWord.equals(endKeyword)) {
                i--;
                break;
            }
            this.createConnection(origin, currentWord, connectionType);
        }
        return i;
    }
    private void createConnection(Box origin, String currentWord, ConnectionType connectionType){
        Box box2 = (Box) Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(currentWord))
                .findFirst().orElse(null);
        if (box2 == null) {
            box2 = new Box(currentWord, 300, 300);
            Blackboard.getBlackboard().getBoxList().add(box2);
        }
        Connection connection = new Connection(origin, box2, connectionType);
        origin.getConnections().add(connection);
    }
}

