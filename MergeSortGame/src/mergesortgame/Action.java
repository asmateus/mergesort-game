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
public abstract class Action {
    public static final int ACTION_IDLE = 0;
    public static final int ACTION_LOGIN = 1;
    public static final int ACTION_REGISTER = 2;
    public static final int ACTION_SET_DFCT = 3;
    public static final int ACTION_SET_LVL = 4;
    
    public static final int IN_OPERATION = -1;
    public static final int IN_ERROR = 0;
    public static final int IN_OK = 1;
    
    public int definitive_state = IN_OPERATION;
    
    private int task_count;
    private int action_id;
    public String[] mess_list;
    
    public Action(int action_id) {
        this.action_id = action_id;
        this.setTaskCount();
    }
    
    public void setActionId(int id) {
        if(this.task_count == 0) {
            this.action_id = id;
            this.setTaskCount();
        }
    }
    
    public String getMessage() {
        if(this.getTaskCount() == 0)
            return "";
        else
            return this.mess_list[this.getTaskCount()-1];
    }
    
    private void setTaskCount() {
        switch(this.action_id) {
            case ACTION_IDLE:
                this.task_count = 0;
                break;
            case ACTION_LOGIN:
                this.task_count = 2;
                break;
            case ACTION_REGISTER:
                this.task_count = 2;
                break;
            case ACTION_SET_DFCT:
                this.task_count = 1;
                break;
            case ACTION_SET_LVL:
                this.task_count = 1;
                break;
        }
    }
    
    public int reduceTaskCount() {
        --this.task_count;
        if(this.task_count != 0) {
            return Action.IN_OPERATION;
        }
        else {
            return this.onTasksEnd();
        }
    }
    
    public int getTaskCount() {
        return this.task_count;
    }
    
    public abstract int executeTaskChain();
    public abstract int onTasksEnd();
}
