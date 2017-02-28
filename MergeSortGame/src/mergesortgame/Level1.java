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
public class Level1 extends Level {
    
    public Level1(GameArea master, Watchdog dog) {
        super(dog.master.getUserDifficulty(), dog);
        
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
    
    @Override
    public boolean masterCall(int key) {
        System.out.println(key);
        return false;
    }
}
