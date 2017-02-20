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
public class ActionLogin extends Action {
    
    public ActionLogin() {
        super(Action.ACTION_LOGIN);
    }
    
    @Override
    public int onTasksEnd() {
        return Action.IN_OK;
    }
}
