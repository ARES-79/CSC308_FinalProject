import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column (name = "UMLtoCode")
    private float UMLtoCode;
    @Column (name = "CodetoUML")
    private float CodetoUML;
    @Column (name = "CodetoMetrics")
    private float CodetoMetrics;
    @Column (name = "UMLtoMetrics")
    private float UMLtoMetrics;


    /*

     */
    public Student(String username, String password, String firstName, String lastName, String classCode) {
        super(username, password, firstName, lastName);
        this.classCode = classCode;
        overallProficiency = 0;
        UMLtoCode = 0;
        CodetoUML = 0;
        CodetoMetrics = 0;
        UMLtoMetrics = 0;
    }

    public Student(){
        super();
    }


    public double getOverallProficiency() {
        return overallProficiency;
    }

    public void updateProficiency(){
        SubjectType subject = Blackboard.getBlackboard().getCurrentSubject();
        if(subject == SubjectType.UMLtoCode){
            UMLtoCode += 1;
        }
        else if(subject == SubjectType.CodetoUML){
            CodetoUML += 1;
        }
        else if(subject == SubjectType.CodetoMetrics){
            CodetoMetrics += 1;
        }
        else if(subject == SubjectType.UMLtoMetrics){
            UMLtoMetrics += 1;
        }
        float total = UMLtoCode + CodetoUML + CodetoMetrics + UMLtoMetrics;
        overallProficiency = total/4;
    }
}
