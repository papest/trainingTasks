import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Deserialization {
    // Дерево Хаффмана
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bufferedReader.readLine());
        String treeString;
        ArrayList<String> result = new ArrayList<>(8192);
        for (int i = 0; i < n; i++) {
            treeString = bufferedReader.readLine();
            deserializeTree(treeString, bufferedWriter, result);
            bufferedWriter.write('0' + result.size());

            for (String a : result) {
                bufferedWriter.newLine();
                bufferedWriter.write(a.toCharArray());
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            result.clear();


        }
        bufferedWriter.close();
    }

    private static void deserializeTree(String treeString, BufferedWriter bufferedWriter, ArrayList<String> result) {
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

        answer(result, tree);


    }

    private static void answer(ArrayList<String> result, Node tree) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Long> prefixes = new LinkedList<>();
        LinkedList<Long> lengths = new LinkedList<>();
        Node node;
        long pref;
        long l;
        nodes.push(tree);
        prefixes.push(0L);
        lengths.push(0L);
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            pref = prefixes.poll();
            l = lengths.poll();
            if (node.left == null) {
                String bitString = BigInteger.valueOf(pref).toString(2);

                if (bitString.length() < l) {
                    result.add(String.format("%0" + (l - bitString.length()) + "d%s", 0, bitString));
                    continue;
                }
                result.add(bitString);
                continue;
            }

            nodes.push(node.right);
            prefixes.push((pref << 1) + 1);
            lengths.push(l + 1);
            nodes.push(node.left);
            prefixes.push(pref << 1);
            lengths.push(l + 1);

        }
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
