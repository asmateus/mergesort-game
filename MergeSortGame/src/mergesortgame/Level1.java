/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author asmateus
 */
public class Level1 extends Level {
    
    private final ActionLevel1 action;
    private final GameArea master;
    
    public Level1(GameArea master, Watchdog dog) {
        super(dog.master.getUserDifficulty(), dog);
        
        this.master = master;
        this.action = new ActionLevel1();
        
    }
    
    @Override
    public void setDescriptionArea() {
        System.out.println();
        
        JEditorPane content = new JEditorPane();
        content.setBackground(Color.BLACK);
        content.setFocusable(false);
        content.setEditable(false);
        content.setContentType("text/html");
        content.setText(DescriptionStrings.getDescriptionLVL1("descriptions/images/"));
        
        this.setDescriptionArea(new DescriptionArea(content));
    }
    
    @Override
    public void setQuestionArea() {
        // Create Question Logic
        ArrayList<String> options = new ArrayList<>();
        chooseOptions(this.getDifficulty(), options);
        QuestionLogic question =
                new QuestionLogic(
                        QuestionOptions.NOOB_QST_LEVEL_1,
                        QuestionOptions.NOOB_CORRECT_LEVEL_1, options);
        
        // Create Question Structure
        this.setQuestionArea(new QuestionArea(question));
        this.action.setCorrectAnswerIndex(this.getQuestionArea().getReturnedQuestion().index_of_correct);
    }
    
    private void chooseOptions(int diff, ArrayList<String> options) {
        String[] opts;
        switch(diff) {
            case 1:
                opts = QuestionOptions.NOOB_LEVEL_1;
                break;
            case 2:
                opts = QuestionOptions.ADVANCED_LEVEL_1;
                break;
            default:
                opts = QuestionOptions.LEGENDARY_LEVEL_1;
                break;
        }
        
        for(String e:opts) {
            options.add(e);
        }
    }
    
    private void manageUserResponse(int key) {
        int result = this.action.validateAnswer(key);
        
        if(result == Action.IN_ERROR) {
            this.time_spent_in_level = this.action.getTimeSpent();
            this.tries_in_level = this.action.getTries();
            this.death_in_level = this.action.getDeaths();
            this.master.selfDestroy(Action.IN_ERROR);
        }
        else if(result == Action.IN_OK) {
            this.time_spent_in_level = this.action.getTimeSpent();
            this.tries_in_level = this.action.getTries();
            this.death_in_level = this.action.getDeaths();
            int time_offset = 0;
            if(this.time_spent_in_level > 60) {
                time_offset = (int) (this.time_spent_in_level - 60);
            }
            this.score_in_level = 1000 - 15*time_offset - 111*this.tries_in_level - 150*this.death_in_level;
            if(this.score_in_level < 0) {
                this.score_in_level = 0;
            }
            this.master.selfDestroy(Action.IN_OK);
        }
        
        this.getQuestionArea().formatQuestion(key);
    }
    
    @Override
    public boolean masterCall(int key) {
        switch(key) {
            case KeyEvent.VK_1:
                this.manageUserResponse(0);
                return true;
            case KeyEvent.VK_2:
                this.manageUserResponse(1);
                return true;
            case KeyEvent.VK_3:
                this.manageUserResponse(2);
                return true;
            case KeyEvent.VK_4:
                this.manageUserResponse(3);
                return true;
            case KeyEvent.VK_5:
                this.manageUserResponse(4);
                return true;
        }
        return false;
    }
}
