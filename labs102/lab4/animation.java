package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class animation {
    public static void main(String[] args) {
        animation x = new animation();
        x.createGUI();
    }

    int frame_width = 1000;
    int frame_height = 500;

    // instance variables
    boolean play = false;
    boolean stop = true;
    boolean update = false;
    int length = 400; // default
    int wheel_count = 2; // default
    int wheel_radius = 35; // default
    final int first_wheel = 30;
    final int last_wheel = -90;
    int window_count = 5; // default
    int window_size = 40; // default
    final int first_window = 20;
    final int last_window = -20; 
    int direction = 1; // 1 means right -1 means left

    // instance GUI components
    JFrame frame;
    JPanel panel;
    JButton button_play;
    JButton button_stop;
    JButton button_update;
    JLabel label_length;
    JLabel label_wheel;
    JTextField textfield_length;
    JTextField textfield_wheel;

    // instance timer 
    Timer timer;

    // instance ActionListeners
    Play play_ae = new Play();
    Stop stop_ae = new Stop();
    Update update_ae = new Update();
    
    road road_painting = new road();
    bus bus_painting = new bus();

    // GUI generation method
    public void createGUI() {
        timer = new Timer(10, new TimerListener());

        frame = new JFrame("Animated Schoolbus");
        panel = new JPanel();

        panel.setLayout(null);

        button_play = new JButton("Play");
        button_stop = new JButton("Stop");
        button_update = new JButton("Update");

        label_length = new JLabel("Length:");
        label_wheel = new JLabel("Wheel:");

        textfield_length = new JTextField(10);
        textfield_wheel = new JTextField(10);

        button_play.setBounds(frame_width - 125, 20, 100, 30);
        button_stop.setBounds(frame_width - 125, 60, 100, 30);
        label_length.setBounds(20, 20, 100, 15);
        textfield_length.setBounds(70, 20, 100, 15);
        label_wheel.setBounds(25, 60, 100, 15);
        textfield_wheel.setBounds(70, 60, 100, 15);
        button_update.setBounds(20, 90, 150, 30);

        button_play.addActionListener(play_ae);
        button_stop.addActionListener(stop_ae);
        button_update.addActionListener(update_ae);

        road_painting.setBounds(0, 0, frame_width, frame_height);
        bus_painting.setBounds(0, 0, frame_width, frame_height);

        panel.add(button_play);
        panel.add(button_stop);
        panel.add(button_update);
        panel.add(label_length);
        panel.add(label_wheel);
        panel.add(textfield_length);
        panel.add(textfield_wheel);

        frame.add(bus_painting);
        frame.add(road_painting);
        frame.add(panel);

        frame.setVisible(true);
        frame.setSize(frame_width, frame_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ActionListener methods
    private class Play implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           play = true;
           stop = false; 
           timer.start();
        }
    }

    private class Stop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           stop = true;
           play = false; 
           timer.stop();
        }
    }

    private class Update implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            length = Integer.parseInt(textfield_length.getText());
            wheel_count = Integer.parseInt(textfield_wheel.getText());
        }
    }

    // paintComponents
    private class road extends JComponent {
        public void paintComponent(Graphics g) {
            g.setColor(new Color(110, 200, 100));
            g.fillRect(0, frame_height - 200, frame_width, 200);
        }
    }

    private class bus extends JComponent {
        public void paintComponent(Graphics g) {
            g.setColor(new Color(230, 210, 30));
            g.fillRect(frame_width / 2 - 200, frame_height - 230, length, 70);
            g.fillRect(frame_width / 2 - 200, frame_height - 300, length - 70, 70);

            g.setColor(Color.GRAY);
            //g.fillOval(frame_width / 2 - 200 + first_wheel, frame_height - 160 - wheel_radius, wheel_radius * 2, wheel_radius * 2);
            //g.fillOval(frame_width / 2 - 200 + length + last_wheel, frame_height - 160 - wheel_radius, wheel_radius * 2, wheel_radius * 2);
            int space = (length - 50) / (wheel_count - 1);
            wheel_radius = length / 12;
            if (wheel_radius > space / 2 - 10) {wheel_radius = space / 2 - 10;}
            for (int i = 0; i < wheel_count; i++) {
                g.fillOval(frame_width / 2 - 200 + first_wheel + (i * space) - wheel_radius, frame_height - 160 - wheel_radius, wheel_radius * 2, wheel_radius * 2);
            }

            g.setColor(new Color(200, 230, 230));
            int window_size = 50;
            int cursor = 20;
            while (cursor + window_size + 20 < length - 70) {
                g.fillRect(cursor + frame_width / 2 - 200, frame_height - 300 + 10, window_size, window_size);
                cursor += window_size + 20;
            }
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int speed = 3;
            if (play) {
                bus_painting.setLocation(bus_painting.getX() + direction * speed, bus_painting.getY());
    
                if (bus_painting.getX() + frame_width / 2 - 200 <= 0) {
                    direction = 1;
                } 
                else if (bus_painting.getX() + frame_width / 2 - 200 + length >= frame_width) {
                    direction = -1;
                }
    
                bus_painting.repaint();
            }
        }
    }
}


