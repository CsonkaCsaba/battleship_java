import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Shot {

    public static Map<String, Integer> HumanShot() {
        Map<String, Integer> shot = new HashMap<>();
        ConsoleOutput.getRowNumber();
        shot.put("row", Validation.inputRowValidation());
        ConsoleOutput.getColNumber();
        shot.put("col", Validation.inputColValidation());
        return shot;
    }

    public static Map<String, Integer> PcShot(Player enemy) {
        Random random = new Random();
        Map<String, Integer> shot = new HashMap<>();
        boolean hitted = false;
        do {
            hitted = false;
            int row = random.nextInt(9);
            int col = random.nextInt(11);
            int[][] board = enemy.getBoard();
            if (board[row][col] == -1) {
                hitted = true;
            } else {
                shot.put("row", row);
                shot.put("col", col);
            }
        } while (hitted);

        return shot;
    }

    public static boolean hittedOrMissed(Player shooterPlayer, Player enemy, Map<String, Integer> shot) {
        boolean hasWinner = false;
        int[][] enemyboard = enemy.getBoard();
        List<Ship> enemyShips = enemy.getShips();
        boolean missed = Player.isMissed(enemyboard, shot.get("row"), shot.get("col"));
        boolean hitted = Player.isHitted(enemyboard, shot.get("row"), shot.get("col"), enemyShips);

        if (missed) {
            ConsoleOutput.missed();
            ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
            ConsoleOutput.pressEnterToContinue();
        } else if (hitted) {
            Player.hitIncrement(shooterPlayer);
            hasWinner = Player.hasWinner(shooterPlayer);
            if (!hasWinner) {
                ConsoleOutput.tableAfterShot(shooterPlayer.getName(), enemy);
                ConsoleOutput.pressEnterToContinue();
            }
        } else {// the cell is 2 or -1 ;
            ConsoleOutput.alreadyShotthere();
        }
        return hasWinner;
    }
}
