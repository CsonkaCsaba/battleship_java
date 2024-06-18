import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Welcome.welcomeMessage();
        Player.showEmptyTable();

        ArrayList<Player> players= new ArrayList<Player>();// ArrayList for save the players
        for(int i = 1; i <= 2; i++) {

             //Get the name of Player
            ConsoleInput.getNameOfThePlayers(i);
            String player_Name = scanner.next();

            //Placement of ships
            Welcome.welcomePlayer(player_Name);
            int[] shipsToPlace = {2,3,3,4,5};
            List<Ship> ships_player = new ArrayList<Ship>();

            Player player = new Player(player_Name, ships_player,0);


            for(int k = 0; k < shipsToPlace.length; k++){
                boolean getShip = true;
                boolean valid = false;
                int row = 0;
                char orient = ' ';
                int col = 0;

                while(getShip){

                    //Get the row number of the first cell
                    System.out.println("Please enter the row number of the first cell of the " + shipsToPlace[k] + " long ship (1-9): ");
                    do {
                        try{
                            row = Integer.parseInt(scanner.next()); //try-catch!!
                            valid = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a number between 1 and 9!");
                                valid = false;
                            }
                    } while(!valid);

                    //Get the column of the first cell
                    System.out.println("Please enter the letter belongs to the column of the first cell of the ship: (A-K)");
                    do {
                        char colFromUser = scanner.next().charAt(0); //try-catch!! Need to convert char->int
                        if (Character.isDigit(colFromUser)){
                            System.out.println("Error: please do not input a number");
                            valid = false;
                        }else {  
                            char colToLower = Character.toLowerCase(colFromUser);
                            if(colToLower <='k'){
                                col = colToLower - 'a';//char to int
                                valid = true;
                            } else {
                                System.out.println("Please enter a letter between A-K");
                                valid = false;
                            }
                        }
                    } while (!valid);
                    
                    //Get the orientation
                    do {
                        System.out.println("Please choose the orientation of the ship: enter h if horizontal and v if vertical!"); //try-catch!!
                        orient = scanner.next().charAt(0);
                        getShip = false; //end of while cycle
                        //check if orientation input is correct
                        if(orient == 'h' || orient == 'v'){
                            //vertical and horizontal check
                            if((orient == 'v' && (row+shipsToPlace[k]-1 > 9)) || orient == 'h' && (col+shipsToPlace[k]-1 > 10)){ //for example: 3 long ship to row 8 can't be placed
                                System.out.println("The ship can't be placed there, the length of the ship is more than the length of the board! Please enter the coordinates again!");
                                getShip = true; //need to get the coordinate again
                                valid = true; //break the do-while for orientation
                            } else {
                                valid = true;
                            }   
                        } else {
                            System.out.println("Please type 'h' if you want the ship horizontal or type 'v' if you want it to vertical");
                            valid = false;
                        }
                    } while (!valid);

                    row--; //Because of the index starts with 0
                                   
                    //Set the values on the player's board + check if it contacts another one
                    boolean canBePlaced = player.inicializeValues(shipsToPlace[k],row,col,orient);

                    //If the ship can be placed (doesn't contact another)
                    if(canBePlaced){
                        Ship newShip = new Ship(shipsToPlace[k],row,col,orient);
                        player.addShip(newShip);
    
                        System.out.println("*******************");
                        System.out.println("The ship is placed!");
                        System.out.println("*******************");
                    }else{
                        System.out.println("Unfortunately, the ship cannot be placed there, because it is in contact with another one. Please enter the coordinates again!");
                        getShip = true;
                    }
                }

            }

            players.add(player);
            //player.showTable();
            
        }

        //Shots
        boolean hasWinner = false;
        boolean validShot = false;
        Player shooterPlayer = null;
        Player enemy = null;

        do {// until we have a winner
            for(int i = 0; i <= 2; i++){
                if(i == 2){
                    i = 0;
                }
                switch (i) {
                    case 0:
                        shooterPlayer = players.get(0);
                        enemy = players.get(1);
                        break;
                    case 1:
                        shooterPlayer = players.get(1);
                        enemy = players.get(0);
                        break;
                    default:
                        break;
                }
                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);

                do{//until the shot is valid

                    ConsoleOutput.getRowNumber();
                    int shotRow = ConsoleInput.getRowNumber();
                    ConsoleInput.inputRowValidation(shotRow);

                    ConsoleOutput.getColNumber();
                    char shotCol = ConsoleInput.getColChar();
                    int validatedShotCol = ConsoleInput.inputColValidation(shotCol);

                        //check the coordinates on the board of the enemy
                    int [][] enemyboard = enemy.getBoard();
                    List<Ship> enemyShips = enemy.getShips();
                    boolean missed = Player.isMissed(enemyboard, shotRow, validatedShotCol);
                    boolean hitted = Player.isHitted(enemyboard, shotRow, validatedShotCol, enemyShips);

                    if (missed){
                        validShot = true;
                    } else if (hitted) {
                        Player.hitIncrement(shooterPlayer);
                        hasWinner = Player.hasWinner(shooterPlayer);
                        validShot = true;
                    }else {// the cell is 2 or -1 ;
                        System.out.println("You have already shot there! Try again!");
                        validShot = false;
                    }

                } while(!validShot);
                ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
                ConsoleInput.pressAnyToContinue();
            } 
        } while(!hasWinner);
        scanner.close();  
    }
}
