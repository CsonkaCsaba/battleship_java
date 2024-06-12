import java.util.ArrayList;
import java.util.Arrays;
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
            
            System.out.println("What is the name of the Player "+ i + " :" );
            String player_Name = scanner.next();

            //Placement of ships
            System.out.println("Welcome " + player_Name + "! Please enter the first cells' coordinate of the given sized ships in the next few steps!\nNote that horizontal means left to right, while vertical means up to bottom directions!");
            int[] shipsToPlace = {2,3,3,4,5};
            List<Ship> ships_player = new ArrayList<Ship>();

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
                            row = Integer.parseInt(scanner.nextLine()); //try-catch!!
                            valid = true;
                            } catch (NumberFormatException e) {
                                System.out.println();
                                System.out.println("Please enter a number between 1 and 9");
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
                                valid = false; //break the do-while for orientation
                            } else {
                                valid = true;
                            }   
                        } else {
                            System.out.println("Please type 'h' if you want the ship horizontal or type 'v' if you want it to vertical");
                            valid = false;
                        }
                    } while (!valid);

                    row--; //Because of the index starts with 0
                    //System.out.println(shipsToPlace[k] + " " + row + " " + col + " " + orient);                  
                }

                //Create an instance of Ship class
                ships_player.add(new Ship(shipsToPlace[k],row,col,orient));

                System.out.println("*******************");
                System.out.println("The ship is placed!");
                System.out.println("*******************");
            }

            Player player = new Player(player_Name, ships_player,0);
            players.add(player);
            player.inicializeValues();
            player.showTable();
            
        }

        //Shots
        boolean hasWinner = false;
        int shotRow = 0;
        int shotCol = 0;
        boolean validCoordinate = false;
        boolean validShot = false;
        int player_hits = 0;
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
                System.out.println(shooterPlayer.getName() + ", it's your turn!");
                do{//until the shot is valid
                    System.out.println("Please enter the number of the row you want to shoot!(1-9): ");
                    do {//until the ROW coordinates are valid
                        try{ 
                            shotRow = Integer.parseInt(scanner.next());
                                if(shotRow <= 0 || shotRow >= 10){
                                    System.out.println("The number is not valid! You need to enter a number between 1 and 9");
                                } else {
                                    validCoordinate = true;
                                }
                            } catch (NumberFormatException e ) {
                                System.out.println();
                                System.out.println("The input is not valid! Please enter a number between 1 and 9");
                            }
                        } while(!validCoordinate);
                    
                        System.out.println("Please enter the letter belongs to the column you want to shoot: (A-K)");
                    do { //until the COLUMN coordinates are valid
                        char shotColFromUser = scanner.next().charAt(0); //try-catch!! Need to convert char->int
                            if (Character.isDigit(shotCol)){
                                System.out.println("Error: please do not input a number");
                                validCoordinate = false;
                            } else {  
                                char shotColToLower = Character.toLowerCase(shotColFromUser);
                                if(shotColToLower <='k'){
                                    shotCol = shotColToLower - 'a';//char to int
                                    validCoordinate = true;
                                } else {
                                    System.out.println("Please enter a letter between A-K");
                                    validCoordinate = false;
                                }
                            }
                        } while(!validCoordinate);

                        shotRow--; //Because of the index starts with 0
                        //check the coordinates on the board of the enemy
                        if (enemy.getBoard()[shotRow][shotCol] == 0){
                            System.out.println("You missed!");
                            validShot = true;

                        } else if(enemy.getBoard()[shotRow][shotCol] == 1){
                            validShot = true;

                            // check the ship is has more cells or not, sunk or not!!!!!!!!!!!!!!!!!!
                            List<Ship> enemyShips = enemy.getShips();
                            for(Ship ship : enemyShips){
                                ship.getOrientation();
                                ship.getShot();
                                ship.getSize();
                                ship.getStart_x();
                                ship.getStart_y();
                                
                                if(ship.getStart_x() == (shotRow) && ship.getStart_y() == (shotCol)){
                                    ship.setShot(ship.getShot()+1);
                                    if(ship.getShot() == ship.getSize()){
                                        System.out.println("Ths ship is sunked!");
                                    } else {
                                        System.out.println("You hit!");
                                    }
                                //} else(){ 

                                };

                            }

                        //System.out.println("You hit!");

                        shooterPlayer.setHits(shooterPlayer.getHits()+1); 
                        player_hits = shooterPlayer.getHits();
                        System.out.println("The number of your hits :" + player_hits);
                        if(player_hits >= 17){
                            hasWinner = true;
                            System.out.println("Congratulations! You won!");
                            break;
                        }

                    } else {//players.get(i).getBoard()[shotRow-1][shotCol] = -2;
                        System.out.println("You have already shot there! Try again!");
                        validShot = false;
                    }
                } while(!validShot);
                enemy.showTable();
            } 
        } while(!hasWinner);
        scanner.close();
        
            // //For testing
            // List<Ship> ships_player = new ArrayList<Ship>();
            // ships_player.add(new Ship(2,4,7,'v'));
            // ships_player.add(new Ship(3,6,0,'v'));
            // ships_player.add(new Ship(3,1,4,'v'));
            // ships_player.add(new Ship(4,7,2,'h'));
            // ships_player.add(new Ship(5,0,10,'v'));
            // // ships_player.add(new Ship(2,0,0,'v'));
            // Player player = new Player("Timi", ships_player);
            // player.inicializeValues();
            // player.showTable();
    // }

    }
}
