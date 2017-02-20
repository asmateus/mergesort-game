/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.BorderLayout;
import java.awt.Color;
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
    
    private final JLabel user_label = new JLabel(NavBar.DEFAULT_NAME);
    private final JLabel level_label = new JLabel(NavBar.DEFAULT_LEVEL);
    private final JLabel menu_label = new JLabel();
    
    public NavBar() {
        super(new BorderLayout());
        
        // Not focusable
        this.setFocusable(false);
        
        this.setBackground(Color.BLACK);
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        
        // Configure level label
        this.setLevelLabelInit();
        this.add(level_label, BorderLayout.CENTER);
        
        // Configure user label
        this.setUserLabelInit();
        this.add(user_label, BorderLayout.WEST);
        
        // Configure menu label
        this.setMenuLabelInit();
        this.add(menu_label, BorderLayout.EAST);
        
        this.user_label.setName("user");
        this.level_label.setName("level");
        this.menu_label.setName("menu");
        
        // Adding event listeners to all labels
        this.user_label.addMouseListener(this);
        this.level_label.addMouseListener(this);
        this.menu_label.addMouseListener(this);
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
    
    public void setLevelLabelText(String level) {
        this.user_label.setText(level);
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
