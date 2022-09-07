import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;


public class ATest {
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
                {"200\n" +
                        "-200\n", "0\n"},
                {"1000000000\n" +
                        "1000000000\n", "2000000000\n"}



        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out)  {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        A.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}