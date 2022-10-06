import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;


public class QTest {
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
                {"5\n" +
                        "32 6\n" +
                        "29 2\n" +
                        "32 6\n" +
                        "29 2\n" +
                        "35 10\n", "YES\n"},
                {"2\n" +
                        "-22 -75\n" +
                        "-30 -70\n", "YES\n"},

        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        Q.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}