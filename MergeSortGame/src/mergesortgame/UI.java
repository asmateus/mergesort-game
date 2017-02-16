package mergesortgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsDevice;
import javax.swing.JFrame;

/**
 *
 * @author asmateus
 */
public class UI extends JFrame
{
    private final GraphicsDevice device;
    private final Container c;
    
    private boolean fullscreen_support;
    
    public UI(GraphicsDevice device)
    {
        super(device.getDefaultConfiguration());
        this.device = device;
        this.c = this.getContentPane();
        
        this.init();
    }
    
    private void init()
    {
        // Application title
        this.setTitle("MergeSort Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setLayout(new BorderLayout());
        c.setBackground(Color.WHITE);
        
        setContentPane(c);
        
        // Create and add the navigation bar
        NavBar nav_bar = new NavBar();
        c.add(nav_bar, BorderLayout.NORTH);
    }
    
    
    public void begin() {
        this.fullscreen_support = this.device.isFullScreenSupported();
        setUndecorated(this.fullscreen_support);
        setResizable(!this.fullscreen_support);
        if (this.fullscreen_support) {
            // Full-screen mode
            this.device.setFullScreenWindow(this);
            validate();
        } else {
            // Windowed mode
            pack();
            setVisible(true);
        }
    }
}
