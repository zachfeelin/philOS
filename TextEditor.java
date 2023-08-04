/*import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
public class TextEditor extends JPanel implements KeyListener{
    Font font;
    
    public TextEditor() {
        
        font = new Font("Consolas", Font.PLAIN, 24);
        addKeyListener(this);
        setFocusable(true);
        myList.add("");
    }
    //String list = "";
    int Pos = 50;
    int place = 0; //cursor var
    int line = 0;

    ArrayList<String> myList = new ArrayList<String>(); //This creates an empty list, with nothing inside.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // We have to set the Graphics object to use our Font for it to show up
        // on the screen. You can use different fonts for different Strings in the
        // same image.
        g.setFont(font);
        g.setColor(new Color(0xb16725));
        for(int i=0; i < myList.size(); i++){
            g.drawString(myList.get(i), 50, (i*50 + 52));
        }
        g.drawString("|", (place*13)+43, Pos);
    }
    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        int code = e.getKeyCode();
        if(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
            if (place > 0) {
                String currentLine = myList.get(line);
                String newLine = currentLine.substring(0, place - 1) + currentLine.substring(place);
                myList.set(line, newLine);
                place = place - 1;
            }
        }
        else if(c == KeyEvent.VK_ENTER){}
        else{
            myList.set(line, myList.get(line).substring(0, place) + c +  myList.get(line).substring(place, myList.get(line).length()));
            place = place + 1;
            //list = list.substring(0, place) + c + list.substring(place, list.length());
        } 
        repaint();
    }
    public void keyReleased(KeyEvent e){
       
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT){
            if(place>= myList.get(line).length()){
                place = place;
            }
            else if(place<=myList.get(line).length()){
                place = place + 1;
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(place<= 0){
                place = place;
            }
            else if(place<=myList.get(line).length()){
                place = place - 1;
            }
        } 
        if(code == KeyEvent.VK_UP){
            if(line > 0){
                line = line - 1;
                Pos = Pos - 50;
                if(place > myList.get(line).length()){
                    place = myList.get(line).length();
                }
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(line < myList.size() - 1){
                line = line + 1;
                Pos = Pos + 50;
                if(place > myList.get(line).length()){
                    place = myList.get(line).length();
                }
            }
        }
        if(code == KeyEvent.VK_BACK_SPACE){
            if(place == 0 && line > 0){
                line = line - 1;
                Pos = Pos - 50;
                place = myList.get(line).length();
            }
        }
        if(code == KeyEvent.VK_ENTER){
        Pos = Pos + 50;
        line = line + 1;
        place = 0;
        myList.add("");
        }
        repaint();  
    }
}*/
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; //Imports filenotfoundexception in order to recognize it when it finds the error type
import java.io.PrintWriter;
public class TextEditor extends JPanel implements KeyListener{
    Font font;
    Button loadButton;
    Button saveButton;

    int lineE = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();

    public TextEditor() {
        font = new Font("Consolas", Font.PLAIN, 24);
        addKeyListener(this);
        setFocusable(true);
        myList.add("");
        System.out.println(width);
    }
    //String list = "";
    int Pos = 85;
    int place = 0; //cursor var
    int line = 0;
    int filenum = 1;
    String name = "File" + filenum;
    String rlname = name + ".txt";

    ArrayList<String> myList = new ArrayList<String>(); //This creates an empty list, with nothing inside.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //displaying the top rectangle
        g.setColor(new Color(0xFF69B4));
        g.fillRect(0, 0, width, 50);
        
        //displaying the load and save buttons
        g.setColor(new Color(0xFFFFFF));
        g.fillRect(20, 10, 70, 30);
        g.fillRect(100, 10, 70, 30);
        saveButton = new Button(20, 10, 70 + 20, 40 + 10);
        loadButton = new Button(100, 10, 70, 40);
        //making the text for the buttons
        g.setColor(new Color(0x000000));
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        g.drawString("Save", 32, 32);
        g.drawString("Load", 113, 32);
        font = new Font("Consolas", Font.PLAIN, 24);

        // We have to set the Graphics object to use our Font for it to show up
        // on the screen. You can use different fonts for different Strings in the
        // same image.
        g.setFont(font);
        g.setColor(new Color(0xb16725));
        for(int i=0; i < myList.size(); i++){
            g.drawString(myList.get(i), 50, (i*50 + 87));
        }
        g.drawString("|", (place*13)+43, Pos);
    }

    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT){
            if(place>= myList.get(line).length()){}
            else if(place<=myList.get(line).length()){
                place = place + 1;
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(place <= 0){}
            else if(place<=myList.get(line).length()){
                place = place - 1;
            }
        } 
        if(code == KeyEvent.VK_UP){
            if(line > 0){
                line = line - 1;
                Pos = Pos - 50;
                if(place > myList.get(line).length()){
                    place = myList.get(line).length();
                }
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(line < myList.size() - 1){
                line = line + 1;
                Pos = Pos + 50;
                if(place > myList.get(line).length()){
                    place = myList.get(line).length();
                }
            }
        }
        if(code == KeyEvent.VK_BACK_SPACE || code == KeyEvent.VK_DELETE){
            if(place == 0 && line > 0){
                line = line - 1;
                Pos = Pos - 50;
                place = myList.get(line).length();
                myList.remove("");
            }
        }
        if(code == KeyEvent.VK_ENTER){
            Pos = Pos + 50;
            line = line + 1;
            place = 0;
            myList.add("");
        }
        repaint();  
    }

    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        int code = e.getKeyCode();
        if(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE){
            if (place != 0) {
                String currentLine = myList.get(line);
                String newLine = currentLine.substring(0, place - 1) + currentLine.substring(place);
                myList.set(line, newLine);
                place = place - 1;
            }
        }
        else if(c == KeyEvent.VK_ENTER){}
        else{
            myList.set(line, myList.get(line).substring(0, place) + c +  myList.get(line).substring(place, myList.get(line).length()));
            place = place + 1;
            //list = list.substring(0, place) + c + list.substring(place, list.length());
        } 
        repaint();
    }
    public void keyReleased(KeyEvent e)
    {
    }

    public void mousePressed(MouseEvent e){
        int button = e.getButton();
        if(button == MouseEvent.BUTTON1){
            int x = e.getX();
            int y = e.getY();
            /*if(saveButton.clicked(x, y)){
                try {
  
                     // Get the file
                     File f = new File("fakefiles/" + rlname);
  
                     // Create new file
                     // if it does not exist
                     if (f.createNewFile()){            
                         System.out.println("File created");
                     }        
                     else{
                         System.out.println("File already exists");
                     }
                }      
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                File a = new File(rlname);
                try {
                    PrintWriter writer = new PrintWriter(a); // scanner for file f
                    writer.println(myList); //Writer prints to the file 
                    writer.close(); //The close method stops the output stream, and saves the changes
                }
                catch(FileNotFoundException t){
                t.printStackTrace(); //prints error message when we come across the error. The throwable class is the superclass of all errors in java. 
                //StackTrace is a method of this class, that allows you to print the throwable error along with its details, like line number, the class, and etc.
                }
                filenum = filenum + 1;

            }*/
            if(loadButton.clicked(x, y)){
                //AKHIL FILL LOAD HERE
            }
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