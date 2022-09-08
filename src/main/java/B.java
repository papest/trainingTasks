import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class B {

    private static long maxCardSum(int k, int[] a) {

        int n = a.length;
        int[] sumA = new int[n + 1];
        int sum = 0;
        int minSumK = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += a[i];
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
            int[] a = new int[n];
            readIntArray(reader, a);
            System.out.println(maxCardSum(k, a));
        }
    }


    private static void readIntArray(BufferedReader reader, int[] array) throws IOException {
        int n = array.length;
        char[] s = reader.readLine().toCharArray();
        int length = s.length;
        int count = 0;
        int number;
        for (int i = 0; i < n - 1; i++) {
            number = 0;
            while (true) {
                if (s[count] == ' ') {
                    array[i] = number;
                    count++;
                    break;
                }
                number = number * 10 + (s[count++] - '0');
            }

        }
        number = 0;
        while (count < length) {
            number = number * 10 + (s[count++] - '0');
        }
        array[n - 1] = number;


    }


}