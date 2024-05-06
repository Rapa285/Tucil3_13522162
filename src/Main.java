package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static String Dictionary = "Dictionary Asisten";

    public static String[] InputWords(Scanner scanner,  Map<Integer, List<String>> dicts){
        String[] words = {"",""};
        
        System.out.print("Enter start word: ");
        words[0] = scanner.nextLine().toLowerCase();
        System.out.print("Enter target word: ");
        words[1] = scanner.nextLine().toLowerCase();
        
        if (words[0].length() != words[1].length()) {
            throw new IllegalArgumentException(new Throwable("DifferenLengthError"));
        }else if(words[0].equals(words[1])){
            throw new IllegalArgumentException(new Throwable("SameWordError"));
        }
        List<String> dict = dicts.get(words[0].length());

        if(!dict.contains(words[0])){
            throw new IllegalArgumentException(new Throwable("StartNotInDictError"));
        }else if (!dict.contains(words[1])){
            throw new IllegalArgumentException(new Throwable("TargetNotInDictError"));
        }
        
        return words;
    }

    public static void change_dict(){
        //System.out.println("Current Dictionary : " + Dictionary);
        switch (Dictionary){
            case "Github Dictionary":
                Dictionary = "Collins Scrabble Words (2019)";
                break;
            case "Collins Scrabble Words (2019)":
                Dictionary = "Basic Dictionary 2";
                break;
            case "Basic Dictionary 2":
                Dictionary = "Dictionary Asisten";
                break;
            case "Dictionary Asisten":
                Dictionary = "Github Dictionary";
                break;
        }
        System.out.println("Changed into : "+Dictionary);
        
        
    }

    public static void Solve(int Algorithm, String startWord, String targetWord,Map<Integer, List<String>> dicts){
        
        List<String> dict = dicts.get(startWord.length());
        List<String> path = new ArrayList<String>();
        Solver solution = new Solver(startWord,targetWord,dict);

        long startTime = System.currentTimeMillis();

        switch(Algorithm){
            case 1:
                path = solution.solve_UCS();
                break;
            case 2:
                path = solution.solve_Greedy();
                break;
            case 3:
                path = solution.solve_Astar();
                break;
        }
        
        long endTime = System.currentTimeMillis();
        
        if (path != null && !path.isEmpty()){
            System.out.println("Path :");
            int i = 1;
            for (String word : path) {
                System.out.println(i++ +"). "+word);
            }
        }else{
            System.out.println("Not found");
        }
        System.out.println("Nodes Passed:" + solution.getCount());
        System.out.println("Runtime: " + (endTime-startTime) + " milliseconds");
    }

    public static void main(String[] args){
    
        Map<Integer, List<String>> dicts = DictInit.init(Dictionary);
        String[] words = {"",""};
        boolean end = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("--- Word Ladder Solver ---");
        System.out.println("--------------------------");
        System.out.println("");
        System.out.println("---------- INFO ----------");
        System.out.println("Start Word : "+words[0]);
        System.out.println("Target Word : "+words[1]);
        System.out.println("Current Dictionary : "+Dictionary);
        System.out.println("");
        System.out.println("-------- COMMANDS --------");
        System.out.println("1). DICT (change dictionary)");
        System.out.println("2). WORD (change start Word and target Word)");
        System.out.println("3). UCS (Solve using UCS algorithm)");
        System.out.println("4). GREEDY (Solve using Greedy Best First Search Algorithm)");
        System.out.println("5). A* (Solve using A* Algorithm)");
        System.out.println("6). END (End Program)");
        System.out.println("7). INFO (Displays Current Start,Target, and Dictionary)");
        System.out.println("8). HELP (Displays This Commands List)\n");

        while (!end){
            System.out.print(">>> "); 
            String input;
            input = scanner.nextLine().toUpperCase();
            switch (input){
                case "DICT":
                    change_dict();
                    dicts = DictInit.init(Dictionary);
                    break;
                case "WORD":
                    try{
                        words = InputWords(scanner,dicts);
                    }catch (IllegalArgumentException e) {
                        switch(e.getCause().getMessage()){
                            case "DifferenLengthError":
                                System.out.println("ERROR! , Start and Target must be same length\n");
                                break;
                            case "SameWordError":
                                System.out.println("ERROR! , Start Word must be different from Target\n");
                                break;
                            case "StartNotInDictError":
                                System.out.println("ERROR! , Start Word not in dictionary\n");
                                break;
                            case "TargetNotInDictError":
                                System.out.println("ERROR! , Target Word not in dictionary\n");
                                break;
                            default :
                                System.out.println("Error!");
                                break;
                        }
                    }
                    break;
                case "UCS":
                    if (words[0].equals("")){
                        System.out.println("Mohon input Start Word dan Target Word terlebih dahulu\n");
                    }
                    else{
                        Solve(1, words[0], words[1],dicts);
                    }
                    break;
                case "GREEDY":
                    if (words[0].equals("")){
                        System.out.println("Mohon input Start Word dan Target Word terlebih dahulu\n");
                    }
                    else{
                        Solve(2, words[0], words[1],dicts);
                    }
                    break;
                case "A*":
                    if (words[0].equals("")){
                        System.out.println("Mohon input Start Word dan Target Word terlebih dahulu\n");
                    }
                    else{
                        Solve(3, words[0], words[1],dicts);
                    }
                    break;
                case "END":
                    end = true;
                    System.out.println("CLOSING PROGRAM");
                    break;
                case "INFO":
                    System.out.println("");
                    System.out.println("---------- INFO ----------");
                    System.out.println("Start Word : "+words[0]);
                    System.out.println("Target Word : "+words[1]);
                    System.out.println("Current Dictionary : "+Dictionary);
                    System.out.println("");
                    break;
                case "HELP":
                    System.out.println("");
                    System.out.println("-------- COMMANDS --------");
                    System.out.println("1). DICT (change dictionary)");
                    System.out.println("2). WORD (change start Word and target Word)");
                    System.out.println("3). UCS (Solve using UCS algorithm)");
                    System.out.println("4). GREEDY (Solve using Greedy Best First Search Algorithm)");
                    System.out.println("5). A* (Solve using A* Algorithm)");
                    System.out.println("6). END (End Program)");
                    System.out.println("7). INFO (Displays Current Start,Target, and Dictionary)");
                    System.out.println("8). HELP (Displays This Commands List)");
                    System.out.println("");
                    break;
                default:
                    System.out.println("That command does not exists");
                    System.out.println("Try HELP to see commands list");
                    System.out.println("");
                    break;
            }
            
        }
        scanner.close();
        
    }
}
