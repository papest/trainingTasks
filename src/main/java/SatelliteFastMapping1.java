import java.io.*;

public class SatelliteFastMapping1 {


    static int[] x1;
    static int[] x2;
    static int[] y1;
    static int[] y2;
    static long[] sum1;
    static long[] sum2;
    static long[] sum3;
    static long[] sum4;
    static int n;


    public static void main(String[] args) throws IOException, InterruptedException {

        ConsoleHelper consoleHelper = new ConsoleHelper();



        n = consoleHelper.readUnsignedInt();


        StringBuilder result = new StringBuilder(65536);

        x1 = new int[n];
        x2 = new int[n];
        y1 = new int[n];
        y2 = new int[n];
        sum1 = new long[n];
        sum2 = new long[n];
        sum3 = new long[n];
        sum4 = new long[n];


        for (int i = 0; i < n; i++) {

            x1[i] = consoleHelper.readUnsignedInt();
            y1[i] = consoleHelper.readUnsignedInt();
            x2[i] = consoleHelper.readUnsignedInt();
            y2[i] = consoleHelper.readUnsignedInt();

        }


        Thread[] threads = new Thread[3];


        threads[0] = new SatelliteFastMapping1.Sum(x1, y2, sum2);
        threads[0].start();
        threads[1] = new SatelliteFastMapping1.Sum(x2, y1, sum3);
        threads[1].start();
        threads[2] = new SatelliteFastMapping1.Sum(x2, y2, sum4);
        threads[2].start();
     
        sum(x1, y1, sum1);

        for (int i = 0; i < 3; i++) {
            threads[i].join();
        }

        for (int i = 0; i < n; i++) {

           result.append(sum1[i] + sum2[i] + sum3[i] + sum4[i]).append("\n");


        }
        System.out.write(result.toString().getBytes());


    }

    private static void sum(int[] x, int[] y, long[] sum) {

        int[] previousX = {};
        int[] previousY = {};
        int indX;
        int indY;
        int[] previousXTemp;
        int[] previousYTemp;

        for (int i = n - 1; i >= 0; i--) {
            indX = binarySearchX(previousX, x[i]);
            indY = binarySearchY(previousY, y[i]);
            int size = previousX.length;

            if (indX < size) {
                if (previousY[indX] >= y[i]) {
                    continue;
                }
            }
            int x0 = 0;
            long sum0 = 0;
            int nextJ = Math.max(indY, 0);

            if (size > 0 && indY >= 0) {
                x0 = previousX[indY];
            }

            sum[i] = (long) (x[i] - x0) * y[i];
            if (sum[i] == 0) {
                continue;
            }

            for (int j = Math.max(indY, 0); j < size - 1 && previousX[j] <= x[i]; j++) {
                if (previousY[j] < y[i]) {
                    sum0 += (long) (previousX[j] - x0) * previousY[j];
                }
                x0 = previousX[j];
                nextJ++;
            }

            if (x0 < x[i] && size > 0) {
                if (x[i] < previousX[nextJ]) {
                    sum0 += (long) (x[i] - x0) * previousY[nextJ];
                } else {
                    if (previousX[nextJ] > x0) {
                        sum0 += (long) (previousX[nextJ] - x0) * previousY[nextJ];
                    }
                }
            }

            sum[i] = sum[i] - sum0;
            if (sum[i] == 0) {
                continue;
            }

           if (size == 0) {
               previousX = new int[] {x[i]};
               previousY = new int[] {y[i]};

           }  else {

               int size1 = 0;
               x0 = 0;

               if (!(indX == 0 && previousX[0] >= x[i] || y[i] >= previousY[0] )) {
                   size1 += indY + 1;
                   x0 = indY + 1;
               }
               if (x[i] < previousX[size - 1]) {
                   size1 += size - indX;
               }
               size1++;
               previousXTemp = new int[size1];
               previousYTemp = new int[size1];

               if ((indX != 0 || previousX[0] < x[i]) && y[i] < previousY[0]) {
                   System.arraycopy(previousX, 0, previousXTemp, 0, indY + 1);
                   System.arraycopy(previousY, 0, previousYTemp, 0, indY + 1);

               }
               previousXTemp[x0] = x[i];
               previousYTemp[x0] = y[i];

               if ( x[i] < previousX[size - 1]) {
                   System.arraycopy(previousX, indX, previousXTemp, x0 + 1, size - indX);
                   System.arraycopy(previousY, indX, previousYTemp, x0 + 1, size - indX);
               }
               previousX = previousXTemp;
               previousY = previousYTemp;

           }


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
            for (int i = 0; i < count1 ; i++) {

                result = result * RADIX;
                result += (digitArray[i] - '0');
            }

            return result;


        }


        private boolean isDelimiter(char ch) {

            return  '\n' == ch || '\r' == ch || ' ' == ch;

        }

    }


    private static class Sum extends Thread {

        int[] x;
        int[] y;
        long[] sum;

        public Sum(int[] x, int[] y, long[] sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        public void run() {
            sum(x, y, sum);
        }
    }

}