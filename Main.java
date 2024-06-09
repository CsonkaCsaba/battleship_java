import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        welcome.welcomeMessage();
        
        //Get the name of Player1
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the first Player?");
        String player1Name = scanner.nextLine();


        //Place ships
        System.out.println("Welcome " + player1Name + "! Please enter the first cells' coordinate of the given sized ships in the next few steps! \n Note that horizontal means left to right, while vertical means up to bottom directions!");
        int[] shipsToPlace = {2,3,3,4,5};
        List<Ship> ships_player1 = new ArrayList<Ship>();

        for(int k = 0; k < shipsToPlace.length; k++){
            boolean getShip = true;
            boolean valid = false;
            int row = 0;
            char orient;
            int colCharToInt;
            while(getShip){

                System.out.println("Please enter the row number of the first cell of the " + shipsToPlace[k] + " long ship (1-9): ");
                do {
                    try{
                        row = Integer.parseInt(scanner.nextLine()); //try-catch!!
                        valid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number between 1 and 9");
                        }
                } while(!valid);

                System.out.println("Please enter the letter belongs to the column of the first cell of the ship: (A-K)");
                do {
                    char col = scanner.next().charAt(0); //try-catch!! Need to convert char->int
                    char colToLower = Character.toLowerCase(col);
                    if(colToLower <='k'){
                        colCharToInt = colToLower - 'a' + 1;//char to int
                        valid = true;
                    } else {
                        System.out.println("Please enter a letter between A-K");
                        valid = false;
                    }
                } while (!valid);
                
                System.out.println("Please choose the orientation of the ship: enter h if horizontal and v if vertical!"); //try-catch!!
                do {
                    orient = scanner.next().charAt(0); //try-catch!!
                    if(orient == 'h' || orient == 'v'){
                         //vertical and horizontal check
                        if((orient == 'v' && (row+shipsToPlace[k]-1 > 9)) || orient == 'h' && (row+shipsToPlace[k]-1 > 11)){ //for example: 3 long ship to row 8 can't be placed
                        System.out.println("The ship can't be placed there, the length of the ship is more than the length of the board! Please enter the coordinates again!");
                        valid = false;
                        } else {
                            valid = true;
                        }   
                    } else {
                        System.out.println("Please type 'h' if you want the ship horizontal or type 'v' if you want it to vertical");
                        valid = false;
                    }
                } while (!valid);
                
                getShip = false;
            }
            System.out.println("The ship is placed!");
            System.out.println("*******************");
        }

        // ships_player1.add(new Ship(2,0,0,"vertical"));
        // ships_player1.add(new Ship(3,2,0,"vertical"));
        // ships_player1.add(new Ship(3,6,5,"horizontal"));
        // ships_player1.add(new Ship(4,9,3,"horizontal"));
        // ships_player1.add(new Ship(5,1,4,"vertical"));

        Player player1 = new Player("Csaba", ships_player1);
        player1.showTable();


        //Get the name of the 1st player
        //Get the start point and orientation of the ships -> store in an array
        //Create Player1 object with name and ships

        //Get the name of the 2nd player
        //Get the start point and orientation of the ships -> store in an array
        //Create Player2 object with name and ships
    }

}
