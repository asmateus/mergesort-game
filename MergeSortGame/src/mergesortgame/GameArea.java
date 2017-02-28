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
    private UI ui;
    
    public GameArea(UI ui, Watchdog dog) {
        this.ui = ui;
        
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(ui.screen_size);
        
        configureLevel(dog);
    }
    
    private void configureLevel(Watchdog dog) {
        switch(dog.master.getUserLevel()) {
            case 1:
                this.level = new Level1(this, dog);
                break;
            case 2:
                this.level = new Level1(this, dog);
                break;
            case 3:
                this.level = new Level1(this, dog);
                break;
            case 4:
                this.level = new Level1(this, dog);
                break;
            case 5:
                this.level = new Level1(this, dog);
                break;
            default:
                this.level = new Level1(this, dog);
                break;
        }
        this.level.setContent();
        this.add(level);
        //this.ui.pack();
    }
    
    private void ascendUser() {
        generalUpdate();
    }
    
    private void descendUser() {
        generalUpdate();
    }
    
    private void generalUpdate() {
        User user = this.level.getDog().master;
        IOManager io = new IOManager(user.getUserName());
    }
    
    @Override
    public void selfDestroy(int exit_status) {
        if(exit_status == Action.IN_OK) {
            ascendUser();
        }
        else {
            descendUser();
        }
        this.level.getDog().removeMember();
        this.level.setVisible(false);
        this.level.setEnabled(false);
        this.removeAll();
    }
}
