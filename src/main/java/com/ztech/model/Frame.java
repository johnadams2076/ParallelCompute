package com.ztech.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Frame {


    boolean strike;
    private int frameScore = 0;
    private boolean spare;
    private AtomicInteger frameNum;
    private AtomicInteger currentBallNum;
    private List<Ball> ballList = new CopyOnWriteArrayList<>();

    public int getFrameScore() {

        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public boolean isSpare() {
        return spare;
    }

    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    public AtomicInteger getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(AtomicInteger frameNum) {
        this.frameNum = frameNum;
    }

    public AtomicInteger getCurrentBallNum() {
        return currentBallNum;
    }

    public void setCurrentBallNum(AtomicInteger currentBallNum) {
        this.currentBallNum = currentBallNum;
    }

    public List<Ball> getBallList() {
        return ballList;
    }

    public int calculateCurrScore() {

        int sumScore = 0;
        for (Ball b : this.getBallList()) {
            sumScore += b.getScore();
        }
        return sumScore;

    }
}
