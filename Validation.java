import java.util.HashMap;
import java.util.Map;


public class Validation {
    public static boolean gameModeValidation(int gameModeInput) {
        boolean gameModeValid = false;

        if (gameModeInput >= 1 && gameModeInput <= 3) {
            gameModeValid = true;
            Map<Integer, String> gameModeMap = new HashMap<Integer, String>();
            gameModeMap.put(1, "Human vs. Human ");
            gameModeMap.put(2, "Human vs. PC");
            gameModeMap.put(3, "PC vs. PC");
            String gameMode = gameModeMap.get(gameModeInput);
            ConsoleOutput.yourGameModeChoice(gameMode);
        } else {
            ConsoleOutput.invalidGameModeInput();
            gameModeValid = false;
        }
        return gameModeValid;
    }

    public static int inputRowValidation() {
        int rowInput = 0;
        boolean validate = false;
        do{// until the ROW coordinates are valid
            try {
                rowInput = ConsoleInput.getRowNumber();
                if (rowInput <= 0 || rowInput >= 10) {
                    ConsoleOutput.wrongRowNumber();
                } else {
                    validate = true;
                }
            } catch (NumberFormatException e) {
                ConsoleOutput.wrongRowNumber();
            }
        }while(!validate);
        return rowInput-1; 
    }

    public static int inputColValidation() {

        boolean validCoordinate = false;
        int value = 0;
        do{// until the COLUMN coordinates are valid
            char colFromUser = ConsoleInput.getChar();
            if (Character.isDigit(colFromUser)) {
               ConsoleOutput.wrongColInput();
               colFromUser = ConsoleInput.getChar();
            } else {
                char shotColToLower = Character.toLowerCase(colFromUser);
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
                    ConsoleOutput.wrongColInput();
                    validCoordinate = false;
                    ConsoleOutput.getColNumber();
                    colFromUser = ConsoleInput.getChar();
                }
            }
        }while(!validCoordinate);
        return value;
    }
}
