
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;



public class SatelliteFastMapping1Test {
    static ByteArrayOutputStream output;
    static ByteArrayInputStream input;
    static PrintStream standardOut;
    static InputStream standardIN;

    @BeforeMethod
    public void setUpStreams() {

        standardOut = System.out;
        standardIN = System.in;
        output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.setOut(standardOut);
        System.setIn(standardIN);
        System.out.println(result.getEndMillis() - result.getStartMillis());

    }


    @DataProvider
    Object[][] data() {
        Object[][] data = new Object[][]{
                {"3\n" +
                        "-1 -1 1 1\n" +
                        "-1 0 1 1\n" +
                        "-1 -1 0 1\n", "1\n" +
                        "1\n" +
                        "2\n"},
                {"4\n" +
                        "-3 -3 3 3\n" +
                        "0 0 0 0\n" +
                        "-5 0 4 0\n" +
                        "-1 -4 1 3\n", "24\n" +
                        "0\n" +
                        "0\n" +
                        "14\n"},
                {"3\n" + "-1 0 1 7\n" + "-1000000000 -1000000000 1000000000 1000000000\n"
                        + "-1000000000 -1000000000 1000000000 1000000000\n",
                        "0\n" +
                                "0\n" +
                                "4000000000000000000\n"},
                {"4\n" +
                        "-3 -3 3 3\n" +
                        "-6 -6 6 6\n" +
                        "-5 0 4 0\n" +
                        "-1 -4 1 3\n", "0\n" +
                        "130\n" +
                        "0\n" +
                        "14\n"},
                {"4\n" +
                        "-6 -6 6 6\n" +
                        "-4 -4 4 4\n" +
                        "-3 -3 3 3\n" +
                        "-1 -1 1 1\n", "80\n" +
                        "28\n" +
                        "32\n" +
                        "4\n"},
                {"4\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-3 -1 3 1\n" +
                        "-1 -5 1 5\n", "16\n" +
                        "28\n" +
                        "8\n" +
                        "20\n"},
                {"6\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-1 -1 1 1\n" +
                        "-1 -5 1 5\n" +
                        "-6 -6 6 6\n" +
                        "-7 -2 7 2\n",
                        "0\n" +
                                "0\n" +
                                "0\n" +
                                "0\n" +
                                "96\n" +
                                "56\n"},
                {"6\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-1 -1 1 1\n" +
                        "-8 -1 8 1\n" +
                        "-6 -6 6 6\n" +
                        "-7 -2 7 2\n",
                        "0\n" +
                                "0\n" +
                                "0\n" +
                                "4\n" +
                                "96\n" +
                                "56\n"},
                {"7\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-1 -1 1 1\n" +
                        "-8 -1 8 1\n" +
                        "-6 -6 6 6\n" +
                        "-6 -6 6 6\n" +
                        "-7 -2 7 2\n",
                        "0\n" +
                                "0\n" +
                                "0\n" +
                                "4\n" +
                                "0\n" +
                                "96\n" +
                                "56\n"},
                {"3\n" +
                        "-3 -1 3 1\n" +
                        "-2 -1 2 1\n" +
                        "-1 -1 1 1\n", "4\n" +
                        "4\n" +
                        "4\n"},


                {"3\n" +
                        "-3 -1 3 1\n" +
                        "0 0 0 0\n" +
                        "-1 -5 1 5\n", "8\n" +
                        "0\n" +
                        "20\n"},
                {"4\n" +
                        "-6 -3 6 3\n" +
                        "-6 -3 6 3\n" +
                        "-6 -1 6 1\n" +
                        "-1 -5 1 5\n", "0\n" +
                        "40\n" +
                        "20\n" +
                        "20\n"},
                {"4\n" +
                        "-10 0 0 10\n" +
                        "-6 -3 6 3\n" +
                        "-6 -1 6 1\n" +
                        "-1 -5 1 5\n", "80\n" +
                        "40\n" +
                        "20\n" +
                        "20\n"},
                {"3\n" + "-1 -7 1 7\n" + "-1000000000 -1 1000000000 1\n"
                        + "0 -1000000000 0 1000000000\n",
                        "24\n" +
                                "4000000000\n"
                                + "0\n"},
                {"3\n" + "-1000000000 -1000000000 1000000000 1000000000\n" +
                        "-999999999 -999999999 999999999 999999999\n"
                        + "-999999998 -999999998 999999998 999999998\n",
                        "7999999996\n"
                                + "7999999988\n"
                                + "3999999984000000016\n"},
                {"4\n" + "-1000000000 -1000000000 1000000000 1000000000\n" +
                        "-999999999 -999999999 999999999 999999999\n"
                        + "-999999998 -999999998 999999998 999999998\n"
                        + "-1000000000 -1000000000 1000000000 1000000000\n",
                        "0\n"
                                + "0\n"
                                + "0\n" +
                                "4000000000000000000\n"},
                {"9\n" +
                        "-9 -9 9 9\n" +
                        "-8 -9 8 9\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-1 -1 1 1\n" +
                        "-8 -1 8 1\n" +
                        "-6 -6 6 6\n" +
                        "-6 -6 6 6\n" +
                        "-7 -2 7 2\n",
                        "36\n" +
                                "132\n" +
                                "0\n" +
                                "0\n" +
                                "0\n" +
                                "4\n" +
                                "0\n" +
                                "96\n" +
                                "56\n"},
                {"1\n" + "-10 -10 10 10\n",
                        "400\n"},
                {"1\n" + "0 0 0 0\n",
                        "0\n"},
                {"3\n" +
                        "-6 -6 6 6\n" +
                        "-5 -4 5 4\n" +
                        "-5 -5 5 5\n", "44\n" +
                        "0\n" +
                        "100\n"},
                {"3\n" +
                        "-1 -6 1 6\n" +
                        "0 0 0 0\n" +
                        "-2 -5 2 5\n", "4\n" +
                        "0\n" +
                        "40\n"},
                {"9\n" +
                        "-9 -9 9 9\n" +
                        "-8 -9 8 9\n" +
                        "-6 -2 6 2\n" +
                        "-4 -3 4 3\n" +
                        "-1 -1 1 1\n" +
                        "-6 -6 6 6\n" +
                        "-8 -3 8 3\n" +
                        "-9 -2 9 2\n" +
                        "-7 -4 7 4\n",
                        "28\n" +
                                "116\n" +
                                "0\n" +
                                "0\n" +
                                "0\n" +
                                "48\n" +
                                "4\n" +
                                "16\n" +
                                "112\n"}


        };
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("300000\n");
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 1; i <= 300000; i++) {
            stringBuilder.append(-i).append(" ").append(-i).append(" ")
                    .append(i).append(" ").append(i).append("\n");
        }
        char[] cb = new char[299999 * 2];
        for (int j = 0; j < 599998; j = j + 2) {
            cb[j] = '0';
            cb[j + 1] = '\n';
        }

        stringBuilder1.append(cb);

        long a = 300000L * 300000L * 4;
        stringBuilder1.append(a + "\n");


        data = new Object[][]{{stringBuilder.toString(), stringBuilder1.toString()}};

        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in,String out) throws IOException, InterruptedException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        SatelliteFastMapping1.main(new String[]{});
        Assert.assertEquals(output.toString(),out);

    }

}