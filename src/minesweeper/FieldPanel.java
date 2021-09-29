package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldPanel extends JPanel {
    int i; int j;
    int value=0;
    boolean isMine;
    boolean isFlagged=false;
    boolean isRevealed =false;

    public FieldPanel(int i, int j) {
        this.i=i;
        this.j=j;
        isMine=(Math.random()<.15)? true : false;
        if (isMine) {
            GameMine.f.mineNumber++;
            GameMine.mines.add(this);
        }
    }

    public void setFlagStatus(boolean flag) {
        this.isFlagged=flag;
        // show it is flagged, add picture on it

        // check if all mines are flagged NOT HOW IT WORKS
        /*boolean all=true;
        for (FieldPanel p : GameMine.mines) {
            if (!p.isFlagged) {
                all=false;
            }
        }
        if (all) {
            GameMine.f.displayGameWon();
        }*/
    }
}
