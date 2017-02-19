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
    private int status;
    private int level;
    
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_ANS_QUST = 2;
    
    public User(String user_name) {
        this.user_name = user_name;
    }
    
    // get and set methods here
}
