import lombok.Getter;
import lombok.Setter;

import javax.persistence.PersistenceException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class CreateStudentAccountPanel extends SignInPanel implements ActionListener {

    private JTextField firstName;
    private JTextField lastName;
    private JTextField classCode;

    public CreateStudentAccountPanel(TempSignInWindow host) {
        super();

        this.host = host;

        setLayout(new GridLayout(4, 1));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 6));

        JButton back = new JButton("Back");
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                topPanel.add(back);
            } else {
                topPanel.add(new JLabel());
            }
        }
        add(topPanel);

        JLabel welcome = new JLabel(
                "Please input your information to create an account.",
                SwingConstants.CENTER);
        add(welcome);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new GridLayout(6, 4));

        JLabel firstNamePrompt = new JLabel("First Name: ", SwingConstants.CENTER);
        JLabel lastNamePrompt = new JLabel("Last Name: ", SwingConstants.CENTER);
        JLabel userNamePrompt = new JLabel("Username: ", SwingConstants.CENTER);
        JLabel passwordPrompt = new JLabel("Password: ", SwingConstants.CENTER);
        JLabel classCodePrompt = new JLabel("Class Code: ", SwingConstants.CENTER);

        firstName = new JTextField("FirstName");
        lastName = new JTextField("LastName");
        username = new JTextField("Username");
        password = new JTextField("Password");
        classCode = new JTextField("Class Code");
        password.setActionCommand("Submit");
        password.setVisible(true);
        password.addActionListener(this);

        JButton submit = new JButton("Submit");

        for (int i = 0; i < 24; i++) {
            if (i == 1) {
                userInfoPanel.add(firstNamePrompt);
            } else if (i == 2) {
                userInfoPanel.add(firstName);
            } else if (i == 5) {
                userInfoPanel.add(lastNamePrompt);
            } else if (i == 6) {
                userInfoPanel.add(lastName);
            } else if (i == 9) {
                userInfoPanel.add(userNamePrompt);
            } else if (i == 10) {
                userInfoPanel.add(username);
            } else if (i == 13) {
                userInfoPanel.add(passwordPrompt);
            } else if (i == 14) {
                userInfoPanel.add(password);
            } else if (i == 17) {
                userInfoPanel.add(classCodePrompt);
            } else if (i == 18) {
                userInfoPanel.add(classCode);
            } else if (i == 22) {
                userInfoPanel.add(submit);
            } else {
                userInfoPanel.add(new JLabel(""));
            }
        }
        add(userInfoPanel);


        //action listeners
        back.addActionListener(this);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ("Back") -> {
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new StudentSignInPanel(host));
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
            case ("Submit") -> {
                //TODO: implement putting the data into the database
                Student test = new Student(username.getText(), password.getText(),
                        firstName.getText(), lastName.getText(), classCode.getText());
                try {
                    Blackboard.getBlackboard().getDatabaseController().saveStudent(test);
                    TutorApp.main();
                    host.dispose();
                    JOptionPane.showMessageDialog(this,
                            "Successfully created your student account",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (PersistenceException exception) {
                    JOptionPane.showMessageDialog(this,
                            "Username Already Exists or Class Code Not Valid",
                            "Error Creating Account",
                            JOptionPane.ERROR_MESSAGE);
                }
                Blackboard.getBlackboard().setCurrentUser(test);
                Blackboard.getBlackboard().appendStudent(test);
                System.out.println(test);

            }
        }

    }

    /**
     * @param input string input as the username from the user
     * @return true if the username is not in use, false otherwise
     */
    @Override
    boolean isUserNameValid(String input) {
        //TODO: implement database check
        return true;
    }

    boolean isClassCodeValid(String input) {
        //TODO: implement database check
        return true;
    }

    @Override
    boolean isPasswordCorrect(char[] input) {
        return true;
    }
}
