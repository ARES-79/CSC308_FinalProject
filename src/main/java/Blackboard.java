import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Final Project
 * @author Mitashi Parikh, Jamie Luna, Archie Jones, Andrew Estrada
 * @version 1.0
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

    public void statusBarNewProject(){
        statusBarMessage = "     New Project Created";
        updateData();;
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
    }
    public DatabaseController getDatabaseController() {
        return databaseController;
    }

}
