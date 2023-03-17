import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Status bar - displays the status of the program and describes the actions performed
 */

@Getter
@Setter
public class StatusBar extends JLabel implements MyObserver {
    private JLabel statusBar;

    public StatusBar(){
        super("    Program started");
    }

    @Override
    public void update(MyObservable ob) {
        setText("     " + Blackboard.getBlackboard().getStatusBarMessage());
        revalidate();
        repaint();
    }
}
