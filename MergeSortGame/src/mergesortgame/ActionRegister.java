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
public class ActionRegister extends Action {
    private CommandBar cmd;
    
    private String username = "";
    private String password = "";
    
    public ActionRegister(CommandBar cmd) {
        super(Action.ACTION_REGISTER);
        
        this.mess_list = new String[] {"password (NEW)", "username (NEW)"};
        this.cmd = cmd;
    }
    
    @Override
    public int executeTaskChain() {
        if(this.getTaskCount() == 2) {
            this.username = this.cmd.getText();
            this.definitive_state = this.reduceTaskCount();
            this.cmd.setMessage("");
        }
        else if(this.getTaskCount() == 1) {
            this.password = this.cmd.getText();
            this.definitive_state = this.reduceTaskCount();
        }
        return this.definitive_state;
    }
    
    @Override
    public int onTasksEnd() {
        IOManager io = new IOManager(this.username);
        io.createUserFields(this.password);
        return Action.IN_OK;
    }
}
