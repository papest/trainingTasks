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
    static ArrayList <Integer> valueList = new ArrayList<>();
    static ArrayList<Node> nodeList = new ArrayList<>();


    public static Node cloneGraph(Node node) {

        int nodeIndex = valueList.indexOf(node.val);
        if (nodeIndex != -1) {
            return nodeList.get(nodeIndex);
        }

        Node finalClone = new Node(node.val);
        node.neighbours.forEach(node1 -> finalClone.neighbours.add(N.cloneGraph(node1)));
        valueList.add(node.val);
        nodeList.add(finalClone);
        return finalClone;
    }
}
