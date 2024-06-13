import java.util.Formatter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Welcome {
     
    public static void welcomeMessage(){
        System.out.println("**********************");
        System.out.println("Welcome to Battleship!");
        System.out.println("**********************");
        
    }

    public static void welcomePlayer(String name){
        System.out.println("Welcome " + name + "! Please enter the first cells' coordinate of the given sized ships in the next few steps!\nNote that horizontal means left to right, while vertical means up to bottom directions!\nThe row number must to be a number between 1-9 and the column's letter must to be a capital letter between A-K");
    }
   
}
