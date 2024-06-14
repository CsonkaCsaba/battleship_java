import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ConsoleInput {
    Scanner scanner = new Scanner(System.in);
    
 

    public static void getNameOfThePlayers(int i){    
        System.out.println("What is the name of the Player "+ i + " :" );
    }

    // public static void getRowNumber(){    
    //         int shotRow = Integer.parseInt(scanner.next());
    //         scanner.next();
    //         shotRow--; //Because of the index starts with 0

    //    // return 
    // }

    public static void inputRowValidation(int shotRow){
        boolean validCoordinate = false;
        do {//until the ROW coordinates are valid          
            try{ 
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

    }
    public static void inputColValidation(char shotCol){
        boolean validCoordinate = false;
        do { //until the COLUMN coordinates are valid
                if (Character.isDigit(shotCol)){
                    System.out.println("Error: please do not input a number");
                    validCoordinate = false;
                } else {  
                    char shotColToLower = Character.toLowerCase(shotCol);
                    if(shotColToLower <='k'){
                        Map<Character,Integer> charMap = new HashMap <>(){{
                            put('a',1);
                            put('b',2);
                            put('c',3);
                            put('d',4);
                            put('e',5);
                            put('f',6);
                            put('g',7);
                            put('h',8);
                            put('i',9);
                            put('j',10);
                            put('k',11);
                    }};
                        int value = charMap.get(shotColToLower);
                        //shotCol = shotColToLower - 'a';//char to int
                        validCoordinate = true;
                    } else {
                        System.out.println("Please enter a letter between A-K");
                        validCoordinate = false;
                    }
                }
            } while(!validCoordinate);
    }
}
