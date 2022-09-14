import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class HTest {
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
                {"2\n" +
                        "2 6\n", "8\n"},
                {"3\n" +
                        "6 2 4\n" , "18\n"},
                {"5\n" +
                        "2 3 4 5 6 7\n", "68\n"},

        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        H.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}