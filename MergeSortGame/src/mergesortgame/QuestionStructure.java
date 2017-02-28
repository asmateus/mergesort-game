/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asmateus
 */
public class QuestionStructure extends JPanel {
    
    private QuestionLogic q;
    private ReturnedQuestion qr;
    
    private ArrayList<JLabel> options = new ArrayList<>();
    private JLabel description;
    
    public QuestionStructure(QuestionLogic q) {
        this.q = q;
        qr = this.q.pickOptions(QuestionLogic.BOTH_ANSWERS, 5);
        
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.2,0.2,0.2,0.2,0.1,0.1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        // Init options and description
        initOptions();
        this.description.setText(this.q.getQuestion());
        
        styleDescription();
        styleOptions();
        
        // Add description to QuestionStructure
        addDescription();
        addOptions();
    }
    
    private void addDescription() {
    
    }
    
    private void initOptions() {
        qr.options.forEach((e) -> {
            this.options.add(new JLabel(e));
        });
    }
    
    private void styleDescription() {
        this.description.setFont(this.getFont().deriveFont(Font.BOLD, 16f));
        this.description.setForeground(Color.YELLOW);
    }
    
    private void styleOptions() {
        for(JLabel e: this.options) {
            e.setForeground(Color.WHITE);
        }
    }
}
