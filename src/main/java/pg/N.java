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
    static Node[] nodes;
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

        nodes = new Node[MAX_NODES];
        nodeIndex = new HashMap<>();
        pos = 0;
        LinkedList<Node> stack = new LinkedList<>();
        nodes[pos] = new Node(node.val);
        stack.push(node);
        nodeIndex.put(node.val, pos++);

        while (!stack.isEmpty()) {

            Node node1 = stack.pop();
            int index = nodeIndex.get(node1.val);
            for (Node node2 : node1.neighbours) {
                if (nodeIndex.get(node2.val) == null) {
                    nodes[pos] = new Node(node2.val);
                    stack.push(node2);
                    nodeIndex.put(node2.val, pos++);

                }
                nodes[index].neighbours.add(nodes[nodeIndex.get(node2.val)]);
            }
        }


        return nodes[0];
    }
}
