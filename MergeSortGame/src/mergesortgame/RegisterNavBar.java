/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author asmateus
 */
public class RegisterNavBar extends JPanel implements Bar {
    private UI ui;
    
    public RegisterNavBar(Watchdog dog, UI ui) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.ui = ui;
        
        // Not focusable
        this.setFocusable(false);
        
        this.setBackground(new Color(255, 0, 0));
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        
        CommandBar cmd = new CommandBar(100, Action.ACTION_REGISTER, dog, this);
        this.add(cmd);
    }
    
    @Override
    public void selfDestroy(int exit_status) {
        this.ui.enableFlexBar(Action.ACTION_REGISTER);
        this.setEnabled(false);
        this.setVisible(false);
        this.removeAll();
    }
}
