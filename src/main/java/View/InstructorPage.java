package View;

import Controller.DatabaseController;
import Controller.InstructorController;
import Model.Blackboard;
import Model.Instructor;
import Model.Student;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


@Getter
@Setter
public class InstructorPage extends JPanel {


    public InstructorPage(InstructorController controller) {
        super();
        setLayout(new GridLayout(8, 1));
        add(new JLabel(""));

        JLabel welcome;
        //Welcome Message
        if (!(Blackboard.getBlackboard().getCurrentUser() == null)) {
            welcome = new JLabel("Welcome, " + Blackboard.getBlackboard().getCurrentUser().getFirstName() +
                    ", to the UML tutor! Below you can view the students in your class, click on" +
                    "a student for more information", SwingConstants.CENTER);
        } else {
            welcome = new JLabel(
                    "Welcome to the UML tutor! Please select your subject.", SwingConstants.CENTER);
        }
        JLabel classCode = new JLabel("For future reference, you class code is "
                + ((Instructor)Blackboard.getBlackboard().getCurrentUser()).getClassCode(), SwingConstants.CENTER);

        add(welcome);
        add(classCode);
        DatabaseController dbController = Blackboard.getBlackboard().getDatabaseController();
        ArrayList<Student> students = dbController.getStudentsByClassCode(((Instructor) Blackboard.getBlackboard().getCurrentUser()).getClassCode());
        for(Student student : students){
            JButton s = new JButton(student.getUsername());
            s.addActionListener(controller);
            add(s);
        }

    }

}
