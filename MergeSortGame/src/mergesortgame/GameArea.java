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
public class GameArea extends JPanel {
    public GameArea(UI ui) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {0.3, 0.7};
        gbl.rowWeights = new double[] {0, 0.4, 0.6, 0};
        
        this.setLayout(gbl);
        this.setBackground(Color.blue);
        this.setPreferredSize(ui.screen_size);
    }
}
