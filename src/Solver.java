package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver {
    private String startWord;
    private String endWord;
    private Integer count;
    private List<String> dict;

    public Solver(String startWord,String endWord,List<String> dict){
        this.startWord = startWord.toLowerCase();
        this.endWord = endWord.toLowerCase();
        this.count = 0;
        this.dict = dict;
    }
    public void addCount(){
        this.count++;
    }
    public Integer getCount(){
        return count;
    }
    public static int getDistance(String string1,String string2){ //Menggunakan Hamming Distance
        int value = 0;
        for (int i = 0; i<string1.length();i++){
            if (string1.charAt(i) != string2.charAt(i)){
                value++;
            }
        }
        return value;
    }
    public List<String> solve_UCS(){
        //System.out.println("flag inside ucs");
        Queue<List<String>> queue = new LinkedList<List<String>>();
        List<String> ladder = new ArrayList<String>();
        ladder.add(startWord);
        queue.offer(ladder);
        //System.out.println("is queue empty "+queue.isEmpty());
        Map<String, Boolean> visited = new HashMap<>();
        while (!queue.isEmpty()){
            
            addCount();
            List<String> currentList = queue.remove();
            //System.out.println(currentList);
            String currentWord = currentList.get(currentList.size()-1);
            visited.put(currentWord,true);
            for (String word : dict) {
                if (!currentList.contains(word) && getDistance(word,currentWord) == 1 && !visited.containsKey(word)){
                    List<String> temp = new ArrayList<String>(currentList);
                    temp.add(word);
                    queue.offer(temp);
                    if (word.equals(endWord)){
                        //System.out.println("found flag");
                        return temp;
                    }
                }

            }
            
        }
        
        return null;
    }

    public List<String> solve_Greedy(){
        List<String> ladder = new ArrayList<String>();
        boolean found = false;
        ladder.add(startWord);
        
        //get words with same length
        while (!found){
            //System.out.println(ladder);
            String currentWord = ladder.get(ladder.size()-1);
            int min_distance = 100;
            String min_dis_word = "";
            addCount();
            for (String word : dict) {
                int distance = getDistance(endWord, word);
                if (distance < min_distance && getDistance(word, currentWord) == 1 && !ladder.contains(word)){
                    min_dis_word = word;
                    min_distance = distance;
                }
            }
            
            if (min_dis_word.equals(endWord)){
                ladder.add(endWord);
                found = true;
            }else if (min_dis_word.equals("")){
                return null;
            }else{
                ladder.add(min_dis_word);
            }
        }
        
        return ladder;
    }

    public List<String> solve_Astar(){
        //System.out.println("flag inside ucs");
        PriorityQueue<StringNode> queue = new PriorityQueue<>(new NodeComparator());
        List<String> ladder = new ArrayList<String>();
        ladder.add(startWord);
        StringNode start = new StringNode(ladder,getDistance(startWord, endWord));
        queue.add(start);
        //System.out.println("is queue empty "+queue.isEmpty());
        Map<String, Boolean> visited = new HashMap<>();
        while (!queue.isEmpty()){
            addCount();
            StringNode currentNode = queue.remove();
            List<String> currentList = currentNode.getList();
            Integer currentcost = currentNode.getCost();
            //System.out.println(currentList);
            String currentWord = currentList.get(currentList.size()-1);
            visited.put(currentWord,true);
            for (String word : dict) {
                if (!currentList.contains(word) && getDistance(word,currentWord) == 1 && !visited.containsKey(word)){
                    List<String> temp_list = new ArrayList<String>(currentList);
                    temp_list.add(word);
                    Integer temp_cost = currentcost + getDistance(word, endWord);
                    queue.add(new StringNode(temp_list,temp_cost));
                    if (word.equals(endWord)){
                        //System.out.println("found flag");
                        return temp_list;
                    }
                }

            }
            
        }
        
        return null;
    }
}
