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
    
    public GameArea(UI ui, Watchdog dog) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(ui.screen_size);
        
        configureLevel(dog);
        this.add(level);
    }
    
    private void configureLevel(Watchdog dog) {
        this.level = new Level1(this, dog);
        this.level.setContent();
    }
    
    @Override
    public void selfDestroy(int exit_status) {
    
    }
}
