import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel implements MyObserver{
    private JLabel statusBar;

    public StatusBar(){
        statusBar = new JLabel("    Program started");
        //ideally should have:
        //setLayout();
        //add(statusBar);
    }

    @Override
    public void update(MyObservable ob) {
        statusBar.setText("     " + Blackboard.getBlackboard().getStatusBarMessage());
        statusBar.revalidate();
    }
}
