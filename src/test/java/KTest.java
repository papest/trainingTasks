import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import java.io.*;


public class KTest {
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
                {"*a*\n" +
                        "adce\n", "YES\n"},
                {"*a**\n" +
                        "adce\n", "YES\n"},
                {"*\n" +
                        "xyz\n", "YES\n"},
                {"algorithms\n" +
                        "algorithmus\n", "NO\n"},
                {"*o****rit**h*m***\n" +
                        "algorithm\n", "YES\n"},
                {"?\n" +
                        "k\n", "YES\n"},
                {"?\n" +
                        "KK\n", "NO\n"},
                {"?*\n" +
                        "KK\n", "NO\n"},
                {"a*b*c\n"+ "abc\n", "YES\n"},
                {"*a*b*c*\n"+ "abc\n", "YES\n"},
                {"*a*b*ck*\n"+ "abc\n", "NO\n"},
                {"*a*b*ck\n"+ "abc\n", "NO\n"},
                {"*****\n" +
                        "k\n", "YES\n"},
                {"*\n" +
                        "kjjjjioppl\n", "YES\n"},
                {"*abcdef\n" +"cdef\n", "NO\n"},
                {"*abcdef\n" +"abcdef\n", "YES\n"},
                {"ab?\n" + "ab\n", "NO\n"},
                {"a??k\n" + "abk\n", "NO\n"},
                {"?****?*?\n" + "abkcc\n", "YES\n"},
                {"ab*???\n" + "abdd\n", "NO\n"},
                {"ab*????\n" + "abdd\n", "NO\n"},
                {"?b*?d*\n" + "abdd\n", "YES\n"},
                {"ab*??\n" + "abdd\n", "YES\n"},
                {"*abc**ab***hk\n" +"abkabchakbjabjhk\n", "YES\n"},
                {"*abc**abc***hk\n" +"abkabchakbjabcjhk\n", "YES\n"},
                {"*ab**abc***hk\n" +"abkabchakbjabcjhkhk\n", "YES\n"},
                {"*ab?**ab?c***hk\n" +"abkabchakbjabjcjhkhk\n", "YES\n"},
                {"*ab?**ab?c***hk\n" +"abkabchakbjabjcjhkhkk\n", "NO\n"},
                {"*ab?**ab?c***hk\n" +"abkabchakbjabjjhkhk\n", "NO\n"},
                {"*ab?**?b?c***hk\n" +"abkabchakbjabjjhkhk\n", "NO\n"},
                {"*a?b?*****hk\n" +"abkabchakbjabjjhkhk\n", "YES\n"},
                {"abkabchakbjab*?jhkhk\n" +"abkabchakbjabjjhkhk\n", "YES\n"},
                {"algorithms*k\n" +
                        "algorithms\n", "NO\n"},
                {"**algorithms\n" +
                        "algorithms\n", "YES\n"}

        };
//        final int x = 2000;
//        char[] arrayTemplate = new char[x];
//        for (int i = 0; i < x; i++) {
//            arrayTemplate[i] = i % 2 == 0 ? '?': '*';
//        }
//        char[] arrayString = new char[x];
//        for (int i = 0; i < x; i++) {
//            arrayString[i] = (char)(i % 2 == 0 ? 'a' :'a' + i%20);
//        }
//        data = new Object[][] {
//                {
//                    new String(arrayTemplate) + "\n" +
//                            new String(arrayString) + "\n",
//                        "YES\n"
//                }};

        return data;
    }

    @Test(dataProvider = "data")
    public void testMain(String in, String out) throws IOException {
        input = new ByteArrayInputStream(in.getBytes());
        System.setIn(input);
        K.main(new String[]{});
        Assert.assertEquals(output.toString(), out);
    }

}