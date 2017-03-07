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
import java.util.Collections;
import java.util.Iterator;
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
        catch(Exception e) {this.error_flag = true;System.out.println("Error init");}
    }
    
    private String pullDependent(JSONObject obj, String key_root, int dependency) {
        String give_back = obj.getString(key_root+dependency);
        return give_back;
    }
    
    private void pushDependent(JSONObject obj, String key_root, int dependency, String value) {
        obj.put(key_root+dependency, value);
    }
    
    public SimpleUser lightWeightPullFromOrigin() {
        SimpleUser u = new SimpleUser();
        u.setUserName(this.user);
        
        if(!this.error_flag) {
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(this.user);
                u.setLevel(Integer.parseInt(obj.getString("level")));
                u.setDiff(Integer.parseInt(obj.getString("current_difficulty")));
                u.setFailCount(Integer.parseInt(obj.getString("fail_count")));
                
                u.setScore(SimpleUser.calculateScore(this.pullDependent(obj, "score", u.getDiff())));
                String[] sc = this.pullDependent(obj, "score", u.getDiff()).split(",");
                Integer[] scs = new Integer[5];
                
                for(int i = 0; i < sc.length; ++i) {
                    scs[i] = Integer.parseInt(sc[i]);
                }
                u.setScores(scs);
                
                String[] tries = this.pullDependent(obj, "tries", u.getDiff()).split(",");
                Integer[] triess = new Integer[5];
                
                for(int i = 0; i < sc.length; ++i) {
                    triess[i] = Integer.parseInt(tries[i]);
                }
                u.setTries(triess);
                
                String[] diff = obj.getString("diff_played").split(",");
                Integer[] diffs = new Integer[5];
                
                for(int i = 0; i < sc.length; ++i) {
                    diffs[i] = Integer.parseInt(diff[i]);
                }
                u.setDiffsPlayed(diffs);
                
                String[] times = this.pullDependent(obj, "times", u.getDiff()).split(",");
                double[] timess = new double[5];
                
                for(int i = 0; i < sc.length; ++i) {
                    timess[i] = Double.parseDouble(times[i]);
                }
                u.setTimes(timess);
                
                u.setLastOnline(obj.getString("last"));
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true;System.out.println("Error light pull");}
        }
        
        return u;
    }
    
    public void lightWeightPushToOrigin(SimpleUser u) {
        if(!this.error_flag) {
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(u.getUserName());
                obj.put("level", "" + u.getLevel());
                obj.put("current_difficulty", "" + u.getDiff());
                obj.put("fail_count", "" + u.getFailCount());
                obj.put("last", u.getLastOnline());
                this.pushDependent(obj, "score", u.getDiff(), SimpleUser.joinSparseData(u.getScores()));
                this.pushDependent(obj, "tries", u.getDiff(), SimpleUser.joinSparseData(u.getTries()));
                this.pushDependent(obj, "times", u.getDiff(), SimpleUser.joinSparseData(u.getTimes()));
                obj.put("diff_played", SimpleUser.joinSparseData(u.getDiffPlayed()));
                
                this.db.put(this.user, obj);
                try (FileWriter file = new FileWriter("data/db.json")) 
                {
                    file.write(this.db.toString());
                }
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true; System.out.println("Error light push");}
        }
    }
    
    public ArrayList<String> pullDataFromOrigin() {
        this.init_db();
        
        ArrayList<String> parsed_data = new ArrayList<>();
        if(!this.error_flag) {
            // We need to return a difficulty level, the score array, the level
            // and the fail count
            
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(this.user);
                int dif = Integer.parseInt(obj.getString("current_difficulty"));
                parsed_data.add(obj.getString("level"));
                parsed_data.add(obj.getString("current_difficulty"));
                parsed_data.add(obj.getString("fail_count"));
                parsed_data.add(this.pullDependent(obj, "score", dif));
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true; System.out.println("Error pull");}
        }
        return parsed_data;
    }
    
    public void pushDataToOrigin(ArrayList<String> data) {
        this.init_db();
        
        if(!this.error_flag) {
            // We need to return a difficulty level, the score array, the level
            // and the fail count
            
            JSONObject obj;
            try{
                obj = this.db.getJSONObject(this.user);
                obj.put("level", data.get(User.LEVEL_OFFSET));
                obj.put("current_difficulty", data.get(User.DIFFICULTY_OFFSET));
                obj.put("fail_count", data.get(User.FAIL_COUNT_OFFSET));
                this.pushDependent(obj, 
                        "socre", Integer.parseInt(data.get(User.DIFFICULTY_OFFSET)), 
                        data.get(User.SCORE_OFFSET));
                
                this.db.put(this.user, obj);
                try (FileWriter file = new FileWriter("data/db.json")) 
                {
                    file.write(this.db.toString());
                }
                
                this.error_flag = false;
            }
            catch(Exception e){this.error_flag = true; System.out.println("Error push");}
        }
    }
    
    public void createUserFields(String pass) {
        this.pullDataFromOrigin();
        
        if(this.error_flag) {
            this.error_flag = false;
            try {
                JSONObject obj = new JSONObject();
                obj.put("pass", pass);
                obj.put("level", "1");
                obj.put("current_difficulty", "1");
                obj.put("fail_count", "0");
                obj.put("score1", "0,0,0,0,0");
                obj.put("score2", "0,0,0,0,0");
                obj.put("score3", "0,0,0,0,0");
                obj.put("tries1", "0,0,0,0,0");
                obj.put("tries2", "0,0,0,0,0");
                obj.put("tries3", "0,0,0,0,0");
                obj.put("times1", "0,0,0,0,0");
                obj.put("times2", "0,0,0,0,0");
                obj.put("times3", "0,0,0,0,0");
                obj.put("diff_played", "1,1,1,1,1");
                obj.put("last", "");
                
                this.db.put(this.user, obj);
                try (FileWriter file = new FileWriter("data/db.json")) 
                {
                    file.write(this.db.toString());
                }

                this.error_flag = false;
            }
            catch(Exception e) {
                System.out.println("Error create fields");
                this.error_flag = true;
            }
        }
    }
    
    public String fetchStringFromCode(String code) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("data/id_to_description.json"));
            JSONObject code_db = new JSONObject(new String(encoded, "utf8"));
            
            return code_db.getString(code);
        }
        catch(Exception e) {return "";} 
    }
    
    public ArrayList<SimpleUser> getTopPlayers(int amount, int diff_base) {
        ArrayList<SimpleUser> scores = new ArrayList<>();
        ArrayList<SimpleUser> returnarray = new ArrayList<>();
        String next;
        SimpleUser u;
        JSONObject obj;
        
        Iterator keys = db.keys();
        while(keys.hasNext()) {
            next = keys.next().toString();
            obj = db.getJSONObject(next);
            
            u = new SimpleUser();
            u.setUserName(next);
            u.setDiff(Integer.parseInt(obj.getString("current_difficulty")));
            u.setFailCount(Integer.parseInt(obj.getString("fail_count")));
            u.setLevel(Integer.parseInt(obj.getString("level")));
            u.setScore(SimpleUser.calculateScore(this.pullDependent(obj, "score", diff_base)));
            
            scores.add(u);
        }
        
        Collections.sort(scores);
        
        int i = 0;
        while(i < scores.size() && amount > 0) {
            returnarray.add(scores.get(scores.size()-1-i));
            ++i; --amount;
        }
        
        return returnarray;
    }
    
    public void passToExcel() {
        SimpleUser u = this.lightWeightPullFromOrigin();
        u.getUserName();
    }
}
