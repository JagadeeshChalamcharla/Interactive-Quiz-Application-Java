package quizapp.gui;

import quizapp.ManageQuestions;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AdminDashboard {

    private JFrame frame;

    private JButton manageQuestionsButton;
    private JButton viewResultsButton;
    private JButton logoutButton;

    public AdminDashboard() {

        frame = new JFrame("Admin Dashboard");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "ADMIN DASHBOARD",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 25));

        manageQuestionsButton = new JButton("Manage Questions");
        viewResultsButton = new JButton("View Player Results");
        logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(manageQuestionsButton);
        buttonPanel.add(viewResultsButton);
        buttonPanel.add(logoutButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        manageQuestionsButton.addActionListener(e -> {
            new ManageQuestions();
        });

        viewResultsButton.addActionListener(e -> {
            new ViewResults();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePage();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}