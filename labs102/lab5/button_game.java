package lab5;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class button_game {    
    public static void main(String[] args) {
        Boolean continue_game = true;

        do {
            JFrame frame = new JFrame();
            frame.setSize(700,700);   
    
            frame.add(new GamePanel(1));
    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            while (Player.getScore() > 0 && Player.get_grid_count() != 0) {
                frame.setTitle("Score: " + Integer.toString(Player.getScore()) + " || Move: " + Player.getMove() + " || Remaining Grids: " + Player.get_grid_count());
            }

            if (Player.get_grid_count() == 0) {
                int x = JOptionPane.showConfirmDialog(frame, "You have won the game. Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    continue_game = true;
                }
                else {
                    continue_game = false;
                }
            }
            else if (Player.getScore() == 0) {
                int x = JOptionPane.showConfirmDialog(frame, "You have lost the game. Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    continue_game = true;
                }
                else {
                    continue_game = false;
                }
            }

            frame.dispose();

            Player.set_grid_count(0);
        }
        while (continue_game);
    }



}
