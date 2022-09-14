import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class ETest {
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
                {"VIV\n", "-1\n"},
                {"II\n" , "2\n"},
                {"MCMLXI\n", "1961\n"},
                {"CCCL\n", "350\n"},
                {"I\n", "1\n"},
                {"GGGGGGGGGG", "-1\n"},
                {"DCXCIX\n", "699\n"},
                {"CMXCIX\n", "999\n"},
                {"MMMCMXC\n", "3990\n"},
                {"MMMCMD\n", "-1\n"}


        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        E.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}