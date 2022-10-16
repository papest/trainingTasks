import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class S {
    // По ip вычислю

    private static final String IPV4 = "IPv4";
    private static final String IPV6 = "IPv6";
    private static final String ERROR = "Error";

    /**
     * @return R.java#IPV4, R.java#IPV6, or R.java#ERROR
     */
    private static String checkIpAddress(String ip) {
        char ch;
        char[] ipString = ip.toCharArray();
        int size = ipString.length;
        int i = 0;

        if (size < 8) {
            return ERROR;
        }

        while (i < 5) {
            ch = ipString[i++];

            if (ch == '.') {
                return checkValidIpV4(ipString);
            }
            if (ch == ':') {
                return checkValidIpV6(ipString);
            }

        }
        return ERROR;
    }

    private static String checkValidIpV6(char[] ipString) {
        String[] array = new String(ipString).split(":");
        if (array.length != 8) {
            return ERROR;
        }
        try {

            if (Arrays.stream(array).filter(b -> {
                if (b.length() > 4) {
                    return false;
                }
                if (b.length() > 1) {
                    if (b.charAt(0) == '-') {
                        return false;
                    }

                }

                return true;
            }).mapToInt(a -> Integer.parseInt(a, 16)).filter(i -> i < 65536 && i >= 0).count() == 8) {
                return IPV6;
            }
        } catch (NumberFormatException e) {
            return ERROR;

        }
        return ERROR;
    }

    private static String checkValidIpV4(char[] ipString) {
        String[] array = new String(ipString).split("\\.");
        if (array.length != 4) {
            return ERROR;
        }
        try {
            if (Arrays.stream(array).filter(b -> {
                if (b.length() > 1) {
                    if (b.charAt(0) == '0' || b.charAt(0) == '-') {
                        return false;
                    }

                }
                return true;
            }).mapToInt(a -> Integer.parseInt(a)).filter(i -> i < 256 && i >= 0).count() == 4) {
                return IPV4;
            }
        } catch (NumberFormatException e) {
            return ERROR;

        }


        return ERROR;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String ipAddress = reader.readLine();
            System.out.println(checkIpAddress(ipAddress));
        }
    }
}