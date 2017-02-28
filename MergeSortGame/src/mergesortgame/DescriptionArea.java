/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 *
 * @author asmateus
 */
public class DescriptionArea extends JScrollPane {
    
    private JEditorPane content = new JEditorPane();
    
    public DescriptionArea(String html_source) {
        this.add(content);
        this.setBackground(Color.BLACK);
    }
    
}
