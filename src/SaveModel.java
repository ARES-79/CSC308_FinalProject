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

            out.writeObject(Blackboard.getBlackboard().getBoxList());

            out.close();
            file.close();

            System.out.println("Project has been serialized");
            boolean retVal = Blackboard.getBlackboard().appendSavedProjectsList(filename);

            if(!retVal){
                System.out.println("Project already exists");
                JOptionPane.showMessageDialog(null,"Project already exists");
            }
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            JOptionPane.showMessageDialog(null,"Error while Saving");
        }
        try{
            FileOutputStream file1 = new FileOutputStream("list/SavedProjects.ser");
            ObjectOutputStream out1 = new ObjectOutputStream(file1);

            out1.writeObject(Blackboard.getBlackboard().getSavedProjects());
            System.out.println("Saved Projects list has been updated");

            out1.close();
            file1.close();
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
