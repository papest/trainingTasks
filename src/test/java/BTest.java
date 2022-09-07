import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class BTest {
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
                {"7\n" +
                        "3\n" +
                        "5 8 2 1 3 4 11\n", "24\n"},
                {"5\n" +
                        "5\n" +
                        "1 2 3 4 5\n", "15\n"},
                {"7\n" +
                        "4\n" +
                        "1 1 9 2 2 2 6\n", "17\n"},

                {"7\n" +
                        "6\n" +
                        "1 1 9 2 2 2 6\n", "22\n"}


        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        B.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}