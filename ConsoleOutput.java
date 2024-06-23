public class ConsoleOutput {

    public static void welcomeMessage() {
        System.out.println("**********************");
        System.out.println("Welcome to Battleship!");
        System.out.println("**********************");
        Player.showEmptyTable();
        ConsoleInput.pressEnterToContinue();
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Please press the Enter key to choose the game mode: ");
        }

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

    public static void tableAfterShot(String name, Player enemy) {
        System.out.println("The table after " + name + " 's shot: ");
        enemy.showTable();
    }

}
