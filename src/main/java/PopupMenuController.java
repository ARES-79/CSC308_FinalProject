import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PopupMenuController implements ActionListener {
    private UMLComponent clickedBox;
    private PopupMenuModel popupMenuModel;


    public PopupMenuController(UMLComponent clickedBox, PopupMenuModel pmodel){
        this.clickedBox = clickedBox;
        this.popupMenuModel = pmodel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ("Delete Class") -> {
                Blackboard.getBlackboard().getBoxList().remove(clickedBox);
                Blackboard.getBlackboard().notifying();
            }
            case("Add Method") -> {
                String name = popupMenuModel.showDialogueBox("Method");
                if (name != null){
                    MethodDec tempMethodDec = (MethodDec) this.clickedBox;
                    if (Arrays.stream(tempMethodDec.getMethodName().split("\n"))
                            .noneMatch(str -> str.equals(name))) {
                        tempMethodDec.setMethodName((tempMethodDec.getMethodName() + "\n" + name));
                    }
                    Blackboard.getBlackboard().notifying();
                }
            }
            case("Add Variable") -> {
                String name = popupMenuModel.showDialogueBox("Variable");
                if (name != null) {
                    MethodDec tempMethodDec = (MethodDec) clickedBox;
                    VarDec tempVarDec = (VarDec) tempMethodDec.getComponent();
                    if (Arrays.stream(tempVarDec.getVarName().split("\n"))
                            .noneMatch(str -> str.equals(name))) {
                        tempVarDec.setVarName((tempVarDec.getVarName() + "\n" + name));
                    }
                    Blackboard.getBlackboard().notifying();
                }
            }
        }
    }

}
