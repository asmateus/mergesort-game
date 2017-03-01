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
public class LevelDummy extends Level {
    
    public LevelDummy(GameArea g, Watchdog dog) {
        super(dog.master.getUserLevel(), dog);
        
        dog.removeMember();
    }
    
    
    @Override
    public void setDescriptionArea() {
        JEditorPane content = new JEditorPane();
        content.setContentType("text/html");
        content.setFocusable(false);
        content.setEditable(false);
        content.setBackground(Color.BLACK);
        content.setText(DescriptionStrings.getDummyString());
        this.setDescriptionArea(new DescriptionArea(content));
    }
    
    @Override
    public void setQuestionArea() {
        this.setQuestionArea(new QuestionArea(new QuestionLogic("","",new ArrayList<>()),0,0));
    }
    
    @Override
    public boolean masterCall(int key) {return false;}
}
