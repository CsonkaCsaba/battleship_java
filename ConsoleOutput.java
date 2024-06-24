public class ConsoleOutput {

    public static void welcomeMessage() {
        System.out.println("**********************");
        System.out.println("Welcome to Battleship!");
        System.out.println("**********************");
        ConsoleInput.pressEnterToContinue();
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Please press the Enter key to choose the game mode: ");
        }

    }

    public static void chooseGameMode() {
        System.out.println("Please choose the game mode! Enter a number between 1 and 3");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. PC");
        System.out.println("3. PC vs. PC");
    }

    public static void invalidGameModeInput() {
        System.out.println("The input is not valid! Please enter a number between 1 and 3");
    }

    public static void validGameModeInput(String choice) {
        System.out.println("Your choice is: " + choice);
    }

    public static void playerTurn(String name, Player enemy) {
        System.out.println(name + ", it's your turn!");
        System.out.println("Your opponent's table:");
        enemy.showTable();
    }

    public static void welcomePlayer(String name) {
        System.out.println("Welcome " + name
                + "! Please enter the first cells' coordinate of the given sized ships in the next few steps!\nNote that horizontal means left to right, while vertical means up to bottom directions!\nThe row number must to be a number between 1-9 and the column's letter must to be a capital letter between A-K");
    }

    public static void getRowNumber() {
        System.out.println("Please enter the number of the row you want to shoot!(1-9): ");
    }

    public static void getColNumber() {
        System.out.println("Please enter the letter belongs to the column you want to shoot: (A-K)");
    }

    public static void getShipRow(int length) {
        System.out.println("Please enter the row number of the first cell of the " + length + " long ship (1-9): ");
    }

    public static void wrongRowNumber() {
        System.out.println("Please enter a number between 1 and 9!");
    }

    public static void getShipCol() {
        System.out.println("Please enter the letter belongs to the column of the first cell of the ship: (A-K)");
    }

    public static void getOrientation() {
        System.out.println("Please choose the orientation of the ship: enter h if horizontal and v if vertical!");
    }

    public static void tableAfterShot(String name, Player enemy) {
        System.out.println("The table after " + name + " 's shot: ");
        enemy.showTable();
    }

    public static void shipIsPlaced() {
        System.out.println("*******************");
        System.out.println("The ship is placed!");
        System.out.println("*******************");
    }

    public static void shipContactError() {
        System.out.println(
                "Unfortunately, the ship cannot be placed there, because it is in contact with another one. Please enter the coordinates again!");
    }

}
