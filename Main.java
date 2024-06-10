import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        welcome.welcomeMessage();
        Player.showEmptyTable();
        ArrayList<Player> players= new ArrayList<Player>();// ArrayList for save the players
        for(int i = 1; i <= 2; i++) {
             //Get the name of Player
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is the name of the Player "+ i + " :" );
            String player_Name = scanner.nextLine();


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

            Player player = new Player(player_Name, ships_player);
            player.inicializeValues();
            player.showTable();
            players.add(player);
        }
    }

}
