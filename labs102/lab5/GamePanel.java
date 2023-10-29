package lab5;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public GamePanel(int depth) {
        GridLayout gl = new GridLayout(2, 2);
        this.setLayout(gl);

        if (depth > 0) {
            for (int i = 0; i < 4; i++) {
                this.add(new GamePanel(depth - 1));
            }
        }
        else if (depth == 0) {
            Player.increase_grid_count(1);

            button1 = new JButton();
            button2 = new JButton();
            button3 = new JButton();
            button4 = new JButton();

            Button_Color b1 = new Button_Color();
            Button_Color b2 = new Button_Color();
            Button_Color b3 = new Button_Color();
            Button_Color b4 = new Button_Color();
    
            button1.addActionListener(b1);
            button2.addActionListener(b2);
            button3.addActionListener(b3);
            button4.addActionListener(b4);

            this.add(button1);
            this.add(button2);
            this.add(button3);
            this.add(button4);

            button1.doClick(1);
            button2.doClick(1);
            button3.doClick(1);
            button4.doClick(1);
        }   

        Player.setMove(0);
        Player.setScore(10);
    }

    private class Button_Color implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random random = new Random();
            int color = random.nextInt(3);

            if (e.getSource() == button1) {
                switch (color) {
                    case 0:
                        button1.setBackground(Color.BLUE);
                        break;
                    case 1:
                        button1.setBackground(Color.GREEN);
                        break;
                    case 2: 
                        button1.setBackground(Color.RED);
                        break;
                } 
            }
            else if (e.getSource() == button2) {
                switch (color) {
                    case 0:
                        button2.setBackground(Color.BLUE);
                        break;
                    case 1:
                        button2.setBackground(Color.GREEN);
                        break;
                    case 2: 
                        button2.setBackground(Color.RED);
                        break;
                }
            }
            else if (e.getSource() == button3) {
                switch (color) {
                    case 0:
                        button3.setBackground(Color.BLUE);
                        break;
                    case 1:
                        button3.setBackground(Color.GREEN);
                        break;
                    case 2: 
                        button3.setBackground(Color.RED);
                        break;
                }
            }
            else if (e.getSource() == button4) {
                switch (color) {
                    case 0:
                        button4.setBackground(Color.BLUE);
                        break;
                    case 1:
                        button4.setBackground(Color.GREEN);
                        break;
                    case 2: 
                        button4.setBackground(Color.RED);
                        break;
                }
            }
            
            Player.increaseMove(1);

            if (button1.getBackground().equals(button2.getBackground()) &&
                button2.getBackground().equals(button3.getBackground()) &&
                button3.getBackground().equals(button4.getBackground())) {
                button1.setBackground(Color.GRAY);
                button1.setEnabled(false);
                button2.setBackground(Color.GRAY);
                button2.setEnabled(false);
                button3.setBackground(Color.GRAY);
                button3.setEnabled(false);
                button4.setBackground(Color.GRAY);
                button4.setEnabled(false);

                Player.increase_grid_count(-1);

                Player.manageScore(true);
            }
            else {
                Player.manageScore(false);
            }
        }
    }
}
