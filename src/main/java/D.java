import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D {

    private static int getLongestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] array = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array[i][j] == -1) array[i][j] = path(i, j, array, matrix);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, array[i][j]);
            }
        }

        return max + 1;
    }

    private static int path(int i, int j, int[][] array, int[][] matrix) {

        int[] delta = new int[]{-1, 1};
        int n = array.length;
        int m = array[0].length;
        for (int i1 : delta) {
            if (i1 + i >= 0 && i1 + i < n && matrix[i][j] < matrix[i + i1][j]) {
                if (array[i + i1][j] == -1) {
                    array[i][j] = Math.max(1 + path(i + i1, j, array, matrix), array[i][j]);
                } else {
                    array[i][j] = Math.max(1 + array[i + i1][j], array[i][j]);
                }
            }
        }

        for (int j1 : delta) {
            if (j + j1 >= 0 && j + j1 < m && matrix[i][j] < matrix[i][j + j1]) {
                if (array[i][j + j1] == -1) {
                    array[i][j] = Math.max(1 + path(i, j + j1, array, matrix), array[i][j]);
                } else {
                    array[i][j] = Math.max(1 + array[i][j + j1], array[i][j]);
                }
            }
        }

        return Math.max(array[i][j], 0);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[][] matrix = readMatrix(reader);

            System.out.println(getLongestIncreasingPath(matrix));
        }

    }

    private static int[][] readMatrix(BufferedReader reader) throws IOException {
        String[] sizes = reader.readLine().strip().split(" ");
        int n = Integer.parseInt(sizes[0]);
        int m = Integer.parseInt(sizes[1]);
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(reader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        return matrix;
    }

}