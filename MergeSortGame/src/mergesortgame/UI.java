package mergesortgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author asmateus
 */
public class UI extends JFrame implements Member
{
    private final GraphicsDevice device;
    private final Watchdog dog = new Watchdog();
    private final Container c;
    
    private boolean fullscreen_support;
    
    public UI(GraphicsDevice device)
    {
        super(device.getDefaultConfiguration());
        this.device = device;
        this.c = this.getContentPane();
        
        this.init();
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
        
        this.initWatchdog();
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
    
    private void initWatchdog() {
        this.dog.addMember(this);
        this.addKeyListener(this.dog);
    }
    
    @Override
    public boolean masterCall(int key) {
        if(key == KeyEvent.VK_L) {
            System.out.println("Log in triggered");
            return true;
        }
        
        return false;
    }
}
