import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ship {
    static Scanner scanner = new Scanner(System.in);
    private int size;
    private int startX;
    private int startY;
    private char orientation;
    private int shot;

    // Constructor
    public Ship(int size, int startX, int startY, char orientation) {
        this.size = size;
        this.startX = startX;
        this.startY = startY;
        this.orientation = orientation;
        this.shot = 0;
    }

    // Getters and setters

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

    // methods
    public void itSunked(int shipGetSots, int shipSize) {

        if (shipGetSots == shipSize) {
            System.out.println("The ship is sunked!");
        } else {
            System.out.println("You hit!");
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

        for (int k = 0; k < ships.length; k++) {
            boolean getShip = true;
            boolean valid = false;
            int row = 0;
            char orient = ' ';
            int col = 0;

            while (getShip) {

                // Get the row number of the first cell
                System.out.println(
                        "Please enter the row number of the first cell of the " + ships[k] + " long ship (1-9): ");
                do {
                    try {
                        row = Integer.parseInt(scanner.next()); // try-catch!!
                        valid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number between 1 and 9!");
                        valid = false;
                    }
                } while (!valid);

                // Get the column of the first cell
                System.out
                        .println("Please enter the letter belongs to the column of the first cell of the ship: (A-K)");
                do {
                    char colFromUser = scanner.next().charAt(0); // try-catch!! Need to convert char->int
                    if (Character.isDigit(colFromUser)) {
                        System.out.println("Error: please do not input a number");
                        valid = false;
                    } else {
                        char colToLower = Character.toLowerCase(colFromUser);
                        if (colToLower <= 'k') {
                            col = colToLower - 'a';// char to int
                            valid = true;
                        } else {
                            System.out.println("Please enter a letter between A-K");
                            valid = false;
                        }
                    }
                } while (!valid);

                // Get the orientation
                do {
                    System.out.println(
                            "Please choose the orientation of the ship: enter h if horizontal and v if vertical!"); // try-catch!!
                    orient = scanner.next().charAt(0);
                    getShip = false; // end of while cycle
                    // check if orientation input is correct
                    if (orient == 'h' || orient == 'v') {
                        // vertical and horizontal check
                        if ((orient == 'v' && (row + ships[k] - 1 > 9)) || orient == 'h' && (col + ships[k] - 1 > 10)) { // for
                                                                                                                         // example:
                                                                                                                         // 3
                                                                                                                         // long
                                                                                                                         // ship
                                                                                                                         // to
                                                                                                                         // row
                                                                                                                         // 8
                                                                                                                         // can't
                                                                                                                         // be
                                                                                                                         // placed
                            System.out.println(
                                    "The ship can't be placed there, the length of the ship is more than the length of the board! Please enter the coordinates again!");
                            getShip = true; // need to get the coordinate again
                            valid = false; // break the do-while for orientation
                        } else {
                            valid = true;
                        }
                    } else {
                        System.out.println(
                                "Please type 'h' if you want the ship horizontal or type 'v' if you want it to vertical");
                        valid = false;
                    }
                } while (!valid);

                row--; // Because of the index starts with 0

                // Set the values on the player's board + check if it contacts another one
                boolean canBePlaced = player.inicializeValues(ships[k], row, col, orient);

                // If the ship can be placed (doesn't contact another)
                if (canBePlaced) {
                    Ship newShip = new Ship(ships[k], row, col, orient);
                    player.addShip(newShip);

                    System.out.println("*******************");
                    System.out.println("The ship is placed!");
                    System.out.println("*******************");
                } else {
                    System.out.println(
                            "Unfortunately, the ship cannot be placed there, because it is in contact with another one. Please enter the coordinates again!");
                    getShip = true;
                }
            }

        }
        ConsoleInput.pressEnterToContinue();

    }

    public static void placeThePcShips(int[] ships, Player player) {

        for (int k = 0; k < ships.length; k++) {
            boolean getShip = true;
            Random random = new Random();
            char orient = ' ';

            while (getShip) {
                // Get the row number of the first cell
                int row = random.nextInt(8);

                // Get the column of the first cell
                int colPC = random.nextInt(9);

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
                if ((orient == 'v' && (row + ships[k] - 1 > 9)) || orient == 'h' && (colPC + ships[k] - 1 > 10)) { // for
                                                                                                                   // example:
                                                                                                                   // 3
                                                                                                                   // long
                                                                                                                   // ship
                                                                                                                   // to
                                                                                                                   // row
                                                                                                                   // 8
                                                                                                                   // can't
                                                                                                                   // be
                                                                                                                   // placed
                    getShip = true; // need to get the coordinate again
                } else {
                    // row--; //Because of the index starts with 0

                    // Set the values on the player's board + check if it contacts another one
                    boolean canBePlaced = player.inicializeValues(ships[k], row, colPC, orient);

                    // If the ship can be placed (doesn't contact another)
                    if (canBePlaced) {
                        Ship newShip = new Ship(ships[k], row, colPC, orient);
                        player.addShip(newShip);

                        System.out.println("*******************");
                        System.out.println("The PC's ship is placed!");
                        System.out.println("*******************");
                        getShip = false;
                    } else {
                        getShip = true;
                    }
                }
            }

        }

    }

}
