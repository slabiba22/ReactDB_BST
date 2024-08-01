
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024.1
 */


public class KVPairTest extends TestCase {
    
    private KVPair<String, Integer> pair;
    private KVPair<Integer, Integer> pair1;
    private KVPair<Integer, Integer> pair2;
    private KVPair<Integer, Integer> pair3;


    /**
     * set up that runs before each test.
     */

    public void setUp() {
        pair = new KVPair<>("ABC", 123);
        pair1 = new KVPair<>(1, 345);
        pair2 = new KVPair<>(2, 123);
        pair3 = new KVPair<>(1, 908);
 
    }

    // ----------------------------------------------------------
    /**
     * Tests to see if the correct key is returned
     */
    public void testGetKey() {
        assertEquals("ABC", pair.getKey());
    }

    /**
     * Test the getValue method.
     */
    public void testGetValue() {
        assertTrue(pair.getValue() == 123);
    }
    
    /**
     * Tests the toString
     */
    public void testToString()
    {
        String expectedOutput = "ABC, 123";
        assertEquals(expectedOutput, pair.toString());
    }
    
    /**
     * Tests the compareTo value
     */
    public void testCompareTo()
    {
        // different key
        assertEquals(-1, pair1.compareTo(pair2));
        //assertEquals(1, pair2.compareTo(pair1));

        //same key
        assertEquals(0, pair1.compareTo(pair3));
    }

}
