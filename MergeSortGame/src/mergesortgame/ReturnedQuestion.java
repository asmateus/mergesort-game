/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.util.ArrayList;

/**
 *
 * @author asmateus
 */
public class ReturnedQuestion {
    public final ArrayList<String> options;
    public final int index_of_correct;
        
    public ReturnedQuestion(ArrayList<String> options, int i) {
        this.options = options;
        this.index_of_correct = i;
    }
}
