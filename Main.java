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
                    ConsoleOutput.clearScreen();
                    playerName = gameModeObj.namesGameMode1(playerNumber);
                    Player player = new Player(playerName);
                    players.add(player);
                    Ship.placeTheHumanShips(ships, player);
                }
                GameMode.getShotGameMode1(players);
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
                //GameMode.getShotGameMode2(players);
                break;
            default:
                ConsoleOutput.PCvsPC();
         }
        }
    }


