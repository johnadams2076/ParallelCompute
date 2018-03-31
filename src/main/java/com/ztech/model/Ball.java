package com.ztech.model;

public class Ball {

    private boolean isFoul;
    private int ballNum;
    private int score;

    public boolean isFoul() {
        return isFoul;
    }

    public void setFoul(boolean foul) {
        isFoul = foul;
    }

    public int getBallNum() {
        return ballNum;
    }

    public void setBallNum(int ballNum) {
        this.ballNum = ballNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
