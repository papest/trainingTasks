import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class STest {
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
                {"127.0.0.1\n", "IPv4\n"},
                {"...9\n", "Error\n"},
                {"2001:8:853:0:0:82:0370:733\n", "IPv6\n"},
                {"2001:8:853:0:0:82:0_0:733\n", "Error\n"},
                {"2001:8:853:0:0:82:-1:733\n", "Error\n"},
                {"256.0.0.0" , "Error\n"},
                {"2001:0db8:85a3:00:0:8a2e:0370:733\n", "IPv6\n"},
                {"2001:0db8:85a3:00:0:8a2e0:0370:733\n", "Error\n"},
                {"01:0db8:85a3:0:030:8a2e:0370:7334\n","IPv6\n"},
                {"FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF\n", "IPv6\n"},
                {"00:0:000:000:-0:00:0:0","Error\n"},
                {"0.0.0.-0", "Error\n"},
                {"255.0", "Error\n"},
                {"5.8.09.0\n", "Error\n"},
                {"5.8..0\n", "Error\n"},
                {"127.0.0.1\n", "Error\n"},


        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        S.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}