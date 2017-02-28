/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author asmateus
 */
public abstract class Level extends JPanel implements Member {
    
    private DescriptionArea description;
    private QuestionArea question;
    
    private GridBagConstraints c = new GridBagConstraints();
    
    private final Watchdog dog;
    private final int difficulty;
    
    public Level(int difficulty, Watchdog dog) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.5,0.5};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.dog = dog;
        this.difficulty = difficulty;
    }
    
    public Watchdog getDog() {
        return this.dog;
    }
    
    public int getDifficulty() {
        return this.difficulty;
    }
    
    public DescriptionArea getDescriptionArea() {
        return this.description;
    }
    
    public QuestionArea getQuestionArea() {
        return this.question;
    }
    
    public void setDescriptionArea(DescriptionArea d) {
        this.description = d;
    }
    
    public void setQuestionArea(QuestionArea q) {
        this.question = q;
    }
    
    public void setContent() {
        this.setDescriptionArea();
        this.setQuestionArea();
        
        // Add description Area based on constraints
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(this.getDescriptionArea(), c);
        
        // add question Area based on constraints
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(this.getQuestionArea(), c);
    }
    
    public abstract void setDescriptionArea();
    public abstract void setQuestionArea();
}
