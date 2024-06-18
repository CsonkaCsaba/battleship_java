public class ConsoleOutput {
    public static void playerTurn(String name, Player enemy){
        System.out.println(name + ", it's your turn!");
        System.out.println("Your opponent's table:");
        enemy.showTable();
    }
    public static void getRowNumber(){
        System.out.println("Please enter the number of the row you want to shoot!(1-9): ");
    }
    public static void getColNumber(){
        System.out.println("Please enter the letter belongs to the column you want to shoot: (A-K)");
    }
    public static void tableAfterShot(String name, Player enemy){
        System.out.println("The table after " + name +" 's shot: ");
        enemy.showTable();
    }
    
}
