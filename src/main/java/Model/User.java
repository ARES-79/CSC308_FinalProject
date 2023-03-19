package Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Mitashi Parikh
 */
@MappedSuperclass
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Id
    private int userId;

    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(){}



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserId() {
        return userId;
    }

    private int generateUserId(){
        return 1;
    }

    @Override
    public String toString() {
        return "Model.User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
