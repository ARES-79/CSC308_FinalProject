import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * Container for Instructor Information
 */
public class Instructor extends User{

    private final String classCode;
    //studentList may be changed to a list of Student Objects
    //private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Integer> studentList = new ArrayList<>();

    public String getClassCode() {
        return classCode;
    }

    public ArrayList<Integer> getStudentList() {
        return studentList;
    }

    /**
     * Constructor
     *
     * @param username username of the instructor
     * @param password password of the instructor
     * @param firstName first name of the instructor
     * @param lastName last name of the instructor
     */
    public Instructor(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        classCode = generateClassCode();
    }

    /**
     * creates a random code that identifies the teacher's "class" or group of students.
     * students will use this code to join a teacher's class
     *
     * @return an alphanumeric code unique to this instructor
     */
    private String generateClassCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 8;
        Random random = new Random();
        String generatedString;

        do {
            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65)) // && (i <= 90 || i >= 97)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } while (!validateClassCode(generatedString));

        return generatedString;
    }

    /**
     * checks if the code already exists in the database
     *
     * @return true if the code is free to use, false otherwise
     */
    private boolean validateClassCode(String code){
        if (code == null){
            return false;
        }
        //TODO: implement the check
        return true;
    }

    /**
     * adds a student to the teachers list of students
     *
     * @param userId user ID of the new student in the instructor's class
     */
    public void addStudent(int userId){
        studentList.add(userId);
    }

//    public static void main(String[]args){
//        Instructor test = new Instructor("user", "pass", "First", "Last");
//        System.out.println(test.getClassCode());
//        System.out.println(test.generateClassCode());
//
//    }

}
