import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Q {
    // Прямота

    private static boolean isOnOneLine(List<Point> points) {
        Set<Point> set = new HashSet<>(points);
        if (set.size() < 3) {
            return true;
        }

        Iterator<Point> iterator = set.iterator();
        Point first = iterator.next();
        iterator.remove();
        Point second = iterator.next();
        final long xDelta = second.x - first.x;
        final long yDelta = second.y - first.y;
        iterator.remove();
        Point current;
        do {
            current = iterator.next();
            iterator.remove();
            long xDelta1 = current.x - first.x;
            long yDelta1 = current.y - first.y;
            if (xDelta * yDelta1 != yDelta * xDelta1) {
                return false;
            }

        } while (iterator.hasNext());


        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Long> coordinates = readTwoNumbers(reader);
                points.add(new Point(coordinates.get(0), coordinates.get(1)));
            }
            if (isOnOneLine(points)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Long> readTwoNumbers(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(elem -> Long.parseLong(elem))
                .collect(Collectors.toList());
    }

    public static class Point {
        public final long x;
        public final long y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Point(long x, long y) {

            this.x = x;
            this.y = y;
        }
    }
}
