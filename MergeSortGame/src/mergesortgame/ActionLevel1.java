/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

/**
 *
 * @author asmateus
 */
public class ActionLevel1 extends Action {
    
    private int index = 0;
    private int index_of_answer = 0;
    private double init_time = 0;
    private double end_time = 0;
    private int tries = 0;
    private int deaths = 0;
    
    public ActionLevel1() {
        super(Action.ACTION_LVL_1);
        this.init_time = System.currentTimeMillis();
        this.end_time = System.currentTimeMillis();
    }
    
    public void setCorrectAnswerIndex(int index) {
        this.index = index;
    }
    
    public double getTimeSpent() {
        return (this.end_time-this.init_time)/1000;
    }
    
    public int getTries() {
        return tries;
    }
    
    public int getDeaths() {
        return deaths;
    }
    
    public int validateAnswer(int index_of_answer) {
        this.index_of_answer = index_of_answer;
        return this.executeTaskChain();
    }
    
    @Override
    public int executeTaskChain() {
        return reduceTaskCount();
    }
    
    @Override
    public int onTasksEnd() {
        if(this.index == this.index_of_answer) {
            this.end_time = System.currentTimeMillis();
            return Action.IN_OK;
        }
        else {
            ++this.tries;
            if(this.tries == 3) {
                this.end_time = System.currentTimeMillis();
                this.deaths += 1;
                return Action.IN_ERROR;
            }
            return Action.IN_OPERATION;
        }
    }
}
