package mergesortgame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author asmateus
 */
public class MSGame
{   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Get the device of the screen to do active refreshing. Use current screen always
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getScreenDevices()[0];
        
        // Launch a user instance, it is *visitor* for default
        User user = new User("");
        
        // Initiate graphical user interface
        UI ui = new UI(device);
        ui.begin();
    }
    
}
