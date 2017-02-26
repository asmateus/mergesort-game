/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/**
 *
 * @author asmateus
 */
public class ActionLogin extends Action {
    
    private CommandBar cmd;
    private JSONObject db;
    
    private String username = "";
    
    public ActionLogin(CommandBar cmd) {
        super(Action.ACTION_LOGIN);
        
        this.mess_list = new String[] {"password", "username"};
        this.cmd = cmd;
        this.load_db();
    }
    
    private void load_db() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("data/db.json"));
            this.db = new JSONObject(new String(encoded, "utf8"));
        }
        catch(Exception e) {System.out.println(e.toString());}
    }
    
    @Override
    public int executeTaskChain() {
        if(this.db != null) {
            if(this.getTaskCount() == 2) {
                boolean isthere = false;
                JSONObject obj;
                try {
                    obj = db.getJSONObject(this.cmd.getText());
                    isthere = true;
                }
                catch(Exception e) {
                    System.out.println(e.toString());
                }

                if(isthere == true) {
                    this.username = this.cmd.getText();
                    definitive_state = this.reduceTaskCount();
                    this.cmd.setMessage("");
                }
                else {
                    this.cmd.setMessage("(USER NOT FOUND) ");
                }
            }
            else if(this.getTaskCount() == 1) {
                JSONObject obj = db.getJSONObject(username);
                String pass = obj.getString("pass");
                boolean isthere = false;
                if(this.cmd.getText().equals(pass)) {
                    isthere = true;
                }
                
                if(isthere == true) {
                    this.cmd.setUserFromName(username);
                    definitive_state = this.reduceTaskCount();
                }
                else {
                    this.cmd.setMessage("(WRONG PASSWORD) ");
                }
            }
        }
        return this.definitive_state;
    }
    
    @Override
    public int onTasksEnd() {
        return Action.IN_OK;
    }
}
