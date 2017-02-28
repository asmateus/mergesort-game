/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author asmateus
 */
public class Level1 extends JPanel implements Bar {
    
    private QuestionLogic question;
    private JPanel cardArea = new JPanel();
    private JPanel question_area = new JPanel();
    private GridBagConstraints c = new GridBagConstraints();
    private String[] cards_path = {
        "data/2.png","data/J.png","data/1.png","data/5.png","data/Q.png",
        "data/1.png","data/3.png","data/8.png",
    };
    
    public Level1(int difficulty) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.5,0.5};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1,0,0,0,0,0,0,0};
        gbl.rowWeights = new double[] {1};
         
        this.cardArea.setLayout(gbl);
        this.cardArea.setBackground(Color.BLACK);
        
        gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {1,0,0,0,0,0};
         
        this.question_area.setLayout(gbl);
        this.question_area.setBackground(Color.BLACK);
        
        // Draw Cards
        loadCards();
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE;
        this.add(this.cardArea, c);
        
        // Create Question
        loadQuestionArea();
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(this.question_area, c);
    }
    
    private void loadQuestionArea() {
        JLabel description = new JLabel("Hola Mundo esta es la primera pregunta");
        description.setFont(this.getFont().deriveFont(Font.BOLD, 20f));
        description.setForeground(Color.yellow);
        JLabel opt1 = new JLabel("Opción 1");
        opt1.setForeground(Color.WHITE);
        JLabel opt2 = new JLabel("Opción 2");
        opt2.setForeground(Color.WHITE);
        JLabel opt3 = new JLabel("Opción 3");
        opt3.setForeground(Color.WHITE);
        JLabel opt4 = new JLabel("Opción 4");
        opt4.setForeground(Color.WHITE);
        JLabel opt5 = new JLabel("Opción 5");
        opt5.setForeground(Color.WHITE);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(description, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(opt1, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(opt2, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(opt3, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(opt4, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.question_area.add(opt5, c);
    }
    
    private void loadCards() {
        for(int i = 0; i < 8; ++i) {
            JLabel card = new JLabel();
            card.setBorder(new EmptyBorder(5,5,5,5));
            
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(this.cards_path[i]));
                ImageIcon icon = new ImageIcon(img);
                card.setIcon(icon);
            }
            catch(Exception e) {
                System.out.println("ERROR");
            }
            
            c = new GridBagConstraints();
            c.gridx = i;
            c.gridy = 0;
            c.gridheight = 1;
            c.gridwidth = 1;
            cardArea.add(card,c);
        }
    }
    
    @Override
    public void selfDestroy(int exit_code) {
    
    }
}
