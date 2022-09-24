import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class M {
    // Массив юрского периода
    private static class HistoricalArray {
        int n;
        TreeMap<Integer, Integer> eras;
        TreeSet<Integer>[] changes;
        TreeMap<Integer, TreeMap<Integer, Integer>> map = new TreeMap<>();
        int currentEra;
        int currentEraIndex;


        public HistoricalArray(int n) {

            this.n = n;
            eras = new TreeMap<>();
            eras.put(0, 0);
            currentEraIndex = 0;
            changes = new TreeSet[n];
            for (int i = 0; i < n; i++) {
                changes[i] = new TreeSet<Integer>();

            }
            currentEra = 0;
            map.put(0, new TreeMap<>());
        }

        public void set(int index, int value) {

            map.get(currentEraIndex).put(index, value);
            changes[index].add(currentEraIndex);
        }

        public void beginNewEra(int eraId) {
            currentEraIndex++;
            map.put(currentEraIndex, new TreeMap<>());
            currentEra = eraId;
            eras.put(eraId, currentEraIndex);

        }

        public int get(int index, int eraId) {
            Integer eraIndex = changes[index].floor(eras.get(eraId));
            return eraIndex == null ? 0 : map.get(eraIndex).get(index);

        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            HistoricalArray array = new HistoricalArray(n);
            int q = readInt(reader);
            for (int i = 0; i < q; i++) {
                List<String> queryParts = Arrays.asList(reader.readLine().strip().split(" "));
                String queryType = queryParts.get(0);
                if (queryType.equals("set")) {
                    int index = parseInteger(queryParts.get(1));
                    int value = parseInteger(queryParts.get(2));
                    array.set(index, value);
                } else if (queryType.equals("begin_new_era")) {
                    int eraId = parseInteger(queryParts.get(1));
                    array.beginNewEra(eraId);
                } else if (queryType.equals("get")) {
                    int index = parseInteger(queryParts.get(1));
                    int eraId = parseInteger(queryParts.get(2));
                    writer.write(array.get(index, eraId) + "\n");
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return parseInteger(reader.readLine());
    }

    private static int parseInteger(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            result = result * 10 + ch - '0';
        }
        return result;
    }
}