//package test.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmitTest {

    @Test
    void submitUMLTestHint1() {
        Student test = new Student("TestA", "abcd", "Test", "A", "code");
        Blackboard.getBlackboard().setCurrentSubject(SubjectType.UMLtoCode);
        test.updateProficiency();

        assertEquals(0.25, test.getOverallProficiency(), "Testing overall proficiency 1");
        assertEquals(1, test.getUmlToCode(), "Testing subject proficiency UML to Code");
    }


}