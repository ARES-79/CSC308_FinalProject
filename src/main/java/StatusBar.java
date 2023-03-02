import javax.swing.*;

public class StatusBar extends JLabel implements MyObserver {
    private JLabel statusBar;

    public StatusBar(){
        super("    Program started");
        //ideally should have:
        //setLayout();
        //add(statusBar);
    }

    @Override
    public void update(MyObservable ob) {
        setText("     " + Blackboard.getBlackboard().getStatusBarMessage());
        revalidate();
        repaint();
    }
}
