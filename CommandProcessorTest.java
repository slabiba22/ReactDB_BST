import java.util.List;
import student.TestCase;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author Labiba Sajjad
 * @version <version_no>
 */
public class CommandProcessorTest extends TestCase {

    // private static final boolean String = false;
    private Database db;
    private CommandProcessor cp;

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * creat a database here for use in each test case.
     */
    public void setUp() {
        db = new Database();
        cp = new CommandProcessor(db);

    }


    /**
     * Tests the remove(name) method
     */
    public void testRemoveName() {
        //
        cp.processor("insert r4 20 25 7 9");
        cp.processor("insert r4 20 12 3 3");
        cp.processor("insert b 10 9 2 4");

        cp.processor("remove r4");

        // dump to check if a was removed
        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 1, Value (b, 10, 9, 2, 4)\n"
            + "Node has depth 0, Value (r4, 20, 25, 7, 9)\n" + "BST size is: 2";

        assertEquals(expectedOutput, out);

        // Search for a to see if it returns the right a
        cp.processor("search r4");
        out = db.search("r4");
        expectedOutput = "Rectangles found matching \"r4\":\n"
            + "(r4, 20, 25, 7, 9)";
        assertEquals(expectedOutput, out);

    }


    // ----------------------------------------------------------
    /**
     * Tests a valid rectangle
     */
    public void testValid() {
        cp.processor("insert a 1 0 2 4");
        String expectedOutput = "Rectangle accepted: a 1, 0, 2, 4";
        assertEquals(expectedOutput, cp.getOutput());

    }


    // ----------------------------------------------------------
    /**
     * Tests for when two recs with the same name is inserted
     */
    public void testValidSameName() {
        cp.processor("insert a 1 0 2 4");
        cp.processor("insert a 1 0 2 4");
        cp.processor("insert a 3 6 7 8");
        String expectedOutput = "Rectangle accepted: a 1, 0, 2, 4\n"
            + "Rectangle accepted: a 1, 0, 2, 4\n"
            + "Rectangle accepted: a 3, 6, 7, 8";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests for invalid coord
     */
    public void testInvalidCoord() {

        // invalid x
        cp.processor("insert b -1 3 5 3");
        String expectedOutput = "Rectangle rejected: b -1, 3, 5, 3";
        assertEquals(expectedOutput, cp.getOutput());

        // invalid y
        cp.processor("insert ab 1 -3 5 3");
        String expectedOutput1 = "Rectangle rejected: b -1, 3, 5, 3\n"
            + "Rectangle rejected: ab 1, -3, 5, 3";
        assertEquals(expectedOutput1, cp.getOutput());

        // invalid w
        cp.processor("insert b 1 3 0 3");
        String expectedOutput2 = "Rectangle rejected: b -1, 3, 5, 3\n"
            + "Rectangle rejected: ab 1, -3, 5, 3\n"
            + "Rectangle rejected: b 1, 3, 0, 3";
        assertEquals(expectedOutput2, cp.getOutput());

        // invalid h
        cp.processor("insert b 1 3 5 0");
        String expectedOutput3 = "Rectangle rejected: b -1, 3, 5, 3\n"
            + "Rectangle rejected: ab 1, -3, 5, 3\n"
            + "Rectangle rejected: b 1, 3, 0, 3\n"
            + "Rectangle rejected: b 1, 3, 5, 0";
        assertEquals(expectedOutput3, cp.getOutput());

    }


    /**
     * Tests for when coords are out of bounds
     */
    public void testOutofBounds() {

        cp.processor("insert b 900 900 200 200");
        String expectedOutput = "Rectangle rejected: b 900, 900, 200, 200";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests for when height is out of bounds
     */
    public void testOutofBoundsY() {
        cp.processor("insert b 900 900 2 200");
        String expectedOutput = "Rectangle rejected: b 900, 900, 2, 200";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests for when width is out of bounds
     */
    public void testOutOfBoundsX() {
        cp.processor("insert b 900 900 200 2");
        String expectedOutput = "Rectangle rejected: b 900, 900, 200, 2";
        assertEquals(expectedOutput, cp.getOutput());
    }


    /**
     * Tests wehn rectangle is touching the border
     */
    public void testInBounds() {
        cp.processor("insert iso 1020 1020 4 4");
        String expectedOutput = "Rectangle accepted: iso 1020, 1020, 4, 4";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests when rectangle with inValid name
     */
    public void testInsertInvalidName() {
        cp.processor("insert 9a 1020 1020 4 4");
        String expectedOutput = "Rectangle rejected: 9a 1020, 1020, 4, 4";
        assertEquals(expectedOutput, cp.getOutput());
    }


    // ----------------------------------------------------------
    /**
     * Tests to check if the tree dump is being output
     */
    public void testDump() {
        cp.processor("dump");
        String output = db.dump();
        String expectedOut = "BST dump:\n" + "Node has depth 0, Value (null)\n"
            + "BST size is: 0";

        // Compare expected output with execuated output
        assertEquals(expectedOut, output);

        // Process rectangles
        cp.processor("insert name2 1 0 2 4");
        cp.processor("insert name3 1 2 1023 4");

        // Dump to the database
        cp.processor("dump");

        // Get a string of dump
        String out = db.dump();
        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (name2, 1, 0, 2, 4)\n"
            + "Node has depth 1, Value (name3, 1, 2, 1023, 4)\n"
            + "BST size is: 2";

        // Compare expected output with execuated output
        assertEquals(expectedOutput, out);
    }


    /**
     * Tests dump on null tree
     */
    public void testNullDump() {
        cp.processor("dump");

        String out = db.dump();
        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (null)\n" + "BST size is: 0";

        assertEquals(expectedOutput, out);
    }


    /**
     * Tests search method to see if correctly prints out the rectangles
     * with the specified name
     */
    public void testSearch() {
        cp.processor("insert a 10 10 15 15");
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        cp.processor("search a");
        String out = db.search("a");

        String expectedOut = "Rectangles found matching \"a\":\n"
            + "(a, 50, 21, 52, 1)\n" + "(a, 10, 10, 15, 15)";

        assertEquals(expectedOut, out);
    }


    /**
     * Tests with rectangle that doesn't exist
     */

    public void testNoRecSearch() {

        cp.processor("search c");
        String out = db.search("c");
        String expectedOutput = "Rectangle not found: c";

        assertEquals(expectedOutput, out);

    }


    /**
     * Tests remove(name) with non existing rec
     */
    public void testRemoveNameInvalid() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        // Trying to remove rec that doesn't exist
        cp.processor("remove c");
        String out = db.remove("c");

        String expected = "Rectangle not found: (c)";
        assertEquals(expected, out);

    }


    // ----------------------------------------------------------
    /**
     * Tests the remove(x y w h) method
     * everything valid
     */
    public void testRemoveCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        cp.processor("remove 50 21 52 1");

        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (b, 10, 9, 2, 4)\n" + "BST size is: 1";
        assertEquals(expectedOutput, out);

    }


    /**
     * Tests the remove(x y w h) method with two rec w same coords
     */
    public void testRemoveSameCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("insert a 50 21 52 1");

        cp.processor("remove 50 21 52 1");

        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 1, Value (a, 50, 21, 52, 1)\n"
            + "Node has depth 0, Value (b, 10, 9, 2, 4)\n" + "BST size is: 2";

        assertEquals(expectedOutput, out);

    }


    /**
     * Tests the remove(x y w h) method with a nonexisting rec
     */
    public void testRemoveNonRec() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("insert a 50 21 52 1");

        cp.processor("remove 55 20 55 10");
        cp.processor("dump");
        String out = db.remove(55, 20, 55, 10);

        String expected = "Rectangle not found: (55, 20, 55, 10)";

        assertEquals(expected, out);
    }


    /**
     * Tests the remove(x y w h) method with invalid coord
     */
    public void testRemoveInvalidCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("remove -55 -20 55 10");

        String out = db.remove(-55, -20, 55, 10);

        String expected = "Rectangle rejected: (-55, -20, 55, 10)";

        assertEquals(expected, out);

    }


    /**
     * Tests to see if intersections method works
     */
    public void testIntersectionValid() {
        cp.processor("insert a 10 10 15 15");
        cp.processor("insert b 11 11 5 5");
        cp.processor("insert c 0 0 1000 10");
        cp.processor("insert d 0 0 10 1000");

        cp.processor("intersections");

        String expectedOutput = "Rectangle accepted: a 10, 10, 15, 15\n"
            + "Rectangle accepted: b 11, 11, 5, 5\n"
            + "Rectangle accepted: c 0, 0, 1000, 10\n"
            + "Rectangle accepted: d 0, 0, 10, 1000\n" + "Intersection pairs:\n"
            + "a 10, 10, 15, 15 | b 11, 11, 5, 5\n"
            + "b 11, 11, 5, 5 | a 10, 10, 15, 15\n"
            + "c 0, 0, 1000, 10 | d 0, 0, 10, 1000\n"
            + "d 0, 0, 10, 1000 | c 0, 0, 1000, 10";

        assertEquals(expectedOutput, systemOut().getHistory().trim());

    }


    /**
     * Tests intersection with no intersecting recs
     */
    public void testIntersectionsInvalid() {
        cp.processor("insert a 10 10 5 5");
        cp.processor("insert b 15 10 5 5");
        cp.processor("intersections");

        String expectedOut = "Rectangle accepted: a 10, 10, 5, 5\n"
            + "Rectangle accepted: b 15, 10, 5, 5\n" + "Intersection pairs:";
        assertEquals(expectedOut, systemOut().getHistory().trim());
    }


    /**
     * Tests intersections with the same rectangle
     */
    public void testIntersectionSame() {
        cp.processor("insert a 10 10 5 5");
        cp.processor("insert a 10 10 5 5");
        cp.processor("insert b 10 10 5 5");

        cp.processor("intersections");

        String expectedOut = "Rectangle accepted: a 10, 10, 5, 5\n"
            + "Rectangle accepted: a 10, 10, 5, 5\n"
            + "Rectangle accepted: b 10, 10, 5, 5\n" + "Intersection pairs:\n"
            + "a 10, 10, 5, 5 | a 10, 10, 5, 5\n"
            + "a 10, 10, 5, 5 | a 10, 10, 5, 5\n"
            + "a 10, 10, 5, 5 | b 10, 10, 5, 5\n"
            + "b 10, 10, 5, 5 | a 10, 10, 5, 5\n"
            + "a 10, 10, 5, 5 | b 10, 10, 5, 5\n"
            + "b 10, 10, 5, 5 | a 10, 10, 5, 5";

        assertEquals(expectedOut, systemOut().getHistory().trim());
    }


    /**
     * Tests region search valid
     */
    public void testValidRegionsearchValid() {

        cp.processor("insert b 0 0 910 10");
        cp.processor("insert a 0 0 1000 10");

        cp.processor("regionsearch 900 5 1 0");
        String expectedOut = "Rectangle accepted: b 0, 0, 910, 10\n"
            + "Rectangle accepted: a 0, 0, 1000, 10\n"
            + "Rectangle rejected: (900, 5, 1, 0)";
    }


    /**
     * Test region search valid region no intersect
     */
    public void testRegionSearchInvalid() {
        cp.processor("insert b 0 0 910 10");
        cp.processor("insert a 0 0 1000 10");
        cp.processor("regionsearch 0 500 20 1");

        String expectedOut = "Rectangle accepted: b 0, 0, 910, 10\n"
            + "Rectangle accepted: a 0, 0, 1000, 10\n"
            + "Rectangles intersecting region (0, 500, 20, 1):";

        assertEquals(expectedOut, systemOut().getHistory().trim());

    }


    /**
     * Tests invalid region search
     */
    public void testRegionSearchW() {
        cp.processor("regionsearch 0 500 -20 1");
        String expectedOut = "Rectangle rejected: (0, 500, -20, 1)";
        assertEquals(expectedOut, systemOut().getHistory().trim());

    }


    /**
     * Tests invalid region search
     */
    public void testRegionSearchH() {
        cp.processor("regionsearch 0 500 20 -1");
        String expectedOut = "Rectangle rejected: (0, 500, 20, -1)";
        assertEquals(expectedOut, systemOut().getHistory().trim());

    }


    /**
     * Tests region search for more covg
     */
    public void testRSearch()

    {
        cp.processor("insert r1 0 0 217 1474647");
        cp.processor("insert r2 10 10 15 15");
        cp.processor("insert R2 11 11 5 5");
        cp.processor("insert r3 0 0 1000 10");
        cp.processor("insert r4 0 0 10 1000");

        cp.processor("regionsearch 900 5 0 0");
        cp.processor("regionsearch 900 5 1 1");

        String expectedOut = "Rectangle rejected: r1 0, 0, 217, 1474647\n"
            + "Rectangle accepted: r2 10, 10, 15, 15\n"
            + "Rectangle accepted: R2 11, 11, 5, 5\n"
            + "Rectangle accepted: r3 0, 0, 1000, 10\n"
            + "Rectangle accepted: r4 0, 0, 10, 1000\n"
            + "Rectangle rejected: (900, 5, 0, 0)\n"
            + "Rectangles intersecting region (900, 5, 1, 1):\n"
            + "(r3, 0, 0, 1000, 10)";
        assertEquals(expectedOut, systemOut().getHistory().trim());
    }

}
