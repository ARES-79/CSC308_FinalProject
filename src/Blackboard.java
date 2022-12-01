import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Blackboard extends MyObservable{
    private List<UMLComponent> BoxList = new ArrayList<>();
    private ConnectionType connectionType = ConnectionType.ASSOCIATION;
    private HashSet<String> savedProjects = LoadModel.deserializeSavedProjects();
    private CustomTextArea customTextArea = new CustomTextArea(30, 20);
    private JLabel statusBar = new JLabel();

    public CustomTextArea getCustomTextArea() {
        return customTextArea;
    }

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

    public List<UMLComponent> getBoxList() {
        return BoxList;
    }

    public void appendBoxList(UMLComponent box){
        BoxList.add(box);
    }

    public void setBoxList(List<UMLComponent> boxList) {
        BoxList = boxList;
        updateData();
    }

    public HashSet<String> getSavedProjects() {
        return savedProjects;
    }

    public boolean appendSavedProjectsList(String name){
        return savedProjects.add(name);
    }

    /**
     * updateData - calls notifying which then updates all the observers
     */
    public void updateData(){
        notifying();
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        System.out.println(connectionType + " connection chosen");
        this.connectionType = connectionType;
    }

    public JLabel getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    public void reset(){
        setBoxList(new ArrayList<>());
    }
}
