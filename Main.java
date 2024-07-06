import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {
        boolean newGame = GameMode.startGame(false);
        do{
            String playerName;
            int[] ships = Ship.shipsArray();
            ConsoleOutput.welcomeMessage();
            GameModes gameMode = ConsoleInput.chooseGameMode();
            ArrayList<Player> players = new ArrayList<Player>();// ArrayList for save the players
            String[] names;

            switch (gameMode) {
                case HumanHuman:
                    for (int playerNumber = 1; playerNumber <= 2; playerNumber++) {
                        GameMode gameModeObj = new GameMode();
                        ConsoleOutput.clearScreen();
                        playerName = gameModeObj.namesGameMode1(playerNumber);
                        Player player = new Player(playerName, false);
                        players.add(player);
                        Ship.placeTheHumanShips(ships, player);
                    }
                    GameMode.getShotGameMode1(players);
                    break;
                case HumanPC:
                    GameMode gameModeObj2 = new GameMode();
                    ConsoleOutput.clearScreen();
                    names = gameModeObj2.namesGameMode2();
                    Player human = new Player(names[0], false);
                    Player PC = new Player(names[1], true);
                    players.add(human);
                    players.add(PC);
                    Ship.placeTheHumanShips(ships, human);
                    Ship.placeThePcShips(ships, PC);
                    GameMode.getShotGameMode2(players);
                    break;
                default:
                    GameMode gameModeObj3 = new GameMode();
                    names = gameModeObj3.namesGameMode3();
                    Player PC1 = new Player(names[0], true);
                    Player PC2 = new Player(names[1], true);
                    players.add(PC1);
                    players.add(PC2);
                    Ship.placeThePcShips(ships, PC1);
                    Ship.placeThePcShips(ships, PC2);
                    GameMode.getShotGameMode3(players);
                    break;
            }
        }while(!newGame);
    }
}


