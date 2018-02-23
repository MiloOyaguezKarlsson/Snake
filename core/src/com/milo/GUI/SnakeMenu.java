package com.milo.GUI;

import com.milo.snake.SnakeGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeMenu{
    private JButton highscoresButton;
    private JButton exitButton;
    private JButton startButton;
    private JPanel panelMain;
    private JButton restartButton;
    private HighScoreList highScoreList = new HighScoreList();
    private static JFrame frame = new JFrame("Snake Menu");

    public SnakeMenu() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SnakeGame.gameRunning = true;
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //för att avsluta hela programmet
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SnakeGame.restart = true;
                SnakeGame.gameRunning = true;
            }
        });
        highscoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoreList.open();
            }
        });
    }

    public void open(){
        frame.setContentPane(new SnakeMenu().panelMain);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
