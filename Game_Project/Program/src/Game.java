import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game {
    private JFrame frame;
    int currentLevel = 0;
    private Player player = new Player(2, 2);
    Levels levels = new Levels();
    char[][] area = levels.getLevel(currentLevel);
    private ArrayList<Crate> crates = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();

    public Game() {
        initializeGame();
    }

    public ArrayList<Crate> getCrates() {
        return crates;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public boolean isWall(int i, int j) {
        return area[i][j] == '#';
    }

    public boolean matchCoinCrate(int givenX, int givenY) {
        for (Coin coin : coins) {
            if (coin.position.getX() == givenX && coin.position.getY() == givenY) {
                coin.check();
                System.out.println("________ A COIN HAS CHECKED!");
                if (isCompleted()) {
                    nextLevel(); // Move to the next level
                }
                return true;
            }
        }
        return false;
    }

    public void uncheckCoin(int crateOldX, int crateOldY) {
        for (Coin coin : coins) {
            if (coin.position.getX() == crateOldX && coin.position.getY() == crateOldY) {
                System.out.println("________ A COIN HAS UNCHECKED!");
                coin.uncheck();
            }
        }
    }

    public boolean isCompleted() {
        int count = 0;
        for (Coin coin : coins) {
            if (coin.ischecked()) {
                count++;
            }
        }
        return count == coins.size();
    }

    public boolean isCoin(int i, int j) {
        return area[i][j] == '.';
    }

    public boolean isCrate(int i, int j) {
        return area[i][j] == '$';
    }

    public boolean isEmpty(int i, int j) {
        return area[i][j] == ' ';
    }

    public boolean isInBounds(int x, int y) {
        return (y >= 0 && y < area.length) && (x >= 0 && x < area[0].length);
    }

    public void renderWalls(JFrame frame) {
        int tileSize = 100;
        // wall Icon 
        frame.add(player.getLabel());
        ImageIcon wallIcon = new ImageIcon("Program\\imgs\\wall.png");
        Image scaledWallImage = wallIcon.getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
        wallIcon = new ImageIcon(scaledWallImage);

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                if (isWall(i, j)) {
                    JLabel wallLabel = new JLabel(wallIcon);
                    wallLabel.setBounds(j * tileSize, i * tileSize, tileSize, tileSize);
                    frame.add(wallLabel);
                }
                if (isCrate(i, j)) {
                    Crate crate = new Crate(i, j);
                    crates.add(crate);
                    frame.add(crate.getCrateLabel());
                }
                if (isCoin(i, j)) {
                    Coin coin = new Coin(i, j);
                    coins.add(coin);
                    frame.add(coin.getCoinLabel());
                }
            }
        }
    }

    private void nextLevel() {
        currentLevel++;
        if (currentLevel < levels.getTotalLevels()) {
            area = levels.getLevel(currentLevel);
            crates.clear();
            coins.clear();
            frame.getContentPane().removeAll();
            renderWalls(frame);
            frame.repaint();
        } else {
            GameOver(); // Game over if no more levels
        }
    }

    private void GameOver() {
        int choice = JOptionPane.showConfirmDialog(
                frame,
                "LEVEL completed  Do you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            frame.dispose();
            new Game();
        } else {
            frame.dispose();
            System.exit(0);
        }
    }

    private void initializeGame() {
        frame = new JFrame("SOKOBAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600, 600);

        renderWalls(frame);
        addKeyListener();

        frame.setVisible(true);
    }

    private void addKeyListener() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int dx = 0, dy = 0;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        dx = 0;
                        dy = -1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dx = 0;
                        dy = 1;
                        break;
                    case KeyEvent.VK_UP:
                        dx = -1;
                        dy = 0;
                        break;
                    case KeyEvent.VK_DOWN:
                        dx = 1;
                        dy = 0;
                        break;
                    default:
                        return;
                }

                int newX = player.position.getX() + dx;
                int newY = player.position.getY() + dy;

                if (isInBounds(newX, newY) && !isWall(newX, newY)) {
                    if (isCrate(newX, newY)) {
                        int crateNewX = newX + dx;
                        int crateNewY = newY + dy;

                        if (isInBounds(crateNewX, crateNewY) && !isWall(crateNewX, crateNewY)) {
                            for (Crate crate : crates) {
                                if (crate.position.getX() == newX && crate.position.getY() == newY) {
                                    uncheckCoin(crate.position.getX(), crate.position.getY());
                                    crate.move(dx, dy);

                                    if (matchCoinCrate(crateNewX, crateNewY)) {
                                        System.out.println("A coin is here!");
                                    }

                                    area[newX][newY] = ' ';
                                    area[crateNewX][crateNewY] = '$';
                                    break;
                                }
                            }
                        } else {
                            return;
                        }
                    }

                    player.move(dx, dy);
                    area[player.position.getX() - dx][player.position.getY() - dy] = ' ';
                    area[player.position.getX()][player.position.getY()] = '@';
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
