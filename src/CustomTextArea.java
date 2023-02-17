import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assignment 01
 * @author Archie Jones
 * @version 2.1
 * CustomTextArea - parses the TextArea to retrieve data and use it to create UMLComponent on the DrawPanel.
 * Also, parses the existing data in the Blackboard to update the TextArea.
 * It is an Observer of the Blackboard
 */
public class CustomTextArea extends JTextArea implements MyObserver {
    public CustomTextArea(int x, int y) {
        super(x, y);
    }

    /**
     * parseClasses - method to update the text area when new classes are created in the DrawPanel
     * @param boxes List<UMLComponent> the current list of boxes which will be parsed to extract data
     */
    private void parseClasses(List<UMLComponent> boxes) {
        for (UMLComponent box : boxes) {
            String text = "";
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
            MethodDec tempMethodDec = (MethodDec) box;
            List<String> variables = Arrays.stream(((VarDec) tempMethodDec.getComponent())
                    .getVarName().split("\n")).collect(Collectors.toList());
            List<String> methods = Arrays.stream(((MethodDec) box).getMethodName().split("\n"))
                    .collect(Collectors.toList());
            variables.remove(0);
            methods.remove(0);
            text = "class " + box.getName();
            if (inheritenceCons.size() > 0) {
                text += " extends " + String.join(" ", inheritenceCons);
            }
            text += " {\n";
            if (compositionCons.size() > 0) {
                text += " " + String.join("\n ", compositionCons) + "\n";
            }
            if (variables.size() > 0) {
                text += " " + String.join("\n ", variables) + "\n";
            }
            if (associationCons.size() > 0) {
                text += " methods() {\n  " + String.join("\n  ", associationCons) + "\n }\n";
            }
            if (methods.size() > 0) {
                text += " " + String.join("() {\n }\n ", methods) + "() {\n }";
            }
            text += "\n}\n";
            this.append(text);
        }
    }

    /**
     * parseText - parses the updates in the TextAre(when the Update button is clicked) to retrieve the changes
     * and makes corresponding changes to the data in the Blackboard
     */
    public void parseText() {
        String[] text = this.getText().split("class", -1);
        for (String s : Arrays.copyOfRange(text, 1, text.length)) {
            ArrayList<String> splitClass = (ArrayList<String>) Arrays.stream(s.strip()
                            .replace("\n", "").split("\\W+"))
                    .collect(Collectors.toList());
            String boxName = splitClass.get(0);
            UMLComponent box = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(boxName))
                    .findFirst().orElse(new MethodDec("", new VarDec("", new CustomBox(boxName, 100, 100))));
            int i = 1;
            while (i < splitClass.size()) {
                if (splitClass.get(i).equals("extends")) {
                    i = getConnections(ConnectionType.INHERITANCE, splitClass, i, s, box);
                } else {
                    String currentWord = splitClass.get(i);
                    int currentWordIndex = s.indexOf(currentWord);
                    char lastChar = s.charAt(currentWordIndex + currentWord.length());
                    if (lastChar == '\n') {
                        this.parseVariable(currentWord, box);
                    } else if (lastChar == '(' && !currentWord.equals("methods")) {
                        this.parseMethod(currentWord, box);
                    } else {
                        i = this.parseAssociation(currentWord, box, i, currentWordIndex, s, splitClass);
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

    private void parseVariable(String currentWord, UMLComponent box){
        String finalCurrentWord = currentWord;
        UMLComponent box2 = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(finalCurrentWord))
                .findFirst().orElse(null);
        if (box2 != null) {
            if (box.getConnections().stream().noneMatch(connection -> connection.getDestination().equals(box2) &&
                    connection.getType().equals(ConnectionType.COMPOSITION))) {
                Connection connection = new Composition(box, box2, ConnectionType.COMPOSITION);
                box.getConnections().add(connection);
            }
        } else {
            System.out.println("This class have a variable " + currentWord);
            MethodDec tempMethodDec = (MethodDec) box;
            VarDec tempVarDec = (VarDec) tempMethodDec.getComponent();
            if (Arrays.stream(tempVarDec.getVarName().split("\n"))
                    .noneMatch(str -> str.equals(currentWord))) {
                tempVarDec.setVarName((tempVarDec.getVarName() + "\n" + currentWord));
            }
        }
    }

    private void parseMethod(String currentWord, UMLComponent box){
        System.out.println("This class has a method " + currentWord);
        MethodDec tempMethodDec = (MethodDec) box;
        String finalCurrentWord2 = currentWord;
        if (Arrays.stream(tempMethodDec.getMethodName().split("\n"))
                .noneMatch(str -> str.equals(finalCurrentWord2))) {
            tempMethodDec.setMethodName((tempMethodDec.getMethodName() + "\n" + currentWord));
        }
    }

    private int parseAssociation(String currentWord, UMLComponent box, int i, int currentWordIndex, String s,
                                 ArrayList<String> splitClass){
        char lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
        while (lastChar != '}') {
            i++;
            currentWord = splitClass.get(i);
            currentWordIndex = s.indexOf(currentWord);
            lastChar = s.charAt(currentWordIndex + currentWord.length() + 2);
            String finalCurrentWord1 = currentWord;
            if (box.getConnections().stream().noneMatch(connection -> connection.getDestination().equals(finalCurrentWord1) &&
                    connection.getType().equals(ConnectionType.ASSOCIATION))) {
                this.createConnection(box, currentWord, ConnectionType.ASSOCIATION);
            }
        }
        return i;
    }

    /**
     * update - overridden method which is called when the Blackboard has an update
     * @param ob MyObervable to indicate which Observable object has called this update method
     */
    @Override
    public void update(MyObservable ob) {
        this.setText("");
        this.parseClasses(Blackboard.getBlackboard().getBoxList());
    }

    /**
     * getConnections - method to parses the text concerning connection to extract the information required to make the connections
     *                  and make the connection for the origin
     * @param connectionType ConnectionType which indicated the type of connection current being parsed
     * @param splitClass ArrayList<String> the text for each class split into a list of constituent words
     * @param i int index of the current word being processed
     * @param s String text for the class
     * @param origin UMLComponent origin Box (or decorated box) from which the new connection will originate
     * @return int i which is the index of the current word being processed
     */
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

    /**
     * createConnections - method to create a new Connection and add it to the Origin box, is it doesn't already exist
     * @param origin UMLComponent which is the where the connection will originate from
     * @param currentWord String which might become the name of a new box, if needed
     * @param connectionType ConnectionType of the connection to be created
     */
    private void createConnection(UMLComponent origin, String currentWord, ConnectionType connectionType) {
        UMLComponent box2 = Blackboard.getBlackboard().getBoxList().stream().filter(b -> b.getName().equals(currentWord))
                .findFirst().orElse(null);
        if (box2 == null) {
            box2 = new MethodDec("", new VarDec("", new CustomBox(currentWord, 300, 300)));
            Blackboard.getBlackboard().getBoxList().add(box2);
        }
        UMLComponent finalBox = box2;
        if (origin.getConnections().stream().filter(connection -> connection.getDestination().equals(finalBox) &&
                connection.getType().equals(connectionType)).collect(Collectors.toList()).size() == 0) {
            Connection connection;
            if (connectionType == ConnectionType.INHERITANCE){
                connection = new Inheritance(origin, box2, connectionType);
            } else if (connectionType == ConnectionType.COMPOSITION){
                connection = new Composition(origin, box2, connectionType);
            } else {
                connection = new Association(origin, box2, connectionType);
            }
            origin.getConnections().add(connection);
        }
    }
}

