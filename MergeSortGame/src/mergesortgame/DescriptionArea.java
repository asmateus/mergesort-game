/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author asmateus
 */
public class DescriptionArea extends JScrollPane {
    
    
    public DescriptionArea(JEditorPane source) {
        super(source);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600,300));
    }
    
    @Override
    public void setBorder(Border b) {
        super.setBorder(new EmptyBorder(20,20,20,20));
    }
    
}
