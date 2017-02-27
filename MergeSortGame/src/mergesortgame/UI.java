package mergesortgame;

import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author asmateus
 */
public class UI extends JFrame implements Member
{
    private final GraphicsDevice device;
    private final Watchdog dog = new Watchdog();
    private final Container c;
    
    private JPanel flex_bar;
    private NavBar nav_bar;
    
    private boolean fullscreen_support;
    private boolean active_flex_bar = false;
    private boolean active_user_session = false;
    
    public UI(GraphicsDevice device)
    {
        super(device.getDefaultConfiguration());
        this.device = device;
        this.c = this.getContentPane();
        
        this.init();
    }
    
    public void begin(User user) {
        // Display interface
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
        
        // Load user data
        this.loadUserData(-1);
    }
    
    public void enableFlexBar(int cause) {
        this.active_flex_bar = false;
        this.requestFocusInWindow();
        if(cause == Action.ACTION_LOGIN) {
            this.loadUserData(-1);
        }
        else if(cause == Action.ACTION_REGISTER) {
            
        }
    }
    
    private void loadUserData(int precision) {
        switch(precision) {
            case User.LEVEL_OFFSET:
                this.nav_bar.setUserLevelLabel(this.dog.master.getUserLevel(),
                    this.dog.master.getUserScore());
                break;
            case User.SCORE_OFFSET:
                this.nav_bar.setUserLevelLabel(this.dog.master.getUserLevel(),
                    this.dog.master.getUserScore());
                break;
            case User.DIFFICULTY_OFFSET:
                this.nav_bar.setDifficultyLabel(this.dog.master.getUserDifficulty());
                break;
            case User.FAIL_COUNT_OFFSET:
                this.nav_bar.setFailCountLabel(this.dog.master.getUserFailCount());
                break;
            default:
                this.nav_bar.setUserNameLabel(this.dog.master.getUserName());
                this.nav_bar.setUserLevelLabel(this.dog.master.getUserLevel(), this.dog.master.getUserScore());
                this.nav_bar.setDifficultyLabel(this.dog.master.getUserDifficulty());
                this.nav_bar.setFailCountLabel(this.dog.master.getUserFailCount());
        }
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
        nav_bar = new NavBar();
        c.add(nav_bar, cons);
    }
    
    private void createLoginInterface() {
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
    
    private void createRegisterInterface() {
        this.flex_bar = new RegisterNavBar(this.dog, this);
        
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
        // Login
        if(key == KeyEvent.VK_L) {
            if(!active_user_session) {
                if(!active_flex_bar) {
                    this.active_flex_bar = true;
                    this.createLoginInterface();
                    this.active_user_session = true;
                }
            }
            else {
                this.active_user_session = false;
                
                // save user statistics
                this.dog.master.SendDataToOrigin();
                this.dog.master = new User(User.DEFAULT_USER);
                this.loadUserData(-1);
            }
            return true;
        }
        // Register
        else if(key == KeyEvent.VK_R) {
            if(!active_flex_bar) {
                this.active_flex_bar = true;
                this.createRegisterInterface();
            }
            
            return true;
        }
        // Increase difficulty
        else if(key == KeyEvent.VK_PLUS) {
            this.dog.master.upDifficulty();
            this.loadUserData(User.DIFFICULTY_OFFSET);
            return true;
        }
        // Decrease difficulty
        else if(key == KeyEvent.VK_MINUS) {
            this.dog.master.lowerDifficulty();
            this.loadUserData(User.DIFFICULTY_OFFSET);
            return true;
        }
        
        return false;
    }
}
