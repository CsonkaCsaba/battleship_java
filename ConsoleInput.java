import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ConsoleInput {
    static Scanner scanner = new Scanner(System.in);

    public static GameModes chooseGameMode() {
        int gameModeInput = 0;
        boolean valid;
        GameModes gameMode;

        do {
            ConsoleOutput.chooseGameMode();
            gameModeInput = scanner.nextInt();
            scanner.nextLine();
            valid = Validation.gameModeValidation(gameModeInput);
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
        int rowInput = Integer.parseInt(scanner.next()) - 1;// Because of the index starts with 0
        return rowInput;
    }

    public static char getChar() {
        return scanner.next().charAt(0);
    }

    public static String getName() {
        return scanner.next();
    }

}
