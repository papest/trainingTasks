import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;


public class M {
// Массив юрского периода
    private static class HistoricalArray {
        int n;
        TreeMap<Integer, int[]> map = new TreeMap<>();
        int currentEra;

        public HistoricalArray(int n) {
            // your code goes here
            this.n = n;
            currentEra = 0;
            map.put(0, new int[n]);
        }

        public void set(int index, int value) {
            map.get(currentEra)[index] = value;
        }

        public void beginNewEra(int eraId) {
            map.put(eraId, Arrays.copyOf(map.get(currentEra), n));
            currentEra = eraId;
        }

        public int get(int index, int eraId) {

            return map.get(eraId)[index];
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
                    int index = Integer.parseInt(queryParts.get(1));
                    int value = Integer.parseInt(queryParts.get(2));
                    array.set(index, value);
                } else if (queryType.equals("begin_new_era")) {
                    int eraId = Integer.parseInt(queryParts.get(1));
                    array.beginNewEra(eraId);
                } else if (queryType.equals("get")) {
                    int index = Integer.parseInt(queryParts.get(1));
                    int eraId = Integer.parseInt(queryParts.get(2));
                    writer.write(array.get(index, eraId) + "\n");
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}