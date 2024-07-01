import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;



class Main {
    public static void main(String[] args) {
        String playerName;
        int[] ships = Ship.shipsArray();

        ConsoleOutput.welcomeMessage();
        GameModes gameMode = ConsoleInput.chooseGameMode();
        ArrayList<Player> players = new ArrayList<Player>();// ArrayList for save the players

        switch (gameMode) {
            case HumanHuman:
            for (int playerNumber = 1; playerNumber <= 2; playerNumber++) {
                    GameMode gameModeObj = new GameMode();
                    playerName = gameModeObj.namesGameMode1(playerNumber);
                    Player player = new Player(playerName);
                    players.add(player);
                    Ship.placeTheHumanShips(ships, player);
                }
                break;
            case HumanPC:
                GameMode gameModeObj2 = new GameMode();
                String[] names = gameModeObj2.namesGameMode2();
                Player human = new Player(names[0]);
                Player PC = new Player(names[1]);
                players.add(human);
                players.add(PC);
                Ship.placeTheHumanShips(ships, human);
                Ship.placeThePcShips(ships, PC);
                break;
            default:
                ConsoleOutput.PCvsPC();
        }

        // Shots
        boolean hasWinner = false;
        boolean validShot = false;
        Player shooterPlayer = null;
        Player enemy = null;

        do {// until we have a winner
            for (int playerNumber = 0; playerNumber <= 2; playerNumber++) {
                if (playerNumber == 2) {
                    playerNumber = 0;
                }
                switch (playerNumber) {
                    case 0:
                        shooterPlayer = players.get(playerNumber);
                        enemy = players.get(playerNumber+1);
                        break;
                    case 1:
                        shooterPlayer = players.get(playerNumber);
                        enemy = players.get(playerNumber-1);
                        break;
                    default:
                        break;
                }
                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);

                do {// until the shot is valid

                    ConsoleOutput.getRowNumber();
                    int validatedShotRow = Validation.inputRowValidation();

                    ConsoleOutput.getColNumber();
                    char shotCol = ConsoleInput.getChar();
                    int validatedShotCol = Validation.inputColValidation(shotCol);

                    // check the coordinates on the board of the enemy
                    int[][] enemyboard = enemy.getBoard();
                    List<Ship> enemyShips = enemy.getShips();
                    boolean missed = Player.isMissed(enemyboard, validatedShotRow, validatedShotCol);
                    boolean hitted = Player.isHitted(enemyboard, validatedShotRow, validatedShotCol, enemyShips);

                    if (missed) {
                        validShot = true;
                    } else if (hitted) {
                        Player.hitIncrement(shooterPlayer);
                        hasWinner = Player.hasWinner(shooterPlayer);
                        validShot = true;
                    } else {// the cell is 2 or -1 ;
                        ConsoleOutput.alreadyShotthere();
                        validShot = false;
                    }

                } while (!validShot);
                ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
                ConsoleOutput.pressEnterToContinue();
            }
        } while (!hasWinner);
    }
}
