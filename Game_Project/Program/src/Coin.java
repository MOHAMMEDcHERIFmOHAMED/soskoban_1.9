
import javax.swing.*;
import java.awt.*;

public class Coin {

    Position position ; 
    boolean checked = false  ;
    private int tileSize = 100; 
    private int width = 100;   // Image width
    private int height = 100;  // Image height
    private JLabel coinLabel;

    public Coin(int x, int y) {
        // Initialize the position
        position = new Position(x, y);

        // Initialize the Coin image and label
        ImageIcon CoinIcon = new ImageIcon("Program\\imgs\\coin.png");
        Image CoinImage = CoinIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        coinLabel = new JLabel(new ImageIcon(CoinImage));
        coinLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    // return Coin Icon label
    public void check(){
        checked = true ;
    }
    public void uncheck(){
        checked = false ;
    }
    public boolean ischecked(){
        return checked == true ;
    }
    public JLabel getCoinLabel(){
        return this.coinLabel ;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        coinLabel.setBounds(position.getY() * tileSize, position.getX() * tileSize, width, height);
    }

    public void move(int dx, int dy) {
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;
        position.setX(newX);
        position.setY(newY);
        coinLabel.setBounds(newY * tileSize, newX * tileSize, width, height);
    }

    public void displayPos() {
        System.out.println("X: " + position.getX() + ", Y: " + position.getY());
    }
}
