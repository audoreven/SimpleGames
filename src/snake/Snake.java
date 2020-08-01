package snake;

import java.awt.*;

public class Snake {
    Snake behind; // pixel before
    Snake before; // pixel after
    int x; int y; // posi
    int dir; // 1 -> right | 2 -> down | 3 -> up | 4 -> left
    static int size=1;

    public Snake(Snake b, Snake f, int x, int y, int d, boolean isHead) {
        this.behind =b;
        this.before=f;
        this.x=x;
        this.y=y;
        this.dir=d;
        if (isHead) size=1;
    }

    void add() {
        if (size==1) {
            Game.tail = new Snake(null, Game.head,
                    Game.head.x + Game.dir[3 - Game.head.dir][0],
                    Game.head.y + Game.dir[3 - Game.head.dir][1],
                    Game.head.dir, false);
            Game.head.behind=Game.tail;
        } else {
            Snake newT = Game.tail;
            Game.tail = new Snake(null, newT, newT.x, newT.y, newT.dir, false);
            newT.behind=Game.tail;
            System.out.println(Game.tail);
        }
        Game.board[Game.tail.x][Game.tail.y]=1;
        Game.displayBoard.grid[Game.tail.x][Game.tail.y].setBackground(Color.BLACK);
        size++;
        // REWRITE
        System.out.println("+1");


    }


    void move(Snake s, int px, int py) {
        if (size==1) { // is only node
            // move to new location
            Game.displayBoard.grid[s.x][s.y].setBackground(Color.WHITE);
            Game.board[s.x][s.y]=0;
            s.x+=Game.dir[dir][0];
            s.y+=Game.dir[dir][1];
            // check if obstacle
            if (Game.board[s.x][s.y]==-1) {
                add();
                Game.displayBoard.createFood();
            } else if (Game.board[s.x][s.y]==1) {
                die();
            }
            if (s.x >= 0 && s.x < 55 && s.y < 75 && s.y >= 0) {
                Game.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
                Game.board[s.x][s.y]=1;
            }

        } else if (Game.head==s) {
            // move to new location
            px=s.x; py=s.y;
            s.x+=Game.dir[dir][0];
            s.y+=Game.dir[dir][1];
            // check if obstacle
            if (Game.board[s.x][s.y]==-1) {
                add();
                Game.displayBoard.createFood();
            } else if (Game.board[s.x][s.y]==1) {
                die();
            }
            // change bg
            Game.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
            Game.board[s.x][s.y]=1;
            move(s.behind, px, py);
        } else {
            // follow the one before
            int newpx=s.x; int newpy=s.y;
            s.x=px;
            s.y=py;
            Game.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
            Game.board[s.x][s.y]=1;
            if (Game.tail==s) {
                Game.displayBoard.grid[newpx][newpy].setBackground(Color.WHITE);
                Game.board[newpx][newpy]=0;
            } else {
                move(s.behind, newpx, newpy);
            }
        }
    }
    void die() {
        Game.displayBoard.displayGameOver();
        Game.start=false;
    }

}
