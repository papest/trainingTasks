import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

public class Deserialization {
    // Дерево Хаффмана
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String treeString;
        for (int i = 0; i < n; i++) {
            treeString = bufferedReader.readLine();
            deserializeTree(treeString);

        }
    }

    private static void deserializeTree(String treeString) {
        char[] array = treeString.toCharArray();
        Node tree = new Node("root");
        Node node = tree;
        Node newNode;
        for (char ch : array) {
            if (ch == 'D') {

                newNode = new Node("left", node);
                node.left = newNode;
                node = newNode;

            } else if (ch == 'U') {
                while (Objects.equals(node.type, "right")) {
                    node = node.up;
                }

                node = node.up;

                newNode = new Node("right", node);
                node.right = newNode;
                node = newNode;


            }
        }
        ArrayList<String> result = new ArrayList<>();
        answer(result, tree, 0, 0);
        System.out.println(result.size());
        result.forEach(System.out::println);


    }

    private static void answer(ArrayList<String> result, Node tree, int length, long prefix) {

        if (tree.left == null) {
            String bitString = BigInteger.valueOf(prefix).toString(2);

            if (bitString.length() < length) {
                result.add(String.format("%0" + (length - bitString.length()) + "d%s", 0, bitString));
                return;
            }
            result.add(bitString);
            return;
        }
        answer(result, tree.left, length + 1, prefix << 1);
        answer(result, tree.right, length + 1, (prefix << 1) + 1);
    }

    private static class Node {
        Node left;
        Node right;
        Node up;
        String type;

        public Node(String type) {
            this.type = type;
        }

        public Node(String type, Node up) {

            this.type = type;
            this.up = up;
        }
    }
}
