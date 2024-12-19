import javax.swing.*;
import java.awt.*;

public class Player {
     Position position; // Player's position on the grid
    private int tileSize = 100; 
    private int width = 100;   // Image width
    private int height = 100;  // Image height
    private JLabel playerLabel;

    public Player(int x, int y) {
        // Initialize the position
        position = new Position(x, y);

        // Initialize the player image and label
        ImageIcon playerIcon = new ImageIcon("Program\\imgs\\char.png");
        Image playerImage = playerIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        playerLabel = new JLabel(new ImageIcon(playerImage));
        playerLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    // return player Icon label

    public JLabel getLabel(){
        return this.playerLabel ;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        playerLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    public void move(int dx, int dy) {
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        position.setX(newX);
        position.setY(newY);
        playerLabel.setBounds(newY * tileSize, newX * tileSize, width, height);
    }

    public void displayPos() {
        System.out.println("X: " + position.getX() + ", Y: " + position.getY());
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }
}
