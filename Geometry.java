/* We can interpret our screen as a side-view rather than a top-down view of
   a scene. 
 */

import javax.swing.JPanel;

import javafx.scene.image.ImageView;

// import javax.swing.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.  MouseListener;


public class Geometry extends JPanel implements KeyListener, MouseListener
{
  Square ourSquare;
  boolean DeathBoolean = false;
  int acceleration = 1;
  int jumpSpeed = 20;
  int gravity = 11/8;
  int floor = 900; // y coordinate to stop falling at
  int ceiling = 135;
  boolean jump = false;
  boolean negativeJump = false;
  BufferedImage image;
  BufferedImage rewind;
  int backgroundSpeed = 8;
  boolean onYellow = false;
  int backgroundPositionX = 0;
  boolean buttonPressed = false;
  
  private static final int BACKGROUND_WIDTH = 15000; // Width of your background image
  private static final int BACKGROUND_HEIGHT = 1000; // Height of your background image
  private static final int WINDOW_HEIGHT = 1000;
  private static final int WINDOW_WIDTH = 15000;
  
  private ImageView backgroundImageView;

 
  public Geometry()
  {
    ourSquare = new Square(200, 850, 50, new Color(0x40ff80));
    addKeyListener(this);
    addMouseListener(this);
    setFocusable(true);
    {
    image = readImage("Geometry.png");
    rewind = readImage("play.png");
    }
    


  }
  public void moveBackground() 
  {
      // Update the background position to create a scrolling effect
      backgroundPositionX -= backgroundSpeed;
      
  }

  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(image, backgroundPositionX, 0, null);
    ourSquare.drawTo(g);
    if (DeathBoolean)
    { 
    // Draw death image
    
    g.drawImage(rewind, 850, 425, null);
    }
  }
  
  public void mainLoop()
  {
    while(true)
    {
      try
      {
        Thread.sleep(17);
      }
      catch(Exception e){e.printStackTrace();}


      
      ourSquare.applyAccelerationY(gravity);
      
      ourSquare.move();

        if(buttonPressed)
        {
          backgroundPositionX = 0;
          DeathBoolean = false;
          backgroundSpeed = 8;
          buttonPressed = false;
          ourSquare.y = 865;
          negativeJump = false;
          gravity = 11/8;
        }
        

    for(int row = ourSquare.y; row < ourSquare.y + ourSquare.size; row++)
    {                     
      for(int col = ourSquare.x - backgroundPositionX; col < ourSquare.x - backgroundPositionX + ourSquare.size; col++)
      {
        if(image.getRGB(col, row) == 0xffec1c24)
        {
          DeathBoolean = true;
          //sSystem.out.println("YOU DIED!");
          //System.exit(0);
        }
        if(image.getRGB(col, row) == 0xff00a8f3)
        {
          backgroundSpeed = 12; 
        }
        if(image.getRGB(col, row) == 0xff0ed145)
        {
          backgroundSpeed = 16; 
        }
        if(image.getRGB(col, row) == 0xffff7f27)
        {
          backgroundSpeed = 8 ;   
        }
        
        if(image.getRGB(col, row) == 0xffb83dba)      
        { 
          gravity = -11/8;
        }
        if(image.getRGB(col, row) == 0xff3f48cc)
        {
          gravity = 11/8;
        }
        if(image.getRGB(col, row) == 0xfffff200) 
        {
          onYellow = true;
        }
        else
        {
          onYellow = false; 
        }

      }
    }
      if(ourSquare.y + ourSquare.size >= floor)
      {
        ourSquare.y = floor - ourSquare.size;
        ourSquare.ySpeed = 1;
      }

      if(ourSquare.y + ourSquare.size <= ceiling)
      {
        ourSquare.y = ceiling - ourSquare.size;
        ourSquare.ySpeed = 1;
        gravity = -11/8;
      }
      if (ourSquare.y == 0)
      {
        gravity = 11/8;
      }
      
      if (!DeathBoolean) {
        moveBackground();

      }

      repaint();
 
    }
  }
  
  /* Required methods for KeyListener.
     (These are unchanged from EvenBetterKeyboardExample, which is part of why
      we set it up in this way!)
   */
  public void keyPressed(KeyEvent e)
  {
    int code = e.getKeyCode();
      if(ourSquare.y >= 860 || ourSquare.y == 100)
    {
      jump = true;
      negativeJump = true;
    }

    else if(ourSquare.y < 860 || ourSquare.y > 100)
    {
      jump = false;
      negativeJump = false;
    } 

    if(code == KeyEvent.VK_SPACE && jump == true && ourSquare.y == 865 || onYellow == true)
    {
      ourSquare.applyAccelerationY(-jumpSpeed);
    }
    
    
    if(code == KeyEvent.VK_SPACE && negativeJump == true && ourSquare.y == 100)  
    {
      ourSquare.applyAccelerationY(+jumpSpeed);
    }
    
    if(onYellow = false)
    {
      jump = false;
      negativeJump = false; 
    }

  }
  public void keyReleased(KeyEvent e)
  {
    int code = e.getKeyCode();
  
    System.out.println(jump);
    System.out.println(ourSquare.y);
    System.out.println(buttonPressed);
  }
    
  public void keyTyped(KeyEvent e)
  {
  }

  public void mousePressed(MouseEvent e)
  {
    // getButton tells us which button was clicked.
    int button = e.getButton();
    
    // BUTTON1 = left click
    if(button == MouseEvent.BUTTON1)
    {
      int x = e.getX( );
      int y = e.getY();

      if (x > 850 && x < rewind.getWidth() + 850 && y > 425 && y < rewind.getHeight() + 425)
      {
        System.out.println("Button was clicked!");
      buttonPressed = true;
      }
    }
    else
    {
      buttonPressed = false;
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



  public static BufferedImage readImage(String infile)
  {
    try
    {
      BufferedImage ret = ImageIO.read(new File(infile));
      return ret;
    }
    catch(Exception e){System.out.println(e.getMessage()); return null;}
  }
}