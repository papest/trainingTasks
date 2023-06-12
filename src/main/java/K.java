import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class K {
    //Разрыв шаблона

    private static boolean stringMatchesTemplate(String stringToCheck, String template) {

        StringBuilder builderToCheck = new StringBuilder(stringToCheck);
        List<String> listRegex = Arrays.stream(template.split("\\*")).collect(Collectors.toList());
        if (template.endsWith("*")) listRegex.add("");

        int index = 0;
        boolean first = true;
        boolean flag = false;
        for (String regex1 : listRegex) {
            if (regex1.isEmpty()) {
                flag = true;
                continue;
            }
            index = check(builderToCheck, regex1, index);

            if (index == -1) return false;
            if (index != 0 && first && !flag) return false;
            builderToCheck.delete(0, index + regex1.length());
            index = 0;
            if (flag && first) {
                flag = false;
                first = false;
            }
        }
        return builderToCheck.isEmpty() || flag || check(builderToCheck, listRegex.get(listRegex.size() - 1), index) != -1;
    }

    private static int check(StringBuilder builderToCheck, String regex1, int index) {
        if (!regex1.contains("?")) return builderToCheck.indexOf(regex1, index);
        Pattern p = Pattern.compile(regex1.replaceAll("\\?", "[a-z]"));
        Matcher m = p.matcher(builderToCheck.toString());
        if (m.find()) {
            return m.start();
        } else return -1;
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