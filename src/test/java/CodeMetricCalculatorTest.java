//package test.java;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class CodeMetricCalculatorTest {
//
//    //-------------------- LOC TESTS --------------------/
//    /**
//     * Testing a valid LOC -- ends with a semicolon
//     */
//     @Test
//     public static void validLOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "int x = 33;";
//        Assertions.assertTrue(calculator.isLOC(loc),
//                "Testing valid LOC input that ends with a semicolon.");
//     }
//
//    /**
//     * Testing a valid LOC -- ends with trailing white space
//     */
//    @Test
//    public static void validLOCWithTrailingWhiteSpace(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "int x = 33;  \n";
//        Assertions.assertTrue(calculator.isLOC(loc),
//                "Testing valid LOC input that ends with a semicolon and has trailing whitespace.");
//    }
//
//    /**
//     * Testing a valid LOC -- ends with open bracket
//     */
//    @Test
//    public static void validLOCWithOpenBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "if(isSet){";
//        Assertions.assertTrue(calculator.isLOC(loc),
//                "Testing valid LOC input that ends with an open bracket");
//    }
//
//    /**
//     * Testing an invalid LOC -- empty string
//     */
//     @Test
//     public static void invalidLOCEmptyString(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "";
//        Assertions.assertFalse(calculator.isLOC(input),
//                "Testing invalid LOC with empty string");
//     }
//
//    /**
//     * Testing an invalid LOC -- all whitespace
//     */
//    @Test
//    public static void invalidLOCAllWhiteSpace(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "\t    \n";
//        Assertions.assertFalse(calculator.isLOC(input),
//                "Testing invalid LOC with all whitespace");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LOC
//     */
//    @Test
//    public static void totalLOCZero(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = "";
//        Assertions.assertEquals(0, calculator.totalLOC(codeInput),
//                "Total LOC of empty string.");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LOC
//     */
//    @Test
//    public static void totalValidLOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                public class A{
//
//                   public static void method1(){
//                        System.out.println("Hello World");
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(5, calculator.totalLOC(codeInput),
//                "Total LOC of code input that looks like source valid code.");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LOC -- only brackets and paranthesis
//     */
//    @Test
//    public static void totalValidLOCParens(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                {
//
//                   {
//                    (
//                    )
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(6, calculator.totalLOC(codeInput),
//                "Total LOC of code input that is only brackets and parenthesis.");
//    }
//
//    //-------------------- eLOC TESTS --------------------/
//
//    /**
//     * Testing a valid ELOC -- ends with a semicolon
//     */
//    @Test
//    public static void validELOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "int x = 33;";
//        Assertions.assertTrue(calculator.isELOC(loc),
//                "Testing valid ELOC input that ends with a semicolon.");
//    }
//
//
//    /**
//     * Testing a valid ELOC -- ends with open bracket
//     */
//    @Test
//    public static void validELOCWithOpenBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "if(isSet){";
//        Assertions.assertTrue(calculator.isELOC(loc),
//                "Testing valid ELOC input that ends with an open bracket");
//    }
//
//    /**
//     * Testing an invalid ELOC -- empty string
//     */
//    @Test
//    public static void invalidELOCEmptyString(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "";
//        Assertions.assertFalse(calculator.isELOC(input),
//                "Testing invalid ELOC with empty string");
//    }
//
//    /**
//     * Testing an invalid ELOC -- bracket
//     */
//    @Test
//    public static void invalidELOCBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "{";
//        Assertions.assertFalse(calculator.isELOC(input),
//                "Testing invalid ELOC with bracket");
//    }
//
//    /**
//     * Testing an invalid ELOC -- Parenthesis
//     */
//    @Test
//    public static void invalidELOCParenthesis(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "(";
//        Assertions.assertFalse(calculator.isELOC(input),
//                "Testing invalid ELOC with parenthesis");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LOC
//     */
//    @Test
//    public static void totalELOCZero(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = "";
//        Assertions.assertEquals(0, calculator.totalELOC(codeInput),
//                "Total ELOC of empty string.");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero ELOC
//     */
//    @Test
//    public static void totalValidELOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                public class A{
//
//                   public static void method1(){
//                        System.out.println("Hello World");
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(3, calculator.totalELOC(codeInput),
//                "Total ELOC of code input that looks like source valid code.");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LOC -- only brackets and paranthesis
//     */
//    @Test
//    public static void totalValidELOCParens(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                {
//
//                   {
//                    (
//                    )
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(0, calculator.totalELOC(codeInput),
//                "Total LOC of code input that is only brackets and parenthesis.");
//    }
//
//    //-------------------- lLOC TESTS --------------------/
//
//    /**
//     * Testing a valid LLOC -- ends with a semicolon
//     */
//    @Test
//    public static void validLLOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "int x = 33;";
//        Assertions.assertTrue(calculator.isLLOC(loc),
//                "Testing valid ELOC input that ends with a semicolon.");
//    }
//
//
//    /**
//     * Testing a valid LLOC -- for loop
//     */
//    @Test
//    public static void validLLOCWithOpenBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "for (int i= 0; i < 10; i++){";
//        Assertions.assertTrue(calculator.isLLOC(loc),
//                "Testing valid ELOC input with for loop");
//    }
//
//    /**
//     * Testing an invalid LLOC -- empty string
//     */
//    @Test
//    public static void invalidLLOCEmptyString(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "";
//        Assertions.assertFalse(calculator.isLLOC(input),
//                "Testing invalid LLOC with empty string");
//    }
//
//    /**
//     * Testing a invalid LLOC -- if statement
//     */
//    @Test
//    public static void invalidLLOCWithOpenBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String loc = "if(isSet){";
//        Assertions.assertTrue(calculator.isLLOC(loc),
//                "Testing invalid LLOC if statement");
//    }
//
//    /**
//     * Testing an invalid LLOC -- bracket and parenthesis
//     */
//    @Test
//    public static void invalidLLOCBracket(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String input = "{ (";
//        Assertions.assertFalse(calculator.isLLOC(input),
//                "Testing invalid LLOC with bracket");
//    }
//
//    /**
//     * Testing a chunk of code with zero LLOC
//     */
//    @Test
//    public static void totalLLOCEmptyString(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = "";
//        Assertions.assertEquals(0, calculator.totalLLOC(codeInput),
//                "Total LLOC of empty string.");
//    }
//
//    /**
//     * Testing a chunk of code with nonzero LLOC
//     */
//    @Test
//    public static void totalValidLLOC(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                public class A{
//
//                   public static void method1(){
//                        System.out.println("Hello World");
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(1, calculator.totalLLOC(codeInput),
//                "Total LLOC of code input that looks like source valid code.");
//    }
//
//    /**
//     * Testing a chunk of code with zero LLOC -- nonempty string
//     */
//    @Test
//    public static void totalLLOCZero(){
//        CodeMetricCalculator calculator = new CodeMetricCalculator();
//        String codeInput = """
//                method1(){
//
//                   if(set True){
//                   }
//
//                }
//                """;
//        Assertions.assertEquals(0, calculator.totalLLOC(codeInput),
//                "Total LLOC of code input that has no semicolons.");
//    }
//}
