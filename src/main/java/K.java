import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
                    if (!lastChars.isEmpty()) lastChars.append(ch);
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
                        index = indexOfLastChars(lastChars.toString(), checking); //checking.indexOf(lastChars.toString());
                        if (index == -1) return false;
                        index = index + lastChars.length() - 1;
                    }

                    checking.delete(0, index + 1);
                    minLenght--;
                    if (checking.indexOf(lastChars.toString()) == -1) { //String.valueOf(ch)
                        notFixedStart = false;
                    }

                }
            }
        }
        return checking.length() == 0 || notFixedStart;
    }

    private static int indexOfLastChars(String lastChars, StringBuilder checking) {
        if (!lastChars.contains("?")) return checking.indexOf(lastChars);
        int lenght = checking.length();
        if (lastChars.length() > lenght) return -1;
        ArrayList<StringBuilder> array = new ArrayList<>();
        //int[] offsetArray;
        int size = lastChars.length();
        char curr;
        for (int i = 0; i < size; i++) {
            StringBuilder current = new StringBuilder();
            while (i < size && (curr = lastChars.charAt(i)) != '?') {
                i++;
                current.append(curr);
            }
            if (!current.isEmpty()) {
                array.add(current);
                current = new StringBuilder();
            }

            while (i < size && (curr = lastChars.charAt(i)) == '?') {
                i++;
                current.append(curr);
            }
            i--;
            if (!current.isEmpty()) {
                array.add(current);
            }
        }

        size = array.size();
//        offsetArray = new int[size];
//        offsetArray[0] = 0;
//        for (int i = 1; i < size; i++) {
//            offsetArray[i] = offsetArray[i - 1] + array.get(i - 1).length();
//        }
        int index = -1;
        int index1 = 0;
        int next = 0;
        for (int i = 0; i < size; i++) {
            int index2 = index1;
            index1 = checking.indexOf(array.get(i++).toString(), index2);
            if (index1 == -1) return -1;
            if (i - 1 != 0 && index1 != index2) {
                if (next == -1) return -1;
                i = -1;
                index1 = next;
                continue;
            }
            if (i - 1 == 0) {
                index = index1;
                if (next != -1) next = checking.indexOf(array.get(0).toString(), next + 1);
            }
            if (i == size) break;

            index1 += array.get(i - 1).length();
            if (!(i < size && (index1 += array.get(i).length()) < lenght)) return -1;

        }

        return index;
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