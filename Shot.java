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

    public static Map<String, Integer> PcShot() {
        Random random = new Random();
        Map<String, Integer> shot = new HashMap<>();
        shot.put("row", random.nextInt(8));
        shot.put("col", random.nextInt(10));
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
