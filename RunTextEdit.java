import javax.swing.JFrame; //javax.swing package is all the graphics library for a pop-up window. JFrame handles the actual frame of the popup window.

public class RunTextEdit
{
  public static void main(String[] args)
  { 
    // Initialize the window
    JFrame frame = new JFrame("Text Editor");

    // Set the width and height of the window in pixels
    frame.setSize(800,600);
    
    // Initialize the drawing canvas (replace BGPExample with the name of any
    // class that extends JPanel)
    TextEditor panel = new TextEditor();
    
    // Add the drawing canvas to the window
    frame.add(panel);
    
    // Show the window when we are done with all of our initialization
    frame.setVisible(true);
    
    // Make it so the application exits when you close the window
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}