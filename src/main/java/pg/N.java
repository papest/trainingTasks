package pg;

import java.util.*;

///**
 class Node {

 public final int val;
 public final List<Node> neighbours;

 public Node(int val) {
 this.val = val;
 this.neighbours = new ArrayList<>();
 }
 }
 //*/

public class N {
    static TreeMap <Integer, Node> map = new TreeMap<>();

    public static Node cloneGraph(Node node) {
        Node clone = map.get(node.val);
        if (clone != null) {
            return clone;
        }

        Node finalClone = new Node(node.val);
        node.neighbours.forEach(node1 -> finalClone.neighbours.add(N.cloneGraph(node1)));
        map.put(node.val, finalClone);
        return finalClone;
    }
}
