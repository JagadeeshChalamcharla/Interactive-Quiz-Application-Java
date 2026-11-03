package quizapp;

public class QuestionData {

    public static void addQuestions(Model m) {

        Question q = new Question();
        
        q.setQuestionId(11);
        q.setQuestionText("Which collection in Java stores elements as key-value pairs?");
        q.setOption1("ArrayList");
        q.setOption2("HashSet");
        q.setOption3("HashMap");
        q.setOption4("LinkedList");
        q.setCorrectOption(3);
        q.setReward(150000);

        m.addQuestion(q);
    }
}