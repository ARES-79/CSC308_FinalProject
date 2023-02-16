import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * Second attempt at a window that can open when a button is pressed
 *      supposed to much easier to digest
 */
public class TempAfterMenuPageV3 extends JPanel implements ActionListener {

//    @Override
//    public void run() {
////        EventQueue.invokeLater(this);
//        this.setBounds(parentWindow.getBounds());
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }



    public TempAfterMenuPageV3(){
        super();

        setLayout(new GridLayout(1,3));
        JEditorPane codeProblem = new JEditorPane("type",
                "Text Code Here \n" +
                "ex: \n" +
                "class A {\n" +
                "}");
        codeProblem.setEditable(false);
        JButton testCloseButton = new JButton("Test Close");
        JButton startButton = new JButton("Submit");

        this.add(codeProblem);
        this.add(testCloseButton);
        this.add(startButton);

        startButton.addActionListener(this);
        testCloseButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ("Submit") -> {
                System.out.println("Submit was pressed.");
            }
            case ("Test Close") -> {
                //
            }

        }
    }

}
