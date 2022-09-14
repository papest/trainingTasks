import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.testng.Assert;
import org.testng.ITestResult;

import org.testng.annotations.DataProvider;


import java.io.*;


public class FTest {
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
        Object[][] data = new Object[][] {
                {"vxOoOoVvx\n", "vxx\n"},
                {"abBa\n", "aa\n"},
                {"AbBa\n", "\n"},
                {"AaBB\n", "BB\n"},
                {"AabBcc\n", "cc\n"},
                {"AabB\n", "\n"},
                {"CAaBbccCKnNk\n","\n"},
                {"CaAFfKkcccNnAaaAc\n", "ccc\n"},
                {"\n", "\n"}

        };
        StringBuilder stringBuilder = new StringBuilder(100000);
        while (stringBuilder.length() < 100000) {
            for (int i = 'a'; i < 'z'; i++) {
                stringBuilder.append((char) i).append(Character.toUpperCase((char) i));
            }
        }
        stringBuilder.append("\n");
        data = new Object[][]{{stringBuilder.toString(), "\n"}};
        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        F.main(new String[]{});
        Assert.assertEquals(output.toString(), out);

    }

}