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
                    //For check the surrounding of ships  
                    case 3:
                        System.out.print("3 ");                        
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
                int col = ship.getStart_y();
                int row = ship.getStart_x();
                int length = ship.getSize();
                int end = row+length;

                for(int k = row; k < end; k++){
                    this.board[k][col] = 1;

                    //Setting the values of surrounding cells to 3
                    //Left
                    if(col > 0){
                        this.board[k][col-1] = 3;
                    }
                    //Right
                    if(col < 10){
                        this.board[k][col+1] = 3;
                    }
                }

                //Setting the values of surrounding cells to 3
                //Top
                if((row-1) > -1){
                    this.board[row-1][col] = 3;
                }

                //bottom
                if(end < 8){
                    this.board[end][col] = 3;
                }

            //Horizontal
            }else{
                int col = ship.getStart_y();
                int row = ship.getStart_x();
                int length = ship.getSize();
                int end = col+length;

                for(int k = col; k < end; k++){
                    this.board[row][k] = 1;

                    //Setting the values of surrounding cells to 3
                    //Top
                    if(row > 0){
                        this.board[row-1][k] = 3;
                    }
                    //Bottom
                    if(row < 10){
                        this.board[row+1][k] = 3;
                    }

                }

                //Setting the values of surrounding cells to 3
                //Left
                if((col-1) > 0){
                    this.board[row-1][col-1] = 3;
                }

                //Right
                if(end < 10){
                    this.board[row][end] = 3;
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
