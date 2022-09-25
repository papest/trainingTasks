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
     //Атака клонов
    static Node[] nodes = new Node[10000];
    static int pos = 0;


    public static Node cloneGraph(Node node) {

        int nodeIndex = -1;

        for (int i = 0; i < pos; i++) {
            if (nodes[i].val == node.val) {
                nodeIndex = i;
                break;
            }
        }
        if (nodeIndex != -1) {
            return nodes[nodeIndex];
        }

        Node finalClone = new Node(node.val);
        node.neighbours.forEach(node1 -> finalClone.neighbours.add(N.cloneGraph(node1)));
        nodes[pos++] = finalClone;
        return finalClone;
    }
}
