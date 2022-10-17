import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;


import java.io.*;


public class GenealogyTest {
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
                {"9\n" +
                        "Alexei Peter_I\n" +
                        "Anna Peter_I\n" +
                        "Elizabeth Peter_I\n" +
                        "Peter_II Alexei\n" +
                        "Peter_III Anna\n" +
                        "Paul_I Peter_III\n" +
                        "Alexander_I Paul_I\n" +
                        "Nicholaus_I Paul_I\n" +
                        "Anna Nicholaus_I\n" +
                        "Peter_II Peter_I\n" +
                        "Alexei Paul_I\n", "1 2 0\n"},


        };
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        Genealogy.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}