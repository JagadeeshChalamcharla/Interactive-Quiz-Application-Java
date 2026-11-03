package quizapp.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminLogin {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;

    public AdminLogin() {

        frame = new JFrame("Admin Login");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("ADMIN LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);

        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        loginButton = new JButton("Login");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> validateLogin());

        frame.setVisible(true);
    }

    private void validateLogin() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {

            JOptionPane.showMessageDialog(frame, "Login Successful!");

            frame.dispose();
            new AdminDashboard();

        } else {

            JOptionPane.showMessageDialog(frame, "Invalid Username or Password!");
        }
    }

    public static void main(String[] args) {

        new AdminLogin();
    }
}