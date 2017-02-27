/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.util.ArrayList;

/**
 *
 * @author asmateus
 */
public class User {
    
    // User data from database, or excel file
    private String user_name = "";
    private int status = 0;
    private int diff = 1;
    private int fail_count = 0;
    private Integer[] score = {0,0,0,0,0};
    private int level = 1;
    
    private final IOManager io;
    
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_ANS_QUST = 2;
    public static final int LEVEL_OFFSET = 0;
    public static final int DIFFICULTY_OFFSET = 1;
    public static final int FAIL_COUNT_OFFSET = 2;
    public static final int SCORE_OFFSET = 3;
    
    public static final int DIFF_UPPER_CAP = 3;
    public static final int DIFF_LOWER_CAP = 1;
    public static final String DIFF_MESSAGE_1 = "Noob";
    public static final String DIFF_MESSAGE_2 = "Advanced";
    public static final String DIFF_MESSAGE_3 = "Legendary";
    
    public static final String DEFAULT_USER = "visitor";
    
    public User(String user_name) {
        this.user_name = user_name;
        this.io = new IOManager(user_name);
    }
    
    public void RequestDataFromOrigin() {
        if(!this.io.error_flag) {
            ArrayList<String> arr = this.io.pullDataFromOrigin();

            this.level = Integer.parseInt(arr.get(User.LEVEL_OFFSET));
            this.diff = Integer.parseInt(arr.get(User.DIFFICULTY_OFFSET));
            this.fail_count = Integer.parseInt(arr.get(User.FAIL_COUNT_OFFSET));

            String[] temp = arr.get(User.SCORE_OFFSET).split(",");
            for(int i = 0; i < temp.length; ++i) {
                this.score[i] = Integer.parseInt(temp[i]);
            }
        }
    }
    
    public void SendDataToOrigin() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(""+this.level);
        arr.add(""+this.diff);
        arr.add(""+this.fail_count);
        arr.add(""+this.score[0]+","+this.score[1]+","+this.score[2]+","+this.score[3]);
        
        this.io.pushDataToOrigin(arr);
    }
    
    // get and set methods here
    
    public void lowerDifficulty() {
        if(this.diff > User.DIFF_LOWER_CAP) {
            --this.diff;
        }
    }
    
    public void upDifficulty() {
        if(this.diff < User.DIFF_UPPER_CAP) {
            ++this.diff;
        }
    }
    
    public String getUserName() {
        return this.user_name;
    }
    
    public int getUserLevel() {
        return this.level;
    }
    
    public int getUserStatus() {
        return this.status;
    }
    
    public int getUserFailCount() {
        return this.fail_count;
    }
    
    public int getUserDifficulty() {
        return this.diff;
    }
    
    public int getUserScore() {
        int sc = 0;
        for(int i = 0; i < this.score.length; ++i) {
            sc += this.score[i];
        }
        return sc;
    }
}
