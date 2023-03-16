import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class TEMP_UMLToCodeWindow extends JFrame implements ActionListener,  Runnable {
    Component parentWindow;

    @Override
    public void run() {
//        EventQueue.invokeLater(this);
        this.setBounds(parentWindow.getBounds());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public TEMP_UMLToCodeWindow(Component parentWindow){
        super("UML --> Code");
        this.parentWindow = parentWindow;
        JPanel startPanel = new JPanel(new GridBagLayout());
        JButton testCloseButton = new JButton("Test Close");
        JButton startButton = new JButton("Submit");

        startPanel.add(testCloseButton);
        startPanel.add(startButton);

        startButton.addActionListener(this);
        testCloseButton.addActionListener(this);

        add(startPanel);
        run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ("Submit") -> {
                System.out.println("Submit was pressed.");
            }
            case ("Test Close") -> {
                parentWindow.setBounds(this.getBounds());
                parentWindow.setVisible(true);
                dispose();
            }

        }
    }

}
