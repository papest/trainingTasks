import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class P {
    // Граница дерева

    private static HashSet<Integer> getTreeBorder(List<Vertex> tree, int root) {
        HashSet<Integer> result = new HashSet<>();
        Vertex currentVertex = tree.get(root);
        result.add(root);
        int leftCurrent = -1;
        int rightCurrent = -1;
        if (currentVertex.left != -1) {
            leftCurrent = currentVertex.left;
        } else if (currentVertex.right != -1) {
            leftCurrent = currentVertex.right;
        }
        if (currentVertex.right != -1) {
            rightCurrent = currentVertex.right;
        } else if (currentVertex.left != -1) {
            rightCurrent = currentVertex.left;
        }

        while (leftCurrent != -1) {
            result.add(leftCurrent);
            currentVertex = tree.get(leftCurrent);
            leftCurrent = -1;
            if (currentVertex.left != -1) {
                leftCurrent = currentVertex.left;
            } else if (currentVertex.right != -1) {
                leftCurrent = currentVertex.right;
            }

        }
        while (rightCurrent != -1) {
            result.add(rightCurrent);
            currentVertex = tree.get(rightCurrent);
            rightCurrent = -1;
            if (currentVertex.right != -1) {
                rightCurrent = currentVertex.right;
            } else if (currentVertex.left != -1) {
                rightCurrent = currentVertex.left;
            }
        }

        for (int i = 0, size = tree.size(); i < size; i++) {
            currentVertex = tree.get(i);
            if (currentVertex.left == -1 && currentVertex.right == -1) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<Integer> firstLine = readTwoNumbers(reader);
            int n = firstLine.get(0);
            int root = firstLine.get(1);
            List<Vertex> tree = readTree(reader, n);
            outputAnswer(getTreeBorder(tree, root), writer);
        }
    }


    private static void outputAnswer(HashSet<Integer> treeBorder, BufferedWriter writer) throws IOException {
        for (int elem : treeBorder) {
            writer.write(elem + " ");
        }
        writer.write("\n");
    }

    private static List<Vertex> readTree(BufferedReader reader, int n) throws IOException {
        List<Vertex> tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> leftAndRight = readTwoNumbers(reader);
            tree.add(new Vertex(leftAndRight.get(0), leftAndRight.get(1)));
        }
        return tree;
    }

    private static List<Integer> readTwoNumbers(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static class Vertex {
        public final int left;
        public final int right;

        public Vertex(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}