package minesweeper;

import java.awt.*;
import java.util.*;

public class GameMine {
    static Field f =new Field();
    static ArrayList<FieldPanel> mines=new ArrayList<>();

    static void startGame() {
        f.startGame();

        Timer timer = new Timer();
        timer.schedule(new Second(), 0, 1000);
    }

    static void dfs(int i, int j, boolean last) {
        if (!f.field[i][j].isFlagged && !f.field[i][j].isRevealed) {
            if (f.field[i][j].isMine) {
                f.field[i][j].setBackground(Color.RED);
                f.field[i][j].setBorder(null);
                //reveal all mines
                f.revealMines();
                // game over after few seconds
                f.displayGameOver();

            } else if (!last) {
                f.vis[i][j] = true;
                // reveal this one
                f.field[i][j].setBackground(Color.WHITE);
                f.field[i][j].setBorder(null);
                f.field[i][j].isRevealed =true;
                if (f.field[i][j].value > 0) {
                    f.field[i][j].add(new Label(" " + f.field[i][j].value));
                }
                // colors matter!
                f.revealed++;
                for (int k = 0; k < 8; k++) {
                    int checki = i + f.dir[k][0];
                    int checkj = j + f.dir[k][1];
                    if (checki >= 0 && checkj >= 0 && checki < f.height && checkj < f.width && !f.field[checki][checkj].isMine) {
                        if (!f.vis[checki][checkj]) {
                            dfs(checki, checkj, (f.field[i][j].value > 0) ? true : false);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        startGame();
    }

    static int count=0;
    static String colon=" ";
    static String sec=" ";
    static class Second extends TimerTask {

        public void run() {
            if (f.gameFin) {
                cancel();
            }
            count++;

            if (count%2==0) {
                colon=":";
            } else {
                colon=" ";
            }

            if (count%60<10) {
                sec="0"+count%60;
            } else {
                sec=""+count%60;
            }

            f.timer.setText(count/60+colon+sec);


            if ((f.revealed)==(f.width*f.height)-f.mineNumber) {
                System.out.println("FIN");
                //f.revealMines();
                f.displayGameWon();
                cancel();
            }

        }
    }
}
