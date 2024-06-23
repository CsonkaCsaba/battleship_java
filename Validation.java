import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
                System.out.println("Your choice: " + gameMode);
            } else {   
                ConsoleOutput.invalidGameModeInput();
                gameModeValid = false;
            }
        return gameModeValid;
    }
}
