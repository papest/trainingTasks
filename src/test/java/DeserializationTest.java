import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;


import java.io.*;


public class DeserializationTest {
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
                        "DUDDUU\n" +
                        "DU\n", "4\n" +
                        "0\n" +
                        "100\n" +
                        "101\n" +
                        "11\n" +
                        "2\n" +
                        "0\n" +
                        "1\n"},
                {"1\n" +
                        "DDUDUUDDDUUU\n", "7\n" +
                        "00\n" +
                        "010\n" +
                        "011\n" +
                        "1000\n" +
                        "1001\n" +
                        "101\n" +
                        "11\n"},
                {"1\n" + "\n", "0\n"}

        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        Deserialization.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}