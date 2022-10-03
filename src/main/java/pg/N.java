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
    static int MAX_NODES = 100000;
    static Node[] nodes1;
    static HashMap<Integer, Integer> nodeIndex;
    static int pos;


    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        node1.neighbours.add(node2);
        node2.neighbours.add(node1);
        node1.neighbours.add(node3);
        node3.neighbours.add(node1);
        node2.neighbours.add(node3);
        node3.neighbours.add(node2);
        Node clone = cloneGraph(node1);
        int n = 5;

    }


    public static Node cloneGraph(Node node) {

        nodes1 = new Node[MAX_NODES];
        nodeIndex = new HashMap<>();
        pos = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        nodes1[pos] = new Node(node.val);
        nodeIndex.put(node.val, pos++);
        for (Node node1 : node.neighbours) {
            if (nodeIndex.get(node1.val) == null) {
                nodes1[pos] = new Node(node1.val);
                stack.push(pos);
                nodeIndex.put(node1.val, pos++);

            }
            nodes1[nodeIndex.get(node.val)].neighbours.add(node1);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            Node node1 = nodes1[index];
            if (nodeIndex.get(node1.val) == null) {
                nodes1[node1.val] = new Node(node1.val);
                stack.push(pos);
                nodeIndex.put(node1.val, pos++);

            }
            nodes1[nodeIndex.get(node1.val)].neighbours.add(node1);
        }


        return nodes1[0];
    }
}
