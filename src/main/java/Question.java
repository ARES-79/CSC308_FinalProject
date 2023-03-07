import com.sun.source.tree.StatementTree;
import lombok.Getter;
import lombok.Setter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 *
 * Question object to hold information about each of the questions
 *
 */

@Getter
@Setter
public class Question implements Serializable {
    private int id;
    private String text;
    private String answer;
    private List<Hint> hints;
    double difficulty;

    public Question(int id, String text, String answer, List<Hint> hints, double difficulty){
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    boolean checkAnswer(String studentAnswer){
        return this.answer.strip().replace(" ", "").equalsIgnoreCase(studentAnswer.strip().replace(" ", ""));
    }
//
//    void writeCodeToUMLQuestions(){
//        String TEMPTEXT1 = """
//
//
//
//
//
//                        class A {
//
//                        }
//
//                        """;
//
//        String TEMPTEXT2 = """
//
//
//
//
//
//                        class B {
//                            A a;
//                            C c;
//
//                        }
//
//                        """;
//
//        String TEMPTEXT3 = """
//
//
//
//
//
//                        class B {
//                            A a;
//                            C c;
//
//                        }
//
//                        class C {
//                            D d;
//                            draw(){
//
//                            }
//
//                        }
//
//                        """;
//
//        //TODO: Load Question from DB
//        //will come from Blackboard eventually:
//        Hint hint1 = new Hint("hint1");
//        Hint hint2 = new Hint("hint2");
//        Hint hint3 = new Hint("hint3");
//        ArrayList<Hint> hints_list = new ArrayList<Hint>(Arrays.asList(hint1, hint2, hint3));
//        Question question1 = new Question(100, TEMPTEXT1, TEMPTEXT1, hints_list, 1);
//        Question question2 = new Question(101, TEMPTEXT2, TEMPTEXT2, hints_list, 2);
//        Question question3 = new Question(102, TEMPTEXT3, TEMPTEXT3, hints_list, 3);
//
//        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));
//
//        try {
//            FileOutputStream fileOut = new FileOutputStream("codeToUMLQuestions.bin");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(questions);
//            out.close();
//            fileOut.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }
}
