
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;
public class MainWindow extends BaseWindow {
    private List<Window> gameWindows = new ArrayList<>();
    public MainWindow(){
        super(800,800);
        JButton small = new JButton();
        small.setText("6   x   6");
        small.setBounds(50,100,95,30);
        small.addActionListener(getActionListener(6));

        small.setPreferredSize(new Dimension(100,100));
        small.setBackground(Color.GREEN);
        small.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createLineBorder(Color.CYAN, 5),
                       BorderFactory.createLineBorder(Color.BLACK, 20)));
        
        
        JButton medium = new JButton();
        medium.setText("10 x 10");
        medium.addActionListener(getActionListener(10));
        medium.setPreferredSize(new Dimension(100,100));
        medium.setBackground(Color.GREEN);
        medium.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createLineBorder(Color.CYAN, 5),
                       BorderFactory.createLineBorder(Color.BLACK, 20)));
        


        JButton large = new JButton();
        large.setText("14 x 14");
        large.addActionListener(getActionListener(14));
        large.setPreferredSize(new Dimension(100,100));
        large.setBackground(Color.GREEN);
        large.setBorder(BorderFactory.createCompoundBorder(
                       BorderFactory.createLineBorder(Color.CYAN, 5),
                       BorderFactory.createLineBorder(Color.BLACK, 20)));
        
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }
    /**
     * Creating an ActionListener and override it's actionPerformed method  
     *@param size size of the matrix 
     *@return ActionListener
     */
    private ActionListener getActionListener(final int size) {
        return new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(800,800,size, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
        };
    }
    /**
     * Creating an ActionListener and override it's actionPerformed method  
     *@return gameWindows: List contains the game windows
     */
    public List<Window> getWindowList() {
        return gameWindows;
    }
    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}
