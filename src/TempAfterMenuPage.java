import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempAfterMenuPage {
      //Unsure if main is needed
//    public static void main(String[] args) {
//        new TempAfterMenuPage();
//    }

    //https://stackoverflow.com/questions/15512876/java-how-to-close-program-and-move-to-anther-one-from-a-gui

    public TempAfterMenuPage(Component parentWindow){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                final JFrame frame = new JFrame("Code --> UML");
                frame.setBounds(parentWindow.getBounds());
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel startPanel = new JPanel(new GridBagLayout());
                JButton testCloseButton = new JButton("Test Close");
                JButton startButton = new JButton("Submit");

                startPanel.add(testCloseButton);
                startPanel.add(startButton);

                testCloseButton.addActionListener(e -> {
                        parentWindow.setBounds(frame.getBounds());
                        parentWindow.setVisible(true);
                        frame.dispose();
                });

                startButton.addActionListener(e -> System.out.println("Student pressed submit."));
                frame.add(startPanel);

            }

        });
    }
}
