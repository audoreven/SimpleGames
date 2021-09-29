package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.*;

public class SnekBoard /*extends JPanel */ implements KeyListener{
    JPanel[][] grid=new JPanel[55][75];
    JFrame game= new JFrame("Snek");
    JPanel bg=new JPanel();
    JPanel board=new JPanel();
    Popup gmrOvrPopup;
    Popup gmWonPopup;
    //GameSnek game=new GameSnek();

    void startGame(){
        // create bg
        JLabel title = new JLabel("The Snek Game",  SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        Dimension size = title.getPreferredSize();
        title.setBounds(225, 25, size.width, size.height);
        /*JLabel score = new JLabel("Score: 0");
        score.setFont(new Font("Verdana", Font.PLAIN, 20));
        Dimension size2 = score.getPreferredSize();
        score.setBounds(525, 100, size2.width, size2.height);*/
        bg.setLayout(null);
        bg.setBounds(0,0,750,600);
        bg.setBackground(Color.GRAY);
        bg.add(title);
        //bg.add(score);
        // add instructions to restart at bottom

        //create board
        JPanel board=new JPanel();
        board.setBackground(Color.GRAY);
        board.setLayout(new GridLayout(55,75));
        for (int i = 0; i < 55; i++) {
            for (int j = 0; j < 75; j++) {
                JPanel panel = new JPanel();
                if ((i>0 && i<54) && (j>0 && j<74)) {
                    panel.setBackground(Color.WHITE);
                } else {
                    panel.setBackground(Color.BLACK);
                }
                //panel.setSize(5,5);
                grid[i][j] = panel;
                board.add(panel);
            }
        }
        board.setBounds(125,125,500,375);
        bg.add(board);

        //create game
        game.add(bg);
        game.setSize(750,600);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(null);
        game.setVisible(true);

        game.addKeyListener(new SnekBoard());

    }

    // create new food
    void createFood() {
        double randx = Math.random()*53+1;
        double randy = Math.random()*73+1;
        if (GameSnek.board[(int) randx][(int) randy] == 0) {
            this.grid[(int) randx][(int) randy].setBackground(Color.RED);
            GameSnek.board[(int) randx][(int) randy] = -1;
        } else {
            createFood();
        }
    }

    // check change in movement
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                if (GameSnek.head.dir!=1 || Snake.size==1) {
                    //System.out.println("UP");
                    GameSnek.head.dir = 2;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (GameSnek.head.dir!=2 || Snake.size==1) {
                    //System.out.println("DOWN");
                    GameSnek.head.dir = 1;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (GameSnek.head.dir!=0 || Snake.size==1) {
                    //System.out.println("LEFT");
                    GameSnek.head.dir = 3;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (GameSnek.head.dir!=3 || Snake.size==1) {
                    //System.out.println("RIGHT");
                    GameSnek.head.dir = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
                // figure out how to restart ig
                if (!GameSnek.start) {
                    System.out.println("helloooo restartttt");
                    GameSnek.start = true;
                    GameSnek.startGame();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void displayGameOver() {
        JPanel gmOvr=new JPanel();
        JLabel gameOverText = new JLabel("Game Over!",  SwingConstants.CENTER);
        gameOverText.setFont(new Font("Monospace", Font.BOLD, 40));
        gameOverText.setForeground(Color.RED);
        gmOvr.setBackground(Color.BLACK);
        gmOvr.add(gameOverText);

        PopupFactory gm=new PopupFactory();

        gmrOvrPopup =gm.getPopup(game, gmOvr, 265, 325);

        gmrOvrPopup.show();
    }

    void displayGameWon() {
        JPanel gmWon=new JPanel();
        JLabel gameWonText = new JLabel("You Won!",  SwingConstants.CENTER);
        gameWonText.setFont(new Font("Monospace", Font.BOLD, 40));
        gameWonText.setForeground(Color.GREEN);
        gmWon.setBackground(Color.BLACK);
        gmWon.add(gameWonText);

        // maybe add later
        /*JLabel congrats = new JLabel("good job. do you want a cookie?",  SwingConstants.CENTER);
        congrats.setFont(new Font("Monospace", Font.BOLD, 10));
        congrats.setForeground(Color.LIGHT_GRAY);*/

        PopupFactory gm=new PopupFactory();

        gmWonPopup =gm.getPopup(game, gmWon, 265, 325);

        gmWonPopup.show();
    }


}
