package quizapp;

import java.util.ArrayList;

public class QuizLogic {
	
	private ArrayList<Question> questions;

	private int currentQuestionIndex;
	private int correctAnswers;
	private int wrongAnswers;
	private int earnings;
	private boolean audiencePollUsed;
	private boolean fiftyFiftyUsed;
	
	public QuizLogic(ArrayList<Question> questions) {

	    this.questions = questions;

	    currentQuestionIndex = 0;
	    correctAnswers = 0;
	    wrongAnswers = 0;
	    earnings = 0;

	    audiencePollUsed = false;
	    fiftyFiftyUsed = false;

	}
	
	public Question getCurrentQuestion() {

	    if (currentQuestionIndex < questions.size()) {
	        return questions.get(currentQuestionIndex);
	    }

	    return null;
	}
	
	public boolean checkAnswer(int selectedOption) {

	    Question q = getCurrentQuestion();

	    if (q.getCorrectOption() == selectedOption) {

	        correctAnswers++;
	        earnings = q.getReward();

	        return true;

	    } else {

	        wrongAnswers++;

	        if (currentQuestionIndex < 4) {
	            earnings = 0;
	        }

	        return false;
	    }
	}
	
	public void nextQuestion() {

	    currentQuestionIndex++;

	}
	
	public boolean isQuizCompleted() {

	    return currentQuestionIndex >= questions.size();

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
	
	public boolean useAudiencePoll() {

	    if (!audiencePollUsed && currentQuestionIndex < 7) {

	        audiencePollUsed = true;

	        return true;
	    }

	    return false;
	}
	
	public int[] generateAudiencePoll() {

	    Question q = getCurrentQuestion();

	    int correctOption = q.getCorrectOption();

	    int[] percentages = new int[4];

	    percentages[correctOption - 1] = 70;

	    int remaining = 30;

	    for (int i = 0; i < 4; i++) {

	        if (i != correctOption - 1) {

	            int value = remaining / 3;

	            percentages[i] = value;

	            remaining = remaining - value;
	        }
	    }

	    return percentages;
	}
	
	public int[] useFiftyFiftyOptions() {

	    Question q = getCurrentQuestion();

	    int correctOption = q.getCorrectOption();

	    int[] availableOptions = new int[2];

	    availableOptions[0] = correctOption;

	    int incorrectOption = (correctOption % 4) + 1;

	    availableOptions[1] = incorrectOption;

	    return availableOptions;
	}
	
	public boolean isAudiencePollUsed() {

	    return audiencePollUsed;
	}

	public boolean isFiftyFiftyUsed() {

	    return fiftyFiftyUsed;
	}
	
	public int getCurrentQuestionIndex() {

	    return currentQuestionIndex;
	}
	
	public String getPlayerStatus() {

	    if (isQuizCompleted()) {
	        return "Completed";
	    } else {
	        return "Failed";
	    }
	}
	
	public QuizResults getResult(String playerName) {

	    QuizResults result = new QuizResults();

	    result.setPlayerName(playerName);
	    result.setTotalQuestions(getTotalQuestions());
	    result.setQuestionsAttempted(getQuestionsAttempted());
	    result.setCorrectAnswers(getCorrectAnswers());
	    result.setWrongAnswers(getWrongAnswers());
	    result.setEarnings(getEarnings());
	    result.setStatus(getPlayerStatus());

	    return result;
	}
	
	public boolean useFiftyFifty() {

	    if (!fiftyFiftyUsed && currentQuestionIndex < 7) {

	        fiftyFiftyUsed = true;

	        return true;
	    }

	    return false;
	}
	
	public int getTotalQuestions() {

	    return questions.size();
	}
	
	public int getQuestionsAttempted() {

	    return correctAnswers + wrongAnswers;
	}
}
