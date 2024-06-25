public class GameMode {
    
    public String namesGameMode1(int i) { // 1. Human vs. Human
        ConsoleInput.getNameOfThePlayers(i + 1);
        String playerName = ConsoleInput.getName();
        ConsoleOutput.welcomePlayer(playerName);
        return playerName;
    }

    public String[] namesGameMode2() { // 2. Human vs. PC
        ConsoleInput.getNameOfThePlayers(1);
        String playerName = ConsoleInput.getName();
        ConsoleOutput.welcomePlayer(playerName);
        String[] names = { playerName, "PC" };
        return names;
    }

    public static void shotGameMode1() {

    }

    public static void shotGameMode2() {

    }
}
