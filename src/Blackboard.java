import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blackboard extends MyObservable{
    private List<Box> BoxList = new ArrayList<Box>();

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

    public List<Box> getBoxList() {
        return BoxList;
    }

    public void appendBoxList(Box box){
        BoxList.add(box);
    }

    /**
     * updateData - calls notifying which then updates all the observers
     */
    public void updateData(){
        notifying();
    }
}
