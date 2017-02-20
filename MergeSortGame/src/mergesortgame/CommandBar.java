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
    
    public CommandBar(int cap, int action_id, Watchdog dog) {
        super(cap);
        this.setBackground(new Color(255, 0, 0));
        this.setForeground(Color.WHITE);
        this.setText(this.task + SYMBOL);
        this.setCaretPosition(this.task.length() + 1);
        this.setFont(this.getFont().deriveFont(Font.BOLD, 12f));
        
        // Add command bar to watchdog
        this.dog = dog;
        this.dog.addMember(this);
        this.addKeyListener(dog);
        
        // Create an action for commandBar
        this.createAction(action_id);
        
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (offset < task.length()+1) {
                    return;
                }
                super.insertString(fb, offset, string, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (offset < task.length()+1) {
                    length = Math.max(0, length - task.length()+1);
                    offset = task.length()+1;
                }
                super.replace(fb, offset, length, text, attrs);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (offset < task.length()+1) {
                    length = Math.max(0, length + offset - task.length()+1);
                    offset = task.length()+1;
                }
                if (length > 0) {
                    super.remove(fb, offset, length);
                }
            }
        });
        
        // Get focus, this is outside the range of the Watchdog
        this.requestFocusInWindow();
    }
    
    private void createAction(int id) {
        switch(id) {
            case Action.ACTION_IDLE:
                this.action = new ActionLogin();
                break;
            case Action.ACTION_LOGIN:
                
                break;
            case Action.ACTION_REGISTER:
                
                break;
            case Action.ACTION_SET_DFCT:
                
                break;
            case Action.ACTION_SET_LVL:
                
                break;
        }
    }
    
    private void processInput() {
        System.out.println(this.getText());
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
