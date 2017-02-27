/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.*;

/**
 *
 * @author asmateus
 */
public class IOManager {
    
    private String user;
    private JSONObject db;
    
    public boolean error_flag = false;
    
    public IOManager(String user) {
        this.user = user;
        this.init_db();
    }
    
    private void init_db() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("data/db.json"));
            this.db = new JSONObject(new String(encoded, "utf8"));
            
            this.error_flag = false;
        }
        catch(Exception e) {this.error_flag = true;}
    }
    
    public ArrayList<String> pullDataFromOrigin() {
        ArrayList<String> parsed_data = new ArrayList<>();
        if(!this.error_flag) {
            // We need to return a difficulty level, the score array, the level
            // and the fail count
            
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(this.user);
                parsed_data.add(obj.getString("level"));
                parsed_data.add(obj.getString("current_difficulty"));
                parsed_data.add(obj.getString("fail_count"));
                parsed_data.add(obj.getString("score"));
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true;}
        }
        return parsed_data;
    }
    
    public void pushDataToOrigin(ArrayList<String> data) {
        if(!this.error_flag) {
            // We need to return a difficulty level, the score array, the level
            // and the fail count
            
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(this.user);
                obj.put("level", data.get(User.LEVEL_OFFSET));
                obj.put("current_difficulty", data.get(User.DIFFICULTY_OFFSET));
                obj.put("fail_count", data.get(User.FAIL_COUNT_OFFSET));
                obj.put("score", data.get(User.SCORE_OFFSET));
                
                this.db.put(this.user, obj);
                try (FileWriter file = new FileWriter("data/db.json")) 
                {
                    file.write(this.db.toString());
                }
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true;}
        }
    }
}
