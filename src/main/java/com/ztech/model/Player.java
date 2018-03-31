package com.ztech.model;

import com.ztech.util.DummyObjects;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private String name;
    private List<Frame> playerFrameList = Collections.synchronizedList(new LinkedList<Frame>());
    private List<Score> scoreObjectList = Collections.synchronizedList(new LinkedList<Score>());
    private AtomicInteger currentFrameNum = new AtomicInteger(0);
    private int totScore;

    public Player() {

        DummyObjects.populateList(playerFrameList, scoreObjectList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Frame> getPlayerFrameList() {
        return playerFrameList;
    }

    public List<Score> getScoreObjectList() {
        return scoreObjectList;
    }

    public void setScoreObjectList(List<Score> scoreObjectList) {
        this.scoreObjectList = scoreObjectList;
    }

    public AtomicInteger getCurrentFrameNum() {
        return currentFrameNum;
    }

    public void setCurrentFrameNum(AtomicInteger currentFrameNum) {
        this.currentFrameNum = currentFrameNum;
    }

    public int getTotScore() {
        return totScore;
    }

    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }

    public void evalScore(int v, int currFrameNum) {


        List<Frame> playerFrameAray = this.getPlayerFrameList();
        Frame currFrame = playerFrameAray.get(currFrameNum - 1);
        currFrame.setFrameScore(currFrame.calculateCurrScore());
        Frame currFrameMinusOne = null;
        Frame currFrameMinusTwo = null;
        if (currFrameNum == 2) {
            currFrameMinusOne = playerFrameAray.get(currFrameNum - 2);
            if (currFrameMinusOne.isStrike() || currFrameMinusOne.isSpare()) {
                currFrameMinusOne.setFrameScore(currFrameMinusOne.getFrameScore() + currFrame.getFrameScore());
            }
        } else if (currFrameNum >= 3) {
            currFrameMinusOne = playerFrameAray.get(currFrameNum - 2);
            currFrameMinusTwo = playerFrameAray.get(currFrameNum - 3);
            if (currFrameMinusTwo.isStrike()) {
                currFrameMinusTwo.setFrameScore(currFrameMinusTwo.getFrameScore() + currFrame.getFrameScore());
            }

            if (currFrameMinusOne.isSpare() || currFrameMinusOne.isStrike()) {
                currFrameMinusOne.setFrameScore(currFrameMinusOne.getFrameScore() + currFrame.getFrameScore());
            }
        }
    }
}
