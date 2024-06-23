import java.util.Scanner;

public class GameMode {
    Scanner scanner = new Scanner(System.in);

    public String namesGameMode1(int i) { // 1. Human vs. Human
        ConsoleInput.getNameOfThePlayers(i + 1);
        String playerName = scanner.next();
        ConsoleOutput.welcomePlayer(playerName);
        return playerName;
    }

    public String[] namesGameMode2() { // 2. Human vs. PC
        ConsoleInput.getNameOfThePlayers(1);
        String playerName = scanner.next();
        ConsoleOutput.welcomePlayer(playerName);
        String[] names = { playerName, "PC" };
        return names;
    }

    public static void shotGameMode1() {

    }

    public static void shotGameMode2() {

    }
}
