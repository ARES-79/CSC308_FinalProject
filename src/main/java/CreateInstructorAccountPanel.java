import javax.persistence.EntityExistsException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInstructorAccountPanel extends SignInPanel implements ActionListener {

    JTextField firstName;
    JTextField lastName;

    public CreateInstructorAccountPanel(TempSignInWindow host){
        super();

        this.host = host;

        setLayout(new GridLayout(4,1));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,6));

        JButton back = new JButton("Back");
        for (int i = 0; i < 6; i++){
            if(i==0){topPanel.add(back);}
            else{topPanel.add(new JLabel());}
        }
        add(topPanel);

        JLabel welcome = new JLabel(
                "Please input your information to create an account.",
                SwingConstants.CENTER);
        add(welcome);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new GridLayout(5,4));

        JLabel firstNamePrompt = new JLabel("First Name: ", SwingConstants.CENTER);
        JLabel lastNamePrompt = new JLabel("Last Name: ", SwingConstants.CENTER);
        JLabel userNamePrompt = new JLabel("Username: ", SwingConstants.CENTER);
        JLabel passwordPrompt= new JLabel("Password: ", SwingConstants.CENTER);

        firstName = new JTextField("FirstName");
        lastName = new JTextField("LastName");
        username = new JTextField("Username");
        password = new JTextField("Password");
        password.setActionCommand("Submit");
        password.setVisible(true);
        password.addActionListener(this);

        JButton submit = new JButton("Submit");

        for (int i =0; i < 20; i++){
            if(i == 1){userInfoPanel.add(firstNamePrompt);}
            else if (i == 2){userInfoPanel.add(firstName);}
            else if (i == 5){userInfoPanel.add(lastNamePrompt);}
            else if (i == 6){userInfoPanel.add(lastName);}
            else if(i == 9){userInfoPanel.add(userNamePrompt);}
            else if(i == 10){userInfoPanel.add(username);}
            else if(i == 13){userInfoPanel.add(passwordPrompt);}
            else if(i == 14){userInfoPanel.add(password);}
            else if(i == 18){userInfoPanel.add(submit);}
            else{userInfoPanel.add(new JLabel(""));}
        }
        add(userInfoPanel);


        //action listeners
        back.addActionListener(this);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ("Back") ->{
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new InstructorSignInPanel(host));
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
            case ("Submit") ->{
                //TODO: implement putting the data into the database
                Instructor test = new Instructor(username.getText(), password.getText(),
                            firstName.getText(), lastName.getText(), null);
                try {
                    Blackboard.getBlackboard().getDatabaseController().saveInstructor(test);
                }
                catch (EntityExistsException exception){
                    JOptionPane.showMessageDialog(this,
                            "An instructor with that username already exists",
                            "User Already Exists",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    /**
     *
     * @param input string input as the username from the user
     * @return true if the username is not in use, false otherwise
     */
    @Override
    boolean isUserNameValid(String input) {
        //TODO: implement database check
        return true;
    }

    @Override
    boolean isPasswordCorrect(char[] input) {
        return true;
    }
}
