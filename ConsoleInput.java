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
        return Integer.parseInt(scanner.next()) - 1;// Because of the index starts with 0
    }

    public static char getColChar() {
        return scanner.next().charAt(0);
    }

    public static void inputRowValidation(int shotRow) {
        boolean validCoordinate = false;
        do {// until the ROW coordinates are valid
            try {
                if (shotRow < 0 || shotRow >= 10) {
                    System.out.println("The number is not valid! You need to enter a number between 1 and 9");
                } else {
                    validCoordinate = true;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("The input is not valid! Please enter a number between 1 and 9");
            }
        } while (!validCoordinate);
    }

    public static int inputColValidation(char shotCol) {
        boolean validCoordinate = false;
        int value = 0;
        do { // until the COLUMN coordinates are valid
            if (Character.isDigit(shotCol)) {
                System.out.println("Error: please do not input a number");
            } else {
                char shotColToLower = Character.toLowerCase(shotCol);
                if (shotColToLower <= 'k') {
                    Map<Character, Integer> charMap = new HashMap<>() {
                        {
                            put('a', 1);
                            put('b', 2);
                            put('c', 3);
                            put('d', 4);
                            put('e', 5);
                            put('f', 6);
                            put('g', 7);
                            put('h', 8);
                            put('i', 9);
                            put('j', 10);
                            put('k', 11);
                        }
                    };
                    value = charMap.get(shotColToLower) - 1;
                    validCoordinate = true;

                } else {
                    System.out.println("Please enter a letter between A-K");
                    validCoordinate = false;
                }
            }
            return value;
        } while (!validCoordinate);

    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue!");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("It's not any key. It you want to skip to another player, please press Enter!");
        }
    }
}
