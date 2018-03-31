package com.ztech.util;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.ztech.model.Frame;
import com.ztech.model.Score;

import java.util.List;

public class DummyObjects {

    ;

    public final static ListMultimap<String, Integer> bowlingFile = LinkedListMultimap.create();

    static {
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);
        bowlingFile.put("John", 10);


        bowlingFile.put("Jeff", 9);
        bowlingFile.put("Jeff", 8);
        bowlingFile.put("Jeff", 6);
        bowlingFile.put("Jeff", 5);
        bowlingFile.put("Jeff", 3);
        bowlingFile.put("Jeff", 10);
        bowlingFile.put("Jeff", 4);
        bowlingFile.put("Jeff", 6);
        bowlingFile.put("Jeff", 0);
        bowlingFile.put("Jeff", 2);


    }

    public static void populateList(List<Frame> playerFrameList, List<Score> scoreFrameList) {
        for (int i = 0; i < 10; i++) {
            playerFrameList.add(new Frame());
            scoreFrameList.add(new Score());

        }

    }

}
