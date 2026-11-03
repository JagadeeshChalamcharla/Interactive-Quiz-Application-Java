package quizapp.gui;

import quizapp.Model;
import quizapp.Question;
import quizapp.QuizLogic;
import quizapp.QuizResults;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class QuizPage {

	private JFrame frame;
	private JLabel questionLabel;
	private JLabel questionNumberLabel;
	
	private JRadioButton option1;
	private JRadioButton option2;
	private JRadioButton option3;
	private JRadioButton option4;
	
	private ButtonGroup buttonGroup;
	
	private JButton submitButton;
	private JButton audiencePollButton;
	private JButton fiftyFiftyButton;
	
	private Model model;
	private QuizLogic quiz;
	
	private String playerName;

	public QuizPage(String playerName) {

	    this.playerName = playerName;
	
	    model = new Model();
	
	    ArrayList<Question> questions = model.getAllQuestions();
	
	    if (questions.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No questions available!");
	        return;
	    }
	
	    quiz = new QuizLogic(questions);
	
	    frame = new JFrame("Quiz Application");
	
	    frame.setSize(700, 500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setLayout(new BorderLayout(10, 10));
	
	    questionNumberLabel = new JLabel("", SwingConstants.CENTER);
	    questionNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
	
	    questionLabel = new JLabel("", SwingConstants.CENTER);
	    questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
	
	    JPanel questionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
	
	    questionPanel.add(questionNumberLabel);
	    questionPanel.add(questionLabel);
	
	    option1 = new JRadioButton();
	    option2 = new JRadioButton();
	    option3 = new JRadioButton();
	    option4 = new JRadioButton();
	
	    option1.setFont(new Font("Arial", Font.PLAIN, 17));
	    option2.setFont(new Font("Arial", Font.PLAIN, 17));
	    option3.setFont(new Font("Arial", Font.PLAIN, 17));
	    option4.setFont(new Font("Arial", Font.PLAIN, 17));
	
	    buttonGroup = new ButtonGroup();
	
	    buttonGroup.add(option1);
	    buttonGroup.add(option2);
	    buttonGroup.add(option3);
	    buttonGroup.add(option4);
	
	    JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
	
	    optionsPanel.add(option1);
	    optionsPanel.add(option2);
	    optionsPanel.add(option3);
	    optionsPanel.add(option4);
	
	    submitButton = new JButton("Submit Answer");
	    audiencePollButton = new JButton("Audience Poll");
	    fiftyFiftyButton = new JButton("50-50");
	
	    JPanel buttonPanel = new JPanel();
	
	    buttonPanel.add(audiencePollButton);
	    buttonPanel.add(fiftyFiftyButton);
	    buttonPanel.add(submitButton);
	
	    frame.add(questionPanel, BorderLayout.NORTH);
	    frame.add(optionsPanel, BorderLayout.CENTER);
	    frame.add(buttonPanel, BorderLayout.SOUTH);
	
	    submitButton.addActionListener(e -> checkAnswer());
	
	    audiencePollButton.addActionListener(e -> showAudiencePoll());
	
	    fiftyFiftyButton.addActionListener(e -> useFiftyFifty());
	
	    displayQuestion();
	
	    frame.setVisible(true);
	}
	
	private void displayQuestion() {
	
	    Question q = quiz.getCurrentQuestion();
	
	    if (q == null) {
	        return;
	    }
	
	    questionNumberLabel.setText(
	            "Question " + (quiz.getCurrentQuestionIndex() + 1)
	            + " / " + quiz.getTotalQuestions());
	
	    questionLabel.setText(q.getQuestionText());
	
	    option1.setText("1. " + q.getOption1());
	    option2.setText("2. " + q.getOption2());
	    option3.setText("3. " + q.getOption3());
	    option4.setText("4. " + q.getOption4());
	
	    option1.setVisible(true);
	    option2.setVisible(true);
	    option3.setVisible(true);
	    option4.setVisible(true);
	
	    buttonGroup.clearSelection();
	
	    audiencePollButton.setEnabled(
	            !quiz.isAudiencePollUsed()
	            && quiz.getCurrentQuestionIndex() < 7
	    );

	    fiftyFiftyButton.setEnabled(
	            !quiz.isFiftyFiftyUsed()
	            && quiz.getCurrentQuestionIndex() < 7
	    );
	}
	
	private void checkAnswer() {
	
	    int selectedOption = 0;
	
	    if (option1.isSelected()) {
	        selectedOption = 1;
	    } else if (option2.isSelected()) {
	        selectedOption = 2;
	    } else if (option3.isSelected()) {
	        selectedOption = 3;
	    } else if (option4.isSelected()) {
	        selectedOption = 4;
	    }
	
	    if (selectedOption == 0) {
	        JOptionPane.showMessageDialog(frame, "Please select an answer!");
	        return;
	    }
	
	    boolean result = quiz.checkAnswer(selectedOption);
	
	    if (result) {
	
	        JOptionPane.showMessageDialog(frame, "Correct Answer!");
	
	        quiz.nextQuestion();
	
	        if (quiz.isQuizCompleted()) {

	            JOptionPane.showMessageDialog(frame, "Congratulations! Quiz Completed!");

	            showResult();

	        } else {

	            displayQuestion();
	        }
	
	    } 
	    else {

	        JOptionPane.showMessageDialog(frame, "Wrong Answer! Quiz Over!");

	        showResult();
	    }
	}
	
	private void showAudiencePoll() {
	
	    if (quiz.useAudiencePoll()) {
	
	        int[] poll = quiz.generateAudiencePoll();
	
	        String message = "Audience Poll Results:\n\n";
	
	        for (int i = 0; i < poll.length; i++) {
	            message += (i + 1) + ". " + poll[i] + "%\n";
	        }
	
	        JOptionPane.showMessageDialog(frame, message);
	
	        audiencePollButton.setEnabled(false);
	
	    } else {
	
	        JOptionPane.showMessageDialog(frame, "Audience Poll already used!");
	    }
	}
	
	private void useFiftyFifty() {
	
	    if (quiz.useFiftyFifty()) {
	
	        int[] options = quiz.useFiftyFiftyOptions();
	
	        for (int i = 1; i <= 4; i++) {
	
	            if (i != options[0] && i != options[1]) {
	
	                if (i == 1) {
	                    option1.setVisible(false);
	                } else if (i == 2) {
	                    option2.setVisible(false);
	                } else if (i == 3) {
	                    option3.setVisible(false);
	                } else if (i == 4) {
	                    option4.setVisible(false);
	                }
	            }
	        }
	
	        fiftyFiftyButton.setEnabled(false);
	
	    } else {
	
	        JOptionPane.showMessageDialog(frame, "50-50 already used!");
	    }
	}
	
	private void showResult() {

	    QuizResults result = quiz.getResult(playerName);

	    model.saveResult(result);

	    frame.dispose();

	    new ResultPage(result);
	}
	
	public static void main(String[] args) {
	    new WelcomePage();
	}

}
