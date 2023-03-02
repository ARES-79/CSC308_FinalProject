import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mitashi Parikh
 */
@Entity
@Table(name = "Students")
@Getter
@Setter
public class Student extends User{
    @Column(name = "classCode")
    private String classCode;
    @Column(name = "overallProficiency")
    private float overallProficiency;
    @Transient
    private List<Integer> completedQuestions = new ArrayList<>();
    @Transient
    private HashMap<String,Double> subjectProficiency = new HashMap<>();;

    public Student(String username, String password, String firstName, String lastName, String classCode) {
        super(username, password, firstName, lastName);
        this.classCode = classCode;
        overallProficiency = 0;
        subjectProficiency.put("UMLtoCode", 0.0);
        subjectProficiency.put("CodeUML", 0.0);
        subjectProficiency.put("CodetoMetrics", 0.0);
        subjectProficiency.put("UMLtoMetrics", 0.0);
    }

    public Student(){
        super();
    }
}
