package minesweeper;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class test extends JFrame {

    public static void main(String[] args) {
        test s = new test();
        s.setVisible(true);

    }

    public test() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setTitle("Frame");
        setLayout(null);
        JPanel but = new JPanel();
        but.setBounds(0, 0, 120, 50);
        but.setBackground(Color.BLACK);
        /*but.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e))
                    System.out.println("right");
                else if(SwingUtilities.isLeftMouseButton(e))
                    System.out.println("left");
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
        });*/
        but.addMouseListener(new MyMouseListener());
        add(but);
    }

}