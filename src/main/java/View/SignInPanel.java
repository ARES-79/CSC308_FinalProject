package View;

import javax.swing.*;

public abstract class SignInPanel extends JPanel {

    SignInWindow host;
    JTextField username;
    JTextField password;

    /**
     * checks that the username given is for a valid user
     *
     * @param input string input as the username from the user
     * @return true if the username is in the database, false otherwise
     */
    abstract boolean isUserNameValid(String input);

    /**
     * checks that the password given matches the user's password
     *
     * @param input the characters in the password field
     * @return true if the input matches the user's password, false otherwise
     */
    abstract boolean isPasswordCorrect(char[] input);
}
