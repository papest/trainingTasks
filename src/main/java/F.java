import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class F {
    // Хорошие строки

    private static String convertToGoodString(String probablyBadString) {

        char first;
        char second;
        char[] ourString = probablyBadString.toCharArray();
        int index1 = 0;
        int index2;


        for (int i = 0, size = probablyBadString.length(); i < size - 1; i++) {
            index2 = i + 1;
            first = ourString[index1];
            second = ourString[index2];
            if ((Character.toUpperCase(first) == Character.toUpperCase(second)) && (first != second)) {

                ourString[index1] = ' ';
                ourString[index2] = ' ';


                while (ourString[index1] == ' ') {
                    index1--;
                    if (index1 == -1) {
                        index1 = index2 + 1;
                        break;
                    }

                }

            } else {
                index1 = index2;
            }

        }

        return new String(ourString).replaceAll(" ", "");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String probablyBadString = reader.readLine().strip();
            System.out.println(convertToGoodString(probablyBadString));
        }
    }
}