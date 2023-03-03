//package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void updateProficiencyTestBasic() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getSubjectProficiency().get(SubjectType.UMLtoCode), "Testing subject proficiency 1");
    }
}