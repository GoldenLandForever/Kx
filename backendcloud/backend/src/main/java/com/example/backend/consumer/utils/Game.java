package com.example.backend.consumer.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.consumer.WebSocketServer;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Game extends Thread {
    final private Player playerA, playerB;

    private Integer[] aMap, bMap;

    private Integer a_point = 0;
    private Integer b_point = 0;

    private String  turn  = "A";

    private Integer stepA = null;

    private Integer stepB = null;

    private ReentrantLock lock = new ReentrantLock();


    public Player getPlayerA() {
        return this.playerA;
    }

    public Player getPlayerB() {
        return this.playerB;
    }

    public void setNextStepA(Integer nextStep) {
        stepA = nextStep;
    }

    public void setNextStepB(Integer nextStep) {
        stepB = nextStep;
    }

//    public Integer getStep() {
//        return step;
//    }
    public String getTurn(){
        return turn;
    }

    public Integer[] getAMap() {
        return aMap;
    }

    public Integer[] getBMap() {
        return bMap;
    }
    public Integer getA_point(){return  a_point;}
    public Integer getB_point(){return  b_point;}

    private String status = "playing";

    private String loser = null;

    public Game(Integer id_a, Integer id_b) {
        aMap = new Integer[9];
        bMap = new Integer[9];
        for (int i = 0; i < 9; i++) aMap[i] = bMap[i] = 0;

        turn = "A";

        playerA = new Player(id_a, 0);
        playerB = new Player(id_b, 0);
    }
    //投骰子
    public void rollA() {
        Random random = new Random();
        if (a_point == 0) a_point = random.nextInt(6) + 1;

        JSONObject resp = new JSONObject();
        resp.put("event", "rollA");
        resp.put("a_point", a_point);

        sendAllMsg(resp.toJSONString());
    }
    public void rollB() {
        Random random = new Random();
        if (b_point == 0) b_point = random.nextInt(6) + 1;

        JSONObject resp = new JSONObject();
        resp.put("event", "rollB");
        resp.put("b_point", b_point);

        sendAllMsg(resp.toJSONString());
    }

    //检查下一步操作并切换当前操作用户
    private boolean nextStep() {
        //最快两毫秒操作一次
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 200; i++) {
            try {
                Thread.sleep(100);

                if (Objects.equals(turn, "A") && stepA != null) return true;
                if (Objects.equals(turn, "B") && stepB != null) return true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    //检查消除
    private void setMap() {
        try{
            lock.lock();
            Integer numA = a_point;
            Integer numB = b_point;
            if ("A".equals(turn)) {
                Integer pos = stepA;
                aMap[pos] = numA;
                for (int i = pos / 3 * 3; i < pos / 3 * 3 + 3; i++) if (bMap[i].equals(numA)) bMap[i] = 0;
            } else {
                Integer pos = stepB;
                bMap[pos] = numB;
                for (int i = pos / 3 * 3; i < pos / 3 * 3 + 3; i++) if (aMap[i].equals(numB)) aMap[i] = 0;
            }
        }finally {
            lock.unlock();
        }
    }

    //计算分数
    private int countMap(Integer[] map) {
        int score = 0;

        for (int i = 0; i < 3; i++) {
            int a = map[i * 3];
            int b = map[i * 3 + 1];
            int c = map[i * 3 + 2];

            if (a == b && b == c) score += a * 9;
            else if (a == b) score += a * 4 + c;
            else if (a == c) score += a * 4 + b;
            else if (b == c) score += b * 4 + a;
            else score += (a + b + c);
        }

        return score;
    }



    //判断是否结束
    private void judge_full() {
        int cnt = 0;

        if (Objects.equals(turn, "A")) {
            for (int i = 0; i < 9; i++) if (aMap[i] != 0) cnt++;
        } else {
            for (int i = 0; i < 9; i++) if (bMap[i] != 0) cnt++;
        }

        if (cnt == 9) {
            judge_loser();
        }
    }
    //判断胜负
    private void judge_loser() {
        int a_score = countMap(aMap);
        int b_score = countMap(bMap);

        if (a_score > b_score) loser = "B";
        else if (a_score < b_score) loser = "A";
        else loser = "all";

        getPlayerA().setScore(a_score);
        getPlayerB().setScore(b_score);

        status = "finished";
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            //没有下一步操作或者下一步操作不合法都会导致游戏结束
            if (nextStep()) {
                //执行下一步操作
                setMap();
                judge_full();
                sendCurMap();

                if (!status.equals("playing")) {
                    sendResult();
                    break;
                }
            } else {
                if (Objects.equals(turn, "A")) loser = "A";
                else loser = "B";

                sendResult();
                break;
            }
        }
    }

    //向两个用户广播
    private void sendAllMsg(String msg) {
        if (WebSocketServer.users.get(playerA.getId()) != null)
            WebSocketServer.users.get(playerA.getId()).sendMsg(msg);
        if (WebSocketServer.users.get(playerB.getId()) != null)
            WebSocketServer.users.get(playerB.getId()).sendMsg(msg);
    }

    //广播结果
    private void sendResult() {
        JSONObject resp = new JSONObject();
        resp.put("event", "result");
        resp.put("loser", loser);
        resp.put("a_score", playerA.getScore());
        resp.put("b_score", playerB.getScore());

        sendAllMsg(resp.toJSONString());
    }

    //广播移动
    private void sendCurMap() {
        stepA = stepB = null;
        a_point = 0;
        b_point = 0;
        if(Objects.equals(turn, "A")) turn ="B";
        else turn = "A";
        JSONObject resp = new JSONObject();
        resp.put("event", "curMap");
        resp.put("aMap", aMap);
        resp.put("bMap", bMap);
        resp.put("turn", turn);
        resp.put("a_point", a_point);
        resp.put("b_point", b_point);
        sendAllMsg(resp.toJSONString());
    }
}
