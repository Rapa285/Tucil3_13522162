package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictInit {
    public static Map<Integer, List<String>> init(String dict){
        Map<Integer, List<String>> wordLists = new HashMap<Integer, List<String>>();
        String filename = "";
        switch(dict){
            case "Dictionary Asisten":
                filename = "src\\Dict_Asisten.txt";
                break;
            case "Github Dictionary":
                filename = "src\\WordList.txt";
                break;
            case "Basic Dictionary 2":
                filename = "src\\WordList2.txt";
                break;
            case "Collins Scrabble Words (2019)":
                filename = "src\\Collins Scrabble Words (2019).txt";
                break;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove leading and trailing whitespaces
                line = line.trim();

                // Calculate the length of the word
                int length = line.length();

                // Get the list of strings for the current word length
                List<String> wordList = wordLists.getOrDefault(length, new ArrayList<String>());

                // Add the word to the list
                wordList.add(line);

                // Update the map with the modified list
                wordLists.put(length, wordList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordLists;
    }
}
