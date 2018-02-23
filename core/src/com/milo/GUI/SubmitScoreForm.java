package com.milo.GUI;

import com.milo.snake.SQLHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitScoreForm {

    private JPanel mainPanel;
    private JLabel scoreLabel;
    private JTextField nicknameInput;
    private JButton submitButton;
    private static int score;

    public SubmitScoreForm() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitScore(nicknameInput.getText(), score);
            }
        });
        // skriva in poängen i labelen
        scoreLabel.setText(scoreLabel.getText() + score);
    }

    public void submitScore(String nick, int score){
        SQLHandler.postScore(nick, score);
    }

    public void open(int score) {
        this.score = score;
        JFrame frame = new JFrame("Submit Score");
        frame.setContentPane(new SubmitScoreForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


