package quizapp;

import java.util.ArrayList;

public class TestGetQuestions {

    public static void main(String[] args) {

        Model m = new Model();
        m.deleteQuestion(11);

        ArrayList<Question> al = m.getAllQuestions();

        for (Question q : al) {
            System.out.println(q);
            System.out.println("----------------------");
        }
    }
}