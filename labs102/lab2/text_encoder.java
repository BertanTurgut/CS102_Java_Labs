package lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class text_encoder {
    public static void main(String[] args) {
        String text = read_file("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab2\\input.txt");

        ArrayList<String> word_map_list = word_mapper_1(text);

        String word_map_text = word_map_formatter(word_map_list);

        write_file("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab2\\map.txt", word_map_text);

        String encoded_text = encoder(word_map_list, text);

        write_file("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab2\\output.txt", encoded_text);   
        
        ArrayList<String> x = form_word_map_list(word_map_text);

        String decoded_text = decoder(x, encoded_text);

        write_file("C:\\Users\\berta\\OneDrive\\Masaüstü\\CS102\\labs102\\lab2\\decoded_text.txt", decoded_text); 
    }

    /**
     * This method reads the text file from the input path.
     * @param path
     * @return
     */
    public static String read_file(String path) {
        String str = "";
        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                str += scan.nextLine() + "\n";
            }

            scan.close();
        } 
        catch (IOException e) {
            System.out.print(e);
        }

        return str;
    }

    /**
     * This method creates and writes a text file according to the file path and input text.
     * @param path
     * @param text
     */
    public static void write_file(String path, String text) {
        try {
            FileWriter file_writer = new FileWriter(path);
            file_writer.write(text);
            file_writer.close();
        }
        catch (IOException e) {
            System.out.print(e);
        } 
    }

    /**
     * This method returns a word map for the given input string.
     * @param input_string
     * @return
     */
    public static ArrayList<String> word_mapper_0(String input_string) {
        ArrayList<String> dictionary_original = new ArrayList<>();
        int word_start_index = 0;

        for (int i = 0; i < input_string.length(); i++) {
            if ((i + 1) == input_string.length() || input_string.substring(i, i + 1).equals(" ") || input_string.substring(i, i + 1).equals("\n")) {
                dictionary_original.add(input_string.substring(word_start_index, i));
                word_start_index = i + 1;
            }
        }

        ArrayList<String> dictionary_remastered = new ArrayList<>();

        for (int i = 0; i < dictionary_original.size(); i++) {
            if (i == 0) {
                dictionary_remastered.add(dictionary_original.get(i));
            }
            else {
                boolean add = false;
                for (int a = 0; a < dictionary_remastered.size(); a++) {
                    add = dictionary_original.get(i).equals(dictionary_remastered.get(a)) ? false : true;
                    if (!add)
                        break;
                }
                if (add) {
                    dictionary_remastered.add(dictionary_original.get(i));
                }
            }
        }

        return dictionary_remastered;
    }

    /**
     * This method returns a word map for the given input string.
     * @param input_string
     * @return
     */
    public static ArrayList<String> word_mapper_1(String input_string) {
        ArrayList<String> dictionary_original = new ArrayList<>();
        int word_start_index = 0;

        for (int i = 0; i < input_string.length(); i++) {
            if ((i + 1) == input_string.length() || input_string.substring(i, i + 1).equals(" ") || input_string.substring(i, i + 1).equals("\n")) {
                dictionary_original.add(input_string.substring(word_start_index, i));
                word_start_index = i + 1;
            }
        }

        ArrayList<String> dictionary_remastered = new ArrayList<>();

        for (int i = 0; i < dictionary_original.size(); i++) {
            if (i == 0) {
                dictionary_remastered.add(dictionary_original.get(i));
            }
            else {
                boolean add = false;
                for (int a = 0; a < dictionary_remastered.size(); a++) {
                    add = dictionary_original.get(i).equals(dictionary_remastered.get(a)) ? false : true;
                    if (!add)
                        break;
                }
                if (add) {
                    dictionary_remastered.add(dictionary_original.get(i));
                }
            }
        }

        int count = 1; // any value greater than 0 will work
        while (count != 0) {    
            count = 0;    
            for (int i = 0; i < dictionary_remastered.size(); i++) {
                if (i != dictionary_remastered.size() - 1 && dictionary_remastered.get(i).length() < dictionary_remastered.get(i + 1).length()) {
                    String prev = dictionary_remastered.get(i);
                    String last = dictionary_remastered.get(i + 1);
                    dictionary_remastered.set(i, last);
                    dictionary_remastered.set(i + 1, prev);

                    count++;
                }
            }
        }   

        return dictionary_remastered;
    }

    /**
     * 
     * This methods formats the input word map list into a String.
     * @param word_map_list
     * @return
     */
    public static String word_map_formatter(ArrayList<String> word_map_list) {
        String word_map_string = "";

        for (int i = 0; i < word_map_list.size(); i++) {
            word_map_string += i + ": " + word_map_list.get(i) + "\n";
        }

        return word_map_string.substring(0, word_map_string.length() - 1);
    }

    /**
     * This method returns the encoded text according to the word map and input text.
     * @param text
     * @param map
     * @return
     */
    public static String encoder(ArrayList<String> map, String text) {
        String encoded_text = "";
        int word_start_index = 0;

        for (int i = 0; i < text.length(); i++) {
            if ((i + 1) == text.length() || text.substring(i, i + 1).equals(" ") || text.substring(i, i + 1).equals("\n")) {
                for (int a = 0; a < map.size(); a++) {
                    if (text.substring(word_start_index, i).equals(map.get(a))) {
                        encoded_text += a;

                        if ((i + 1) == text.length()) {
                            break;
                        }
                        else if (text.substring(i, i + 1).equals(" " )) {
                            encoded_text += " ";
                        }
                        else if (text.substring(i, i + 1).equals("\n")) {
                            encoded_text += "\n";
                        }
                    } 
                }

                word_start_index = i + 1;
            }
        }

        return encoded_text;
    }

    /**
     * This method returns the decoded text according to the word map and input text.
     * @param map
     * @param text
     * @return
     */
    public static String decoder(ArrayList<String> map, String text) {
        String decoded_text = "";
        int word_start_index = 0;

        for (int i = 0; i < text.length(); i++) {
            if ((i + 1) == text.length() || text.substring(i, i + 1).equals(" ") || text.substring(i, i + 1).equals("\n")) {
                if ((i + 1) == text.length() && text.substring(i, i + 1).equals("\n")) {
                    decoded_text += map.get(Integer.parseInt(text.substring(word_start_index, i)));
                }
                else if ((i + 1) == text.length() && !text.substring(i, i + 1).equals("\n")) {
                    decoded_text += map.get(Integer.parseInt(text.substring(word_start_index, i + 1)));
                }
                else {
                    decoded_text += map.get(Integer.parseInt(text.substring(word_start_index, i)));
                }

                if (text.substring(i, i + 1).equals(" " )) {
                    decoded_text += " ";
                }
                else if (text.substring(i, i + 1).equals("\n")) {
                    decoded_text += "\n";
                } 

                word_start_index = i + 1;
            }
        }

        return decoded_text;
    }


    /**
     * This method returns an ArrayList<String> word_map according to the input text of word_map.
     * @param word_map_text
     * @return
     */
    public static ArrayList<String> form_word_map_list(String word_map_text) {
        int word_count = 0;

        for (int i = 0; i < word_map_text.length(); i++) {
            if (word_map_text.substring(i, i + 1).equals(":")) {
                word_count++;
            }
        }

        String[] word_map_array = new String[word_count];
        int index = 0;
        String word = "";
        int word_start_index = 0;

        for (int i = 0; i < word_map_text.length(); i++) {
            if (word_map_text.substring(i, i + 1).equals(":")) {
                index = Integer.parseInt(word_map_text.substring(word_start_index, i));
                word_start_index = i + 2; // we are adding 2 to skip the space between ":" and the word.
            }

            if (word_map_text.substring(i, i + 1).equals("\n")) {
                word = word_map_text.substring(word_start_index, i);
                word_start_index = i + 1;

                word_map_array[index] = word;
            }

            if (i == word_map_text.length() - 1) {
                word = word_map_text.substring(word_start_index, i + 1);
                word_start_index = i + 1;

                word_map_array[index] = word;
            }
        }

        ArrayList<String> word_map_list = new ArrayList<>();

        for (int i = 0; i < word_count; i++) {
            word_map_list.add(word_map_array[i]);
        }

        return word_map_list;
    }
}
