/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileReader;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author asmateus
 */
public class StatusArea extends JPanel {
    
    public final static String HELP_TEXT_FILE_PATH = "data/help.html" ;
    
    private GridBagConstraints c = new GridBagConstraints();
    private JPanel help_panel = new JPanel();
    
    public StatusArea() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.5, 0.5, 0};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.createHelpPanel();
    }
    
    private void createHelpPanel() {
        JEditorPane help_text = new JEditorPane();
        help_text.setBackground(Color.BLACK);
        help_text.setFocusable(false);
        help_text.setEditable(false);
        help_text.setEditorKit(new HTMLEditorKit());
        
        try {
            FileReader reader = new FileReader(HELP_TEXT_FILE_PATH);
            help_text.read(reader, HELP_TEXT_FILE_PATH);
        }
        catch(Exception e) {}
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        
        this.add(help_text, c);
    }
}
