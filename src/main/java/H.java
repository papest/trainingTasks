import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


public class H {
    // Сизиф

    private static long getEnergyForUnion(List<Long> stones) {
        PriorityQueue<Long> queue = new PriorityQueue<Long>(stones);
        long sum = 0;
        long current;
        while (queue.size() > 1) {
            current = queue.poll() + queue.poll();
            sum += current;
            queue.add(current);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Long> stones = readList(reader);

            System.out.println(getEnergyForUnion(stones));
        }
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Long.parseLong(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}