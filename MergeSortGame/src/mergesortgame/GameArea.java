/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author asmateus
 */
public class GameArea extends JPanel implements Bar{
    
    private Level level;
    private boolean level_up_pending = false;
    private Watchdog dog;
    private UI ui;
    
    public GameArea(UI ui, Watchdog dog) {
        this.ui = ui;
        this.dog = dog;
        
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(ui.screen_size);
        
        this.generateDummyLevel();
    }
    
    public void launchLevel() {
        this.removeAll();
        this.configureLevel(dog);
        this.ui.pack();
    }
    
    private void configureLevel(Watchdog dog) {
        int i = 0;
        if(this.level_up_pending) {
            i = 10;
        }
        switch(dog.master.getUserLevel()+i) {
            case 1:
                this.level = new Level1(this, dog);
                break;
            case 2:
                this.level = new Level2(this, dog);
                break;
            case 3:
                this.level = new Level3(this, dog);
                break;
            case 4:
                this.level = new Level4(this, dog);
                break;
            case 5:
                this.level = new Level5(this, dog);
                break;
            default:
                this.level = new LevelUP(this, dog);
                break;
        }
        this.level.setContent();
        this.add(level);
    }
    
    private void generateDummyLevel() {
        this.level = new LevelDummy(this, dog);
        this.level.setContent();
        this.add(level);
    }
    
    private void ascendUser() {
        generalUpdate();
        if(this.level_up_pending == true) {
            this.level_up_pending = false;
            this.simpleLevelUpdate(1, -1);
            this.generateDummyLevel();
        }
        else {
            this.level_up_pending = true;
            this.configureLevel(dog);
        }
        
    }
    
    private void descendUser() {
        this.level_up_pending = false;
        generalUpdate();
        this.simpleLevelUpdate(0, -1);
        this.generateDummyLevel();
    }
    
    public void simpleLevelUpdate(int direction, int arb) {
        User user = this.level.getDog().master;
        int level = user.getUserLevel();
        // Calculate final level
        switch(direction) {
            // Ascending
            case 1:
                if(level < 6)
                    ++level;
                break;
            // Descending
            case 0:
                if(level > 1)
                    --level;
                break;
            default:
                if(arb > 0 && arb < 6)
                    level = arb;
                break;
        }
        
        IOManager io = new IOManager(user.getUserName());
        
        // Updating JSON
        SimpleUser u = io.lightWeightPullFromOrigin();
        u.setLevel(level);
        io.lightWeightPushToOrigin(u);
        
        // Update online user
        user.RequestDataFromOrigin();
        
        // Update interface
        ui.loadUserData(-1);
    }
    
    private void generalUpdate() {
        User user = this.level.getDog().master;
        IOManager io = new IOManager(user.getUserName());
        
        // Updating JSON
        SimpleUser u = io.lightWeightPullFromOrigin();
        
        // Build and update Score array
        Integer[] t = u.getScores();
        if(this.level_up_pending)
            t[u.getLevel()-1] += this.level.score_in_level;
        else
            t[u.getLevel()-1] = this.level.score_in_level;
        u.setScores(t);
        
        // Update fail_count
        u.setFailCount(u.getFailCount()+this.level.death_in_level);
        
        // Build and update Tries array
        t = u.getTries();
        t[u.getLevel()-1] = this.level.tries_in_level;
        u.setTries(t);
        
        // Update difficulty
        u.setDiff(user.getUserDifficulty());
        
        //Update difficulties played
        if(this.level.difficulty_played != 0) {
            t = u.getDiffPlayed();
            t[u.getLevel()-1] = user.getUserDifficulty();
            u.setDiffsPlayed(t);
        }
        
        // Build and update Times array
        double[] ti = u.getTimes();
        ti[u.getLevel()-1] = this.level.time_spent_in_level;
        u.setTimes(ti);
        
        // Write JSON
        io.lightWeightPushToOrigin(u);
        
        // Update online user
        user.RequestDataFromOrigin();
        
        // Update interface
        ui.loadUserData(-1);
    }
    
    @Override
    public void selfDestroy(int exit_status) {
        this.level.getDog().removeMember();
        this.removeAll();
        
        if(exit_status == Action.IN_OK) {
            ascendUser();
            this.ui.pack();
        }
        else {
            descendUser();
            this.ui.pack();
        }
    }
}
