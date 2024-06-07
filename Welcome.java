import java.util.Formatter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Welcome {
    public void welcomeMessage(){
        System.out.println("**********************");
        System.out.println("Welcome to Battleship!");
        System.out.println("**********************");
        
       
        Formatter emptyBoard = new Formatter();
        emptyBoard.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s\n", ' ','A','B','C','D','E','F','G','H','I','J','K');
        for(int i = 1; i <= 9; i++){
            emptyBoard.format("%9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s %9s\n", i,'-', '-','-','-','-','-','-','-','-','-','-');
        }
        System.out.println(emptyBoard);
        
        // System.out.println("First you need to enter the name of Player 1: ");
        // Scanner scanner = new Scanner(System.in);
        // String name_player1 = scanner.nextLine();
        // System.out.println("Enter the name of Player 2:");
        // String name_player2 = scanner.nextLine();
        // Player player1 = new Player();

    }
}
