import java.util.ArrayList;
import java.util.Map;

enum GameModes {
    HumanHuman, HumanPC, PCPC
}

public class GameMode {

    public String namesGameMode1(int playerNumber) { // 1. Human vs. Human
        ConsoleOutput.getNameOfThePlayers(playerNumber);
        String playerName = ConsoleInput.getName();
        ConsoleOutput.welcomePlayer(playerName);
        return playerName;
    }

    public String[] namesGameMode2() { // 2. Human vs. PC
        ConsoleOutput.getNameOfThePlayers(1);
        String playerName = ConsoleInput.getName();
        ConsoleOutput.welcomePlayer(playerName);
        String[] names = { playerName, "PC" };
        return names;
    }

    public String[] namesGameMode3() { // 2. PC1 vs. PC2
        String[] names = { "PC1", "PC2" };
        return names;
    }

    public static void getShotGameMode1(ArrayList<Player> players) {
        boolean hasWinner = false;
        do {// until we have a winner
            for (int playerNumber = 0; playerNumber <= 2; playerNumber++) {
                if (playerNumber == 2 && hasWinner == false) {
                    playerNumber = 0;
                }

                Player shooterPlayer = players.get(playerNumber);
                Player enemy = (playerNumber == 0) ? players.get(playerNumber + 1) : players.get(playerNumber - 1);

                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);
                Map<String, Integer> humanShot = Shot.HumanShot();
                hasWinner = Shot.hittedOrMissed(shooterPlayer, enemy, humanShot);
                if (hasWinner) {
                    break;
                }
            }
        } while (!hasWinner);
        ConsoleOutput.newGame();
        boolean newGame = ConsoleInput.newGame();
        if (newGame) {
            GameMode.startGame(newGame);
        } else {
            System.exit(0);
        }
    }

    public static void getShotGameMode2(ArrayList<Player> players) {
        boolean hasWinner = false;
        do {// until we have a winner
            for (int playerNumber = 0; playerNumber <= 2; playerNumber++) {
                if (playerNumber == 2 && hasWinner == false) {
                    playerNumber = 0;
                }

                Player shooterPlayer = players.get(playerNumber);
                Player enemy = (playerNumber == 0) ? players.get(playerNumber + 1) : players.get(playerNumber - 1);

                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);
                if (shooterPlayer.getisPc()) {
                    Map<String, Integer> pcShot = Shot.PcShot(enemy);
                    hasWinner = Shot.hittedOrMissed(shooterPlayer, enemy, pcShot);
                    if (hasWinner) {
                        break;
                    }
                } else {
                    Map<String, Integer> humanShot = Shot.HumanShot();
                    hasWinner = Shot.hittedOrMissed(shooterPlayer, enemy, humanShot);
                    if (hasWinner) {
                        break;
                    }
                }
            }
        } while (!hasWinner);
        ConsoleOutput.newGame();
        boolean newGame = ConsoleInput.newGame();
        if (newGame) {
            GameMode.startGame(newGame);
        } else {
            System.exit(0);
        }
    }

    public static void getShotGameMode3(ArrayList<Player> players) {
        boolean hasWinner = false;
        do {// until we have a winner
            for (int playerNumber = 0; playerNumber <= 2; playerNumber++) {
                if (playerNumber == 2 && hasWinner == false) {
                    playerNumber = 0;
                }

                Player shooterPlayer = players.get(playerNumber);
                Player enemy = (playerNumber == 0) ? players.get(playerNumber + 1) : players.get(playerNumber - 1);

                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);
                Map<String, Integer> pcShot = Shot.PcShot(enemy);
                hasWinner = Shot.hittedOrMissed(shooterPlayer, enemy, pcShot);
                if (hasWinner) {
                    break;
                }
            }
        } while (!hasWinner);
        ConsoleOutput.newGame();
        boolean newGame = ConsoleInput.newGame();
        if (newGame) {
            GameMode.startGame(newGame);
        } else {
            System.exit(0);
        }
    }

    public static boolean startGame(boolean newGame) {
        return newGame;
    }

}
