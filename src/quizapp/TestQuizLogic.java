package quizapp;

import java.util.ArrayList;

public class TestQuizLogic {
    public static void main(String[] args) {

        Model m = new Model();
        ArrayList<Question> questions = m.getAllQuestions();

        QuizLogic quiz = new QuizLogic(questions);

        int[] answers = {1, 3, 2, 3, 2, 1, 2, 3, 1, 2};

        for (int answer : answers) {
            System.out.println("Correct: " + quiz.checkAnswer(answer));
            quiz.nextQuestion();
        }

        System.out.println("Correct Answers: " + quiz.getCorrectAnswers());
        System.out.println("Wrong Answers: " + quiz.getWrongAnswers());
        System.out.println("Earnings: ₹" + quiz.getEarnings());
        System.out.println("Status: " + quiz.getPlayerStatus());
    }
}