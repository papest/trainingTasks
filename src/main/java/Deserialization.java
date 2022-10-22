import java.io.*;
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
        ArrayList<Result> result = new ArrayList<>(8192);
        for (int i = 0; i < n; i++) {
            treeString = bufferedReader.readLine();
            deserializeTree(treeString, result);
            bufferedWriter.write('0' + result.size());

            for (Result a : result) {
                bufferedWriter.newLine();
                char[] bitString = resultToString(a);

                bufferedWriter.write(bitString);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            result.clear();


        }


        bufferedWriter.close();
    }

    private static char[] resultToString(Result result) {
        char[] bitString = new char[result.length];


        long value = result.value;
        for (int i = 0; i < result.length; i++) {

            bitString[result.length - 1 - i] = (value & 1L) == 0 ? '0' : '1';
            value = value >>> 1;

        }
        return bitString;
    }

    private static void deserializeTree(String treeString, ArrayList<Result> result) {
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

    private static void answer(ArrayList<Result> result, Node tree) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Long> prefixes = new LinkedList<>();
        LinkedList<Integer> lengths = new LinkedList<>();
        Node node;
        long pref;
        int l;
        nodes.push(tree);
        prefixes.push(0L);
        lengths.push(0);
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            pref = prefixes.poll();
            l = lengths.poll();
            if (node.left == null) {
                if (node.type.equals("root")) {
                    continue;
                }
                result.add(new Result(pref, l));
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

    private static class Result {
        final long value;
        final int length;

        public Result(long value, int length) {
            this.value = value;
            this.length = length;
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
