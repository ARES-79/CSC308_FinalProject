import java.util.HashMap;

/**
 * @author Mitashi Parikh
 */
public class Student extends User{
    private int teacherCode;
    private double overallProficiency;
    private HashMap<String,Double> subjectProficiency = new HashMap<>();;

    Student(String username, String password, String firstName, String lastName, int teacherCode) {
        super(username, password, firstName, lastName);
        this.teacherCode = teacherCode;
        overallProficiency = 0;
        subjectProficiency.put("UMLtoCode", 0.0);
        subjectProficiency.put("CodeUML", 0.0);
        subjectProficiency.put("CodetoMetrics", 0.0);
        subjectProficiency.put("UMLtoMetrics", 0.0);

    }

    public int getTeacherCode() {
        return teacherCode;
    }
}
