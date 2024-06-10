import java.lang.reflect.Array;
import java.util.List;

public class Player {
    private String name;
    private int[][] board;
    private List<Ship> ships; //We need to Create ship objects before construct a Player
    
    //Constructor
    public Player(String name, List<Ship> ships){
        this.name = name;
        this.board = new int[9][11];
        this.ships = ships;

        for(int i = 0; i<board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            } 
        }
    }
    
    
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
 

    //methods
    public void showTable(){
        System.out.print("  ");
        for(char column = 'A'; column <='K'; column++ ){
            System.out.print(column + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    //Hit
                    case 2:
                        System.out.print("+ ");                        
                        break;
                    //Shot, but not hit
                    case -1:
                        System.out.print("X ");                        
                        break; 
                    //For check the position of ships  
                    case 1:
                        System.out.print("1 ");                        
                        break;
                    //Value of matrix is 0 - no action, 1 - ship is there, 3 - the surrounding cells of the ship                     
                    default:
                        System.out.print("- ");                        
                        break;
                }
            }
            System.out.println();
        }
    }

    public void inicializeValues(){
        for(Ship ship : this.ships){
            if(ship.getOrientation() == 'v'){
                int i = ship.getStart_y();
                for(int j = ship.getStart_x(); j < ship.getSize(); j++){
                    this.board[j][i] = 1;
                }
            }else{
                int i = ship.getStart_x();
                for(int j = ship.getStart_y(); j < ship.getSize(); j++){
                    this.board[i][j] = 1;
                }
            }
            
        }
    }
    public static void showEmptyTable(){
        char [][] board = new char[9][11];
        System.out.print("   ");
        for(char column = 'A'; column <='K'; column++ ){
            System.out.print(column + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " -");
            }
            System.out.println();
        }
    }

}
