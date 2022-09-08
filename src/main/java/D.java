import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D {

    private static int getLongestIncreasingPath(List<List<Integer>> matrix) {
        int n = matrix.size();
        int m = matrix.get(0).size();
        int[][] array = new int[n][m];
        array[0][0] = 0;
        List<Integer> current = matrix.get(0);
        int number;
        int previous = current.get(0);
        int previousUp;
        int previousLeft;
        int temp;
        int max = 0;
        for (int i = 1; i < m; i++) {
            number = current.get(i);
            array[0][i] = number == previous ? 0 :
                    number > previous ? array[0][i - 1] >= 0 ? array[0][i - 1] + 1
                            : 1 : array[0][i - 1] <= 0 ? array[0][i - 1] - 1 : -1;
            max = Math.max(max, Math.abs(array[0][i]));
            previous = number;

        }

        for (int i = 1; i < n; i++) {
            previous = current.get(0);
            current = matrix.get(i);
            number = current.get(0);
            array[i][0] = number == previous ? 0 :
                    number > previous ? array[i - 1][0] >= 0 ? array[i - 1][0] + 1
                            : 1 : array[i - 1][0] <= 0 ? array[i - 1][0] - 1 : -1;
            max = Math.max(max, Math.abs(array[i][0]));

            for (int j = 1; j < m; j++) {
                previousLeft = current.get(j - 1);
                previousUp = matrix.get(i - 1).get(j);
                number = current.get(j);
                array[i][j] = number == previousUp ? 0 :
                        number > previousUp ? array[i - 1][j] >= 0 ? array[i - 1][j] + 1
                                : 1 : array[i - 1][j] <= 0 ? array[i - 1][j] - 1 : -1;
                temp = number == previousLeft ? 0 :
                        number > previousLeft ? array[i][j - 1] >= 0 ? array[i][j - 1] + 1
                                : 1 : array[i][j - 1] <= 0 ? array[i][j - 1] - 1 : -1;
                if (Math.abs(temp) > Math.abs(array[i][j])) {
                    array[i][j] = temp;
                }
                max = Math.max(max, Math.abs(array[i][j]));

            }

        }


        return max + 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<List<Integer>> matrix = readMatrix(reader);

            System.out.println(getLongestIncreasingPath(matrix));
        }

    }

    private static List<List<Integer>> readMatrix(BufferedReader reader) throws IOException {
        String[] sizes = reader.readLine().strip().split(" ");
        int n = Integer.parseInt(sizes[0]);
        List<List<Integer>> matrix = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }
}