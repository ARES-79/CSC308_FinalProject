package Model;

import Model.Blackboard;
import Model.User;
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
public class Student extends User {
    @Column(name = "classCode")
    private String classCode;
    @Column(name = "overallProficiency")
    private float overallProficiency;
    @Transient
    private List<Integer> completedQuestions = new ArrayList<>();
    @Column (name = "umlToCode")
    private float umlToCode;
    @Column (name = "codeToUML")
    private float codeToUML;
    @Column (name = "codeToMetrics")
    private float codeToMetrics;
    @Column (name = "umlToMetrics")
    private float umlToMetrics;


    /*

     */
    public Student(String username, String password, String firstName, String lastName, String classCode) {
        super(username, password, firstName, lastName);
        this.classCode = classCode;
        overallProficiency = 0;
        this.umlToCode = 0;
        this.codeToUML = 0;
        this.codeToMetrics = 0;
        this.umlToMetrics = 0;
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
            this.umlToCode += 1;
        }
        else if(subject == SubjectType.CodetoUML){
            this.codeToUML += 1;
        }
        else if(subject == SubjectType.CodetoMetrics){
            this.codeToMetrics += 1;
        }
        else if(subject == SubjectType.UMLtoMetrics){
            this.umlToMetrics += 1;
        }
        float total = this.umlToCode + this.codeToMetrics + this.codeToUML + this.umlToMetrics;
        overallProficiency = total/4;
        Blackboard.getBlackboard().getDatabaseController().updateStudentProficiency(this);
    }
}
