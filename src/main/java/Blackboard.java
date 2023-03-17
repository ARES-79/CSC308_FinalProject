import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Final Project
 * @author Mitashi Parikh, Jamie Luna, Archie Jones, Andrew Estrada
 * @version 2.0
 * Blackboard - central location for the data which needs to be accessed by several data requesters.
 * It is a singleton class and is also observable
 */
@Getter
@Setter
public class Blackboard extends MyObservable {
    private List<UMLComponent> BoxList = new ArrayList<>();
    private ConnectionType connectionType = ConnectionType.ASSOCIATION;
    private HashSet<String> savedProjects = LoadModel.deserializeSavedProjects();
//    private CustomTextArea customTextArea = new CustomTextArea(30, 20);
    private JLabel statusBar = new JLabel();
    private String statusBarMessage;
    private List<User> teachers = new ArrayList<>();
    private List<User> students = new ArrayList<>();
    private User currentUser;
    private SubjectType currentSubject;
    private Question currentQuestion;

    private ArrayList<Question> codeToUMLQuestions = getCodeToUMLQuestions();
    private ArrayList<Question> UMLtoCodeQuestions = getUMLtoCodeQuestions();
    private ArrayList<Question> UMLtoMetricsQuestions = getUMLtoMetricsQuestions();


    private DatabaseController databaseController = new DatabaseController();

    private static Blackboard blackboard;

    /**
     * Blackboard private constructor for singleton Blackboard class
     */
    private Blackboard(){}

    /**
     * getBlackboard - getter method to access the data stored on the Blackboard
     * @return returns the Blackboard object
     */
    public static Blackboard getBlackboard() {
        if(blackboard == null){
            blackboard = new Blackboard();
        }
        return blackboard;
    }

    /**
     * appendBoxList - method to allow to append a new Box(or Decorated Box) to the BoxList
     * @param box - UMLComponent to be appended to the BoxList
     */
    public void appendBoxList(UMLComponent box){
        BoxList.add(box);
        statusBarMessage = "     A new class was created.";
        updateData();
    }
    /**
     * appendSavedProjectsList - method to allow to add a new project name to the list of savedProjects
     * @param name - String name which will be the name of the added project
     * @return boolean to know whether the add was successful or not, to be able to check for duplicates
     */
    public boolean appendSavedProjectsList(String name){
        return savedProjects.add(name);
    }


    public void appendStudent(Student s){
        students.add(s);
    }

    public SubjectType getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(SubjectType currentSubject) {
        this.currentSubject = currentSubject;
    }

    public void statusBarNewProject(){
        statusBarMessage = "     New Project Created";
        updateData();
    }

    public void statusBarProjectSaved(){
        statusBarMessage = "     Project Saved";
        updateData();
    }

    public void statusBarProjectLoaded(){
        statusBarMessage = "     Project Loaded";
        updateData();
    }

    public void statusBarTextAreaUpdated(){
        statusBarMessage = "     Screen updated based on text written.";
        updateData();
    }

    public void statusBarNewConnection(String connectionType, String name, String destName){
        statusBarMessage =
                "     " + connectionType + " connection created from " + name + " to " + destName;
        updateData();
    }

    public void statusBarClickedNoClass(){
        statusBarMessage = "     The user clicked the screen but no class was created.";
        updateData();
    }

    /**
     * updateData - calls notifying which then updates all the observers
     */
    public void updateData(){
        notifying();
    }

    /**
     * reset - resets the BoxList(to empty) by setting boxList to a new empty array
     */
    public void reset(){
        setBoxList(new ArrayList<>());
        updateData();
    }
    public DatabaseController getDatabaseController() {
        return databaseController;
    }


    public ArrayList<Question> getCodeToUMLQuestions(){
        writeCodeToUMLQuestions();
        ArrayList<Question> questions = new ArrayList<>();
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(("codeToUMLQuestions.bin")))) {
            questions = (ArrayList<Question>)
                    is.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

        return questions;
    }


    //TODO: Not sure if this should go here, but couldn't find a better place - Jamie
    //can be deleted if we don't want to change or add to any of the questions
    /**
     * writes set CodeToUMLQuestions to a file
     */
    private void writeCodeToUMLQuestions(){
        String TEMPTEXT1 = "class A { }";

        String TEMPTEXT2 = "class B { A; C; }";

        String TEMPTEXT3 = "class B { A; C; } class C { D; draw(){ } } ";

        //TODO: Load Question from DB
        Hint hint1 = new Hint("Arrows with a solid black triangle head represent Association");
        Hint hint2 = new Hint("Arrows with a outline triangle head represent Inheritance");
        Hint hint3 = new Hint("Arrows with a solid black diamond head represent Composition");
        Hint hint4 = new Hint("Method names end with a ()");
        ArrayList<Hint> hints_list = new ArrayList<>(Arrays.asList(hint1, hint2, hint3, hint4));
        Question question1 = new Question(100, TEMPTEXT1, TEMPTEXT1, hints_list, 1);
        Question question2 = new Question(101, TEMPTEXT2, TEMPTEXT2, hints_list, 2);
        Question question3 = new Question(102, TEMPTEXT3, TEMPTEXT3, hints_list, 3);

        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

        try {
            FileOutputStream fileOut = new FileOutputStream("codeToUMLQuestions.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(questions);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    private void writeUMLtoCodeQuestions(){
        ArrayList<UMLComponent> boxes1 = new ArrayList<>();
        UMLComponent box1 = new CustomBox("A", 100, 100);
        boxes1.add(box1);

        UMLComponent box2 = new CustomBox("A", 75, 75);
        UMLComponent box3 = new CustomBox("B", 75, 150);
        UMLComponent box4 = new CustomBox("C", 225, 75);
        Connection inheritance = new Inheritance(box3, box2, ConnectionType.INHERITANCE);
        ArrayList<Connection> connections2 = new ArrayList<>();
        connections2.add(inheritance);
        box2.setConnections(connections2);
        ArrayList<UMLComponent> boxes2 = new ArrayList<>();
        boxes2.add(box2);
        boxes2.add(box3);
        boxes2.add(box4);

        UMLComponent box5 = new CustomBox("A", 75, 75);
        UMLComponent box6 = new CustomBox("B", 75, 150);
        UMLComponent box7 = new CustomBox("C", 225, 75);
        UMLComponent box8 = new CustomBox("D", 225, 150);
        Connection inheritance5 = new Inheritance(box6, box5, ConnectionType.INHERITANCE);
        ArrayList<Connection> connections5 = new ArrayList<>();
        connections5.add(inheritance5);
        box5.setConnections(connections5);
        ArrayList<Connection> connections7 = new ArrayList<>();
        connections7.add(new Composition(box7, box8, ConnectionType.COMPOSITION));
        box7.setConnections(connections7);
        ArrayList<UMLComponent> boxes3 = new ArrayList<>();
        boxes3.add(box5);
        boxes3.add(box6);
        boxes3.add(box7);
        boxes3.add(box8);

        Hint hint1 = new Hint("Arrows with a solid black triangle head represent Association");
        Hint hint2 = new Hint("Arrows with a outline triangle head represent Inheritance");
        Hint hint3 = new Hint("Arrows with a solid black diamond head represent Composition");
        Hint hint4 = new Hint("Method names end with opening and closing parenthesis and are then followed by opening and closing brackets");
        ArrayList<Hint> hints_list = new ArrayList<>(Arrays.asList(hint1, hint2, hint3, hint4));
        String q1_answer = "class A { }";
        String q2_answer = "class A { } class B extends A { } class C { }";
        String q3_answer = "class A { } class B extends A { } class C { D; } class D { }";

        Question question1 = new Question(200, boxes1, q1_answer, hints_list, 1);
        Question question2 = new Question(201, boxes2, q2_answer, hints_list, 2);
        Question question3 = new Question(202, boxes3, q3_answer, hints_list, 3);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        try {
            FileOutputStream fileOut = new FileOutputStream("UMLtoCodeQuestions.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(questions);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * stores and retrieves UML to Code Questions
     *
     * @return list of UML to Code Questions
     */
    public ArrayList<Question> getUMLtoCodeQuestions(){
        writeUMLtoCodeQuestions();
        ArrayList<Question> questions = new ArrayList<>();
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(("UMLtoCodeQuestions.bin")))) {
            questions = (ArrayList<Question>)
                    is.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

        return questions;
    }

    /**
     * Creates and stores preset UML to Metrics questions for demonstration of application
     */
    private void writeUMLtoMetricsQuestions(){
        ArrayList<UMLComponent> q1boxes = new ArrayList<>();
        UMLComponent q1box1 = new CustomBox("A", 150, 150);
        UMLComponent q1box2 = new CustomBox("B", 150, 250);
        q1boxes.add(q1box1);
        q1boxes.add(q1box2);
        Connection q1connection1 = new Association(q1box2, q1box1, ConnectionType.ASSOCIATION);
        ArrayList<Connection> q1box1connections = new ArrayList<>();
        q1box1connections.add(q1connection1);
        q1box1.setConnections(q1box1connections);

        ArrayList<UMLComponent> q2boxes = new ArrayList<>();
        UMLComponent q2box1 = new CustomBox("A", 150, 150);
        UMLComponent q2box2 = new CustomBox("B", 150, 250);
        UMLComponent q2box3 = new CustomBox("C", 300, 150);
        q2boxes.add(q2box1);
        q2boxes.add(q2box2);
        q2boxes.add(q2box3);
        Connection q2connection1 = new Association(q2box2, q2box1, ConnectionType.ASSOCIATION);
        Connection q2connection2 = new Association(q2box1, q2box3, ConnectionType.ASSOCIATION);
        ArrayList<Connection> q2box1connections = new ArrayList<>();
        q2box1connections.add(q2connection1);
        q2box1.setConnections(q2box1connections);
        ArrayList<Connection> q2box3connections = new ArrayList<>();
        q2box3connections.add(q2connection2);
        q2box3.setConnections(q2box3connections);


        ArrayList<UMLComponent> question3boxes = new ArrayList<>();
        UMLComponent question3box1 = new CustomBox("A", 200, 200);
        UMLComponent question3box2 = new CustomBox("B", 100, 100);
        UMLComponent question3box3 = new CustomBox("C", 300, 100);
        UMLComponent question3box4 = new CustomBox("D", 100, 300);
        question3boxes.add(question3box1);
        question3boxes.add(question3box2);
        question3boxes.add(question3box3);
        question3boxes.add(question3box4);

        Connection question3connection1 = new Inheritance(question3box3, question3box2, ConnectionType.INHERITANCE);
        Connection question3connection2 = new Inheritance(question3box1, question3box2, ConnectionType.INHERITANCE);
        Connection question3connection3 = new Composition(question3box1, question3box3, ConnectionType.COMPOSITION);
        Connection question3connection4 = new Association(question3box4, question3box1, ConnectionType.ASSOCIATION);
        ArrayList<Connection> connections2 = new ArrayList<>();
        connections2.add(question3connection1);
        connections2.add(question3connection2);
        question3box2.setConnections(connections2);
        ArrayList<Connection> connections1 = new ArrayList<>();
        connections1.add(question3connection4);
        question3box1.setConnections(connections1);
        ArrayList<Connection> connections3 = new ArrayList<>();
        connections3.add(question3connection3);
        question3box3.setConnections(connections3);

        Hint hint1 = new Hint("Instability is related to the connections of a class.");
        Hint hint2 = new Hint("The numerator is the number of connections out. ");
        Hint hint3 = new Hint("The denominator should be greater than or equal to the numerator.");
        ArrayList<Hint> hints_list = new ArrayList<>(Arrays.asList(hint1, hint2, hint3));
        Question question1 = new Question(301, q1boxes, "0,1", hints_list, 1);
        Question question2 = new Question(302, q2boxes, "1,2", hints_list, 2);
        Question question3 = new Question(303, question3boxes, "2,3", hints_list, 3);



        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        try {
            FileOutputStream fileOut = new FileOutputStream("UMLtoMetricsQuestions.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(questions);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * stores and retrieves UML to Metrics Questions
     *
     * @return list of UML to Metrics Questions
     */
    public ArrayList<Question> getUMLtoMetricsQuestions(){
        writeUMLtoMetricsQuestions();
        ArrayList<Question> questions = new ArrayList<>();
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(("UMLtoMetricsQuestions.bin")))) {
            questions = (ArrayList<Question>)
                    is.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

        return questions;
    }


    public void drawUMLtoCodeBoxes(Question q){
        for (UMLComponent umlComponent : q.getUML()){
            BoxList.add(umlComponent);
            updateData();
        }
    }
}
