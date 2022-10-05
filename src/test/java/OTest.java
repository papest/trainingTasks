import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;


public class OTest {
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
                {"5 3\n" +
                        "1 1\n" +
                        "3 3\n" +
                        "8 10\n" +
                        "4 1\n" +
                        "1 2\n" +
                        "1\n", "7\n"},
                {"2 1\n" +
                        "1 10\n" +
                        "0 20\n" +
                        "1\n", "21\n"},
                {"2 2\n" +
                        "2 2\n" +
                        "3 3\n" +
                        "1\n", "1\n"},
                {"0 0\n" + "2\n", "2\n"},
                {"1 1\n" + "2 3\n" + "5\n", "8\n"},
                {"3 3\n" +
                        "0 3\n" +
                        "4 2\n" +
                        "3 1\n" +
                        "0\n","6\n"}

        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        O.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}