package pg;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class NTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        System.out.println(result.getEndMillis() - result.getStartMillis());
    }
    @DataProvider
    Iterator<Object[]> data() {
        final int COUNT = 100000;
        ArrayList<Object[]> nodes = new ArrayList<>(COUNT);
        Node[] array = new Node[COUNT];
        for (int i = 0; i < COUNT ; i++) {
            array[i] = new Node(i + 1);
        }
        for (int i = 1; i < COUNT ; i++) {
            array[i].neighbours.add(array[i - 1]);
        }
        for (int i = 0; i < COUNT - 1 ; i++) {
            array[i].neighbours.add(array[i + 1]);
        }
        for (int i = 0; i < 5; i++) {
            nodes.add(new Node[]{array[i]});
        }

        Iterator<Object[]> data = nodes.iterator();

        return data;
    }

    @Test(dataProvider = "data")
    public void testCloneGraph(Node node) {
        Node node1 = N.cloneGraph(node);
        Assert.assertEquals(node.val, node1.val);
        Assert.assertEquals(node.neighbours.size(), node1.neighbours.size());
        Assert.assertNotEquals(node, node1);
    }
}