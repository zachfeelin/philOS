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

public class BackgroundWindow extends JFrame implements MouseListener{
    Font font;
    Button myButton0;
    Button myButton1;
    Button myButton2;
    Button myButton3;
    private BufferedImage philOS;
    private BufferedImage image;
    private BufferedImage trigDash;
    private BufferedImage textEditorImage;
    private BufferedImage fileExplorerImage;

    public BackgroundWindow() {
        font = new Font("Comic Sans MS", Font.PLAIN, 20);
        image = readImage("philBackground.png");
        trigDash = readImage("ROBbottom.png");
        textEditorImage = readImage("text.png");
        fileExplorerImage = readImage("files.png");
        philOS = readImage("philOS.png");
        myButton1 = new Button(20, 20, 20 + 18 * 3, 20 + 18 * 3);
        myButton2 = new Button(100, 20, 100 + 18 * 3, 20 + 18 * 3);
        myButton3 = new Button(180, 20, 180 + 18 * 3, 20 + 18 * 3);
        addMouseListener(this);
        setFocusable(true);

        // Create the background window that covers the taskbar
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        Rectangle bounds = device.getDefaultConfiguration().getBounds();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new Dimension(bounds.width, bounds.height));
        setLocation(bounds.x, bounds.y);
        setVisible(true);
        toFront();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Add a JPanel to the frame to draw the background image
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, null);

                //displaying the bottom rectangle
                g.setColor(new Color(0xFF69B4));
                g.fillRect(0, height - 75, width, 75);

                //displaying the button
                int buttonWidth = 100;
                int buttonHeight = 40;
                int buttonX = 15; // 10 pixels away from the left side
                int buttonY = height - buttonHeight - 17; // 5 pixels above the bottom
                myButton0 = new Button(buttonX, buttonY, buttonWidth + buttonX, buttonHeight + buttonY);
                g.drawImage(philOS, buttonX, buttonY, buttonWidth, buttonHeight, null);

                //displaying the foreground image
                int x = 20; // x-coordinate of top-left corner of image
                int y = 20; // y-coordinate of top-left corner of image
                int width1 = 18 * 3; // width of image after scaling
                int height1 = 18 * 3; // height of image after scaling
                g.drawImage(trigDash, x, y, width1, height1, null);

                //displaying the text editor image
                int x1 = 100; // x-coordinate of top-left corner of image
                int y1 = 20; // y-coordinate of top-left corner of image
                int width2 = 18 * 3; // width of image after scaling
                int height2 = 18 * 3; // height of image after scaling
                g.drawImage(textEditorImage, x1, y1, width2, height2, null);

                //displaying the file explorer image
                int x2 = 180; // x-coordinate of top-left corner of image
                int y2 = 20; // y-coordinate of top-left corner of image
                int width3 = 18 * 3; // width of image after scaling
                int height3 = 18 * 3; // height of image after scaling
                g.drawImage(fileExplorerImage, x2, y2, width3, height3, null);

                //displaying the text
                g.setFont(font);
                g.setColor(new Color(0xFF83C1));
                g.drawString("Trig", 30, 95);
                g.drawString("Dash", 30, 115);
                g.drawString("Editor", 100, 95);
                g.drawString("Files", 186, 95);
            }
        });
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

    public void mousePressed(MouseEvent e) {
        // getButton tells us which button was clicked.
        int button = e.getButton();

        // BUTTON1 = left click
        if (button == MouseEvent.BUTTON1) {
            int x = e.getX();
            int y = e.getY();
            if (myButton0.clicked(x, y)) {
                System.exit(0);
            }
            if (myButton1.clicked(x, y)) {
                /* Free present from your instructor!
                   mousePressed happens on the Swing event dispatch thread.
                   We didn't talk about this much in class, but the problem
                   is that the screen is redrawn on the same thread, so the
                   next screen refresh can't happen until this method ends.
                   This is fine for the text editor because it has no main
                   loop, but the game has a main loop that will block this
                   thread until it ends. So, we need to start a new thread
                   to run the game. We didn't talk about Threads in class,
                   so that's why this is a free present.
                */

                new Thread(){
                    public void run(){
                        JFrame frame = new JFrame("Trig Dash");
                        // Set the width and height of the window in pixels
                        frame.setSize(1900,1050);
            
                        // Initialize the drawing canvas
                        Geometry panel = new Geometry();
            
                        // Add the drawing canvas to the window
                        frame.add(panel);
            
                        // Show the window when we are done with all of our initialization
                        frame.setVisible(true);
                        addMouseListener(new MouseAdapter() {
                            public void mousePressed(MouseEvent e) {
                                frame.toFront();
                            }
                        });
                        panel.mainLoop();
                    }
                }.start();


            }
            if (myButton2.clicked(x, y)) {
                /* 
                RunTextEdit.main(null);
                public void mousePressed(MouseEvent e) {
                    RunTextEdit.main(null).frame.toFront();
                }
                */
                // Initialize the window
                JFrame frame = new JFrame("Text Editor");
                // Set the width and height of the window in pixels
                frame.setSize(800,600);
    
                // Initialize the drawing canvas
                TextEditor panel = new TextEditor();
    
                // Add the drawing canvas to the window
                frame.add(panel);
    
                // Show the window when we are done with all of our initialization
                frame.setVisible(true);
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        frame.toFront();
                    }
                });
            }
            if (myButton3.clicked(x, y)) {
                // Initialize the window
                JFrame frame = new JFrame("File Explorer");
                // Set the width and height of the window in pixels
                frame.setSize(800,600);
    
                // Initialize the drawing canvas
                FileExplorer panel = new FileExplorer();
    
                // Add the drawing canvas to the window
                frame.add(panel);
    
                // Show the window when we are done with all of our initialization
                frame.setVisible(true);
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        frame.toFront();
                    }
                });
            }
        }
        repaint();
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
}

class Button {
    int left;
    int top;
    int right;
    int bottom;

    public Button(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean clicked(int x, int y) {
        return x >= left && x <= right && y >= top && y <= bottom;
    }
}