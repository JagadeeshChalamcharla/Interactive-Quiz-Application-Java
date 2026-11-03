package quizapp.gui;

import quizapp.Model;
import quizapp.QuizResults;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewResults {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewResults() {

        frame = new JFrame("Player Results");

        frame.setSize(900, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        String[] columns = {
            "Result ID",
            "Player Name",
            "Total Questions",
            "Attempted",
            "Correct",
            "Wrong",
            "Earnings",
            "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        Model model = new Model();

        ArrayList<QuizResults> results = model.getAllResults();

        for (QuizResults qr : results) {

            Object[] row = {
                qr.getResultId(),
                qr.getPlayerName(),
                qr.getTotalQuestions(),
                qr.getQuestionsAttempted(),
                qr.getCorrectAnswers(),
                qr.getWrongAnswers(),
                qr.getEarnings(),
                qr.getStatus()
            };

            tableModel.addRow(row);
        }

        frame.add(new JScrollPane(table));

        frame.setVisible(true);
    }
}