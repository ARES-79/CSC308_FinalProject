import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSignInPanel extends SignInPanel implements ActionListener {

    public StudentSignInPanel(TempSignInWindow host){
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
        JButton createInstructor = new JButton("Create Student Account");
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
                //TODO: implement login check
//                char[] input = password.getPassword();
//                if (isPasswordCorrect(input)) {
//                    JOptionPane.showMessageDialog(this,
//                            "Success! You typed the right password.");
//                } else {
//                    JOptionPane.showMessageDialog(this,
//                            "Invalid password. Try again.",
//                            "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//
//                //Zero out the possible password, for security.
//                Arrays.fill(input, '0');
//
//                password.selectAll();

                //TODO: call the main of the instructor app instead of the student app
                TutorApp.main(new String[]{username.getText(), password.getText()});
                host.dispose();
            }
            case ("Back") -> {
                host.remove(host.getScreenPanel());
                host.setScreenPanel(host.createStartScreen());
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
            case ("Create Student Account") ->{
                host.remove(host.getScreenPanel());
                host.setScreenPanel(new CreateStudentAccountPanel(host));
                host.add(host.getScreenPanel());
                host.revalidate();
                host.repaint();
            }
        }
    }
}
