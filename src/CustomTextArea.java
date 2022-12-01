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
            List<String> associationCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.ASSOCIATION))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            List<String> compositionCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.COMPOSITION))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            String text = "class " + box.getName();
            if (inheritenceCons.size() > 0) {
                text += " extends " + String.join(" ", inheritenceCons);
            }
            text += " {\n";
            if(compositionCons.size() > 0){
                text += " " + String.join("\n ", compositionCons) + "\n ";
            }
            if (associationCons.size() > 0) {
                text += " methods() {\n  " + String.join("\n  ", associationCons) + "\n }";
            }
            text += "\n}\n";
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
                    i = getConnections(ConnectionType.INHERITANCE, splitClass, i, s, box);
                }
                else {
                    String currentWord = splitClass.get(i);
                    int currentWordIndex = s.indexOf(currentWord);
                    char lastChar = s.charAt(currentWordIndex + currentWord.length());
                    if (lastChar == '\n') {
                        String finalCurrentWord = currentWord;
                        Box box2 = (Box) Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(finalCurrentWord))
                                .findFirst().orElse(null);
                        if (box2 != null) {
                            if(box.getConnections().stream().filter(connection -> connection.getDestination().equals(box2) &&
                                    connection.getType().equals(ConnectionType.COMPOSITION)).count() == 0){
                                Connection connection = new Connection(box, box2, ConnectionType.COMPOSITION);
                                box.getConnections().add(connection);
                            }
                        } else {
                            System.out.println("This class have a variable " + currentWord);
                        }
                    } else if (lastChar == '(' && !currentWord.equals("method")) {
                        System.out.println("This class has a method " + currentWord);
                    } else {
                        lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
                        while (lastChar != '}') {
                            i++;
                            currentWord = splitClass.get(i);
                            currentWordIndex = s.indexOf(currentWord);
                            lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
                            this.createConnection(box, currentWord, ConnectionType.COMPOSITION);
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

    private int getConnections(ConnectionType connectionType, ArrayList<String> splitClass, int i, String s, UMLComponent origin) {
        String currentWord = splitClass.get(i);
        int currentWordIndex = s.indexOf(currentWord);
        char lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
        while (lastChar != '{') {
            i++;
            currentWord = splitClass.get(i);
            currentWordIndex = s.indexOf(currentWord);
            lastChar = s.charAt(currentWordIndex + currentWord.length() + 1);
            this.createConnection(origin, currentWord, connectionType);
        }
        return i;
    }

    private void createConnection(UMLComponent origin, String currentWord, ConnectionType connectionType) {
        UMLComponent box2 = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(currentWord))
                .findFirst().orElse(null);
        if (box2 == null) {
            box2 = new Box(currentWord, 300, 300);
            Blackboard.getBlackboard().getBoxList().add(box2);
        }
        UMLComponent finalBox = box2;
        if(origin.getConnections().stream().filter(connection -> connection.getDestination().equals(finalBox) &&
                connection.getType().equals(connectionType)).collect(Collectors.toList()).size() == 0){
            Connection connection = new Connection(origin, box2, connectionType);
            origin.getConnections().add(connection);
        }
    }
}

