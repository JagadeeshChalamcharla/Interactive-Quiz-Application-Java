package quizapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Model {

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet res;
    
    public Model() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("The Driver is loaded Successfully");
			System.out.println("------------------------");
			
			con = DriverManager.getConnection(Credentials.url, Credentials.user, Credentials.pwd);
			
			if (con != null) {
			    System.out.println("Connection established Successfully");
			} 
			else {
			    System.out.println("Connection failed");
			}
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void addQuestion(Question q) {
        try {
            String query = "INSERT INTO questions VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, q.getQuestionId());
            pstmt.setString(2, q.getQuestionText());
            pstmt.setString(3, q.getOption1());
            pstmt.setString(4, q.getOption2());
            pstmt.setString(5, q.getOption3());
            pstmt.setString(6, q.getOption4());
            pstmt.setInt(7, q.getCorrectOption());
            pstmt.setInt(8, q.getReward());

            pstmt.executeUpdate();

            System.out.println("Question added successfully!");

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> al = new ArrayList<Question>();
        try {
            String query = "select * from questions";
            stmt = con.createStatement();
            res = stmt.executeQuery(query);
            while (res.next()) {
                Question q = new Question();
                q.setQuestionId(res.getInt("question_id"));
                q.setQuestionText(res.getString("question_text"));
                q.setOption1(res.getString("option1"));
                q.setOption2(res.getString("option2"));
                q.setOption3(res.getString("option3"));
                q.setOption4(res.getString("option4"));
                q.setCorrectOption(res.getInt("correct_option"));
                q.setReward(res.getInt("reward"));

                al.add(q);
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }
    
    public void deleteQuestion(int questionId) {
        try {
            String query = "DELETE FROM questions WHERE question_id = ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, questionId);

            int x = pstmt.executeUpdate();
            if (x > 0) {
                System.out.println("Question deleted successfully!");
            } 
            else {
                System.out.println("Question not found!");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateQuestion(Question q) {
        try {
            String query = "UPDATE questions SET question_text = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_option = ?, reward = ? WHERE question_id = ?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, q.getQuestionText());
            pstmt.setString(2, q.getOption1());
            pstmt.setString(3, q.getOption2());
            pstmt.setString(4, q.getOption3());
            pstmt.setString(5, q.getOption4());
            pstmt.setInt(6, q.getCorrectOption());
            pstmt.setInt(7, q.getReward());
            pstmt.setInt(8, q.getQuestionId());

            int x = pstmt.executeUpdate();
            if (x > 0) {
                System.out.println("Question updated successfully!");
            }
            else {
                System.out.println("Question not found!");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveResult(QuizResults qr) {

        try {

            String query = "INSERT INTO quiz_results(player_name, total_questions, questions_attempted, correct_answers, wrong_answers, earnings, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, qr.getPlayerName());
            pstmt.setInt(2, qr.getTotalQuestions());
            pstmt.setInt(3, qr.getQuestionsAttempted());
            pstmt.setInt(4, qr.getCorrectAnswers());
            pstmt.setInt(5, qr.getWrongAnswers());
            pstmt.setInt(6, qr.getEarnings());
            pstmt.setString(7, qr.getStatus());

            int x = pstmt.executeUpdate();

            if (x > 0) {
                System.out.println("Quiz result saved successfully!");
            } else {
                System.out.println("Quiz result not saved!");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<QuizResults> getAllResults() {

        ArrayList<QuizResults> results = new ArrayList<>();

        try {

            String query = "SELECT * FROM quiz_results";

            stmt = con.createStatement();

            res = stmt.executeQuery(query);

            while (res.next()) {

                QuizResults qr = new QuizResults();

                qr.setResultId(res.getInt("result_id"));
                qr.setPlayerName(res.getString("player_name"));
                qr.setTotalQuestions(res.getInt("total_questions"));
                qr.setQuestionsAttempted(res.getInt("questions_attempted"));
                qr.setCorrectAnswers(res.getInt("correct_answers"));
                qr.setWrongAnswers(res.getInt("wrong_answers"));
                qr.setEarnings(res.getInt("earnings"));
                qr.setStatus(res.getString("status"));

                results.add(qr);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return results;
    }
}