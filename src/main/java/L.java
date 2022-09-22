import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class L {
    // Пересечение отрезков

    private static ArrayList<Integer> getIntersection(int[] firstSequence, int[] secondSequence) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int left = 0;
        int right = firstSequence.length;
        int mid;
        for (int i = 0, sizeSecond = secondSequence.length; i < sizeSecond ; i += 2) {


            while (left < right) {
                mid = (left + right) / 2;
                if (secondSequence[i] > firstSequence[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (secondSequence[i] == firstSequence[left]) {
                if (left%2 == 1) {
                    result.add(firstSequence[left]);
                    result.add(firstSequence[left]);
                }
            }



        }


        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
           outputAnswer(getIntersection(readSegments(reader), readSegments(reader)), writer);


        }
    }

    private static void outputAnswer(ArrayList<Integer> answerSequence, BufferedWriter writer) throws IOException {
        for (int i = 0, size = answerSequence.size(); i < size; i += 2) {
            writer.write(answerSequence.get(i) + " " + answerSequence.get(i + 1) + "\n");
        }
    }

    private static int[] readSegments(BufferedReader reader) throws IOException {
        int n = readInt(reader);
        int size = n * 2;
        int[] segments = new int[n * 2];
        char current;
        char[] chars;

        for (int i = 0; i < size - 1; i += 2) {
            chars = reader.readLine().toCharArray();
            int j = 0;
            current = chars[j++];
            while (current != ' ') {
                segments[i] = segments[i] * 10 + (current - '0');
                current = chars[j++];

            }

            for (int sizeChars = chars.length; j < sizeChars; ) {
                current = chars[j++];
                segments[i + 1] = segments[i + 1] * 10 + (current - '0');
            }

        }

        return segments;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }


}