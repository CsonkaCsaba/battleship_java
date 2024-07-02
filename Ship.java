import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {

    private int size;
    private int startX;
    private int startY;
    private char orientation;
    private int shot;

    public Ship(int size, int startX, int startY, char orientation) {
        this.size = size;
        this.startX = startX;
        this.startY = startY;
        this.orientation = orientation;
        this.shot = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public void itSunked(int shipGetSots, int shipSize) {

        if (shipGetSots == shipSize) {
            ConsoleOutput.sunked();
        } else {
            ConsoleOutput.hit();
        }
    }

    public void shotIncrement(Ship ship) {
        ship.setShot(ship.getShot() + 1);
    }

    public static int[] shipsArray() {
        // List<Ship> ships_player = new ArrayList<Ship>();
        int[] shipsToPlace = { 2, 3, 3, 4, 5 };
        return shipsToPlace;
    }

    public static void placeTheHumanShips(int[] ships, Player player) {

        for (int shipNumber = 0; shipNumber < ships.length; shipNumber++) {
            boolean getShip = true;
            boolean valid = false;
            int row = 0;
            char orient = ' ';
            int col = 0;

            while (getShip) {

                
                do {
                    // Get the row number of the first cell
                    ConsoleOutput.getShipRow(ships[shipNumber]);
                    row = Validation.inputRowValidation();
                    
                    // Get the column of the first cell
                    ConsoleOutput.getShipCol();
                    col = Validation.inputColValidation();

                    // Get the orientation
                    ConsoleOutput.getOrientation();
                    orient = ConsoleInput.getChar();
                    getShip = false; // end of while cycle
                    // check if orientation input is correct
                    if (orient == 'h' || orient == 'v') {
                        // vertical and horizontal check
                        if ((orient == 'v' && (row + ships[shipNumber] > 9))
                                || orient == 'h' && (col + ships[shipNumber] - 1 > 10)) {
                            ConsoleOutput.shipPlaceError();
                            getShip = true; // need to get the coordinate again
                            valid = false; // break the do-while for orientation
                        } else {
                            valid = true;
                        }
                    } else {
                        ConsoleOutput.horizontalOrVertical();
                        valid = false;
                    }
                } while (!valid);

                // Set the values on the player's board + check if it contacts another one
                boolean canBePlaced = player.inicializeValues(ships[shipNumber], row, col, orient);

                // If the ship can be placed (doesn't contact another)
                if (canBePlaced) {
                    Ship newShip = new Ship(ships[shipNumber], row, col, orient);
                    player.addShip(newShip);
                    ConsoleOutput.shipIsPlaced();
                    player.showTable(true);
                } else {
                    ConsoleOutput.shipContactError();
                    getShip = true;
                }
            }
        }
        ConsoleOutput.pressEnterToContinue();
    }

    public static void placeThePcShips(int[] ships, Player player) {

        for (int shipNumber = 0; shipNumber < ships.length; shipNumber++) {
            boolean getShip = true;
            Random random = new Random();
            char orient = ' ';

            while (getShip) {
                // Get the row number of the first cell
                int row = random.nextInt(8 - 1);

                // Get the column of the first cell
                int colPC = random.nextInt(10 - 1);

                // Get the orientation
                int orientation = random.nextInt(2);

                // check if orientation input is correct

                // vertical and horizontal check
                // 0 -> vertical, 1-> horizontal
                if (orientation == 0) {
                    orient = 'v';
                } else {
                    orient = 'h';
                }
                if ((orient == 'v' && (row + ships[shipNumber] - 1 > 9))
                        || orient == 'h' && (colPC + ships[shipNumber] - 1 > 10)) {
                    getShip = true; // need to get the coordinate again
                } else {
                    // row--; //Because of the index starts with 0

                    // Set the values on the player's board + check if it contacts another one
                    boolean canBePlaced = player.inicializeValues(ships[shipNumber], row, colPC, orient);

                    // If the ship can be placed (doesn't contact another)
                    if (canBePlaced) {
                        Ship newShip = new Ship(ships[shipNumber], row, colPC, orient);
                        player.addShip(newShip);
                        ConsoleOutput.shipIsPlaced();
                        player.showTable(true);
                        getShip = false;
                    } else {
                        getShip = true;
                    }
                }
            }

        }

    }

}
