import java.util.*;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the methods of BST class
 *
 * @author CS Staff
 * @version 2024-05-22
 */
public class BSTTest2 extends student.TestCase {

    private BST<String> bst;

    /**
     * setUp the condition.
     */
    public void setUp() {
       
        bst = new BST<String>();
        bst.insert("gas");
        bst.insert("bar");
        bst.insert("cat");

    }


    /**
     * This defines an assertFuzzyContains method that you could use to test
     * your code
     * 
     * @param m
     *            expected output in informal language
     * @param line
     *            the command being tested
     * @param substrs
     *            expected output
     */
    public void assertFuzzyContains(String m, String line, String... substrs) {
        assertTrue(m, fuzzyContains(line, substrs));
    }


    /**
     * Example 1: Test tree not null
     */
    public void testBasic() {
        BST<String> theTree = new BST<String>();
        assertNotNull(theTree);
    }


    /**
     * Example 2: Test tree size in empty tree
     */
    public void testBST() {
        assertEquals("empty BST size should be zero", 3, bst.size());
    }


    /**
     * Example 4:
     */
    public void testFuzzy() {
  

        bst = new BST<String>();
        bst.insert("hello");

        String[] dumps = bst.dump().split("\n");
        assertFuzzyContains("BST dump should have one real node", dumps[1],
            "Node has depth", "Value (hello)");
    }


    /**
     * Tests to see if the inserts in setUp works
     */
    public void testInsert() {
        assertEquals(3, bst.size());
        assertEquals("(bar, cat, gas)", bst.toString());
    }


    /**
     * Tests to see if remove works correctly for leaf node
     */
    public void testRemoveLeafNode() {

        assertEquals("cat", bst.remove("cat"));
        assertEquals(2, bst.size());
        assertTrue(bst.find("car").isEmpty());
        assertEquals("(bar, gas)", bst.toString());
    }


    /**
     * Tests to see if remove works correctly with a node with one child
     */
    public void testOneChildNodeLeft() {

        assertEquals("bar", bst.remove("bar"));
        assertEquals(2, bst.size());
        assertEquals("(cat, gas)", bst.toString());
    }


    /**
     * Tests to see if removes works correctly with a one child
     */
    public void testOneChildNodeRight() {
        bst.insert("joke");
        bst.insert("horse");
        assertEquals("joke", bst.remove("joke"));
        assertEquals("(bar, cat, gas, horse)", bst.toString());

    }


    /**
     * Tests to see if remove works correctly with a node with two children
     */
    public void testTwoChildNodeLeft() {
        bst.insert("abc");
        assertEquals(4, bst.size());
        assertEquals("(abc, bar, cat, gas)", bst.toString());

        assertEquals("bar", bst.remove("bar"));
        assertEquals("(abc, cat, gas)", bst.toString());

    }


    /**
     * Tests to see if remove works correctly with a node with two children
     */

    public void testRemoveNodeWithTwoChildren() {
        assertEquals(3, bst.size());
        assertEquals("(bar, cat, gas)", bst.toString());

        assertEquals("gas", bst.remove("gas"));
        assertEquals(2, bst.size());
        assertEquals("(bar, cat)", bst.toString());
    }


    /**
     * Tests to see if remove works correctly with a node with two children
     */
    public void testTwoChildNodeRight() {
        bst.insert("joke");
        bst.insert("horse");
        bst.insert("lion");
        assertEquals("joke", bst.remove("joke"));
        assertEquals("(bar, cat, gas, horse, lion)", bst.toString());

    }


    /**
     * Tests to see if remove works correctly when root is deleted
     */
    public void testRemoveRoot() {
        assertEquals("gas", bst.remove("gas"));
        assertEquals(2, bst.size());
        assertTrue(bst.find("gas").isEmpty());
        assertEquals("(bar, cat)", bst.toString());
    }


    /**
     * Tests remove with null root
     */
    public void testRemoveNullR() {
        assertEquals(3, bst.size());
        assertNull(bst.remove("nonexistent"));
        assertEquals("(bar, cat, gas)", bst.toString());
        bst = new BST<String>();
        assertEquals(0, bst.size());
        assertNull(bst.remove("bruh"));
        assertEquals("()", bst.toString());

    }


    // ----------------------------------------------------------
    /**
     * To get coverage in getMax
     */
    public void testRemoveMaxCoverage() {
        bst = new BST<String>();
        bst.insert("d");
        bst.insert("b");
        bst.insert("f");
        bst.insert("a");
        bst.insert("c");
        bst.insert("e");
        bst.insert("g");

        // triggers the deleteMax right subtree 
        assertEquals("g", bst.remove("g")); 
        assertEquals(6, bst.size());
        assertEquals("(a, b, c, d, e, f)", bst.toString());

        assertEquals("f", bst.remove("f"));
        assertEquals(5, bst.size());
        assertEquals("(a, b, c, d, e)", bst.toString());

        assertEquals("d", bst.remove("d"));
        assertEquals(4, bst.size());
        assertEquals("(a, b, c, e)", bst.toString());
    }


    /**
     * tests removing in a bigger tree
     */

    public void testRemoveNodeWithTwoChildrenComplex() {
        bst = new BST<String>();
        bst.insert("algorithm");
        bst.insert("binary");
        bst.insert("compiler");
        bst.insert("array");
        bst.insert("bytecode");
        bst.insert("data");
        bst.insert("function");

        assertEquals("algorithm", bst.remove("algorithm"));
        assertEquals(6, bst.size());
        assertTrue(bst.find("algorithm").isEmpty());
        assertEquals("(array, binary, bytecode, compiler, data, function)", bst
            .toString());
    }


    /**
     * Testing got getMax coverage
     */
    public void testRemoveMax2() {
        bst = new BST<String>();
        bst.insert("gas");
        bst.insert("apple");
        bst.insert("bar");
        bst.insert("joke");
        bst.insert("horse");
        bst.insert("lion");

        assertEquals("joke", bst.remove("joke"));
        assertEquals("(apple, bar, gas, horse, lion)", bst.toString());

    }


    /**
     * Testing got getMax coverage
     */
    public void testRemoveMax3() {
        BST<String> bst1 = new BST<String>();
        bst1.insert("m");
        bst1.insert("f");
        bst1.insert("t");
        bst1.insert("b");
        bst1.insert("h");
        bst1.insert("r");
        bst1.insert("z");
        bst1.insert("a");
        bst1.insert("d"); 
        bst1.insert("g");
        bst1.insert("j");
        bst1.insert("p");
        bst1.insert("x");

        assertEquals("t", bst1.remove("t")); 
        assertEquals("(a, b, d, f, g, h, j, m, p, r, z, x)", bst1.toString());
    }


    /**
     * Tests to see if find works correctly
     */
    public void testFind() {
        List<String> found = bst.find("gas");
        assertEquals(1, found.size());
        assertEquals("gas", found.get(0));

        found = bst.find("nonexistent");
        assertTrue(found.isEmpty());

    }


    /**
     * Tests the iterator to see if transvering in-order
     */
    public void testIterator() {
        Iterator<BSTNode<String>> iterator = bst.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("bar", iterator.next().getValue());
        assertTrue(iterator.hasNext());
        assertEquals("cat", iterator.next().getValue());
        assertTrue(iterator.hasNext());
        assertEquals("gas", iterator.next().getValue());
        assertFalse(iterator.hasNext());
    }


    // ----------------------------------------------------------
    /**
     * Checks to see if the correct exception is thrown
     */
    public void testIteratorNextThrowsException() {
        Iterator<BSTNode<String>> iterator = bst.iterator();

        

        Exception thrown = null;
        try {
            assertEquals("bar", iterator.next().getValue());
            assertEquals("cat", iterator.next().getValue());
            assertEquals("gas", iterator.next().getValue());
            assertFalse(iterator.hasNext());
            iterator.next();
        }
        catch (NoSuchElementException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
    }

}
