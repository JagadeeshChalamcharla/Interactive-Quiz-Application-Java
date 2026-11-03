package quizapp;

import java.util.Scanner;

public class ManageQuestions {

    public static void main(String[] args) {

        Model m = new Model();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Update Question");
        System.out.println("2. Delete Question");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {

        case 1:

            Question q = new Question();

            System.out.print("Enter Question ID: ");
            q.setQuestionId(sc.nextInt());

            sc.nextLine();

            System.out.print("Enter Question Text: ");
            q.setQuestionText(sc.nextLine());

            System.out.print("Enter Option 1: ");
            q.setOption1(sc.nextLine());

            System.out.print("Enter Option 2: ");
            q.setOption2(sc.nextLine());

            System.out.print("Enter Option 3: ");
            q.setOption3(sc.nextLine());

            System.out.print("Enter Option 4: ");
            q.setOption4(sc.nextLine());

            System.out.print("Enter Correct Option (1-4): ");
            q.setCorrectOption(sc.nextInt());

            System.out.print("Enter Reward: ");
            q.setReward(sc.nextInt());

            m.updateQuestion(q);

            break;

        case 2:

            System.out.print("Enter Question ID to Delete: ");
            int questionId = sc.nextInt();

            m.deleteQuestion(questionId);

            break;

        default:

            System.out.println("Invalid Choice!");
        }

        sc.close();
    }
}