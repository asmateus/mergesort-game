/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

/**
 *
 * @author asmateus
 */

// This class leverages the keys listened by any class and fowards them to its
// members via the member Stack
public class Watchdog implements KeyListener {
    
    // master is the user of the current session
    public User master = new User("misaka");
    private final Stack<Member> subscribers = new Stack<>();
    
    public Watchdog() {
        
    }
    
    public void addMember(Member member) {
        this.subscribers.push(member);
    }
    
    public void removeMember() {
        this.subscribers.pop();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(!this.subscribers.isEmpty()) {
            int i = this.subscribers.size() - 1;
            while(i < this.subscribers.size() 
                    && !this.subscribers.get(i).masterCall(e.getKeyCode())) {
                ++i;
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
}
