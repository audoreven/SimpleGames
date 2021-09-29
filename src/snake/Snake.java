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
            GameSnek.tail = new Snake(null, GameSnek.head,
                    GameSnek.head.x + GameSnek.dir[3 - GameSnek.head.dir][0],
                    GameSnek.head.y + GameSnek.dir[3 - GameSnek.head.dir][1],
                    GameSnek.head.dir, false);
            GameSnek.head.behind= GameSnek.tail;
        } else {
            Snake newT = GameSnek.tail;
            GameSnek.tail = new Snake(null, newT, newT.x, newT.y, newT.dir, false);
            newT.behind= GameSnek.tail;
            System.out.println(GameSnek.tail);
        }
        GameSnek.board[GameSnek.tail.x][GameSnek.tail.y]=1;
        GameSnek.displayBoard.grid[GameSnek.tail.x][GameSnek.tail.y].setBackground(Color.BLACK);
        size++;
        // REWRITE
        System.out.println("+1");


    }


    void move(Snake s, int px, int py) {
        if (size==1) { // is only node
            // move to new location
            GameSnek.displayBoard.grid[s.x][s.y].setBackground(Color.WHITE);
            GameSnek.board[s.x][s.y]=0;
            s.x+= GameSnek.dir[dir][0];
            s.y+= GameSnek.dir[dir][1];
            // check if obstacle
            if (GameSnek.board[s.x][s.y]==-1) {
                add();
                GameSnek.displayBoard.createFood();
            } else if (GameSnek.board[s.x][s.y]==1) {
                die();
            }
            if (s.x >= 0 && s.x < 55 && s.y < 75 && s.y >= 0) {
                GameSnek.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
                GameSnek.board[s.x][s.y]=1;
            }

        } else if (GameSnek.head==s) {
            // move to new location
            px=s.x; py=s.y;
            s.x+= GameSnek.dir[dir][0];
            s.y+= GameSnek.dir[dir][1];
            // check if obstacle
            if (GameSnek.board[s.x][s.y]==-1) {
                add();
                GameSnek.displayBoard.createFood();
            } else if (GameSnek.board[s.x][s.y]==1) {
                die();
            }
            // change bg
            GameSnek.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
            GameSnek.board[s.x][s.y]=1;
            move(s.behind, px, py);
        } else {
            // follow the one before
            int newpx=s.x; int newpy=s.y;
            s.x=px;
            s.y=py;
            GameSnek.displayBoard.grid[s.x][s.y].setBackground(Color.BLACK);
            GameSnek.board[s.x][s.y]=1;
            if (GameSnek.tail==s) {
                GameSnek.displayBoard.grid[newpx][newpy].setBackground(Color.WHITE);
                GameSnek.board[newpx][newpy]=0;
            } else {
                move(s.behind, newpx, newpy);
            }
        }
    }
    void die() {
        GameSnek.displayBoard.displayGameOver();
        GameSnek.start=false;
    }

}
