/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author asmateus
 */
public class QuestionLogic {
    
    private final String question;
    private ArrayList<String> answer_option_ids;
    private String correct_answer_id;
    
    public static final String DUMMY_QUESTION_ID = "0000";
    public static final int NO_ANSWER = 1;
    public static final int SPECIFIC_ANSWER = 2;
    public static final int BOTH_ANSWERS = 3;
    
    public QuestionLogic(String question, ArrayList<String> options) {
        this.question = question;
        this.answer_option_ids = options;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void addOption(String opt_id) {
        if(!this.answer_option_ids.contains(opt_id)) {
            this.answer_option_ids.add(opt_id);
        }
    }
    
    public void removeOption(String opt_id) {
        if(this.answer_option_ids.contains(opt_id)) {
            this.answer_option_ids.remove(this.answer_option_ids.indexOf(opt_id));
        }
    }
    
    public ReturnedQuestion pickOptions(int include_dummy, int amount) {
        ArrayList<String> picked_options = new ArrayList<>();
        List<Integer> selected = this.shuffleOptionsIndex(answer_option_ids.size(), amount-1);
        int j = 0;
        
        for(int i = 0; i < selected.size(); ++i) {
            picked_options.add(this.answer_option_ids.get(selected.get(i)));
        }
        switch (include_dummy) {
            case NO_ANSWER:
                picked_options.add(QuestionLogic.DUMMY_QUESTION_ID);
                break;
            case SPECIFIC_ANSWER:
                picked_options.add(this.correct_answer_id);
                break;
            default:
                picked_options.remove(picked_options.size()-1);
                picked_options.add(QuestionLogic.DUMMY_QUESTION_ID);
                picked_options.add(this.correct_answer_id);
                break;
        }
        
        Collections.shuffle(picked_options);
        
        switch(include_dummy) {
            case NO_ANSWER:
                j = picked_options.indexOf(QuestionLogic.DUMMY_QUESTION_ID);
                break;
            default:
                j = picked_options.indexOf(this.correct_answer_id);
                break;
        }
        
        return new ReturnedQuestion(picked_options, j);
    }
    
    private List<Integer> shuffleOptionsIndex(int cant_options, int amount) {
        int max;
        if(cant_options <= amount)
            max = cant_options;
        else
            max = amount;
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < max; ++i) {
            result.add(i);
        }
        
        Collections.shuffle(result);
        return result;
    }
}
