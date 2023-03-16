
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * Test Cases for Test Driven Development of a class to calculate code metrics
 */
public class CodeMetricCalculatorTest {

    //-------------------- LOC TESTS --------------------/
    /**
     * Testing a valid LOC -- ends with a semicolon
     */
     @Test
     void validLOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "int x = 33;";
        Assertions.assertTrue(calculator.isLOC(loc),
                "Testing valid LOC input that ends with a semicolon.");
     }

    /**
     * Testing a valid LOC -- ends with trailing white space
     */
    @org.junit.jupiter.api.Test
    void validLOCWithTrailingWhiteSpace(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "int x = 33;  \n";
        Assertions.assertTrue(calculator.isLOC(loc),
                "Testing valid LOC input that ends with a semicolon and has trailing whitespace.");
    }

    /**
     * Testing a valid LOC -- ends with open bracket
     */
    @org.junit.jupiter.api.Test
     void validLOCWithOpenBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "if(isSet){";
        Assertions.assertTrue(calculator.isLOC(loc),
                "Testing valid LOC input that ends with an open bracket");
    }

    /**
     * Testing an invalid LOC -- empty string
     */
     @org.junit.jupiter.api.Test
      void invalidLOCEmptyString(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "";
        Assertions.assertFalse(calculator.isLOC(input),
                "Testing invalid LOC with empty string");
     }

    /**
     * Testing an invalid LOC -- all whitespace
     */
    @org.junit.jupiter.api.Test
     void invalidLOCAllWhiteSpace(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "\t    \n";
        Assertions.assertFalse(calculator.isLOC(input),
                "Testing invalid LOC with all whitespace");
    }

    /**
     * Testing a chunk of code with nonzero LOC
     */
    @org.junit.jupiter.api.Test
     void totalValidLOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                public class A{

                   public static void method1(){
                        System.out.println("Hello World");
                   }

                }
                """;
        Assertions.assertEquals(5, calculator.totalLOC(codeInput),
                "Total LOC of code input that looks like source valid code.");
    }

    /**
     * Testing a chunk of code with zero LOC
     */
    @org.junit.jupiter.api.Test
     void totalLOCZero(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = "";
        Assertions.assertEquals(0, calculator.totalLOC(codeInput),
                "Total LOC of empty string.");
    }

    /**
     * Testing a chunk of code with nonzero LOC -- only brackets and paranthesis
     */
    @org.junit.jupiter.api.Test
     void totalValidLOCParens(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                {

                   {
                    (
                    )
                   }

                }
                """;
        Assertions.assertEquals(6, calculator.totalLOC(codeInput),
                "Total LOC of code input that is only brackets and parenthesis.");
    }

    //-------------------- eLOC TESTS --------------------/

    /**
     * Testing a valid ELOC -- ends with a semicolon
     */
    @org.junit.jupiter.api.Test
     void validELOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "int x = 33;";
        Assertions.assertTrue(calculator.isELOC(loc),
                "Testing valid ELOC input that ends with a semicolon.");
    }


    /**
     * Testing a valid ELOC -- ends with open bracket
     */
    @org.junit.jupiter.api.Test
     void validELOCWithOpenBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "if(isSet){";
        Assertions.assertTrue(calculator.isELOC(loc),
                "Testing valid ELOC input that ends with an open bracket");
    }

    /**
     * Testing an invalid ELOC -- empty string
     */
    @org.junit.jupiter.api.Test
     void invalidELOCEmptyString(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "";
        Assertions.assertFalse(calculator.isELOC(input),
                "Testing invalid ELOC with empty string");
    }

    /**
     * Testing an invalid ELOC -- bracket
     */
    @org.junit.jupiter.api.Test
     void invalidELOCBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "{";
        Assertions.assertFalse(calculator.isELOC(input),
                "Testing invalid ELOC with bracket");
    }

    /**
     * Testing an invalid ELOC -- Parenthesis
     */
    @org.junit.jupiter.api.Test
     void invalidELOCParenthesis(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "(";
        Assertions.assertFalse(calculator.isELOC(input),
                "Testing invalid ELOC with parenthesis");
    }

    /**
     * Testing a chunk of code with nonzero LOC
     */
    @org.junit.jupiter.api.Test
     void totalELOCZero(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = "";
        Assertions.assertEquals(0, calculator.totalELOC(codeInput),
                "Total ELOC of empty string.");
    }

    /**
     * Testing a chunk of code with nonzero ELOC
     */
    @org.junit.jupiter.api.Test
     void totalValidELOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                public class A{

                   public static void method1(){
                        System.out.println("Hello World");
                   }

                }
                """;
        Assertions.assertEquals(3, calculator.totalELOC(codeInput),
                "Total ELOC of code input that looks like source valid code.");
    }

    /**
     * Testing a chunk of code with nonzero LOC -- only brackets and paranthesis
     */
    @org.junit.jupiter.api.Test
     void totalValidELOCParens(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                {

                   {
                    (
                    )
                   }

                }
                """;
        Assertions.assertEquals(0, calculator.totalELOC(codeInput),
                "Total LOC of code input that is only brackets and parenthesis.");
    }

    //-------------------- lLOC TESTS --------------------/

    /**
     * Testing a valid LLOC -- ends with a semicolon
     */
    @org.junit.jupiter.api.Test
     void validLLOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "int x = 33;";
        Assertions.assertTrue(calculator.isLLOC(loc),
                "Testing valid ELOC input that ends with a semicolon.");
    }


    /**
     * Testing a valid LLOC -- for loop
     */
    @org.junit.jupiter.api.Test
     void validLLOCWithOpenBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "for (int i= 0; i < 10; i++){";
        Assertions.assertTrue(calculator.isLLOC(loc),
                "Testing valid ELOC input with for loop");
    }

    /**
     * Testing an invalid LLOC -- empty string
     */
    @org.junit.jupiter.api.Test
     void invalidLLOCEmptyString(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "";
        Assertions.assertFalse(calculator.isLLOC(input),
                "Testing invalid LLOC with empty string");
    }

    /**
     * Testing a invalid LLOC -- if statement
     */
    @org.junit.jupiter.api.Test
     void invalidLLOCWithOpenBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String loc = "if(isSet){";
        Assertions.assertFalse(calculator.isLLOC(loc),
                "Testing invalid LLOC if statement");
    }

    /**
     * Testing an invalid LLOC -- bracket and parenthesis
     */
    @org.junit.jupiter.api.Test
     void invalidLLOCBracket(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String input = "{ (";
        Assertions.assertFalse(calculator.isLLOC(input),
                "Testing invalid LLOC with bracket");
    }

    /**
     * Testing a chunk of code with zero LLOC
     */
    @org.junit.jupiter.api.Test
     void totalLLOCEmptyString(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = "";
        Assertions.assertEquals(0, calculator.totalLLOC(codeInput),
                "Total LLOC of empty string.");
    }

    /**
     * Testing a chunk of code with nonzero LLOC
     */
    @org.junit.jupiter.api.Test
     void totalValidLLOC(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                public class A{

                   public static void method1(){
                        System.out.println("Hello World");
                   }

                }
                """;
        Assertions.assertEquals(1, calculator.totalLLOC(codeInput),
                "Total LLOC of code input that looks like source valid code.");
    }

    /**
     * Testing a chunk of code with zero LLOC -- nonempty string
     */
    @org.junit.jupiter.api.Test
     void totalLLOCZero(){
        CodeMetricCalculator calculator = new CodeMetricCalculator();
        String codeInput = """
                method1(){

                   if(set True){
                   }

                }
                """;
        Assertions.assertEquals(0, calculator.totalLLOC(codeInput),
                "Total LLOC of code input that has no semicolons.");
    }

    //-------------------- observer tests --------------------/
    /**
     * Testing that the string is correct
     */
    @org.junit.jupiter.api.Test
    void updateTextAreaTest(){
        JTextArea textArea = new JTextArea();
        CustomTextArea codeText = new CustomTextArea(30,20);
        codeText.setText("""
                method1(){

                }
                """);
        CodeMetricCalculator calculator = new CodeMetricCalculator(textArea, codeText);
        calculator.update(Blackboard.getBlackboard());
        String expected = "\t LOC: 2\t\t eLOC: 1\t\t lLOC: 0";
        Assertions.assertEquals(expected, textArea.getText(),
                "Update called on the observer code metric calculator");
    }

}
