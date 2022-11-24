import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadModel {
    LoadModel(){
        System.out.println("The user has chosen to LOAD an existing UML");
        deserialize(showDialogueBox());
    }

    /**
     * showDialogueBox - create a dialogue box to prompt the user to enter a project name
     * @return String input by the user or null if nothing was entered or the user clicked cancel
     */
    public String showDialogueBox(){
        String savedProjects = "";
        for (String name: Blackboard.getBlackboard().getSavedProjects()){
            savedProjects = savedProjects + name + "\n";
        }
        String s = (String) JOptionPane.showInputDialog(
                null,
                "What is the name of the project you would like to load?\n"
                        + "Options: \n"
                        +savedProjects,
                "Load Previous Project",
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

    void deserialize(String filename){
        List<UMLComponent> BoxList = new ArrayList<>();
        UMLComponent component = null;

        // Deserialization
        try
        {
            FileInputStream file = new FileInputStream("projects/"+filename+".ser");
            ObjectInputStream in = new ObjectInputStream(file);

            BoxList = (List<UMLComponent>) in.readObject();

            in.close();
            file.close();

            System.out.println("Project has been deserialized ");
            System.out.println("a = " + BoxList.get(0).getName());
            Blackboard.getBlackboard().setBoxList(BoxList);
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            JOptionPane.showMessageDialog(null,"Error in Loading");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            JOptionPane.showMessageDialog(null,"Error in Loading");
        }
    }
}
