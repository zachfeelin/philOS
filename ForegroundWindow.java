import javax.swing.JFrame;

public class ForegroundWindow extends JFrame {
    public ForegroundWindow() {
        // Create the foreground window
        super("Foreground Window");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        toFront();
    }
}