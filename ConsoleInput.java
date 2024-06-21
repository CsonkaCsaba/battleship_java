import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ConsoleInput {
   static Scanner scanner = new Scanner(System.in);
    
    public static int chooseGameMode(){
        boolean gameModeValid = false;
        int gameModeInput = 0;
        System.out.println("Please choose the game mode! Enter a number between 1 and 3");
        do{
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. PC");
        System.out.println("3. PC vs. PC");
        gameModeInput = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, String> gameModeMap = new HashMap<Integer, String>();
        gameModeMap.put(1, "Human vs. Human ");
        gameModeMap.put(2, "Human vs. PC");
        gameModeMap.put(3, "PC vs. PC");
       
            if(gameModeInput >= 1 && gameModeInput <= 3){
                gameModeValid = true;
                String gameMode = gameModeMap.get(gameModeInput);
                System.out.println("Your choice: " + gameMode);
            } else {
                System.out.println("The input is not valid! Please enter a number between 1 and 3");
            }
        }while(!gameModeValid);

        return gameModeInput;
    }
    public static void getNameOfThePlayers(int i){    
        System.out.println("What is the name of the Player "+ i + " :" );
    }

    public static int getRowNumber(){    
            int shotRow = Integer.parseInt(scanner.next());
            shotRow--; //Because of the index starts with 0
            return shotRow;
    }

    public static char getColChar(){    
        char shotCol = scanner.next().charAt(0);
        return shotCol;
}

    public static void inputRowValidation(int shotRow){
        boolean validCoordinate = false;
        do {//until the ROW coordinates are valid          
            try{ 
                    if(shotRow < 0 || shotRow >= 10){
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
    public static int inputColValidation (char shotCol){
        boolean validCoordinate = false;
        int value = 0;
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
                        value = charMap.get(shotColToLower)-1;
                        validCoordinate = true;
                        

                    } else {
                        System.out.println("Please enter a letter between A-K");
                        validCoordinate = false;
                    }
                }
                return value;
            } while(!validCoordinate);
            
    }
    public static void pressEnterToContinue(){
        System.out.println("Press Enter key to continue!");
        try{
            System.in.read();
        }catch(Exception e){
            System.out.println("It's not any key. It you want to skip to another player, please press Enter!");
        }
    }
}
