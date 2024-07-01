import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int[][] board;
    private List<Ship> ships;
    private int hits;

    public Player(String name) {
        this.name = name;
        this.board = new int[9][11];
        this.ships = new ArrayList<Ship>();
        this.hits = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void showTable() {
        ConsoleOutput.space();
        for (char column = 'A'; column <= 'K'; column++) {
            ConsoleOutput.tableColumnLetter(column);
        }
        ConsoleOutput.brake();
        for (int row = 0; row < board.length; row++) {
            ConsoleOutput.tableRowNumber(row);
            for (int col = 0; col < board[row].length; col++) {
                switch (board[row][col]) {
                    // Hit
                    case 2:
                        ConsoleOutput.hitSign();
                        break;
                    // Shot, but not hit
                    case -1:
                        ConsoleOutput.shotNoHit();
                        break;
                    // Value of matrix is 0 - no action, 1 - ship is there, 3 - the surrounding
                    // cells of the ship
                    default:
                        ConsoleOutput.tableDefaultSign();
                        break;
                }
            }
            ConsoleOutput.brake();
        }
    }

    public boolean inicializeValues(int length, int row, int col, char orient) {
        boolean placeable = false;
        if (orient == 'v') {
            int end = row + length;

            for (int k = row; k < end; k++) {
                if (this.board[k][col] != 3) { // Checking the neighbors
                    this.board[k][col] = 1;

                    // Setting the values of surrounding cells to 3
                    // Left
                    if (col > 0) {
                        this.board[k][col - 1] = 3;
                    }
                    // Right
                    if (col < 10) {
                        this.board[k][col + 1] = 3;
                    }
                    placeable = true;
                } else {
                    return placeable;
                }
            }

            // Setting the values of surrounding cells to 3
            // Top
            if ((row - 1) > -1) {
                this.board[row - 1][col] = 3;
            }

            // bottom
            if (end < 8) {
                this.board[end][col] = 3;
            }

            // Horizontal
        } else {
            int end = col + length;

            for (int k = col; k < end; k++) {
                if (this.board[row][k] != 3) { // Checking the neighbors
                    this.board[row][k] = 1;

                    // Setting the values of surrounding cells to 3
                    // Top
                    if (row > 0) {
                        this.board[row - 1][k] = 3;
                    }
                    // Bottom
                    if (row < 8) {
                        this.board[row + 1][k] = 3;
                    }

                    placeable = true;
                } else {
                    return false;
                }
            }

            // Setting the values of surrounding cells to 3
            // Left
            if ((col - 1) > 0) {
                this.board[row][col - 1] = 3;
            }

            // Right
            if (end < 10) {
                this.board[row][end] = 3;
            }
        }
        return placeable;
    }

    public static boolean isMissed(int[][] enemyboard, int shotRow, int validatedShotCol) {

        if (enemyboard[shotRow][validatedShotCol] == 0 || enemyboard[shotRow][validatedShotCol] == 3) {
            enemyboard[shotRow][validatedShotCol] = -1;
            ConsoleOutput.missed();
            // enemy.setBoard(enemyboard);
            return true;
        }
        return false;
    }

    public static boolean isHitted(int[][] enemyboard, int shotRow, int validatedShotCol, List<Ship> enemyShips) {

        if (enemyboard[shotRow][validatedShotCol] == 1) {
            enemyboard[shotRow][validatedShotCol] = 2;

            // check the ship's other cells, sunk or not!
            for (Ship ship : enemyShips) {

                if ((ship.getStartX() == shotRow) && (ship.getStartY() == validatedShotCol)) { // check the cell is a
                                                                                               // start point for any
                                                                                               // ship or not
                    // only one ship could start at that point, so that is shooted
                    ship.shotIncrement(ship);
                    ship.itSunked(ship.getShot(), ship.getSize());
                } else {

                    if (validatedShotCol == 0) {// it is the left side of the board , so we have to check only the first
                                                // column in right
                        if ((enemyboard[shotRow][validatedShotCol + 1] == 1)) {// the ship is horizontal
                            if ((ship.getStartX() == shotRow) && (ship.getStartY() < validatedShotCol)
                                    && ((validatedShotCol - ship.getStartY()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        } else {// the ship is vertical
                            if ((ship.getStartY() == validatedShotCol) && (ship.getStartX() < shotRow)
                                    && ((shotRow - ship.getStartX()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }
                    } else if (validatedShotCol == 10) {// it is the last column on the board, so we have to check only
                                                        // the first column in left
                        if ((enemyboard[shotRow][validatedShotCol - 1] == 1)) {// the ship is horizontal
                            if ((ship.getStartX() == shotRow) && (ship.getStartY() < validatedShotCol)
                                    && ((validatedShotCol - ship.getStartY()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        } else {// the ship is vertical
                            if ((ship.getStartY() == validatedShotCol) && (ship.getStartX() < shotRow)
                                    && ((shotRow - ship.getStartX()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }

                    } else if (shotRow == 0) {// it is the first row on the table, so we have to check only the first
                                              // row in down
                        if ((enemyboard[shotRow + 1][validatedShotCol] == 1)) {// the ship is vertical
                            if ((ship.getStartY() == validatedShotCol) && (ship.getStartX() < shotRow)
                                    && ((shotRow - ship.getStartX()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }

                        } else {// the ship is horizontal
                            if ((ship.getStartX() == shotRow) && (ship.getStartY() < validatedShotCol)
                                    && ((validatedShotCol - ship.getStartY()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }
                    }

                    else if (shotRow == 8) {// it is the last row on the table, so we have to check only the last but
                                            // one row in up

                        if ((enemyboard[shotRow - 1][validatedShotCol] == 1)) {// the ship is vertical
                            if ((ship.getStartY() == validatedShotCol) && (ship.getStartX() < shotRow)
                                    && ((shotRow - ship.getStartX()) < ship.getSize())) {
                                // only one ship could start at that point vertically, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }

                        } else {// the ship is horizontal
                            if ((ship.getStartX() == shotRow) && (ship.getStartY() < validatedShotCol)
                                    && ((validatedShotCol - ship.getStartY()) < ship.getSize())) {
                                // only one ship could start at that point horizontally, so that is shooted
                                ship.shotIncrement(ship);
                                ship.itSunked(ship.getShot(), ship.getSize());
                            }
                        }
                    } else if ((enemyboard[shotRow][validatedShotCol - 1] == 1)
                            || (enemyboard[shotRow][validatedShotCol + 1] == 1)) { // if is not a start point for any
                                                                                   // ship and the shot is not at the
                                                                                   // corners, we have to check the next
                                                                                   // cells, firstly horizontally

                        if ((ship.getStartX() == shotRow) && (ship.getStartY() < validatedShotCol)
                                && ((validatedShotCol - ship.getStartY()) < ship.getSize())) {
                            // only one ship could start at that point horizontally, so that is shooted
                            ship.shotIncrement(ship);
                            ship.itSunked(ship.getShot(), ship.getSize());
                        }
                    } else { // enemy ship is vertical

                        if ((ship.getStartY() == validatedShotCol) && (ship.getStartX() < shotRow)
                                && ((shotRow - ship.getStartX()) < ship.getSize())) {
                            // only one ship could start at that point vertically, so that is shooted
                            ship.shotIncrement(ship);
                            ship.itSunked(ship.getShot(), ship.getSize());
                        }
                    }
                }
            }

            return true;
        }
        return false;
    }

    public static void hitIncrement(Player shooterPlayer) {
        shooterPlayer.setHits(shooterPlayer.getHits() + 1);
        int playerHits = shooterPlayer.getHits();
        ConsoleOutput.numberOfYourHits(playerHits);

    }

    public static boolean hasWinner(Player shooterPlayer) {
        int playerHits = shooterPlayer.getHits();
        if (playerHits >= 17) {
            ConsoleOutput.youWon();
            return true;
        }
        return false;
    }
}
