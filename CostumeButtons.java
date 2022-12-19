import javax.swing.JButton;
public class CostumeButtons {
    public JButton button;
    public int i;int j;
    /**
     * Istantiate an object of CostumeButtons where this object will contain the button itself and it's coordinates in the grid
     *@param Button JButton 
     *@param i row number
     *@param j column number 
     */
    public CostumeButtons(JButton button,int i,int j){
        this.button = button ; 
        this.i = i ;
        this.j = j ;
    }
}
