package mergesortgame;

import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    private LoginNavBar flex_bar;
    
    private boolean fullscreen_support;
    private boolean active_flex_bar = false;
    
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
    
    public void enableFlexBar() {
        this.active_flex_bar = false;
    }
    
    private void init()
    {
        // Application title
        this.setTitle("MergeSort Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Configuring the layout
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {0.4, 0.6};
        gbl.rowWeights = new double[] {0.1, 0.1, 0.4, 0.4};
        c.setLayout(gbl);
        c.setBackground(Color.BLACK);
        
        setContentPane(c);
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.PAGE_START;
        
        // Create and add the navigation bar
        NavBar nav_bar = new NavBar();
        c.add(nav_bar, cons);
    }
    
    private void createLoginInterface() {
        this.active_flex_bar = true;
        this.flex_bar = new LoginNavBar(this.dog, this);
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridheight = 1;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.PAGE_END;
        
        c.add(flex_bar, cons);
        pack();
    }
    
    private void initWatchdog() {
        this.dog.addMember(this);
        this.addKeyListener(this.dog);
    }
    
    @Override
    public boolean masterCall(int key) {
        if(key == KeyEvent.VK_L) {
            if(!active_flex_bar) {
;
                this.createLoginInterface();
            }
            return true;
        }
        
        return false;
    }
}
