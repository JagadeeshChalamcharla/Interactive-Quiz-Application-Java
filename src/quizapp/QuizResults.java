package quizapp;

public class QuizResults {

	private int resultId;
	private String playerName;
	private int totalQuestions;
	private int questionsAttempted;
	private int correctAnswers;
	private int wrongAnswers;
	private int earnings;
	private String status;
	
	
	public QuizResults(int resultId, String playerName, int totalQuestions, int questionsAttempted,
	        int correctAnswers, int wrongAnswers, int earnings, String status) 
	{
	    super();	
	    this.resultId = resultId;
	    this.playerName = playerName;
	    this.totalQuestions = totalQuestions;
	    this.questionsAttempted = questionsAttempted;
	    this.correctAnswers = correctAnswers;
	    this.wrongAnswers = wrongAnswers;
	    this.earnings = earnings;
	    this.status = status;
	}
	
	public void setResultId(int resultId) {
	    this.resultId = resultId;
	}
	
	public void setPlayerName(String playerName) {
	    this.playerName = playerName;
	}
	
	public void setTotalQuestions(int totalQuestions) {
	    this.totalQuestions = totalQuestions;
	}
	
	public void setQuestionsAttempted(int questionsAttempted) {
	    this.questionsAttempted = questionsAttempted;
	}
	
	public void setCorrectAnswers(int correctAnswers) {
	    this.correctAnswers = correctAnswers;
	}
	
	public void setWrongAnswers(int wrongAnswers) {
	    this.wrongAnswers = wrongAnswers;
	}
	
	public void setEarnings(int earnings) {
	    this.earnings = earnings;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public int getResultId() {
	    return resultId;
	}
	
	public String getPlayerName() {
	    return playerName;
	}
	
	public int getTotalQuestions() {
	    return totalQuestions;
	}
	
	public int getQuestionsAttempted() {
	    return questionsAttempted;
	}
	
	public int getCorrectAnswers() {
	    return correctAnswers;
	}
	
	public int getWrongAnswers() {
	    return wrongAnswers;
	}
	
	public int getEarnings() {
	    return earnings;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public QuizResults() {
		
	}
	
	@Override
	public String toString() {
	
	    return "Result ID: " + this.resultId
	            + "\nPlayer Name: " + this.playerName
	            + "\nTotal Questions: " + this.totalQuestions
	            + "\nQuestions Attempted: " + this.questionsAttempted
	            + "\nCorrect Answers: " + this.correctAnswers
	            + "\nWrong Answers: " + this.wrongAnswers
	            + "\nFinal Earnings: ₹" + this.earnings
	            + "\nStatus: " + this.status;
	}
}
