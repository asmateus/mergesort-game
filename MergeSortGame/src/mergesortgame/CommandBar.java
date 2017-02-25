/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author asmateus
 */
public class CommandBar extends JTextField implements Member {
    
    public static final String SYMBOL = "$";
    
    public String task = "mergesort-cmd";
    
    private final Watchdog dog;
    private Action action;
    private String[] user_cred;
    private Bar parent;
    
    public CommandBar(int cap, int action_id, Watchdog dog, Bar parent) {
        super(cap);
        this.parent = parent;
        this.setBackground(new Color(255, 0, 0));
        this.setForeground(Color.WHITE);
        this.setFont(this.getFont().deriveFont(Font.BOLD, 12f));
        
        // Create an action for commandBar
        this.createAction(action_id);
        
        // Create message sequence
        this.setMessage("");
        
        // Add command bar to watchdog
        this.dog = dog;
        this.dog.addMember(this);
        this.addKeyListener(dog);
        
        // Get focus, this is outside the range of the Watchdog
        this.requestFocusInWindow();
    }
    
    public void setUser(String username, String status) {
        this.user_cred = new String[] {username, status};
    }
    
    public void setMessage(String placeholder) {
        // Remove document filter
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {});
        
        this.task = placeholder + this.action.getMessage();
        
        this.setText(this.task + SYMBOL);
        this.setCaretPosition(this.task.length() + 1);
        
        // Add document filter
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (offset < task.length()+1) {
                    return;
                }
                super.insertString(fb, offset, string, attr);
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (offset < task.length()+1) {
                    length = Math.max(0, length - task.length()+1);
                    offset = task.length()+1;
                }
                super.replace(fb, offset, length, text, attrs);
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                if (offset < task.length()+1) {
                    length = Math.max(0, length + offset - task.length()+1);
                    offset = task.length()+1;
                }
                if (length > 0) {
                    super.remove(fb, offset, length);
                }
            }
        });
    }
    
    private void createAction(int id) {
        switch(id) {
            case Action.ACTION_IDLE:
                this.action = new ActionLogin(this);
                break;
            case Action.ACTION_LOGIN:
                this.action = new ActionLogin(this);
                break;
            case Action.ACTION_REGISTER:
                this.action = new ActionLogin(this);
                break;
            case Action.ACTION_SET_DFCT:
                this.action = new ActionLogin(this);
                break;
            case Action.ACTION_SET_LVL:
                this.action = new ActionLogin(this);
                break;
        }
    }
    
    private void processInput() {
        int result = this.action.executeTaskChain();
        if(result == Action.IN_OK) {
            // Execute exit protocol
            this.dog.removeMember();
            this.parent.selfDestroy();
            
        }
        else if(result == Action.IN_ERROR){
            System.out.println("ERROR");
        }
    }
    
    public void changeCommandText(String cmm_txt) {
        this.setText(cmm_txt + this.getText().substring(this.task.length()));
        this.task = cmm_txt;
    }
    
    @Override
    public void setBorder(Border border) {
        super.setBorder(new EmptyBorder(3, 3, 3, 3));
    }
    
    @Override
    public String getText() {
        return super.getText().substring(this.task.length()+1);
    }
    
    @Override
    public boolean masterCall(int key) {
        if(key == KeyEvent.VK_ENTER) {
            this.processInput();
        }
        
        return true;
    }
}
