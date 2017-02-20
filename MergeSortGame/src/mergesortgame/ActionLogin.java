/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/**
 *
 * @author asmateus
 */
public class ActionLogin extends Action {
    
    CommandBar cmd;
    JSONObject db;
    
    public ActionLogin(CommandBar cmd) {
        super(Action.ACTION_LOGIN);
        
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
            JSONArray arr = db.getJSONArray("users");
            int i = 0; boolean isthere = false;
            while(i < arr.length() && isthere == false) {
                if(arr.getJSONObject(i).has(this.cmd.getText())) {
                    isthere = true;
                }
                ++i;
            }
            
            if(isthere == true) {
                System.out.println("User found");
            }
            else {
                System.out.println("User not found");
            }
        }
        return Action.IN_OPERATION;
    }
    
    @Override
    public int onTasksEnd() {
        return Action.IN_OK;
    }
}
