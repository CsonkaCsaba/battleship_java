public class ConsoleOutput {

    public static void welcomeMessage() {
        System.out.println("**********************");
        System.out.println("Welcome to Battleship!");
        System.out.println("**********************");
        ConsoleOutput.pressEnterToContinue();
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
        enemy.showTable(false);
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
        enemy.showTable(false);
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

    public static void wrongColInput() {
        System.out.println("The input is not valid! Please enter a letter between A-K!");
    }

    public static void getNameOfThePlayers(int playerNumber) {
        System.out.println("What is the name of the Player " + playerNumber + " :");
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue!");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("It's not Enter key. It you want to skip to another player, please press Enter!");
        }
    }

    public static void PCvsPC() {
        System.out.println("PC vs. PC");
    }

    public static void alreadyShotthere() {
        System.out.println("You have already shot there! Try again!");
    }

    public static void missed() {
        System.out.println("You missed!");
    }

    public static void numberOfYourHits(int playerHits) {
        System.out.println("The number of your hits :" + playerHits);
    }

    public static void youWon() {
        System.out.println("Congratulations! You won!");
    }

    public static void sunked() {
        System.out.println("The ship is sunked!");
    }

    public static void hit() {
        System.out.println("You hit!");
    }

    public static void itsNotChar() {
        System.out.println("Error: please do not input a number");
    }

    public static void shipPlaceError() {
        System.out.println(
                "The ship can't be placed there, the length of the ship is more than the length of the board! Please enter the coordinates again!");
    }

    public static void horizontalOrVertical() {
        System.out.println("Please type 'h' if you want the ship horizontal or type 'v' if you want it to vertical");
    }

    public static void yourGameModeChoice(String gamemode) {
        System.out.println("Your choice: " + gamemode);
    }

    public static void space() {
        System.out.print("  ");
    }

    public static void brake() {
        System.out.println();
    }

    public static void tableColumnLetter(char column) {
        System.out.print(column + " ");
    }

    public static void tableRowNumber(int row) {
        System.out.print(row + 1 + " ");
    }

    public static void hitSign() {
        System.out.print("+ ");
    }

    public static void shotNoHit() {
        System.out.print("X ");
    }

    public static void showShip() {
        System.out.print("O ");
    }

    public static void tableDefaultSign() {
        System.out.print("- ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
