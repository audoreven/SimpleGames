package minesweeper;

import snake.GameSnek;
import snake.Snake;
import snake.SnekBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field {
    int height = 9;
    int width = 12;
    FieldPanel[][] field = new FieldPanel[height][width];
    boolean[][] vis = new boolean[height][width];
    JFrame game = new JFrame("minesweeoer");
    JPanel bg = new JPanel();
    JPanel board = new JPanel();
    Popup gmrOvrPopup; boolean gameFin;
    Popup gmWonPopup;
    int mineNumber=0; int revealed=0; int mineFlagged=0;
    JLabel timer = new JLabel("0:00");
    int[][] dir = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}, {1, -1}, {0, -1}, {-1, 0}, {-1, -1}};

    void startGame() {
        // create bg
        JLabel title = new JLabel("minesweeper", SwingConstants.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 36));
        Dimension size = title.getPreferredSize();
        title.setBounds(250, 25, size.width, size.height);
        bg.setLayout(null);
        bg.setBounds(0, 0, 750, 600);
        bg.setBackground(Color.LIGHT_GRAY);
        bg.add(title);
        // add instructions to restart at bottom

        //create board
        JPanel board = new JPanel();
        board.setBackground(Color.GRAY);
        board.setLayout(new GridLayout(height, width));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                FieldPanel panel = new FieldPanel(i, j);
                panel.setBackground(Color.GRAY);
                panel.setBorder(BorderFactory.createSoftBevelBorder(0));
                panel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(SwingUtilities.isRightMouseButton(e)) {
                            // flag
                            if (!panel.isFlagged) {
                                panel.setFlagStatus(true);
                                panel.setBorder(BorderFactory.createLineBorder(Color.RED));
                            } else {
                                panel.setFlagStatus(false);
                                panel.setBorder(BorderFactory.createSoftBevelBorder(0));
                            }
                        }
                        else if(SwingUtilities.isLeftMouseButton(e)) {
                            // dfs
                            if (gameFin) {
                                gmrOvrPopup.hide();
                                gameFin=false;
                            } else {
                                GameMine.dfs(panel.i, panel.j, false);
                            }
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });

                field[i][j] = panel;
                board.add(panel);
            }
        }
        // set up
        setUp();
        board.setBounds(125, 125, 500, 375);
        bg.add(board);

        // add number of mines
        JLabel mines = new JLabel("Mines: "+mineNumber);
        mines.setFont(new Font("Courier New", Font.PLAIN, 20));
        Dimension size2 = mines.getPreferredSize();
        mines.setBounds(135, 100, size2.width, size2.height);
        bg.add(mines);

        // timer
        timer.setFont(new Font("Courier New", Font.PLAIN, 20));
        Dimension size3 = timer.getPreferredSize();
        timer.setBounds(565, 100, size2.width, size2.height);
        bg.add(timer);

        //create game
        game.add(bg);
        game.setSize(750, 600);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(null);
        game.setVisible(true);

    }

    void setUp() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < 8; k++) {
                    int checki = i + dir[k][0];
                    int checkj = j + dir[k][1];
                    if (checki >= 0 && checkj >= 0 && checki < height && checkj < width && !field[i][j].isMine) {
                        if (field[checki][checkj].isMine) {
                            field[i][j].value += 1;
                        }
                    }
                }
            }
        }
    }

    void revealMines() {
        for (FieldPanel p : GameMine.mines) {
            p.setBorder(null);
            p.setBackground(Color.RED);
        }
    }

    void displayGameOver() {
        gameFin=true;
        JPanel gmOvr = new JPanel();
        JLabel gameOverText = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverText.setFont(new Font("Monospace", Font.BOLD, 40));
        gameOverText.setForeground(Color.RED);
        gmOvr.setBackground(Color.BLACK);
        gmOvr.add(gameOverText);

        PopupFactory gm = new PopupFactory();

        gmrOvrPopup = gm.getPopup(game, gmOvr, 265, 325);

        gmrOvrPopup.show();
    }

    void displayGameWon() {
        gameFin=true;
        JPanel gmWon = new JPanel();
        JLabel gameWonText = new JLabel("You Won!", SwingConstants.CENTER);
        gameWonText.setFont(new Font("Monospace", Font.BOLD, 40));
        gameWonText.setForeground(Color.GREEN);
        gmWon.setBackground(Color.BLACK);
        gmWon.add(gameWonText);

        // maybe add later
        /*JLabel congrats = new JLabel("good job. do you want a cookie?",  SwingConstants.CENTER);
        congrats.setFont(new Font("Monospace", Font.BOLD, 10));
        congrats.setForeground(Color.LIGHT_GRAY);*/

        PopupFactory gm = new PopupFactory();

        gmWonPopup = gm.getPopup(game, gmWon, 275, 325);

        gmWonPopup.show();
    }

}
