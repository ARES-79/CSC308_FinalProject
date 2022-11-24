import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveModel {
    SaveModel(){
        System.out.println("The user has chosen to SAVE their UML");
        serialize(showDialogueBox());
    }

    /**
     * showDialogueBox - create a dialogue box to prompt the user to enter a project name
     * @return String input by the user or null if nothing was entered or the user clicked cancel
     */
    public String showDialogueBox(){
        String s = (String) JOptionPane.showInputDialog(
                null,
                "What would you like to name the current project?\n"
                        + "Name: ",
                "Save Current Project",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        //If a string was returned
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return null;
    }

    void serialize(String filename){
        try
        {
            FileOutputStream file = new FileOutputStream("projects/"+filename+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            //for(UMLComponent component: Blackboard.getBlackboard().getBoxList()) {
                out.writeObject(Blackboard.getBlackboard().getBoxList());
            //}

            out.close();
            file.close();

            System.out.println("Project has been serialized");
            Blackboard.getBlackboard().appendSavedProjectsList(filename);

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            JOptionPane.showMessageDialog(null,"Error while Saving");
        }
    }
}
