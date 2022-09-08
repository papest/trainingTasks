import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class DTest {
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
                {"2 3\n" +
                        "10 8 5\n" +
                        "10 5 4\n", "4\n"},
                {"2 2\n" +
                        "1 1\n" +
                        "1 1\n", "1\n"},
                {"2 2\n" +
                        "10 9\n" +
                        "9 11\n", "2\n"},
                {"4 5\n" +
                "1 2 3 4 5\n" +
                "10 9 8 7 6\n" +
                "11 12 13 14 15\n" +
                "20 19 18 17 16\n", "20\n"},
                {"3 4\n" +
                        "1 2 3 4\n" +
                        "10 11 12 5\n" +
                        "9 8 7 6", "12\n"}


        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        D.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}