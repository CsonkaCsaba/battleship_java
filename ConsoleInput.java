import java.util.Scanner;
import java.io.Console;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class ConsoleInput {
    static Scanner scanner = new Scanner(System.in);

    public static GameModes chooseGameMode() {
        int gameModeInput = 0;
        boolean valid = false;
        GameModes gameMode;

        do {
            ConsoleOutput.chooseGameMode();
            try{
                gameModeInput = scanner.nextInt();
                valid = Validation.gameModeValidation(gameModeInput);
            }catch(InputMismatchException e){
                ConsoleOutput.invalidGameModeInput();
            }
            scanner.nextLine();
        } while (!valid);

        switch (gameModeInput) {
            case 1:
                gameMode = GameModes.HumanHuman;
                break;
            case 2:
                gameMode = GameModes.HumanPC;
                break;
            default:
                gameMode = GameModes.PCPC;
        }
        return gameMode;
    }

    public static int getRowNumber() {
        return Integer.parseInt(scanner.next());
    }

    public static char getChar() {
        return scanner.next().charAt(0);
    }

    public static String getName() {
        return scanner.next();
    }

    public static boolean newGame(){
        boolean valid = false;
        boolean newGame = false;
        char answer;
        do{
            answer = scanner.next().charAt(0);
            valid = Validation.isValidNewGameInput(answer);
            if(!valid) {
                ConsoleOutput.invalidNewGameInput();
            }
        }while (!valid);
        if(answer == 'y'){
            newGame = true;
        }
        return newGame;
    }
}

