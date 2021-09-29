package snake;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameSnek {
    static int[][] dir={{0,1} /*right*/ ,{1,0} /*down*/ ,{-1,0} /*up*/,{0,-1} /*left*/}; // {y,x}
    static Snake head;
    static Snake tail;
    static int[][] board=new int[55][75];
    static boolean start=true;
    static SnekBoard displayBoard = new SnekBoard();

    // make food
    static void startGame() {
        // create board
        displayBoard.startGame();

        // create snake
        // 28, 38
        displayBoard.grid[27][37].setBackground(Color.BLACK);
        board[27][37] = 1;
        head = new Snake(tail, null, 27, 37, 2, true);

        // create food (blue)
        displayBoard.createFood();

        // move per 0.1 sec when in bound
        Timer timer = new Timer();
        timer.schedule(new Movement(), 0, 75);

        if (head.x < 0 && head.x >= 55 && head.y >= 75 && head.y < 0 && Snake.size != 55 * 75) {
            displayBoard.displayGameOver();
            start=false;
//              break;
        }
    }
    public static void main(String[] args) {
        //while start
        if (start) {
            startGame();
        }
        // check if they pressed "ENTER"

    }
    static int count=0;
    static class Movement extends TimerTask {

        public void run() {
            head.move(head, 27,37);
            count++;

            if (head.x == 0 && head.dir==2) {
                displayBoard.displayGameOver();
                start=false;
                cancel();
            }
            else if (head.y == 0 && head.dir==3) {
                displayBoard.displayGameOver();
                start=false;
                cancel();
            }else if (head.x == 54 && head.dir==1) {
                displayBoard.displayGameOver();
                start=false;
                cancel();
            } else if (head.y == 74 && head.dir==0) {
                displayBoard.displayGameOver();
                start=false;
                cancel();
            }

            if (start==false) {
                cancel();
            }

            /*System.out.println(*//*tail.x + " " + tail.y+" "+*//*Snake.size);
            if (count>3) {
                System.out.println("STOP");
            }*/

        }
    }

}
