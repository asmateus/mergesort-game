/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author asmateus
 */
public class QuestionArea extends JPanel {
    
    private QuestionLogic q;
    private ReturnedQuestion qr;
    private GridBagConstraints c;
    
    private ArrayList<JLabel> options = new ArrayList<>();
    private JLabel description = new JLabel();
    
    public QuestionArea(QuestionLogic q, int question_count, int question_type) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.2,0.2,0.2,0.2,0.1,0.1};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.q = q;
        if(question_count > 0) {
            qr = this.q.pickOptions(question_type, question_count);
        
            // Init options and description
            initOptionsAndDescription();
        }
        styleDescription();
        styleOptions();
        
        // Add description to QuestionArea
        addDescription();
        addOptions();
    }
    
    public void formatQuestion(int index) {
        this.options.get(index).setForeground(Color.RED);
    }
    
    public ReturnedQuestion getReturnedQuestion() {
        return this.qr;
    }
    
    private void addDescription() {
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(description, c);
    }
    
    private void addOptions() {
        c = new GridBagConstraints();
        for(int i = 0; i < this.options.size(); ++i) {
            c.gridx = 0;
            c.gridy = i+1;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
            this.add(this.options.get(i), c);
        }
    }
    
    private void initOptionsAndDescription() {
        String o;
        IOManager io = new IOManager("");
        
        this.description.setText(io.fetchStringFromCode(this.q.getQuestion()));
        
        for(int i = 0; i < this.qr.options.size(); ++i) {
            o = io.fetchStringFromCode(this.qr.options.get(i));
            this.options.add(new JLabel(Integer.toString(i+1) + ". " + o));
        }
    }
    
    private void styleDescription() {
        this.description.setFont(this.getFont().deriveFont(Font.BOLD, 16f));
        this.description.setBorder(new EmptyBorder(5,5,5,5));
        this.description.setForeground(Color.YELLOW);
    }
    
    private void styleOptions() {
        for(JLabel e: this.options) {
            e.setForeground(Color.WHITE);
            e.setBorder(new EmptyBorder(5,5,5,5));
        }
    }
}
