package src;
import java.util.*;


public class StringNode {
    private List<String> list;
    private int cost;
    
    public StringNode(List<String> list, int cost){
        this.list=list;
        this.cost = cost;
    }

    public List<String> getList(){
        return this.list;
    }
    public int getCost(){
        return this.cost;
    }

    
}
