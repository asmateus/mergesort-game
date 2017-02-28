/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class NavBar extends JPanel implements MouseListener {
    
    private static final String DEFAULT_NAME = "Misaka";
    private static final String DEFAULT_LEVEL = "Level 01";
    private static final String DEFAULT_DIFF = User.DIFF_MESSAGE_1;
    private static final String DEFAULT_FAIL = "0";
    
    private final JLabel user_label = new JLabel(NavBar.DEFAULT_NAME);
    private final JLabel level_label = new JLabel(NavBar.DEFAULT_LEVEL);
    private final JLabel diff_label = new JLabel(NavBar.DEFAULT_DIFF);
    private final JLabel fail_label = new JLabel(NavBar.DEFAULT_FAIL);
    private final JLabel menu_label = new JLabel();
    
    public NavBar() {
        super(new GridBagLayout());
        
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        
        // Weight decides to which column give the extra space
        gbl.columnWeights = new double[] {0,1,0,0,0};
        gbl.rowWeights = new double[] {1};
        
        this.setLayout(gbl);
        
        // Not focusable
        this.setFocusable(false);
        
        this.setBackground(Color.BLACK);
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        
        // Configure user label
        this.setUserLabelInit();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 0;
        cons.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
        this.add(user_label, cons);
        
        // Configure level label
        this.setLevelLabelInit();
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 0;
        cons.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
        this.add(level_label, cons);
        
        // Configure difficulty label
        this.setDifficultyLabelInit();
        cons.gridx = 2;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 10;
        cons.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        this.add(diff_label, cons);
        
        // Configure fail count label
        this.setFailCountLabelInit();
        cons.gridx = 3;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 0;
        cons.anchor = GridBagConstraints.PAGE_START;
        this.add(fail_label, cons);
        
        // Configure menu label
        this.setMenuLabelInit();
        cons.gridx = 4;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.ipadx = 0;
        cons.anchor = GridBagConstraints.PAGE_START;
        this.add(menu_label, cons);
        
        this.user_label.setName("user");
        this.level_label.setName("level");
        this.menu_label.setName("menu");
        
        // Adding event listeners to all labels
        this.user_label.addMouseListener(this);
        this.level_label.addMouseListener(this);
        this.menu_label.addMouseListener(this);
    }
    
    public void setUserNameLabel(String name) {
        this.user_label.setText(name);
    }
    
    public void setUserLevelLabel(int level, int score) {
        this.level_label.setText("Level 0" + level + " / Score " + score);
    }
    
    public void setDifficultyLabel(int diff) {
        switch(diff) {
            case 1:
                this.diff_label.setText(User.DIFF_MESSAGE_1);
                this.diff_label.setForeground(new Color(74, 222, 0));
                break;
            case 2:
                this.diff_label.setText(User.DIFF_MESSAGE_2);
                this.diff_label.setForeground(new Color(255, 200, 0));
                break;
            case 3:
                this.diff_label.setText(User.DIFF_MESSAGE_3);
                this.diff_label.setForeground(new Color(229, 0, 0));
        }
    }
    
    public void setFailCountLabel(int failcount) {
        this.fail_label.setText(""+failcount);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        String entity = ((JLabel) me.getSource()).getName();
        System.out.println(entity);
    }
    
    private void setUserLabelInit() {
        this.user_label.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.user_label.setForeground(Color.WHITE);
        
    }
    
    public void setUserLabelText(String username) {
        this.user_label.setText(username);
    }
    
    private void setLevelLabelInit() {
        this.level_label.setBorder(new EmptyBorder(5, 10, 5, 5));
        this.level_label.setForeground(Color.GRAY);
        
    }
    
    private void setDifficultyLabelInit() {
        this.diff_label.setBorder(new EmptyBorder(5, 10, 5, 5));
        this.diff_label.setForeground(new Color(74, 222, 0));
    }
    
    private void setFailCountLabelInit() {
        this.fail_label.setForeground(Color.WHITE);
        
    }
    
    private void setMenuLabelInit() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("data/skull_small.jpeg"));
            ImageIcon icon = new ImageIcon(img);
            this.menu_label.setIcon(icon);
        }
        catch(Exception e) {
            System.out.println("ERROR");
        }
    }
    
    
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mousePressed(MouseEvent me) {}
}
