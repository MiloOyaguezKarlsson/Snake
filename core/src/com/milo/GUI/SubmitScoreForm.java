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
    private JButton closeButton;
    private static int score;
    private static JFrame frame = new JFrame("Submit Score");

    public SubmitScoreForm() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitScore(nicknameInput.getText(), score);
                frame.dispose();
            }
        });

        // skriva in poängen i labelen
        scoreLabel.setText(scoreLabel.getText() + score);

        //stänga fönstret
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void submitScore(String nick, int score){
        SQLHandler.postScore(nick, score);
    }

    public void open(int score) {
        this.score = score;
        frame.setContentPane(new SubmitScoreForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


