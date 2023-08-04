import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FileExplorer extends JPanel implements MouseListener{
    Font font;
    private BufferedImage folder;
    private BufferedImage files;

    public FileExplorer() {
        addMouseListener(this);
        setFocusable(true);
        folder = readImage("folder.png");
        files = readImage("fileIcon.png");
        File folder = new File("sprites");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    private static BufferedImage readImage(String infile) {
        try {
            BufferedImage ret = ImageIO.read(new File(infile));
            return ret;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }  
    public void mouseEntered(MouseEvent e)
    { 
    } 
    public void mouseExited(MouseEvent e)
    { 
    }
    public void mousePressed(MouseEvent e)
    {
    }
}