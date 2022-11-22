import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blackboard extends MyObservable{
    private List<UMLComponent> BoxList = new ArrayList<>();
    private ConnectionType connectionType = ConnectionType.ASSOCIATION;
    private List<String> savedProjects = new ArrayList<>();

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

    public List<String> getSavedProjects() {
        return savedProjects;
    }

    public void appendSavedProjectsList(String name){
        savedProjects.add(name);
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
}
