import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class C {

    private static long getNumberOfGoodPairs(int n, List<Integer> numbers) {

        return
                numbers.stream().map(i -> i % 200)
                        .collect(Collectors.toMap(a -> a, val -> 1, (v1, v2) -> v1 + v2))
                        .values().stream().filter(v -> v != 1)
                        .mapToLong(v -> (long) v * (v - 1) / 2)
                        .sum();


    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> numbers = readList(reader);
            System.out.println(getNumberOfGoodPairs(n, numbers));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}