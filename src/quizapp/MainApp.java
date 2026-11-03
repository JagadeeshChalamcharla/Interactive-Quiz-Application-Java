package quizapp;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
	
	    Model m = new Model();
	
	    ArrayList<Question> questions = m.getAllQuestions();
	
	    QuizLogic quiz = new QuizLogic(questions);
	
	    Scanner sc = new Scanner(System.in);
	
	    System.out.println("Total Questions: " + quiz.getTotalQuestions());
	
	    while (!quiz.isQuizCompleted()) {
	
	        Question currentQuestion = quiz.getCurrentQuestion();
	
	        System.out.println("\nQuestion: " + currentQuestion.getQuestionText());
	
	        System.out.println("1. " + currentQuestion.getOption1());
	        System.out.println("2. " + currentQuestion.getOption2());
	        System.out.println("3. " + currentQuestion.getOption3());
	        System.out.println("4. " + currentQuestion.getOption4());
	        
	        System.out.println("\n1. Use Audience Poll");
	        System.out.println("2. Continue without Lifeline");

	        System.out.print("Choose an option: ");
	        int choice = sc.nextInt();

	        if (choice == 1) {

	            if (quiz.useAudiencePoll()) {

	                int[] poll = quiz.generateAudiencePoll();

	                System.out.println("\nAudience Poll Results:");

	                for (int i = 0; i < poll.length; i++) {
	                    System.out.println((i + 1) + ". " + poll[i] + "%");
	                }

	            } else {
	                System.out.println("Audience Poll already used!");
	            }
	        }
	        
	        System.out.println("\n3. Use 50-50 Lifeline");

	        System.out.print("Choose an option: ");
	        int lifelineChoice = sc.nextInt();

	        if (lifelineChoice == 3) {

	            if (quiz.useFiftyFifty()) {

	                int[] options = quiz.useFiftyFiftyOptions();

	                System.out.println("\n50-50 Lifeline:");

	                for (int i = 1; i <= 4; i++) {

	                    if (i == options[0] || i == options[1]) {
	                        System.out.println(i + ". Available");
	                    } else {
	                        System.out.println(i + ". Removed");
	                    }
	                }

	            } else {
	                System.out.println("50-50 Lifeline already used!");
	            }
	        }
	
	        System.out.print("Enter your answer (1-4): ");
	        int answer = sc.nextInt();
	
	        boolean result = quiz.checkAnswer(answer);
	
	        if (result) {
	
	            System.out.println("Correct Answer!");
	
	            quiz.nextQuestion();
	
	        } else {
	
	            System.out.println("Wrong Answer!");
	            System.out.println("Quiz Over!");
	
	            break;
	        }
	    }
	    
	    System.out.print("Enter Player Name: ");
	    String playerName = sc.next();

	    QuizResults qr = quiz.getResult(playerName);

	    m.saveResult(qr);
	    
	    System.out.println("\n========== QUIZ RESULT ==========");
	
	    System.out.println("Total Questions: " + quiz.getTotalQuestions());
	    System.out.println("Questions Attempted: " + quiz.getQuestionsAttempted());
	    System.out.println("Correct Answers: " + quiz.getCorrectAnswers());
	    System.out.println("Wrong Answers: " + quiz.getWrongAnswers());
	    System.out.println("Total Earnings: ₹" + quiz.getEarnings());
	
	    if (quiz.isQuizCompleted()) {
	        System.out.println("Status: Completed");
	    } else {
	        System.out.println("Status: Failed");
	    }
	
	    sc.close();
	}

}
