package View;

import Controller.PopupMenuController;
import Model.PopupMenuModel;
import Model.UMLComponent;

import javax.swing.*;

public class PopupMenu extends JPopupMenu {
    public PopupMenu(UMLComponent clickedBox) {
        PopupMenuModel popupMenuModel = new PopupMenuModel();
        PopupMenuController controller = new PopupMenuController(clickedBox, popupMenuModel);
        JMenuItem delete = new JMenuItem("Delete Class");
        JMenuItem method = new JMenuItem("Add Method");
        JMenuItem variable = new JMenuItem("Add Variable");
        add(delete);
        add(method);
        add(variable);
        delete.addActionListener(controller);
        method.addActionListener(controller);
        variable.addActionListener(controller);
    }
}
