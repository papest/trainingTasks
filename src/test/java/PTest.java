import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;


public class PTest {
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
                {"10 0\n" +
                        "1 2\n" +
                        "3 4\n" +
                        "5 6\n" +
                        "7 -1\n" +
                        "8 -1\n" +
                        "-1 -1\n" +
                        "9 -1\n" +
                        "-1 -1\n" +
                        "-1 -1\n" +
                        "-1 -1\n", "5 8 9 1 0 7 3 2 6\n"
                },
                {"1 0\n" + "-1 -1\n", "0\n"}





        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        P.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}