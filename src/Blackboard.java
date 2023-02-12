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
public class Blackboard extends MyObservable{
    private List<UMLComponent> BoxList = new ArrayList<>();
    private ConnectionType connectionType = ConnectionType.ASSOCIATION;
    private HashSet<String> savedProjects = LoadModel.deserializeSavedProjects();
    private CustomTextArea customTextArea = new CustomTextArea(30, 20);
    private JLabel statusBar = new JLabel();

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
     * getBoxList - getter method to access BoxList, which is the list of boxes in the current project state
     * @return List</UMLComponent> BoxList which has the list of boxes in the current state of the project
     */
    public List<UMLComponent> getBoxList() {
        return BoxList;
    }

    /**
     * appendBoxList - method to allow to append a new Box(or Decorated Box) to the BoxList
     * @param box - UMLComponent to be appended to the BoxList
     */
    public void appendBoxList(UMLComponent box){
        BoxList.add(box);
    }

    /**
     * setBoxList - setter method to set the boxList when loaded from a deserialized object
     * @param boxList - List<UMLComponents> which is the BoxList loaded from a serialized file
     */
    public void setBoxList(List<UMLComponent> boxList) {
        BoxList = boxList;
        updateData();
    }

    /**
     * getSavedProjects - getter methods to access the hash set of previously saved projects
     * @return HashSet<String> which is the hash set of previously saved projects
     */
    public HashSet<String> getSavedProjects() {
        return savedProjects;
    }

    /**
     * appendSavedProjectsList - method to allow to add a new project name to the list of savedProjects
     * @param name - String name which will be the name of the added project
     * @return boolean to know whether the add was successful or not, to be able to check for duplicates
     */
    public boolean appendSavedProjectsList(String name){
        return savedProjects.add(name);
    }

    /**
     * getCustomTextArea - getter method to access the customTextArea object
     * @return CustomTextArea object, customTextArea
     */
    public CustomTextArea getCustomTextArea() {
        return customTextArea;
    }

    /**
     * getConnectionType - getter method to access the currently selected connectionType
     * @return ConnectionType object, connectionType
     */
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    /**
     * setConnectionType - setter method to set the current connectionType when it is changed by the user
     * @param connectionType - ConnectionType enum which is the new connection type set by the user
     */
    public void setConnectionType(ConnectionType connectionType) {
        System.out.println(connectionType + " connection chosen");
        this.connectionType = connectionType;
    }

    /**
     * getStatusBar -  setter method to access the statusBar and set its text
     * @return JLabel object statusBar
     */
    public JLabel getStatusBar() {
        return statusBar;
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
}
