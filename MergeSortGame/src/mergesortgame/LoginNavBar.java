/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author asmateus
 */
public class LoginNavBar extends JPanel implements MouseListener {
    
    public LoginNavBar() {
        super(new FlowLayout());
        
        this.setBackground(new Color(211, 1, 1));
        
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        String entity = ((JLabel) me.getSource()).getName();
        System.out.println(entity);
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mousePressed(MouseEvent me) {}
}
