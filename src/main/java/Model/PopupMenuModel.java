package Model;

import javax.swing.*;

public class PopupMenuModel {
    public String showDialogueBox(String type){
        String s;
        if(type.equals("Method")) {
            s = (String) JOptionPane.showInputDialog(
                    null,
                    "What would you like to name your new method?\n",
                    "Add a method",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);
        } else{
            s = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter your new variable's data type and name?\n",
                    "Add a variable",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);
        }

        //If a string was returned
        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return null;
    }
}
