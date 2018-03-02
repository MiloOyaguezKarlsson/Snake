package com.milo.snake;


//klass för en score med nickname och poäng
public class Score {
    private String nickname;
    private int score;

    public Score(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }
}
