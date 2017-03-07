/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JEditorPane;

/**
 *
 * @author asmateus
 */
public class LevelKing extends Level {
    
    private int level = 1;
    
    public LevelKing(GameArea g, Watchdog dog, int level) {
        super(dog.master.getUserLevel(), dog);
        this.level = level;
        
        dog.removeMember();
    }
    
    
    @Override
    public void setDescriptionArea() {
        JEditorPane content = new JEditorPane();
        content.setContentType("text/html");
        content.setFocusable(false);
        content.setEditable(false);
        content.setBackground(Color.BLACK);
        
        switch(level) {
            case 1:
                content.setText(DescriptionStrings.getKingNoobString("descriptions/images/"));
                break;
            case 2:
                content.setText(DescriptionStrings.getKingAdvancedString("descriptions/images/"));
                break;
            case 3:
                content.setText(DescriptionStrings.getKingLegendaryString("descriptions/images/"));
                break;
        }
        
        this.setDescriptionArea(new DescriptionArea(content));
    }
    
    @Override
    public void setQuestionArea() {
        this.setQuestionArea(new QuestionArea(new QuestionLogic("","",new ArrayList<>()),0,0));
    }
    
    @Override
    public boolean masterCall(int key) {return false;}
}
