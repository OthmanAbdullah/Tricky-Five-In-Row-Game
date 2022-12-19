
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class BaseWindow extends JFrame {
    public BaseWindow(){}

    /**
     * Create the base window with the given width and height
     * @param width The width of the window
     * @param height The width of the window
     */
    public BaseWindow(final int width, final int height) {
        setTitle("Tricky five-in-a-row");
        setSize(width, height);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
        URL url = Window.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        getContentPane().setBackground(Color.DARK_GRAY);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }
    /**
     * Create a JOtion with yes and no options used for the confirmation
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    /**
     *  Clear up all the related allocated objects before exiting from the window
     */
    protected void doUponExit() {
        this.dispose();
    }
}
