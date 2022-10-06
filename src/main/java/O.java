import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class O {
    // Монополия++

    private static long getMaxFinalCapital(List<Building> buildings, int startCapital, int maxNumberOfBuildings) {
        long currentCapital = startCapital;
        PriorityQueue<Building> currentBuildings = new PriorityQueue<>(buildings.size(), Comparator.comparingInt(a -> a.needCapital));
        currentBuildings.addAll(buildings);
        PriorityQueue<Building> buildingsForPurchase = new PriorityQueue<>(buildings.size(), (a1, a2) -> a2.addedCapital - a1.addedCapital);
        Building current;
        for (int i = 0; i < maxNumberOfBuildings; i++) {
            while (!currentBuildings.isEmpty()) {
                current = currentBuildings.peek();
                if (current.needCapital > currentCapital) {
                    break;
                }
                buildingsForPurchase.add(currentBuildings.poll());
            }
            if (buildingsForPurchase.isEmpty()) {
                break;
            }
            currentCapital += buildingsForPurchase.poll().addedCapital;

        }


        return currentCapital;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nAndK = readTwoNumbers(reader);
            int n = nAndK[0];
            int k = nAndK[1];
            List<Building> buildings = readBuildings(reader, n);
            int M = readInt(reader);
            System.out.println(getMaxFinalCapital(buildings, M, k));
        }
    }

    private static List<Building> readBuildings(BufferedReader reader, int n) throws IOException {
        List<Building> buildings = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int[] parameters = readTwoNumbers(reader);
            buildings.add(new Building(parameters[0], parameters[1]));
        }
        return buildings;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readTwoNumbers(BufferedReader reader) throws IOException {
        char[] readString = reader.readLine().toCharArray();
        int[] result = new int[2];
        int pos = 0;
        char ch = readString[pos++];

        while (ch != ' ') {
            result[0] = result[0] * 10 + (ch - '0');
            ch = readString[pos++];
        }

        while (pos < readString.length) {
            result[1] = result[1] * 10 + (readString[pos++] - '0');

        }
        return result;
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