import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        welcome.welcomeMessage();
        
        //For try
        List<Ship> ships_player1 = new ArrayList<Ship>();
        ships_player1.add(new Ship(2,0,0,"vertical"));
        ships_player1.add(new Ship(3,2,0,"vertical"));
        ships_player1.add(new Ship(3,6,5,"horizontal"));
        ships_player1.add(new Ship(4,9,3,"horizontal"));
        ships_player1.add(new Ship(5,1,4,"vertical"));

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
