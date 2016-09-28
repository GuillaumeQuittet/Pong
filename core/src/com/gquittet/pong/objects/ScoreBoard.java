package com.gquittet.pong.objects;

/**
 * Created by Guillaume QUITTET on 27-09-16.
 */
public class ScoreBoard {

    private int batLeftScore;
    private int batRightScore;

    public ScoreBoard(int batLeftScore, int batRightScore) {
        this.batLeftScore = batLeftScore;
        this.batRightScore = batRightScore;
    }

    public ScoreBoard() {
        batLeftScore = 0;
        batRightScore = 0;
    }

    public void increaseBatLeftScore() {
        batLeftScore += 1;
    }

    public void increaseBatRightScore() {
        batRightScore += 1;
    }

    public int getBatLeftScore() {
        return batLeftScore;
    }

    public void setBatLeftScore(int batLeftScore) {
        this.batLeftScore = batLeftScore;
    }

    public int getBatRightScore() {
        return batRightScore;
    }

    public void setBatRightScore(int batRightScore) {
        this.batRightScore = batRightScore;
    }
}
