/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileReader;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

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
    
}
