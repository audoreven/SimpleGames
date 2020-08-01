package snake;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class test extends JPanel {
    public test() {
        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(new JButton("Foo"));
        centerPanel.add(new JButton("Bar"));

        JLabel bottomLabel = new JLabel("Bottom Label", SwingConstants.CENTER);

        int gap = 5;
        setLayout(new BorderLayout(gap, gap));
        setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        add(centerPanel, BorderLayout.CENTER);
        add(bottomLabel, BorderLayout.PAGE_END);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("TimerFoo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new test());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static Snake head=new Snake(null, null, 30, 30, 2, true);
    static Snake tail=head;
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });*/

        tail.add();

        System.out.println(head.x+" "+head.y);
        System.out.println(tail.x+" "+tail.y);

        double hi=Math.random();
        System.out.println(hi*100);
    }
}