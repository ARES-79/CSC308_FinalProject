import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 308 Final Project
 * @author Mitashi Parikh
 * @version 1.0
 * LoadModel - class which handles the functionality for loading a previously saved state of the draw panel by deserializing an object file of the name entered by the user, if such a file exists
 */
public class LoadModel implements FileHandler {

    /**
     * loadProject - Shows the dialog box to receive the name of the project that needs to be loaded and uses it to deserialize the file and restore the previous project if it exists.
     */
    public void saveLoadProject(){
        System.out.println("The user has chosen to LOAD an existing UML");
        String name = showDialogueBox();
        if (name != null  && (name.length() > 0)) {
            deserialize(name);
        }
    }

    /**
     * deserializeSavedProjects - Deserializes the list of projects that may have been stores in a previous session
     * @return HashSet</String> which is the list of the names of the previously stored project states
     */
    public static HashSet<String> deserializeSavedProjects(){
        HashSet<String> SavedList = new HashSet<>();

        // Deserialization
        try
        {
            FileInputStream file = new FileInputStream("SavedProjects01234567890.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            SavedList = (HashSet<String>) in.readObject();

            in.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            return SavedList;
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            return SavedList;
        }
        return SavedList;
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

    /**
     * deserialize - Deserializes the project state which has the name entered by the user and restores it to the DrawPanel, if a project of the entered name exisits
     * @param filename String name of the project to be deserialized
     */
    void deserialize(String filename){
        List<UMLComponent> BoxList = new ArrayList<>();
        UMLComponent component = null;

        // Deserialization
        try
        {
            FileInputStream file = new FileInputStream(filename+".ser");
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
            JOptionPane.showMessageDialog(null,"Error while Loading. Probably project doesn't exist.");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            JOptionPane.showMessageDialog(null,"Error while Loading");
        }
    }
}
