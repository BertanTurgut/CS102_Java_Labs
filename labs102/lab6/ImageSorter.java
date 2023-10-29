package lab6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ImageSorter {
    public static void main(String[] args) {
        ImageSorter i_s = new ImageSorter();
        i_s.setUI();
    }
    /**
     * This method reads the image from the input filepath and stores a new buffered image at the specified filepath inside the method.
     * @param filepath
     */
    public static void loadImage(String filepath) {
        int width = 600; 
        int heigth = 600;
        BufferedImage buffered_image = null;

        try {
            File input_image = new File(filepath);

            buffered_image = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
            
            buffered_image = ImageIO.read(input_image);

            System.out.println("Importing completed.");
        }
        catch (IOException e) {
            System.out.println(e);
        }

        try {
            File path_buffered_image = new File("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab6\\buffered_image.png");
            
            ImageIO.write(buffered_image , "png", path_buffered_image);

            System.out.println("Storing completed.");
        } 
        catch (IOException e) {
            System.out.println(e);
        }

        _buffered_image = buffered_image;
    }

    /**
     * This method sets the input label's image icon as the input image and afterwards adds the label to the frame.
     * @param frame
     * @param label
     * @param bufferedImage
     */
    public static void displayImage(JFrame frame, JLabel label, BufferedImage bufferedImage) {
        label.setIcon(new ImageIcon(bufferedImage));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setBounds(0, 0, label.getPreferredSize().width, label.getPreferredSize().height);
        frame.add(label);
    }  
    
    /**
     * This method returns the indicated pixel's brightness value.
     * @param buffered_image
     * @param x
     * @param y
     * @return Brightness value of the specified pixel
     */
    public static double getBrightnessValue(BufferedImage buffered_image, int x, int y) {
        int pixel = buffered_image.getRGB(x, y);
        Color color = new Color(pixel, true);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        double brightness = red * 0.2126 + green * 0.7152 + blue * 0.0722;

        return brightness;
    }

    /**
     * This method returns the complete pixel map of the input buffered image as a double[][].
     * @param buffered_image
     * @return Pixel map as a double[][]
     */
    public static double[][] getPixelArray(BufferedImage buffered_image) {
        double[][] pixelArray = new double[buffered_image.getHeight()][buffered_image.getWidth()];

        for (int y = 0; y < buffered_image.getHeight(); y++) {
            for (int x = 0; x < buffered_image.getWidth(); x++) {
                pixelArray[y][x] = buffered_image.getRGB(x, y);
            }
        }

        return pixelArray;
    }

    public static int horizontalStep(BufferedImage image, int row) {
        double[][] array = getPixelArray(image);

        int counter = 0;
        double a;
        /*
        do {
            counter = 0;
            for (int x = 0; x < array[row].length - 1; x++) {
                if (getBrightnessValue(image, x, row) < getBrightnessValue(image, x + 1, row)) {
                    a = array[row][x];

                    array[row][x] = array[row][x + 1];
                    image.setRGB(x, row, (int)array[row][x + 1]);

                    array[row][x + 1] = a;
                    image.setRGB(x + 1, row, (int)a);
    
                    counter++;
                }
            }
        }
        while (counter != 0); */

        for (int x = 0; x < array[row].length - 1; x++) {
            if (getBrightnessValue(image, x, row) < getBrightnessValue(image, x + 1, row)) {
                a = array[row][x];

                array[row][x] = array[row][x + 1];
                image.setRGB(x, row, (int)array[row][x + 1]);

                array[row][x + 1] = a;
                image.setRGB(x + 1, row, (int)a);

                counter++;
            }
        }

        return counter;
    }

    public static int verticalStep(BufferedImage image, int column) {
        double[][] array = getPixelArray(image);

        int counter = 0;
        double a;
        /*
        do {
            counter = 0;
            for (int y = 0; y < array.length - 1; y++) {
                if (getBrightnessValue(image, column, y) < getBrightnessValue(image, column, y + 1)) {
                    a = array[y][column];

                    array[y][column] = array[y + 1][column];
                    image.setRGB(column, y, (int)array[y + 1][column]);

                    array[y + 1][column] = a;
                    image.setRGB(column, y + 1, (int)a);

                    counter++;
                }
            }
        }
        while (counter != 0); */

        for (int y = 0; y < array.length - 1; y++) {
            if (getBrightnessValue(image, column, y) < getBrightnessValue(image, column, y + 1)) {
                a = array[y][column];

                array[y][column] = array[y + 1][column];
                image.setRGB(column, y, (int)array[y + 1][column]);

                array[y + 1][column] = a;
                image.setRGB(column, y + 1, (int)a);

                counter++;
            }
        }

        return counter;
    }

    public void diagonalStep(JFrame frame, JLabel label, BufferedImage buffered_image) {
        /*
        if (_row < _height) {
            horizontalStep(_buffered_image, _row);
            _row++;
        }
        else if (_column < _width) {
            verticalStep(_buffered_image, _column);
            _column++;
        }
        else {
            this.timer.stop();
        }

        displayImage(_frame, _label, _buffered_image); */

        //int a = horizontalStep(buffered_image, _row);
        _row++;
        //int b = verticalStep(buffered_image, _column);
        _column++;

        if (_row == _height) _row = 0;
        if (_column == _width) _column = 0;

        displayImage(_frame, _label, _buffered_image);
    }

    //
    public static JFrame _frame = new JFrame();
    public static JLabel _label = new JLabel();
    public static BufferedImage _buffered_image;
    public static int _row = 0;
    public static int _column = 0;
    public static int _width;
    public static int _height;

    public static JButton _restart = new JButton("Restart");
    public static JButton _speed;
    //
    public class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            diagonalStep(_frame, _label, _buffered_image);
        }
    }
    
    // 
    private Timer timer = new Timer(10, new TimerListener());
    //
    public void startAnimatedBubbleSort() {
        this.timer.start();
        this.timer.setDelay(0);
    }

    public class Key_Listener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 38: // up
                    if (timer.getDelay() != 0) 
                        timer.setDelay(timer.getDelay() - 50);
                    break;
                case 40: // down
                    timer.setDelay(timer.getDelay() + 50);
                    break;
                case 82: // r
                    timer.restart();
                    
                    _row = 0;
                    _column = 0;

                    loadImage("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab6\\lab6_image.jpg");
                    displayImage(_frame, _label, _buffered_image);

                    break;
            }            
        }
    }

    public Key_Listener key_listener = new Key_Listener();

    public void setUI() {
        int width = 600;
        int height = 600;
        _frame.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - width / 2, (int)Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2, width, height);

        JLabel label = _label;

        loadImage("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab6\\lab6_img.png");

        BufferedImage buffered_image = null;
        try {
            File file = new File("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab6\\buffered_image.png");
            buffered_image = ImageIO.read(file);
            _buffered_image = buffered_image;
            _width = _buffered_image.getWidth();
            _height = _buffered_image.getHeight();
        } 
        catch (Exception e) {
            System.out.println(e);
        }

        _frame.addKeyListener(key_listener);

        startAnimatedBubbleSort();

        _frame.setLayout(new BorderLayout());
        _frame.add(label, BorderLayout.CENTER);
        
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);
    }
}



