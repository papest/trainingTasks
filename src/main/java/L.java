import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class L {
    //Пересечение отрезков

    private static List<Segment> getIntersection(List<Segment> firstSequence, List<Segment> secondSequence) {
        List<Segment> result = new ArrayList<Segment>();
        Segment segment2;
        Segment segment1;
        int start;
        int end;
        for (int i = 0, j = 0, size2 = secondSequence.size(), size1 = firstSequence.size(); i < size2; i++) {
            segment2 = secondSequence.get(i);
            for (; j < size1; j++) {
                segment1 = firstSequence.get(j);
                if (segment1.right < segment2.left) {
                    continue;
                }
                if (segment1.left > segment2.right) {
                    break;
                }

                start = Math.max(segment1.left, segment2.left);
                end = Math.min(segment1.right, segment2.right);
                result.add(new Segment(start, end));


            }

            if (j > 0) {
                j--;
            }


        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<Segment> firstSequence = readSegments(reader);
            List<Segment> secondSequence = readSegments(reader);

            List<Segment> intersection = getIntersection(firstSequence, secondSequence);
            outputAnswer(intersection, writer);
        }
    }

    private static void outputAnswer(List<Segment> intersection, BufferedWriter writer) throws IOException {
        for (Segment segment : intersection) {
            writer.write(segment.left + " " + segment.right + "\n");
        }
    }

    private static List<Segment> readSegments(BufferedReader reader) throws IOException {
        int n = readInt(reader);
        List<Segment> segments = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> borders = readTwoNumbers(reader);
            segments.add(new Segment(borders.get(0), borders.get(1)));
        }
        return segments;
    }

    private static class Segment {
        public final int left;
        public final int right;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readTwoNumbers(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}