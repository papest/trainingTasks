import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class LCA {
    // Родословная: LCA
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] coupleStrings;
        int n = Integer.parseInt(bufferedReader.readLine());
        HashMap<String, String> genealogyMap = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            coupleStrings = readTwoStrings(bufferedReader);
            genealogyMap.put(coupleStrings[0], coupleStrings[1]);
        }
        while (bufferedReader.ready()) {
            coupleStrings = readTwoStrings(bufferedReader);
            System.out.println(leastCommonAncestor(coupleStrings[0], coupleStrings[1], genealogyMap));
        }

    }

    private static String leastCommonAncestor(String first, String second, HashMap<String, String> genealogyMap) {
        HashSet<String> verified = new HashSet<>();
        String current = first;
        verified.add(first);
        String ancestor = genealogyMap.get(current);

        while (ancestor != null) {
            verified.add(ancestor);
            current = ancestor;
            ancestor = genealogyMap.get(current);
        }
        current = second;
        while (!verified.contains(current)) {
            current = genealogyMap.get(current);
        }

        return current;
    }

    private static String[] readTwoStrings(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine().strip().split(" ");
    }
}
