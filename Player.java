import java.lang.reflect.Array;
import java.util.List;

public class Player {
    private String name;
    private int[][] board;
    private List<Ship> ships; //We need to Create ship objects before construct a Player
    private int hits;
    
    //Constructor
    public Player(String name, List<Ship> ships, int hits) {
        this.name = name;
        this.board = new int[9][11];
        this.ships = ships;
        this.hits = hits;

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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
 

    //methods

    //Add a ship
    public void addShip(Ship ship){
        this.ships.add(ship);
    }

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
                        System.out.print("- ");                        
                        break;
                    //For check the surrounding of ships  
                    case 3:
                        System.out.print("- ");                        
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

    public boolean inicializeValues(int length, int row, int col, char orient){
        boolean placeable = false;
            if(orient == 'v'){
                int end = row+length;

                for(int k = row; k < end; k++){
                    if(this.board[k][col] != 3){ //Checking the neighbors
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

                        placeable = true;
                    }else{
                        return placeable;
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
                int end = col+length;

                for(int k = col; k < end; k++){
                    if(this.board[row][k] != 3){ //Checking the neighbors
                        this.board[row][k] = 1;

                        //Setting the values of surrounding cells to 3
                        //Top
                        if(row > 0){
                            this.board[row-1][k] = 3;
                        }
                        //Bottom
                        if(row < 8){
                            this.board[row+1][k] = 3;
                        }

                        placeable = true;
                    }else{
                        return false;
                    }
                }

                //Setting the values of surrounding cells to 3
                //Left
                if((col-1) > 0){
                    this.board[row][col-1] = 3;
                }

                //Right
                if(end < 10){
                    this.board[row][end] = 3;
                }
            }
            
            return placeable;            
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

    public static boolean isMissed(int [][] enemyboard, int shotRow, int validatedShotCol){
        
        if (enemyboard[shotRow][validatedShotCol] == 0 || enemyboard[shotRow][validatedShotCol] == 3){
            enemyboard[shotRow][validatedShotCol] = -1;
            System.out.println("You missed!");
            //enemy.setBoard(enemyboard);
            return true;
        }
        return false;
    }

    public static boolean isHitted(int [][] enemyboard, int shotRow, int validatedShotCol, List<Ship> enemyShips){
        
        if(enemyboard[shotRow][validatedShotCol] == 1){
            enemyboard[shotRow][validatedShotCol] = 2;

            // check the ship's other cells, sunk or not!
            for(Ship ship : enemyShips){
                
                if((ship.getStart_x() == shotRow) && (ship.getStart_y() == validatedShotCol)){ // check the cell is a start point for any ship or not
                    // only one ship could start at that point, so that is shooted
                    ship.shotIncrement(ship);
                    ship.itSunked(ship.getShot(), ship.getSize());
                } else {                     

                    if(validatedShotCol == 0){// it is the left side of the board , so we have to check only the first column in right
                        if((enemyboard[shotRow][validatedShotCol+1] == 1)){//the ship is horizontal 
                            if((ship.getStart_x() == shotRow) && (ship.getStart_y() < validatedShotCol) && ((validatedShotCol-ship.getStart_y()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }else {//the ship is vertical
                            if((ship.getStart_y() == validatedShotCol) && (ship.getStart_x() < shotRow) && ((shotRow-ship.getStart_x()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        } 
                    }
                    else if(validatedShotCol == 11){//it is the last column on the board, so we have to check only the first column in left
                        if((enemyboard[shotRow][validatedShotCol-1] == 1)){//the ship is horizontal 
                            if((ship.getStart_x() == shotRow) && (ship.getStart_y() < validatedShotCol) && ((validatedShotCol-ship.getStart_y()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }else {//the ship is vertical
                            if((ship.getStart_y() == validatedShotCol) && (ship.getStart_x() < shotRow) && ((shotRow-ship.getStart_x()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }

                    }
                    else if(shotRow == 0){//it is the first row on the table, so we have to check only the first row in down
                        if((enemyboard[shotRow+1][validatedShotCol] == 1)){//the ship is vertical
                            if((ship.getStart_y() == validatedShotCol) && (ship.getStart_x() < shotRow) && ((shotRow-ship.getStart_x()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }

                        } else {//the ship is horizontal
                            if((ship.getStart_x() == shotRow) && (ship.getStart_y() < validatedShotCol) && ((validatedShotCol-ship.getStart_y()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }
                    }

                    else if(shotRow == 8){//it is the last row on the table, so we have to check only the last but one row in up

                        if((enemyboard[shotRow-1][validatedShotCol] == 1)){//the ship is vertical
                            if((ship.getStart_y() == validatedShotCol) && (ship.getStart_x() < shotRow) && ((shotRow-ship.getStart_x()) < ship.getSize())) {
                               // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }

                        } else {//the ship is horizontal
                            if((ship.getStart_x() == shotRow) && (ship.getStart_y() < validatedShotCol) && ((validatedShotCol-ship.getStart_y()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }
                    }
                    else if ((enemyboard[shotRow][validatedShotCol-1] == 1) || (enemyboard[shotRow][validatedShotCol+1] == 1) ){ // if is not a start point for any ship and the shot is not at the corners, we have to check the next cells, firstly horizontally

                        if((ship.getStart_x() == shotRow) && (ship.getStart_y() < validatedShotCol) && ((validatedShotCol-ship.getStart_y()) < ship.getSize())) {
                            // only one ship could start at that point horizontally, so that is shooted
                            ship.shotIncrement(ship);
                            ship.itSunked(ship.getShot(), ship.getSize());
                        }
                    } else { //enemy ship is vertical

                        if((ship.getStart_y() == validatedShotCol) && (ship.getStart_x() < shotRow) && ((shotRow-ship.getStart_x()) < ship.getSize())) {
                            // only one ship could start at that point vertically, so that is shooted
                            ship.shotIncrement(ship);
                            ship.itSunked(ship.getShot(), ship.getSize());
                        }
                    }
                }
            }

            return true;
        }
        return false;
    }
    public static void hitIncrement(Player shooterPlayer){
        shooterPlayer.setHits(shooterPlayer.getHits()+1); 
        int player_hits = shooterPlayer.getHits();
        System.out.println("The number of your hits :" + player_hits);
        
    }
    public static boolean hasWinner(Player shooterPlayer){
        int player_hits = shooterPlayer.getHits();
        if(player_hits >= 17){
            System.out.println("Congratulations! You won!");
                return true;    
            }
        return false;
    }
    public static Player createPlayer(String PlayerName, List<Ship> ships_player, int  hits){
        Player player = new Player(PlayerName, ships_player,0);
        return player;
    }
}
