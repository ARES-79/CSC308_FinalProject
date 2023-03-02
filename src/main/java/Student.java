import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mitashi Parikh
 */
@Entity
@Table(name = "Students")
public class Student extends User{
    @Column(name = "classCode")
    private String classCode;
    @Column(name = "overallProficiency")
    private float overallProficiency;
    @Transient
    private List<Integer> completedQuestions = new ArrayList<>();
    @Transient
    private HashMap<SubjectType, Double> subjectProficiency = new HashMap<SubjectType, Double>();;

    /*

     */
    public Student(String username, String password, String firstName, String lastName, String classCode) {
        super(username, password, firstName, lastName);
        this.classCode = classCode;
        overallProficiency = 0;
        subjectProficiency.put(SubjectType.UMLtoCode, 0.0);
        subjectProficiency.put(SubjectType.CodetoUML, 0.0);
        subjectProficiency.put(SubjectType.CodetoMetrics, 0.0);
        subjectProficiency.put(SubjectType.UMLtoMetrics, 0.0);
    }

    public Student(){
        super();
    }

    public String getClassCode() {
        return classCode;
    }

    public float getOverallProficiency() {
        return overallProficiency;
    }

    public HashMap<SubjectType, Double> getSubjectProficiency() {
        return subjectProficiency;
    }

    public void updateProficiency(){
        SubjectType subject = Blackboard.getBlackboard().getCurrentSubject();
        subjectProficiency.put(subject, subjectProficiency.get(subject)+1);
        float total = 0;
        for(SubjectType key: subjectProficiency.keySet()) {
            total += subjectProficiency.get(key);
        }
        overallProficiency = total/subjectProficiency.size();
    }
}
