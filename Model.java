
import java.util.List;
import java.util.ArrayList;
public class Model {

    private int size;

    private Player actualPlayer;

    public Player[][] table;
    public List<Pair> playerOPositions;
    public List<Pair> playerXPositions;

    public Model(int size) {
        this.size = size;
        actualPlayer = Player.X;
        playerXPositions = new ArrayList<>();
        playerOPositions = new ArrayList<>();
        table = new Player[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }
    /**
     * Count the number of adjecent cells from up that has the curren player on them
     *@param row row
     *@param column column
     *@return count: the number of adjecent cells from up that has the curren player on them
     */
    private int numOfAdjacentFromUp(int row,int column){
        int cnt = 0;
        row--;
        while(row >= 0 && actualPlayer == table[row][column]){
            System.out.println(actualPlayer.name() );
            System.out.println(table[row][column].name() );
            row--;
            cnt++;
        }
        System.out.println("numOfAdjacentFromUp" +  cnt);
        return cnt;
    }
     /**
      *Count the number of adjecent cells from right that has the curren player on them
     *@param row row
     *@param column column
     *@return count: the number of adjecent cells from right that has the curren player on them
     */
    private int numOfAdjacentFromRight(int row,int column){
        int cnt = 0 ;
        column++; 
        while(column < size && actualPlayer == table[row][column]){
            column++; 
            cnt++;
        }
        System.out.println("numOfAdjacentFromRight :" + cnt);
        return cnt;
    }
    /**
     * Count the number of adjecent cells from left that has the curren player on them
     *@param row row
     *@param column column
     *@return count: the number of adjecent cells from left that has the curren player on them
     */
    private int numOfAdjacentFromLeft(int row,int column){
        int cnt = 0 ;
        column--; 
        while(column >= 0 && actualPlayer == table[row][column]){
            System.out.println( actualPlayer == table[row][column]);
            column--; 
            cnt++;
        }
        System.out.println("numOfAdjacentFromLeft" + cnt);
        return cnt;
    }
    /**
     * Count the number of adjecent cells from buttom that has the curren player on them
     *@param row row
     *@param column column
     *@return count: the number of adjecent cells from buttom that has the curren player on them
     */
    private int numOfAdjacentFromButtom(int row,int column){
        System.out.println("numOfAdjacentFromButtom");
        int cnt = 0;
        row++;
        while(row < size && actualPlayer == table[row][column]){
            row++;
            cnt++;
        }
        System.out.println("numOfAdjacentFromButtom" + cnt);
        return cnt;
    }
    /**
     * Find the maximum of  adjacent cells diagonally that has the same player as the one in the current position
     *@param row row
     *@param column column
     *@return max: the maximum number of adjacent cells diagonally that has the same player as the one in the current position
     */
    private int numOfAdjacentFromDiagonal(int row,int column){

        System.out.println("actualPlayer : " + actualPlayer );
        System.out.println("table[row][column] : " +table[row][column] );
        int maxCnt = 0;
        int cnt = 0;
        int tempi = row - 1 ;
        int tempj = column + 1 ;
        // upper right digonally
        while(tempi >= 0 && tempj < size && actualPlayer == table[tempi][tempj]){
            System.out.println("Entered");
            cnt++;
            tempi--;
            tempj++;
        }

        tempi = row + 1;
        tempj = column - 1;
        while(tempi < size && tempj >= 0  && actualPlayer == table[tempi][tempj]){
            System.out.println("Entered");
            cnt++;
            tempi++;
            tempj--;
        }
        if(cnt > maxCnt) maxCnt = cnt;

        // upper left digonally
        cnt = 0;
        tempi = row - 1;
        tempj = column - 1;
        while(tempi >= 0 && tempj >= 0 && actualPlayer == table[tempi][tempj]){
            System.out.println("Entered");
            cnt++;
            tempi--;
            tempj--;
        }
        tempi = row + 1;
        tempj = column + 1;
        while(tempi < size && tempj < size && actualPlayer == table[tempi][tempj]){
            System.out.println("Entered");
            cnt++;
            tempi++;
            tempj++;
        }
        if(cnt > maxCnt) maxCnt = cnt;
        System.out.println("maxCnt" + maxCnt);
        return maxCnt;
    }
    /**
     * Collect the number of adjecent cells from up,right,left,buttom and maxDiagonal into an array and return it
     *@param row row
     *@param column column
     *@return Array[5]: num of adjecent signs [up,right,left,buttom,maxDiagonal]
     */
    private int[] numOfAdjecentSigns(int row,int column){
        int[] adjacentSigns = new int[]{0,0,0,0,0};// up,right,left,buttom,maxDiagonal
        adjacentSigns[0] = numOfAdjacentFromUp(row , column);
        adjacentSigns[1] = numOfAdjacentFromRight(row , column);
        adjacentSigns[2] = numOfAdjacentFromLeft(row , column);
        adjacentSigns[3] = numOfAdjacentFromButtom(row , column);
        adjacentSigns[4] = numOfAdjacentFromDiagonal(row , column);
        return adjacentSigns ;
    }
    /**
     * remove random signs of the current player from the table according to the given number
     *@param numOfSigns number of signs
     */
    private void removeSignsRandomly(int numberOfSigns){
        int randomInt = 0 ; 
        if(actualPlayer == Player.X){
            if(numberOfSigns == 1){
                randomInt = (int) (Math.random()*(playerXPositions.size()));
                table[playerXPositions.get(randomInt).x] [playerXPositions.get(randomInt).y] = Player.NOBODY;
                playerXPositions.remove(randomInt);
            }else{
                randomInt = (int) (Math.random()*(playerXPositions.size()));
                table[playerXPositions.get(randomInt).x] [playerXPositions.get(randomInt).y] = Player.NOBODY;
                playerXPositions.remove(randomInt);
                randomInt = (int) (Math.random()*(playerXPositions.size()));
                table[playerXPositions.get(randomInt).x] [playerXPositions.get(randomInt).y] = Player.NOBODY;
                playerXPositions.remove(randomInt);
            }
        }else{
            if(numberOfSigns == 1){
                randomInt = (int) (Math.random()*(playerOPositions.size()));
                table[playerOPositions.get(randomInt).x] [playerOPositions.get(randomInt).y] = Player.NOBODY;
                playerOPositions.remove(randomInt);
            }else{
                randomInt = (int) (Math.random()*(playerOPositions.size()));
                table[playerOPositions.get(randomInt).x] [playerOPositions.get(randomInt).y] = Player.NOBODY;
                playerOPositions.remove(randomInt);
                randomInt = (int) (Math.random()*(playerOPositions.size()));
                table[playerOPositions.get(randomInt).x] [playerOPositions.get(randomInt).y] = Player.NOBODY;
                playerOPositions.remove(randomInt);
            }
        }
    }

    /**
     * If the table contains a player in the given coordinates 
     * then this funciton will not do anything, just returning 
     * the player at that position.
     * Else:
     * 1- Insert the actual player to the table at the given position.
     * 2- Add the current player's position to the correspoinding player's positions array.
     * 3- Remove random signs according to the rules of the game if the conditions are met.
     *@param row row
     *@param column column
     *@return Player: The player in table[row][column]
     */
    public Player step(int row, int column) {
        if (table[row][column] != Player.NOBODY){
            return table[row][column];
        }
        System.out.println(table[row][column]);
        table[row][column] = actualPlayer;
        int[] adjacentSigns =  numOfAdjecentSigns(row, column);
        if (actualPlayer == Player.X) {
            playerXPositions.add(new Pair(row, column));
        } else {
            playerOPositions.add(new Pair(row, column));
        }
        for( int i : adjacentSigns){
            System.out.println(i);
        }

        if(adjacentSigns[0] + adjacentSigns[3] == 2 || adjacentSigns[1] + adjacentSigns[2] == 2 || adjacentSigns[4]  == 2   ){
                    removeSignsRandomly(1);
        }
        if(adjacentSigns[0] + adjacentSigns[3] == 3 || adjacentSigns[1] + adjacentSigns[2] == 3 || adjacentSigns[4]  == 3   ){
                    removeSignsRandomly(2);
        }

        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }
        System.out.println(playerOPositions);
        System.out.println(playerXPositions);
        return table[row][column];
    }

    /**
     *Find the winner
     *@param numOfSigns number of signs
     *@return Player: the winner
     */
    public Player findWinner(int i, int j){

        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }

        int[] adjacentSigns =  numOfAdjecentSigns(i,j);
        if(adjacentSigns[0] + adjacentSigns[3] == 4 || adjacentSigns[1] + adjacentSigns[2] == 4 || adjacentSigns[4]  == 4){
            return table[i][j];
        }
        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }
        

        return Player.NOBODY;
    }
    
    /**
     *Get the current player
     *@return Player: the actual player
     */
    public Player getActualPlayer() {
        return actualPlayer;
    }

}
