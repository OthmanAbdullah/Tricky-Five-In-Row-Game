import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.util.ArrayList;
import java.util.List;
public class Window extends BaseWindow {

    private final int size;
    private final Model model;
    private final JLabel label;
    private final MainWindow mainWindow;
    private List<CostumeButtons> buttons;
    public Window(int height,int width,int size, MainWindow mainWindow) {
        super(width,height);
        this.size = size;
        buttons = new ArrayList<>();
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);

        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
    /**
     * Update the gui grid according to the table
     *@param table the backend table
     */
    private void updateGrid(Player[][] table){
        for (CostumeButtons b : buttons){

            if(table[b.i][b.j].name() != "NOBODY"){
                b.button.setText(table[b.i][b.j].name());
            }else{
                b.button.setBackground(null);
                b.button.setText("");
            }
        }
    
    }
    /**
     *1- Adding a button to the panel
     *2- Adding an action listener to the newly created button
     *3- Adding the reference of the button and its coordinates into the buttons list

     *@param panel the panel
     *@param i the row number 
     *@param i the column number 
     */
    private void addButton(JPanel panel, final int i,
            final int j) {
        final JButton button = new JButton();

        button.addActionListener(e -> {
            Player newValue = model.step(i, j);
            button.setText(newValue.name());
            if(newValue.name() == "O" ){
                button.setBackground(Color.red);
            }else{
                button.setBackground(Color.GREEN);
            }
            
            updateLabelText();
            updateGrid(model.table);
            
            Player winner = model.findWinner(i,j);
            if (winner != Player.NOBODY) {
                showGameOverMessage(winner);
            }
        });
        buttons.add(new CostumeButtons(button,i,j));
        panel.add(button);
    }
    /**
    *  shows message dialoag with a message containing the onwer and autumatically starting a new game 
    *@param winner the winner of the game 
    */
    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Game is over. Winner: " + winner.name());
        newGame();
    }
    /**
     * Create a new game
     */
    private void newGame() {
        Window newWindow = new Window(800,800,size, mainWindow);
        // newWindow.setSize(500,500);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    /**
     *update the label's text by the actual player's name
    */
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
}
