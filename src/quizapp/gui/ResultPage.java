package quizapp.gui;

import quizapp.QuizResults;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ResultPage {

    private JFrame frame;

    public ResultPage(QuizResults result) {

        frame = new JFrame("Quiz Results");

        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "QUIZ RESULT",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel resultPanel = new JPanel(new GridLayout(7, 1, 5, 5));

        resultPanel.add(new JLabel("Player Name: " + result.getPlayerName()));
        resultPanel.add(new JLabel("Total Questions: " + result.getTotalQuestions()));
        resultPanel.add(new JLabel("Questions Attempted: " + result.getQuestionsAttempted()));
        resultPanel.add(new JLabel("Correct Answers: " + result.getCorrectAnswers()));
        resultPanel.add(new JLabel("Wrong Answers: " + result.getWrongAnswers()));
        resultPanel.add(new JLabel("Total Earnings: ₹" + result.getEarnings()));
        resultPanel.add(new JLabel("Status: " + result.getStatus()));

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(e -> frame.dispose());

        frame.add(title, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);
        frame.add(closeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}