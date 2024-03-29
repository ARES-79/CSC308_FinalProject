package View;

import Model.Blackboard;
import Controller.TutorController;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MenuPanel  extends JPanel {

    public MenuPanel(TutorController controller){
        super();
        setLayout(new GridLayout(8,1));
        add(new JLabel(""));

        JLabel welcome;
        //Welcome Message
        if (!(Blackboard.getBlackboard().getCurrentUser() == null)){welcome = new JLabel("Welcome, " + Blackboard.getBlackboard().getCurrentUser().getFirstName() +
                ", to the UML tutor! Please select your subject.", SwingConstants.CENTER);
        } else{welcome = new JLabel(
                "Welcome to the UML tutor! Please select your subject.", SwingConstants.CENTER);}
        add(welcome);

        //center
        JButton code_to_UML = new JButton("Code --> UML");
        JButton code_to_Metrics = new JButton("Code --> Metrics");
        JButton UML_to_Code = new JButton("UML --> Code");
        JButton UML_to_Metrics = new JButton("UML --> Metrics");
        JButton practice = new JButton("Practice");

        add(code_to_UML);
        add(code_to_Metrics);
        add(UML_to_Code);
        add(UML_to_Metrics);
        add(practice);

        code_to_UML.addActionListener(controller);
        code_to_Metrics.addActionListener(controller);
        UML_to_Code.addActionListener(controller);
        UML_to_Metrics.addActionListener(controller);
        practice.addActionListener(controller);

    }

}
