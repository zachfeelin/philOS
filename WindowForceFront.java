import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class WindowForceFront
{
  public static void main(String[] args)
  {
    // Create the background window that covers the taskbar
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = env.getDefaultScreenDevice();
    Rectangle bounds = device.getDefaultConfiguration().getBounds();
    JFrame background = new JFrame();
    background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    background.setUndecorated(true);
    background.setResizable(false);
    background.setSize(new Dimension(bounds.width, bounds.height));
    background.setLocation(bounds.x, bounds.y);
    background.setVisible(true);
    background.toFront();
    background.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    // Create the foreground window
    JFrame foreground = new JFrame("Foreground Window");
    foreground.setSize(400, 300);
    foreground.setLocationRelativeTo(null);
    foreground.setVisible(true);
    foreground.toFront();
    
    // Add a mouse listener to the background window to keep the foreground window on top
    background.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        foreground.toFront();
      }
    });
  }
}
