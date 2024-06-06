import java.lang.reflect.Array;
import java.util.List;

public class Player {
    private String name;
    private char[][] board;
    private List<Ship> ships; //We need to Create ship objects before construct a Player
    
    //Constructor
    public Player(String name, List<Ship> ships){
        this.name = name;
        this.board = new char[9][11];
        this.ships = ships;
    }
    
    
    //Getters and setters
 


    //methods
    public void showTable(){
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
        }
    }

}
