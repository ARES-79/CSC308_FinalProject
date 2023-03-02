import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorSignInPanel extends SignInPanel implements ActionListener {
    JPasswordField password;

    public InstructorSignInPanel(TempSignInWindow host){
        super();

        this.host = host;

        setLayout(new GridLayout(6,1));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,6));

        JButton back = new JButton("Back");
        for (int i = 0; i < 6; i++){
            if(i==0){topPanel.add(back);}
            else{topPanel.add(new JLabel());}
        }
        add(topPanel);

        JLabel welcome = new JLabel(
                "Welcome to the UML tutor.\nPlease sign in or create an account.",
                SwingConstants.CENTER);
        add(welcome);

        JPanel userPassPanel = new JPanel();
        userPassPanel.setLayout(new GridLayout(3,3));

        JButton submit = new JButton("Submit");

        username = new JTextField("Username");
        password = new JPasswordField("Password");
        password.setActionCommand("Submit");
        password.addActionListener(this);

        for (int i =0; i < 9; i++){
            if(i == 1){userPassPanel.add(username);}
            else if(i == 4){userPassPanel.add(password);}
            else if(i == 7){userPassPanel.add(submit);}
            else{userPassPanel.add(new JLabel(""));}
        }
        add(userPassPanel);

        JLabel createAccountPrompt = new JLabel("New? Create an account:", SwingConstants.CENTER);
        add(createAccountPrompt);

        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new GridLayout(2,3));
        JButton createInstructor = new JButton("Create Instructor Account");
        for(int i = 0; i < 6; i++){
            if (i == 1){createButtonPanel.add(createInstructor);}
            else{createButtonPanel.add(new JLabel(""));}
        }
        add(createButtonPanel);

        //action listeners
        back.addActionListener(this);
        submit.addActionListener(this);
        createInstructor.addActionListener(this);
    }

    @Override
    boolean isUserNameValid(String input) {
        //TODO: implement database check
        return true;
    }

    @Override
    boolean isPasswordCorrect(char[] input) {
        //TODO: implement database check
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case("Submit") -> {
                Instructor instructor = Blackboard.getBlackboard().getDatabaseController().getInstructorByUsername(username.getText());
                System.out.println(instructor);
                String input = String.valueOf(password.getPassword());
                if (instructor != null && input.equals(instructor.getPassword())) {
                    JOptionPane.showMessageDialog(this,
                            "Please be patient while be finish up the instructor page",
                            "Work In Progress",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid password. Try again.",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }


                //TODO: call the main of the instructor app instead of the student app
//                TutorApp.main(new String[]{username.getText(), password.getText()});
//                host.dispose();
            }
            case ("Back") -> {
                host.remove(host.getScreenPanel());
                host.setScreenPanel(host.createStartScreen());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
            case ("Create Instructor Account") ->{
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new CreateInstructorAccountPanel(host));
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
        }
    }
}
