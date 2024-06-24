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

    public static void getNameOfThePlayers(int playerNumber) {
        System.out.println("What is the name of the Player " + playerNumber + " :");
    }

    public static int getRowNumber() {
        int rowInput = Integer.parseInt(scanner.next()) - 1;// Because of the index starts with 0
         return rowInput;
        
    }

    public static char getColChar() {
        return scanner.next().charAt(0);
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue!");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("It's not Enter key. It you want to skip to another player, please press Enter!");
        }
    }
}
