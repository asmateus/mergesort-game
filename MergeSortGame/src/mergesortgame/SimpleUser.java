/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

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

    public int getLevel() {
        return level;
    }
}
