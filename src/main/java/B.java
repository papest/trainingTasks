import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class B {

    private static long maxCardSum(int k, List<Integer> a) {

        int n = a.size();
        int[] sumA = new int[n + 1];
        int sum = 0;
        int minSumK = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += a.get(i);
            sumA[i + 1] = sum;
        }
        if (n == k) {
            minSumK = 0;
        } else {

            for (int i = n - k; i < n + 1; i++) {

                minSumK = Math.min(sumA[i] - sumA[i - n + k], minSumK);


            }
        }


        return sumA[n] - minSumK;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            int n = Integer.parseInt(reader.readLine());
            int k = Integer.parseInt(reader.readLine());
            List<Integer> a = readList(reader);

            System.out.println(maxCardSum(k, a));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }


}