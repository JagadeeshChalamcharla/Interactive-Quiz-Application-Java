package quizapp.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomePage {

    private JFrame frame;
    private JButton startButton;
    private JButton adminButton;
    private JTextField nameField;

    public WelcomePage() {

        frame = new JFrame("Quiz Application");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "WELCOME TO QUIZ APPLICATION",
                SwingConstants.CENTER
        );
        
        JLabel nameLabel = new JLabel("Enter Player Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameField = new JTextField(20);

        JPanel namePanel = new JPanel();

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        title.setFont(new Font("Arial", Font.BOLD, 25));

        startButton = new JButton("Start Quiz");
        adminButton = new JButton("Admin Login");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(startButton);
        buttonPanel.add(adminButton);

        frame.add(title, BorderLayout.CENTER);
        frame.add(namePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {

            String playerName = nameField.getText().trim();

            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name!");
            } else {
                frame.dispose();
                new QuizPage(playerName);
            }

        });

        adminButton.addActionListener(e -> {
            new AdminLogin();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomePage();
    }
}