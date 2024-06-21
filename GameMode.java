import java.util.Scanner;

public class GameMode {
    Scanner scanner = new Scanner(System.in);
    public String namesGameMode1 (int i){ // 1. Human vs. Human
        ConsoleInput.getNameOfThePlayers(i+1);
        String player_Name = scanner.next();
        ConsoleOutput.welcomePlayer(player_Name);
        return player_Name;
    }
    public String [] namesGameMode2 (){ // 2. Human vs. PC
        ConsoleInput.getNameOfThePlayers(1);
        String player_Name = scanner.next();
        ConsoleOutput.welcomePlayer(player_Name);
        String[] names = {player_Name, "PC"};
        return names;
    }
    public static void namesGameMode3 (){ // PC vs. PC

    }
}
