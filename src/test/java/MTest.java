import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import java.io.*;


public class MTest {
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
                {"6\n" +
                        "9\n" +
                        "set 0 3\n" +
                        "set 1 8\n" +
                        "begin_new_era 6000\n" +
                        "get 0 0\n" +
                        "get 1 0\n" +
                        "set 0 9\n" +
                        "begin_new_era 1000000\n" +
                        "get 0 6000\n" +
                        "get 0 0\n",
                        "3\n" +
                                "8\n" +
                                "9\n" +
                                "3\n"},
                {"1\n" +
                        "12\n" +
                        "set 0 1\n" +
                        "set 0 2\n" +
                        "begin_new_era 1000\n" +
                        "set 0 4\n" +
                        "set 0 100\n" +
                        "begin_new_era 666\n" +
                        "set 0 7\n" +
                        "set 0 42\n" +
                        "begin_new_era 424242\n" +
                        "get 0 0\n" +
                        "get 0 666\n" +
                        "get 0 1000\n",
                        "2\n" +
                        "42\n" +
                        "100\n"}


        };


        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        M.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}