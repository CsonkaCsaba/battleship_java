import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        int[] ships = Ship.shipsArray();
        ConsoleOutput.welcomeMessage();
        int gameMode = ConsoleInput.chooseGameMode();
        ArrayList<Player> players = new ArrayList<Player>();// ArrayList for save the players
       
        
            String player_Name;
            if(gameMode == 1){// 1. Human vs. Human
                for(int i = 0; i <= 1; i++) {
                GameMode gameModeObj = new GameMode();
                player_Name = gameModeObj.gameMode1(i);
                List<Ship> ships_player = new ArrayList<Ship>();
                player = Player.createPlayer(player_Name, ships_player, 0);
                players.add(player);
                Ship.placeTheShipsGameMode1(ships, player);
                }
            } else if(gameMode == 2){// 2. Human vs. PC
                GameMode gameModeObj2 = new GameMode();
                String [] names = gameModeObj2.gameMode2();
                List<Ship> ships_player = new ArrayList<Ship>();
                Player human = Player.createPlayer(names[0], ships_player, 0);
                Player PC = Player.createPlayer(names[1], ships_player, 0);
                players.add(human);
                players.add(PC);

                //Ship.placeTheShipsGameMode2(ships, human); NEXT STEP!!!

            } else {// PC vs. PC


            }
            
           
           
            
        

        //Shots
        boolean hasWinner = false;
        boolean validShot = false;
        Player shooterPlayer = null;
        Player enemy = null;

        do {// until we have a winner
            for(int i = 0; i <= 2; i++){
                if(i == 2){
                    i = 0;
                }
                switch (i) {
                    case 0:
                        shooterPlayer = players.get(0);
                        enemy = players.get(1);
                        break;
                    case 1:
                        shooterPlayer = players.get(1);
                        enemy = players.get(0);
                        break;
                    default:
                        break;
                }
                ConsoleOutput.playerTurn(shooterPlayer.getName(), enemy);

                do{//until the shot is valid

                    ConsoleOutput.getRowNumber();
                    int shotRow = ConsoleInput.getRowNumber();
                    ConsoleInput.inputRowValidation(shotRow);

                    ConsoleOutput.getColNumber();
                    char shotCol = ConsoleInput.getColChar();
                    int validatedShotCol = ConsoleInput.inputColValidation(shotCol);

                        //check the coordinates on the board of the enemy
                    int [][] enemyboard = enemy.getBoard();
                    List<Ship> enemyShips = enemy.getShips();
                    boolean missed = Player.isMissed(enemyboard, shotRow, validatedShotCol);
                    boolean hitted = Player.isHitted(enemyboard, shotRow, validatedShotCol, enemyShips);

                    if (missed){
                        validShot = true;
                    } else if (hitted) {
                        Player.hitIncrement(shooterPlayer);
                        hasWinner = Player.hasWinner(shooterPlayer);
                        validShot = true;
                    }else {// the cell is 2 or -1 ;
                        System.out.println("You have already shot there! Try again!");
                        validShot = false;
                    }

                } while(!validShot);
                ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
                ConsoleInput.pressAnyToContinue();
            } 
        } while(!hasWinner);
        scanner.close();  
    }
}
