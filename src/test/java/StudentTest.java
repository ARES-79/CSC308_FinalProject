//package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void updateProficiencyTestUMLToCode() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getUMLtoCode(), "Testing subject proficiency UML to Code");
    }

    @Test
    void updateProficiencyTestCodeToUML() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoUML);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getCodetoUML(), "Testing subject proficiency Code to UML");
    }

    @Test
    void updateProficiencyTestCodeToMetrics() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getCodetoMetrics(), "Testing subject proficiency Code to Metrics");
    }

    @Test
    void updateProficiencyTestUMLToMetrics() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoMetrics);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getUMLtoMetrics(), "Testing subject proficiency UML to Metrics");
    }

    @Test
    void updateProficiencyTestOverall() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoMetrics);
        test.updateProficiency();
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.CodetoMetrics);
        test.updateProficiency();

        assertEquals(0.5, test.getOverallProficiency(), "Testing overall proficiency 1");
    }
}