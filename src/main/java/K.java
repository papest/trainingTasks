import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    //Разрыв шаблона

    private static boolean stringMatchesTemplate(String stringToCheck, String template) {
        if (!stringToCheck.matches("[a-z]+")) return false;
        int minLenght = template.replaceAll("\\*", "").length();
        if (minLenght > stringToCheck.length()) return false;
        String regex1 = template.replaceAll("\\*\\*", "*");

        StringBuilder checking = new StringBuilder(stringToCheck);
        boolean notFixedStart = false;
        StringBuilder lastChars = new StringBuilder();

        for (char ch : regex1.toCharArray()) {
            switch (ch) {
                case '?' -> {
                    if (minLenght < 1) return false;
                    checking.deleteCharAt(0);
                    minLenght--;
                }
                case '*' -> {
                    notFixedStart = true;
                    lastChars = new StringBuilder();
                }
                default -> {
                    if (minLenght < 1) return false;
                    int index = checking.indexOf(String.valueOf(ch));
                    if (index == -1) return false;
                    if (index != 0 && !notFixedStart) return false;
                    lastChars.append(ch);
                    if (index != 0) {
                        index = checking.indexOf(lastChars.toString());
                        if (index == -1) return false;
                        index = index + lastChars.length() - 1;
                    }

                    checking.delete(0, index + 1);
                    minLenght--;
                    if (checking.indexOf(String.valueOf(ch)) == -1) {
                        notFixedStart = false;
                    }

                }
            }
        }
        return checking.length() == 0 || notFixedStart;
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