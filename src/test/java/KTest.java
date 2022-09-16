import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import java.io.*;


public class KTest {
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
                {"*a*\n" +
                        "adce\n", "YES\n"},
                {"*\n" +
                        "xyz\n", "YES\n"},
                {"algorithms\n" +
                        "algorithmus\n", "NO\n"},
                {"?\n" +
                        "k\n", "YES\n"},
                {"?\n" +
                        "KK\n", "NO\n"},
                {"*****\n" +
                        "k\n", "YES\n"}

        };
//        final int x = 2000;
//        char[] arrayTemplate = new char[x];
//        for (int i = 0; i < x; i++) {
//            arrayTemplate[i] = '*';
//        }
//        char[] arrayString = new char[x];
//        for (int i = 0; i < x; i++) {
//            arrayString[i] = (char)('a' + i%20);
//        }
//        data = new Object[][] {
//                {
//                    new String(arrayTemplate) + "\n" +
//                            new String(arrayString) + "\n",
//                        "YES\n"
//                }};

        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        K.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}