/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author asmateus
 */
public class TheoricArea extends JPanel {
    private final InformationCard card;
    private boolean status = false;
    private GridBagConstraints c = new GridBagConstraints();
    
    public final static String COVER_TEXT_FILE_PATH = "data/cover.html";
    public final static String[] THEORIC_CARDS = 
                                {
                                    "data/pseudocode.html",
                                    "data/tn.html",
                                    "data/tn2.html"
                                };
    
    public TheoricArea(UI ui) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.card = new InformationCard(new ArrayList(Arrays.asList(THEORIC_CARDS)));
        JScrollPane mother_pane = new JScrollPane(this.card);
        mother_pane.setPreferredSize(new Dimension(20,220));
        mother_pane.setFocusable(false);
        
        // Configure card display behaviour
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(mother_pane, c);
        
        this.coverSecrets();
    }
    
    public void lockCode() {
        if(status) {
            status = false;
            this.coverSecrets();
        }
    }
    
    public void unLockCode() {
        if(!status) {
            status = true;
            this.unCoverSecrets();
        }
    }
    
    public void next() {
        if(status) {
            this.card.next();
        }
    }
    
    public void previous() {
        if(status) {
            this.card.previous();
        }
    }
    
    private void coverSecrets() {
        this.card.setDummyCard();
    }
    
    private void unCoverSecrets() {
        this.card.next();
    }
}
