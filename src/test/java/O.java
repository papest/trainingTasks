import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class O {

    private static long getMaxFinalCapital(List<Building> buildings, int startCapital, int maxNumberOfBuildings) {
        long currentCapital = startCapital;
        ArrayList<Building> currentBuildings = new ArrayList<>(buildings);
        int max ;
        int pos ;
        Building current;
        for (int i = 0; i < maxNumberOfBuildings; i++) {
            pos = -1;
            max = 0;
            for (int j = 0, size = currentBuildings.size(); j < size; j++) {

                current = currentBuildings.get(j);

                if (current.needCapital <= currentCapital && current.addedCapital > max) {
                    max = current.addedCapital;
                    pos = j;

                }
            }
            if (pos > -1) {
                currentCapital += max;
                currentBuildings.remove(pos);
            }



        }

        return currentCapital;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> nAndK = readTwoNumbers(reader);
            int n = nAndK.get(0);
            int k = nAndK.get(1);
            List<Building> buildings = readBuildings(reader, n);
            int M = readInt(reader);
            System.out.println(getMaxFinalCapital(buildings, M, k));
        }
    }

    private static List<Building> readBuildings(BufferedReader reader, int n) throws IOException {
        List<Building> buildings = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> parameters = readTwoNumbers(reader);
            buildings.add(new Building(parameters.get(0), parameters.get(1)));
        }
        return buildings;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readTwoNumbers(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split("\\s+"))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static class Building {
        public final int needCapital;
        public final int addedCapital;

        public Building(int c, int p) {
            needCapital = c;
            addedCapital = p;
        }
    }

}