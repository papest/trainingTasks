import java.io.*;

public class SatelliteFastMapping1 {


    static int[] coordinates;
    static long[] sum;
    static int size;
    static int n;


    public static void main(String[] args) throws IOException, InterruptedException {

        ConsoleHelper consoleHelper = new ConsoleHelper();


        n = consoleHelper.readUnsignedInt();


        StringBuilder result = new StringBuilder(262144);
        size = n * 4;


        coordinates = new int[size];
        sum = new long[size];

        for (int i = 0; i < size; i++) {
            coordinates[i] = consoleHelper.readUnsignedInt();

        }


        Thread[] threads = new Thread[3];

// x1, y2, sum2
        threads[0] = new SatelliteFastMapping1.Sum(0, 3, 1);
        threads[0].start();
// x2, y1, sum3
        threads[1] = new SatelliteFastMapping1.Sum(2, 1, 2);
        threads[1].start();
// x2, y2, sum4
        threads[2] = new SatelliteFastMapping1.Sum(2, 3, 3);
        threads[2].start();
// x1, y1, sum1
        sum(0, 1, 0);

        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }

        for (int i = 0; i < size; i += 4) {

            result.append(sum[i] + sum[i + 1] + sum[i + 2] + sum[i + 3]).append("\n");


        }

        System.out.write(result.toString().getBytes());


    }

    private static void sum(int xOffset, int yOffset, int sumOffset) {

        int[] previousX = {};
        int[] previousY = {};
        int indX;
        int indY;
        int[] previousXTemp;
        int[] previousYTemp;

        for (int i = size - 4; i >= 0; i -= 4) {
            indX = binarySearchX(previousX, coordinates[i + xOffset]);
            indY = binarySearchY(previousY, coordinates[i + yOffset]);
            int size = previousX.length;

            if (indX < size) {
                if (previousY[indX] >= coordinates[i + yOffset]) {
                    continue;
                }
            }
            int x0 = 0;
            long sum0 = 0;
            int nextJ = Math.max(indY, 0);

            if (size > 0 && indY >= 0) {
                x0 = previousX[indY];
            }

            sum[i + sumOffset] = (long) (coordinates[i + xOffset] - x0) * coordinates[i + yOffset];
            if (sum[i + sumOffset] == 0) {
                continue;
            }

            for (int j = Math.max(indY, 0); j < size - 1 && previousX[j] <= coordinates[i + xOffset]; j++) {
                if (previousY[j] < coordinates[i + yOffset]) {
                    sum0 += (long) (previousX[j] - x0) * previousY[j];
                }
                x0 = previousX[j];
                nextJ++;
            }

            if (x0 < coordinates[i + xOffset] && size > 0) {
                if (coordinates[i + xOffset] < previousX[nextJ]) {
                    sum0 += (long) (coordinates[i + xOffset] - x0) * previousY[nextJ];
                } else {
                    if (previousX[nextJ] > x0) {
                        sum0 += (long) (previousX[nextJ] - x0) * previousY[nextJ];
                    }
                }
            }

            sum[i + sumOffset] = sum[i + sumOffset] - sum0;
            if (sum[i + sumOffset] == 0) {
                continue;
            }

            if (size == 0) {
                previousX = new int[]{coordinates[i + xOffset]};
                previousY = new int[]{coordinates[i + yOffset]};

            } else {

                int size1 = 0;
                x0 = 0;

                if (!(indX == 0 && previousX[0] >= coordinates[i + xOffset] || coordinates[i + yOffset] >= previousY[0])) {
                    size1 += indY + 1;
                    x0 = indY + 1;
                }
                if (coordinates[i + xOffset] < previousX[size - 1]) {
                    size1 += size - indX;
                }
                size1++;
                previousXTemp = new int[size1];
                previousYTemp = new int[size1];

                if ((indX != 0 || previousX[0] < coordinates[i + xOffset]) && coordinates[i + yOffset] < previousY[0]) {
                    System.arraycopy(previousX, 0, previousXTemp, 0, indY + 1);
                    System.arraycopy(previousY, 0, previousYTemp, 0, indY + 1);

                }
                previousXTemp[x0] = coordinates[i + xOffset];
                previousYTemp[x0] = coordinates[i + yOffset];

                if (coordinates[i + xOffset] < previousX[size - 1]) {
                    System.arraycopy(previousX, indX, previousXTemp, x0 + 1, size - indX);
                    System.arraycopy(previousY, indX, previousYTemp, x0 + 1, size - indX);
                }
                previousX = previousXTemp;
                previousY = previousYTemp;

            }

            Thread.yield();
        }

    }

    private static int binarySearchX(int[] previousX, int x) {
        int left = 0;
        int right = previousX.length;
        int m;
        while (left < right) {
            m = (left + right) >> 1;
            if (previousX[m] < x) {
                left = m + 1;
            } else {
                right = m;
            }
        }
        return left;

    }

    private static int binarySearchY(int[] previousY, int y) {
        int left = -1;
        int right = previousY.length - 1;
        int m;

        while (left < right) {
            m = (left + right + 1) >> 1;
            if (previousY[m] >= y) {
                left = m;
            } else {
                right = m - 1;
            }
        }
        return right;
    }


    private static class ConsoleHelper {

        byte[] buffer;
        int count;
        final int RADIX = 10;
        int[] digitArray = new int[10];

        public ConsoleHelper() throws IOException {
            DataInputStream din = new DataInputStream(System.in);
            buffer = din.readAllBytes();

        }

        public int readUnsignedInt() {
            int ch;
            int count1 = 0;
            int result = 0;


            for (; ; ) {

                ch = buffer[count++];

                if (ch == -1 || isDelimiter((char) ch)) {
                    break;
                }
                if (ch != '-') {
                    digitArray[count1++] = ch;
                }


            }
            for (int i = 0; i < count1; i++) {

                result = result * RADIX;
                result += (digitArray[i] - '0');
            }

            return result;


        }


        private boolean isDelimiter(char ch) {

            return '\n' == ch || '\r' == ch || ' ' == ch;

        }

    }


    private static class Sum extends Thread {

        int xOffset;
        int yOffset;
        int sumOffset;

        public Sum(int xOffset, int yOffset, int sumOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.sumOffset = sumOffset;
        }

        public void run() {
            sum(xOffset, yOffset, sumOffset);
        }
    }

}