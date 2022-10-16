import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beads {
    //Бусинки
    public static void main(String[] args) throws IOException {


        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int size = n - 1;
            if (n < 4) {
                System.out.println(n);
                return;
            }
            for (int i = 0; i < size; i++) {
                int[] bead = readTwoNumbers(bufferedReader);
                addBead(bead[0], bead[1], map);
                addBead(bead[1], bead[0], map);
            }
        }
        HashMap<Integer, Integer> distance = new HashMap<>();


        LinkedList<Integer> query = new LinkedList<>();
        int first = map.entrySet().iterator().next().getKey();
        HashSet<Integer> verified = new HashSet<>();
        int max = 1;
        int last = first;
        query.push(first);
        verified.add(first);

        while (!query.isEmpty()) {
            int current = query.removeLast();
            HashSet<Integer> value = map.get(current);
            for (Integer neighbor : value) {
                if (!verified.contains(neighbor)) {
                    verified.add(neighbor);
                    query.push(neighbor);
                    last = neighbor;
                }
            }
        }
        verified.clear();
        query.push(last);
        distance.put(last, 1);

        while (!query.isEmpty()) {
            int current = query.removeLast();
            int currentDistance = distance.get(current);
            HashSet<Integer> value = map.get(current);
            for (Integer neighbor : value) {
                if (!verified.contains(neighbor)) {
                    verified.add(neighbor);
                    query.push(neighbor);
                    distance.put(neighbor, currentDistance + 1);
                    if (max < currentDistance + 1) {
                        max = currentDistance + 1;

                    }
                }
            }
        }

        System.out.println(max);


    }


    private static void addBead(int first, int second, HashMap<Integer, HashSet<Integer>> map) {
        HashSet<Integer> set = map.get(first);
        if (set == null) {
            set = new HashSet<>();
            set.add(second);
            map.put(first, set);
        } else {
            set.add(second);
        }
    }

    private static int[] readTwoNumbers(BufferedReader bufferedReader) throws IOException {
        return Arrays.stream(bufferedReader.readLine().strip().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
