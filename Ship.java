public class Ship {
    private int size;
    private int start_x;
    private int start_y;
    private char orientation;
    private int shot;

    //Constructor
    public Ship(int size, int start_x, int start_y, char orientation) {
        this.size = size;
        this.start_x = start_x;
        this.start_y = start_y;
        this.orientation = orientation;
        this.shot = 0;
    }
    
    //Getters and setters
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart_x() {
        return start_x;
    }

    public void setStart_x(int start_x) {
        this.start_x = start_x;
    }

    public int getStart_y() {
        return start_y;
    }

    public void setStart_y(int start_y) {
        this.start_y = start_y;
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
    //methods
    public void itSunked(int shipGetSots, int shipSize){
        
        if(shipGetSots == shipSize){
            System.out.println("The ship is sunked!");
        } else {
            System.out.println("You hit!");
        }
    }

    public void shotIncrement(Ship ship){
        ship.setShot(ship.getShot()+1);
    }



   
}
