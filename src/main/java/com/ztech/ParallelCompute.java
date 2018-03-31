package com.ztech;

import com.ztech.model.Ball;
import com.ztech.model.Frame;
import com.ztech.model.Player;
import com.ztech.model.Score;
import com.ztech.util.Constants;
import com.ztech.util.DummyObjects;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ParallelCompute extends RecursiveTask<Integer> {
    static private List<Player> playersList = new ArrayList<>();

    private static Object sharedLock = new Object();
    private static AtomicInteger idx = new AtomicInteger(0);
    private int start;
    private int end;
    private Player p;
    private List<Integer> scoreList;

    public ParallelCompute(Player p, List<Integer> scoreList, int start, int end) {
        this.start = start;
        this.end = end;
        this.p = p;
        this.scoreList = scoreList;

    }

    public static List<Player> getPlayersList() {
        return playersList;
    }

    public static void main(String[] args) {
        process();
        System.out.println("Player List");
        playersList.forEach(s -> s.getScoreObjectList().forEach(s1 -> System.out.print(s1.getScore())));
    }

    public static void process() {


        for (String k : DummyObjects.bowlingFile.keySet())

        {
            List<Integer> scoreListTemp = DummyObjects.bowlingFile.get(k);

            Supplier<List<Integer>> supplier = () -> new LinkedList<>();
            List<Integer> scoreList = scoreListTemp.stream().filter(s -> (s >= -1) && (s <= 10)).collect(Collectors.toCollection(supplier));

            Ball ball = null;


            Player p = new Player();
            p.setName(k);
            p.setCurrentFrameNum(new AtomicInteger(1));
            playersList.add(p);
            System.out.printf("Player %s :", p.getName());

            idx.set(0);

            ForkJoinTask<Integer> task = new ParallelCompute(p, scoreList, 0, scoreList.size());
            ForkJoinPool pool = new ForkJoinPool();
            Instant instBefore = Instant.now();
            Integer totScore = pool.invoke(task);
            Instant instAfter = Instant.now();
            System.out.printf("Total Score %3d", totScore);
            System.out.printf("Total Time Taken %3d", instAfter.toEpochMilli() - instBefore.toEpochMilli());
            p.setTotScore(totScore);
        }

    }

    protected Integer compute() {
        return populateScore();
    }

    private Integer populateScore() {

        if ((end - start) <= Constants.MIN_TASK_NUM) {
            Frame currFrame = new Frame();
            Score score = null;
            int sum = 0;
            List<Score> scoreObjectList = new LinkedList<>();
            for (int j = start; j < end; j++) {

                int v = scoreList.get(j);

                score = new Score();

                if (v == -1) {
                    v = 0;

                    score.setScore("F");
                } else if (v == 10) {
                    score.setScore("X or /");

                } else {
                    score.setScore(String.valueOf(v));
                }
                scoreObjectList.add(score);
                sum += v;

            }
            //@TODO Fix Score Object List.
            //p.getScoreObjectList().addAll(start, scoreObjectList);

            return sum;
        } else {
            int middle = start + ((end - start) / 2);
            RecursiveTask<Integer> recTask = new ParallelCompute(p, scoreList, start, middle);
            recTask.fork();
            return new ParallelCompute(p, scoreList, middle, end).compute() + recTask.join();
        }
    }
}

