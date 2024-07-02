import java.util.ArrayList;
import java.util.List;

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

    public static void getShotGameMode1(ArrayList<Player> players) {
        boolean hasWinner = false;
        do {// until we have a winner
            for (int playerNumber = 0; playerNumber <= 2; playerNumber++) {
                if (playerNumber == 2) {
                    playerNumber = 0;
                }

                Player shooterPlayer = players.get(playerNumber);
                Player enemy = (playerNumber == 0) ? players.get(playerNumber + 1) : players.get(playerNumber - 1);

                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);

                ConsoleOutput.getRowNumber();
                int validatedShotRow = Validation.inputRowValidation();

                ConsoleOutput.getColNumber();
                int validatedShotCol = Validation.inputColValidation();

                // check the coordinates on the board of the enemy
                int[][] enemyboard = enemy.getBoard();
                List<Ship> enemyShips = enemy.getShips();
                boolean missed = Player.isMissed(enemyboard, validatedShotRow, validatedShotCol);
                boolean hitted = Player.isHitted(enemyboard, validatedShotRow, validatedShotCol, enemyShips);

                if (missed) {
                    ConsoleOutput.missed();
                } else if (hitted) {
                    Player.hitIncrement(shooterPlayer);
                    hasWinner = Player.hasWinner(shooterPlayer);
                    hasWinner = true;
                } else {// the cell is 2 or -1 ;
                    ConsoleOutput.alreadyShotthere();

                }
                if (!hasWinner) {
                    ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
                    ConsoleOutput.pressEnterToContinue();
                }
            }
        } while (!hasWinner);
    }

    public static void getShotGameMode2(ArrayList<Player> players) {

    }
}
