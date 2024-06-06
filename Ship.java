public class Ship {
    private int size;
    private int start_x;
    private int start_y;
    private String orientation;
    
    
    //Constructor
    public Ship(int size, int start_x, int start_y, String orientation) {
        this.size = size;
        this.start_x = start_x;
        this.start_y = start_y;
        this.orientation = orientation;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
