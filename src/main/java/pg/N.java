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
    static Node[] nodes1 = new Node[10000];
    static int pos = 0;

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

    private static int binarySearch(Node node) {
        int left = 0;
        int right = pos;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (node.val > nodes[mid].val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static void addNode(Node node) {
        int index = binarySearch(node);
        if (index < pos && nodes[index].val == node.val) {
            return;
        }
        if (index < pos) {
            System.arraycopy(nodes, index, nodes, index + 1, pos - index);
        }
        nodes[index] = node;
        pos++;
        for (int i = 0, size = pos; i < size; i++) {
            nodes[i].neighbours.forEach(node1 -> addNode(node1));
        }

    }

    public static Node cloneGraph(Node node) {
        HashMap<Integer, Integer> nodeIndex = new HashMap<>();
        nodes[pos++] = node;
        node.neighbours.forEach(node1 -> addNode(node1));
        int startNodeIndex = binarySearch(node);
        for (int i = 0; i < pos; i++) {
            nodes1[i] = new Node(nodes[i].val);
            nodeIndex.put(nodes[i].val, i);
        }

        for (int i = 0; i < pos; i++) {

            for (Node node1 : nodes[i].neighbours) {
                int ind = nodeIndex.get(node1.val);
                nodes1[i].neighbours.add(nodes1[ind]);
            }
        }


        return nodes1[startNodeIndex];
    }
}
