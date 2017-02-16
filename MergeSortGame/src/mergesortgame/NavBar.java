/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asmateus
 */
public class NavBar extends JPanel {
    
    public static final String DEFAULT_NAME = "Misaka";
    
    JLabel user_label = new JLabel(this.DEFAULT_NAME);
    
    public NavBar() {
        super(new FlowLayout(FlowLayout.CENTER));
        
        this.setBackground(Color.BLACK);
        this.setUserLabelInit();
        this.add(user_label);
    }
    
    private void setUserLabelInit() {
       user_label.setForeground(Color.WHITE);
        
    }
    
    public void setUserLabelText(String username) {
        this.user_label.setText(username);
    }
}
