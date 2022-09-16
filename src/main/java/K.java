import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class K {
    //Разрыв шаблона

    private static boolean stringMatchesTemplate(String stringToCheck, String template) {
        int size = template.length() << 1;
        char[] array = new char[size];
        char[] templateArray = template.toCharArray();
        int j = 0;
        for (char t : templateArray) {
            switch (t) {
                case '*':
                    array[j++] = '.';
                    array[j++] = '*';
                    break;
                case '?':
                    array[j++] = '.';
                    break;
                default:
                    array[j++] = t;
            }
        }

        String actualTemplate = new String(array, 0, j);

        return stringToCheck.matches(actualTemplate);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String template = reader.readLine().strip();
            String stringToCheck = reader.readLine().strip();

            if (stringMatchesTemplate(stringToCheck, template)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}