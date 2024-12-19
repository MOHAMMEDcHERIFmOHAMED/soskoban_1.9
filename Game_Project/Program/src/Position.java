import javax.swing.JLabel;

public class Position {
    private int X, Y;
    // private Area area;
    private Game game; // Add a reference to the Game instance
    private final int width = 100;
    private final int height = 100;

    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }
    public void setX(int x) { this.X = x; }
    public void setY(int y) { this.Y = y; }
    public int getX() { return X; }
    public int getY() { return Y; }

    public void displayPos() {
        System.out.println("DX: " + X + " DY: " + Y);
    }
}
