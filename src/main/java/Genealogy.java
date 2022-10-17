import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Genealogy {
    // Родословная: предки и потомки
    final static char ANCESTOR = '1';
    final static char DESCENDANT = '2';
    final static char DEFAULT = '0';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] coupleStrings;
        HashSet<String> set;

        int n = Integer.parseInt(bufferedReader.readLine());
        HashMap<String, HashSet<String>> genealogyMap = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            coupleStrings = readTwoStrings(bufferedReader);
            set = genealogyMap.get(coupleStrings[1]);

            if (set == null) {
                set = new HashSet<>();
            }
            set.add(coupleStrings[0]);
            genealogyMap.put(coupleStrings[1], set);
            if (!genealogyMap.containsKey(coupleStrings[0])) {
                genealogyMap.put(coupleStrings[0], new HashSet<>());
            }

        }
        boolean delimiter = false;
        while (bufferedReader.ready()) {
            if (delimiter) {
                bufferedWriter.write(' ');
            } else {
                delimiter = true;
            }
            coupleStrings = readTwoStrings(bufferedReader);
            if (checkAncestor(coupleStrings[0], coupleStrings[1], genealogyMap)) {
                bufferedWriter.write(ANCESTOR);

            } else if (checkAncestor(coupleStrings[1], coupleStrings[0],
                    genealogyMap)) {
                bufferedWriter.write(DESCENDANT);
            } else {
                bufferedWriter.write(DEFAULT);
            }


        }
        bufferedWriter.write('\n');
        bufferedWriter.flush();
    }

    private static boolean checkAncestor(String ancestor, String descendant, HashMap<String, HashSet<String>> genealogyMap) {
        if (ancestor.equals(descendant)) {
            return true;
        }

        LinkedList<String> query = new LinkedList<>();
        query.push(ancestor);
        HashSet<String> set;
        String current;
        while (!query.isEmpty()) {
            current = query.removeLast();
            set = genealogyMap.get(current);
            for (String s : set) {
                if (s.equals(descendant)) {
                            return true;
                }

                query.push(s);

                    }


        }
        return false;
    }

    private static String[] readTwoStrings(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine().strip().split(" ");
    }
}
