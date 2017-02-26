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
public class User {
    
    // User data from database, or excel file
    private String user_name = "";
    private int status = 0;
    private int score = 0;
    private int level = 1;
    
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_ANS_QUST = 2;
    
    public static final String DEFAULT_USER = "misaka";
    
    public User(String user_name) {
        this.user_name = user_name;
    }
    
    public void pullDataFromOrigin() {
    
    }
    
    public void pushDataToOrigin() {
    
    }
    
    // get and set methods here
    
    public String getUserName() {
        return this.user_name;
    }
    
    public int getUserLevel() {
        return this.level;
    }
    
    public int getUserStatus() {
        return this.status;
    }
    
    public int getUserScore() {
        return this.score;
    }
}
