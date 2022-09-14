import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class E {
    // Римлянин

    static Pattern rome = Pattern.compile("M{0,3}((CM)|((C?D)?C{0,3}))?((XC)|((X?L)?X{0,3}))?((IX)|((I?V)?I{0,3}))");
    static Map<Character, Integer> map = Map.of('I', 1,
            'V', 5
            , 'X', 10
            , 'L', 50
            , 'C', 100
            , 'D', 500
            , 'M', 1000);


    private static int convertToArabic(String romanNumber) {

        if (romanNumber == null || romanNumber.isEmpty() || romanNumber.length() > 15) {
            return -1;
        }
        Matcher m = rome.matcher(romanNumber);
        if (!m.matches()) {
            return -1;
        }
        char[] s = romanNumber.toCharArray();
        int size = s.length;
        int second;
        int first = map.get(s[size - 1]);
        int decimal = first;


        for (int i = size - 2; i >= 0; i--) {

            second = map.get(s[i]);

            if (first > second) {
                decimal -= second;

            } else {
                decimal += second;
            }
            first = second;


        }

        return decimal;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String romanNumber = reader.readLine().strip();
            System.out.println(convertToArabic(romanNumber));
        }
    }

}