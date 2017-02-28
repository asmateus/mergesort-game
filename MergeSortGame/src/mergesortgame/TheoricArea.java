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
public class TheoricArea extends JPanel {
    private JEditorPane pseudocode = new JEditorPane();
    private GridBagConstraints c = new GridBagConstraints();
    
    public final static String COVER_TEXT_FILE_PATH = "data/cover.html";
    public final static String PSEUDOCODE_TEXT_FILE_PATH = "data/pseudocode.html";
    
    public TheoricArea() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {0.3, 0.7};
        gbl.rowWeights = new double[] {0, 0.4, 0.6, 0};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.initPseudocodeArea();
        this.unCoverSecrets();
    }
    
    public void lockCode() {
        this.coverSecrets();
    }
    
    public void unLockCode() {
        this.unCoverSecrets();
    }
    
    private void initPseudocodeArea() {
        this.pseudocode.setBackground(Color.BLACK);
        this.pseudocode.setFocusable(false);
        this.pseudocode.setEditable(false);
        this.pseudocode.setEditorKit(new HTMLEditorKit());
    }
    
    private void coverSecrets() {
        try {
            FileReader reader = new FileReader(COVER_TEXT_FILE_PATH);
            this.pseudocode.read(reader, COVER_TEXT_FILE_PATH);
        }
        catch(Exception e) {}
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        
        this.add(this.pseudocode, c);
    }
    
    private void unCoverSecrets() {
        try {
            FileReader reader = new FileReader(PSEUDOCODE_TEXT_FILE_PATH);
            this.pseudocode.read(reader, PSEUDOCODE_TEXT_FILE_PATH);
        }
        catch(Exception e) {}
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        
        this.add(this.pseudocode, c);
    }
}
