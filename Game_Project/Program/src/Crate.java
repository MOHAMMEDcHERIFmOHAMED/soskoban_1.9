
import javax.swing.*;
import java.awt.*;

public class Crate {
    Position position; // Crate's position on the grid
    private int tileSize = 100; 
    private int width = 100;   // Image width
    private int height = 100;  // Image height
    private JLabel CrateLabel;

    public Crate(int x, int y) {
        // Initialize the position
        position = new Position(x, y);

        // Initialize the Crate image and label
        ImageIcon CrateIcon = new ImageIcon("Program\\imgs\\crate.png");
        Image CrateImage = CrateIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        CrateLabel = new JLabel(new ImageIcon(CrateImage));
        CrateLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    // return Crate Icon label

    public JLabel getCrateLabel(){
        return this.CrateLabel ;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        CrateLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    public void move(int dx, int dy) {
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        position.setX(newX);
        position.setY(newY);
        CrateLabel.setBounds(newY * tileSize, newX * tileSize, width, height);
    }

    public void displayPos() {
        System.out.println("X: " + position.getX() + ", Y: " + position.getY());
    }

}
