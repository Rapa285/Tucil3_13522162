package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static List<String> getWordsList(int length){
        List<String> words = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\WordList.txt"))) {
            String line;
            // Read lines from the file until the end of the file is reached
            while ((line = reader.readLine()) != null) {
                // Add each line to the list
                if (line.length() == length){
                    words.add(line);
                }
            }
        } catch (IOException e) {
            // Handle file I/O exception
            e.printStackTrace();
        }
        words.remove(0);
        words.remove(0);
        return words;
    }
    public static void main(String[] args){
        String inputFileName = "src\\WordList2.txt";
        String outputFileName = "WordList2.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Check if the line contains a comma
                    // Split the line by comma and write each part to the output file
                bw.write(line.toLowerCase());
                bw.newLine();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done transfering");
    }
}
