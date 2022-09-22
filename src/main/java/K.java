import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class K {
    //Разрыв шаблона

    private static boolean stringMatchesTemplate(String stringToCheck, String template) {
        char[] templateArray = template.toCharArray();
        char[] checkArray = stringToCheck.toCharArray();
        int i = 0;
        for (; i < templateArray.length && i < checkArray.length; i++) {

            if (templateArray[i] == '*') {
                if (i == templateArray.length - 1) {
                    return true;
                }
                break;
            } else if (templateArray[i] == '?' || templateArray[i] == checkArray[i]) {
                continue;
            }

            return false;
        }
        if (templateArray.length == i && checkArray.length > i && templateArray[i - 1] != '*') {
            return false;
        }
        if (checkArray.length == i && checkArray.length <= templateArray.length) {
            for (int s = i; s < templateArray.length; s++) {
                if (templateArray[s] != '*') {
                    return false;
                }
            }
            return true;
        }


        int j = templateArray.length - 1;
        int k = checkArray.length - 1;
        for (; j >= i && k >= i; j--, k--) {
            if (templateArray[j] == '*') {
                k++;
                break;
            } else if (templateArray[j] == '?' || templateArray[j] == checkArray[k]) {
                continue;
            }
            return false;
        }
        if (templateArray[j] != '*' && k < i) {
            return false;

        }
        int o = i;
        int l = i;
        int m = i;
        for (; l < j && m < k; l++, m++) {
            if (templateArray[l] == '*') {
                o = l + 1;
                m--;
                continue;
            }
            if (templateArray[l] == '?' || templateArray[l] == checkArray[m]) {
                continue;
            }
            if (templateArray[l] != checkArray[m]) {
                l = o - 1;
            }

        }
        for (; l < j; l++) {
            if (templateArray[l] != '*') {
                return false;
            }
        }


        return true;
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