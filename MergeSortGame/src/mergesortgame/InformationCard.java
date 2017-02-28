/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author asmateus
 */
public class InformationCard extends JEditorPane {
    
    private final ArrayList<String> card_sequence;
    private int current_card = -1;
    
    public InformationCard(ArrayList<String> card_sequence) {
        this.setBackground(Color.BLACK);
        this.setFocusable(false);
        this.setEditable(false);
        this.setEditorKit(new HTMLEditorKit());
        
        this.card_sequence = card_sequence;
    }
    
    private void fillCard() {
        try {
            FileReader reader = new FileReader(this.card_sequence.get(this.current_card));
            this.read(reader, this.card_sequence.get(this.current_card));
        }
        catch(Exception e) {}
    }
    
    public void setDummyCard() {
        this.current_card = -1;
        try {
            FileReader reader = new FileReader(TheoricArea.COVER_TEXT_FILE_PATH);
            this.read(reader, TheoricArea.COVER_TEXT_FILE_PATH);
        }
        catch(Exception e) {}
    }
    
    public void next() {
        if(this.current_card < this.card_sequence.size()-1) {
            ++this.current_card;
        }
        else {
            this.current_card = 0;
        }
        
        this.fillCard();
    }
    
    public void previous() {
        if(this.current_card > 0) {
            --this.current_card;
        }
        else {
            this.current_card = this.card_sequence.size()-1;
        }
        
        this.fillCard();
    }
}
