import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ConsoleInput {
    static Scanner scanner = new Scanner(System.in);

    public static gameModes chooseGameMode() {
        int gameModeInput = 0;
        boolean valid;
        gameModes gameMode;

        do {
            ConsoleOutput.chooseGameMode();
            gameModeInput = scanner.nextInt();
            scanner.nextLine();
            valid = Validation.gameModeValidation(gameModeInput);
        } while (!valid);

        switch (gameModeInput) {
            case 1:
                gameMode = gameModes.HumanHuman;
                break;
            case 2:
                gameMode = gameModes.HumanPC;
                break;
            default:
                gameMode = gameModes.PCPC;
        }
        return gameMode;
    }


    public static int getRowNumber() {
        int rowInput = Integer.parseInt(scanner.next()) - 1;// Because of the index starts with 0
         return rowInput;
        
    }

    public static char getChar() {
        return scanner.next().charAt(0);
    }

    public static String getName(){
        return scanner.next();

    }

}
