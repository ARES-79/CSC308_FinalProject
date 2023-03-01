import java.util.HashMap;

/**
 * @author Mitashi Parikh
 */
public class Student extends User{
    private int classCode;
    private double overallProficiency;
    private HashMap<String,Double> subjectProficiency = new HashMap<>();;

    Student(String username, String password, String firstName, String lastName, int classCode) {
        super(username, password, firstName, lastName);
        this.classCode = classCode;
        overallProficiency = 0;
        subjectProficiency.put("UMLtoCode", 0.0);
        subjectProficiency.put("CodeUML", 0.0);
        subjectProficiency.put("CodetoMetrics", 0.0);
        subjectProficiency.put("UMLtoMetrics", 0.0);
    }

    public int getClassCode() {
        return classCode;
    }
}
