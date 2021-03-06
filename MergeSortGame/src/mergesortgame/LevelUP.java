/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JEditorPane;

/**
 *
 * @author asmateus
 */
public class LevelUP extends Level {
    
    private ActionLevelUP action;
    private final GameArea master;
    
    public LevelUP(GameArea master, Watchdog dog) {
        super(dog.master.getUserDifficulty(), dog);
        
        this.master = master;
        this.action = new ActionLevelUP();
    }
    
    @Override
    public void setDescriptionArea() {
        JEditorPane content = new JEditorPane();
        content.setContentType("text/html");
        content.setFocusable(false);
        content.setEditable(false);
        content.setBackground(Color.BLACK);
        content.setText(DescriptionStrings.getLevelUPString());
        this.setDescriptionArea(new DescriptionArea(content));
    }
    
    @Override
    public void setQuestionArea() {
        // Create Question Logic
        String description = "";
        ArrayList<String> opts = new ArrayList<>();
        String correct = "";
        int cant = 0;
        switch(this.getDog().master.getUserLevel()) {
            case 1:
                description = QuestionOptions.QST_LEVEL_1_UP;
                opts = new ArrayList<>(Arrays.asList(QuestionOptions.LEVEL_UP_1));
                correct = QuestionOptions.CORRECT_LEVEL_1_UP;
                cant = 2;
                break;
            case 2:
                description = QuestionOptions.QST_LEVEL_2_UP;
                opts = new ArrayList<>(Arrays.asList(QuestionOptions.LEVEL_UP_2));
                correct = QuestionOptions.CORRECT_LEVEL_2_UP;
                cant = 5;
                break;
            case 3:
                description = QuestionOptions.QST_LEVEL_3_UP;
                opts = new ArrayList<>(Arrays.asList(QuestionOptions.LEVEL_UP_3));
                correct = QuestionOptions.CORRECT_LEVEL_3_UP;
                cant = 5;
                break;
            case 4:
                description = QuestionOptions.QST_LEVEL_4_UP;
                opts = new ArrayList<>(Arrays.asList(QuestionOptions.LEVEL_UP_4));
                correct = QuestionOptions.CORRECT_LEVEL_4_UP;
                cant = 5;
                break;
            default:
                description = QuestionOptions.QST_LEVEL_5_UP;
                opts = new ArrayList<>(Arrays.asList(QuestionOptions.LEVEL_UP_5));
                correct = QuestionOptions.CORRECT_LEVEL_5_UP;
                cant = 5;
                break;
        }
        QuestionLogic question =
                new QuestionLogic(
                        description,
                        correct, opts);
        
        // Create Question Structure
        this.setQuestionArea(new QuestionArea(question, cant, QuestionLogic.SPECIFIC_ANSWER));
        this.action.setCorrectAnswerIndex(this.getQuestionArea().getReturnedQuestion().index_of_correct);
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
            /*
            this.score_in_level = 1000 - 15*time_offset - 111*this.tries_in_level - 150*this.death_in_level;
            if(this.score_in_level < 0) {
                this.score_in_level = 0;
            }*/
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
