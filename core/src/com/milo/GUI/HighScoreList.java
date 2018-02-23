package com.milo.GUI;

import com.milo.snake.SQLHandler;
import com.milo.snake.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HighScoreList {
    private JPanel highScorePanel;
    private JTable scoreTable;
    private JButton closeButton;
    private static List<Score> scoreList;
    private static JFrame frame = new JFrame("High score");

    public HighScoreList() {
        scoreList = SQLHandler.getScoreList();
        String[] columnNames = {"#", "Nickname", "Score"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        //swing tabeller använder sig av objekt arrayer för varje rad
        for (Score score: scoreList){
            Object[] o = new Object[3];
            o[0] = scoreList.indexOf(score) + 1;
            o[1] = score.getNickname();
            o[2] = score.getScore();
            //lägg till en rad som objektet
            model.addRow(o);
        }
        scoreTable.setModel(model);
        //stänga highscorelistan
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void open() {
        scoreList = SQLHandler.getScoreList();
        frame.setContentPane(new HighScoreList().highScorePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
