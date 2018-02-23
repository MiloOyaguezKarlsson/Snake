package com.milo.GUI;

import com.milo.snake.SQLHandler;
import com.milo.snake.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;

public class HighScoreList {
    private JPanel highScorePanel;
    private JTable scoreTable;
    private JTextArea highScoreList;
    private static List<Score> scoreList;

    public HighScoreList() {
        scoreList = SQLHandler.getScoreList();
        String[] columnNames = {"#", "Nickname", "Score"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        //swing tabeller behöver objektarrayer med antal kolumner, 3 i detta fall
        for (Score score: scoreList){
            Object[] o = new Object[3];
            o[0] = scoreList.indexOf(score) + 1;
            o[1] = score.getNickname();
            o[2] = score.getScore();
            //lägg till en rad som objektet
            model.addRow(o);
        }

        scoreTable.setModel(model);
    }

    public void open() {
        scoreList = SQLHandler.getScoreList();
        JFrame frame = new JFrame("High score");
        frame.setContentPane(new HighScoreList().highScorePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
