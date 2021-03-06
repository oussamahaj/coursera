import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by trash on 16-Jan-17.
 */
public class SAPTest {

    @Test
    public void testSimpleLength() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        SAP sap = new SAP(g);
        assertEquals(3, sap.length(0, 3));
        assertEquals(2, sap.length(0, 2));
        assertEquals(3, sap.length(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleLength_CycleTest() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        SAP sap = new SAP(g);
    }

    @Test(expected = NullPointerException.class)
    public void testCornerCases_NullArgument() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        SAP sap = new SAP(g);
        assertEquals(3, sap.length(null, null));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCornerCases_IndexOutOfBound() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 3);
        SAP sap = new SAP(g);
        assertEquals(3, sap.length(0, 5));
    }

    @Test(expected = NullPointerException.class)
    public void testCornerCases_NullGraph() {
        new SAP(null);
    }

    @Test
    public void testSimpleLength1() {
        Digraph g = new Digraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        SAP sap = new SAP(g);
        assertEquals(2, sap.length(0, 3));
        assertEquals(1, sap.length(0, 2));
        assertEquals(2, sap.length(3, 0));
        assertEquals(3, sap.ancestor(3, 0));
    }

    @Test
    public void testSimpleSAP_Five() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        SAP sap = new SAP(g);
        assertEquals(5, sap.length(1, 0));
        assertEquals(5, sap.ancestor(1, 5));
    }

    @Test
    public void testSimpleSAP() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        SAP sap = new SAP(g);
        assertEquals(1, sap.length(1, 0));
        assertEquals(0, sap.ancestor(1, 5));
    }

    @Test
    public void testDigraph1() throws FileNotFoundException {
//        File source = new File("src/test/resources/input/digraph1.txt");
        InputStream source = SAPTest.class.getClassLoader().getResourceAsStream("input/digraph1.txt");
        In in = new In(new Scanner(source));
        Digraph g = new Digraph(in);
        SAP sap = new SAP(g);
        assertEquals(1, sap.ancestor(3, 11));
        assertEquals(4, sap.length(3, 11));
        assertEquals(5, sap.ancestor(9, 12));
        assertEquals(3, sap.length(9, 12));
        assertEquals(0, sap.ancestor(7, 2));
        assertEquals(4, sap.length(7, 2));
        assertEquals(-1, sap.ancestor(1, 6));
        assertEquals(-1, sap.length(1, 6));
    }

    @Test
    public void testLength_OnVertexSet_Digraph1() throws FileNotFoundException {
//        File source = new File("src/test/resources/input/digraph1.txt");
        InputStream source = SAPTest.class.getClassLoader().getResourceAsStream("input/digraph1.txt");
        In in = new In(new Scanner(source));
        Digraph g = new Digraph(in);
        SAP sap = new SAP(g);
        assertEquals(1, sap.ancestor(Arrays.asList(7, 8), Arrays.asList(11, 12)));
        assertEquals(5, sap.length(Arrays.asList(7, 8), Arrays.asList(11, 12)));
        assertEquals(4, sap.length(Arrays.asList(7, 8), Arrays.asList(9, 11, 12)));
        assertEquals(1, sap.ancestor(Arrays.asList(7, 8), Arrays.asList(9, 11, 12)));
        assertEquals(3, sap.length(Arrays.asList(4, 7, 8), Arrays.asList(9, 11, 12)));
        assertEquals(1, sap.ancestor(Arrays.asList(4, 7, 8), Arrays.asList(9, 11, 12)));
    }

}