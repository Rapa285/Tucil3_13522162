package src;

import java.util.Comparator;

public class NodeComparator implements Comparator<StringNode> {
    @Override
    public int compare(StringNode o1, StringNode o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}
