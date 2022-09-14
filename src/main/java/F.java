import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class F {

    private static String convertToGoodString(String probablyBadString) {

        char first;
        char second;
        StringBuilder ourString = new StringBuilder(probablyBadString);

        for (int i = 0, size = probablyBadString.length(); i < size - 1; i++) {

            first = ourString.charAt(i);
            second = ourString.charAt(i + 1);
            if ((Character.toUpperCase(first) == Character.toUpperCase(second)) && (first != second)) {
                size -= 2;
                ourString.deleteCharAt(i);
                ourString.deleteCharAt(i);
                i -= 2;
                if (i == -2) {
                    i = -1;
                }

            }
        }

        return ourString.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String probablyBadString = reader.readLine().strip();
            System.out.println(convertToGoodString(probablyBadString));
        }
    }
}