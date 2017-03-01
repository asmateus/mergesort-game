/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.util.Arrays;

/**
 *
 * @author asmateus
 */
public class SimpleUser implements Comparable<SimpleUser> {
    private String user_name = "";
    private Integer diff = 1;
    private Integer fail_count = 0;
    private Integer score = 0;
    private Integer level = 1;
    private Integer[] scores = {0,0,0,0,0};
    private Integer[] tries = {0,0,0,0,0};
    private Integer[] diff_played = {0,0,0,0,0};
    private double[] times = {0,0,0,0,0};
    private String last_online = "";
    
    public static int calculateScore(String s) {
        String[] ss = s.split(",");
        
        int sc = 0;
        for(int i = 0; i < ss.length; ++i) {
            sc += Integer.parseInt(ss[i]);
        }
        
        return sc;
    }
    
    public static String transforDifficulty(int diff) {
        switch(diff) {
            case 1:
                return User.DIFF_MESSAGE_1;
            case 2:
                return User.DIFF_MESSAGE_2;
            case 3:
                return User.DIFF_MESSAGE_3;
            default:
                return User.DIFF_MESSAGE_1;
        }
    }
    
    public static String joinSparseData(Integer[] data) {
        String[] datas = new String[data.length];
        for(int i = 0; i < data.length; ++i) {
            datas[i] = "" + data[i];
        }
        
        return SimpleUser.joinSparseData(datas);
    }
    
    public static String joinSparseData(double[] data) {
        String[] datas = new String[data.length];
        for(int i = 0; i < data.length; ++i) {
            datas[i] = "" + data[i];
        }
        
        return SimpleUser.joinSparseData(datas);
    }
    
    public static String joinSparseData(String[] data) {
        String sc = "";
        for(String e: data) {
            sc += e + ",";
        }
        
        sc = sc.substring(0, sc.length()-1);
        return sc;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

    public void setFailCount(Integer fail_count) {
        this.fail_count = fail_count;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public void setScores(Integer[] scores) {
        this.scores = scores;
    }
    
    public void setTries(Integer[] tries) {
        this.tries = tries;
    }
    
    public void setTimes(double[] times) {
        this.times = times;
    }
    
    public void setDiffsPlayed(Integer[] diffs) {
        this.diff_played = diffs;
    }
    
    public void setLastOnline(String lo) {
        this.last_online = lo;
    }
    
    @Override
    public int compareTo(SimpleUser i) {
        return this.score.compareTo(i.getScore());
    }

    public String getUserName() {
        return user_name;
    }

    public int getDiff() {
        return diff;
    }

    public int getFailCount() {
        return fail_count;
    }

    public int getScore() {
        return score;
    }
    
    public Integer[] getScores() {
        return scores;
    }
    
    public Integer[] getTries() {
        return tries;
    }
    
    public double[] getTimes() {
        return times;
    }
    
    public Integer[] getDiffPlayed() {
        return diff_played;
    }
    
    public String getLastOnline() {
        return last_online;
    }

    public int getLevel() {
        return level;
    }
}
