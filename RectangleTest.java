import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author Labiba Sajjad
 * @version 2024-05-28
 */
public class RectangleTest extends TestCase {

    /**
     * Initializes a rectangle object to be used for the tests.
     */

    // Fields
    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private Rectangle r5;
    private Rectangle r6; 
    private Rectangle r7;
    private Rectangle r8;

    /**
     * set up that runs before each test.
     */
    public void setUp() {

        // r1 and r2 doesn't overlap
        // r1 and r3 overlaps
        // r1 and r4 overlaps

        r1 = new Rectangle(10, 10, 5, 5);
        r2 = new Rectangle(15, 10, 5, 5);
        r3 = new Rectangle(11, 11, 1, 1);
        r4 = new Rectangle(14, 10, 5, 5);
        r5 = new Rectangle(10, 10, 5, 5);
        r6 = new Rectangle(10, 9, 5, 5);
        r7 = new Rectangle(10, 10, 4, 5);
        r8 = new Rectangle(10, 10, 5, 4);
    }


    /**
     * Tests all the basic getters
     */
    public void testGetters() {

        assertEquals(r1.getxCoordinate(), 10);
        assertEquals(r1.getyCoordinate(), 10);
        assertEquals(r1.getWidth(), 5);
        assertEquals(r1.getHeight(), 5);
    }


    // ----------------------------------------------------------
    /**
     * Tests the equals method to check if two rectangles are equal
     */
    public void testEquals() {
        // compare to itself
        assertTrue(r1.equals(r1));

        // compare to null
        assertFalse(r1.equals(null));

        // compares to a different class
        assertFalse(r1.equals("Rectangle"));

        // compares to a different rectangle that has the same measurements
        assertTrue(r1.equals(r5));

        // compares to a retangle with same the measurements except the x cord
        assertFalse(r1.equals(r2));

        // compares to a retangle with same the measurements except the y cord
        assertFalse(r1.equals(r6));

        // compares to a retangle with same the measurements except the width
        assertFalse(r1.equals(r7));

        // compares to a retangle with same the measurements except the height
        assertFalse(r1.equals(r8));

    }


    // ----------------------------------------------------------
    /**
     * Tests to check the toString prints the right string
     */
    public void testToString() {
        String expectedString = "10, 10, 5, 5";
        assertEquals(expectedString, r1.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests to check if method can recognize incorrect cords
     */
    public void testInvalid() {
        // Rectangle with all invalid cords
        Rectangle r9 = new Rectangle(-1, -2, 0, 0);
        assertTrue(r9.isInvalid());

        // Rectangle with invalid x cord
        Rectangle r10 = new Rectangle(-1, 4, 1, 2);
        assertTrue(r10.isInvalid());

        // Rectangle with invalid y cord
        Rectangle r11 = new Rectangle(1, -4, 1, 2);
        assertTrue(r11.isInvalid());

        // Rectangle with invalid width
        Rectangle r12 = new Rectangle(1, 4, 0, 2);
        assertTrue(r12.isInvalid());

        // Rectangle with invalid height
        Rectangle r13 = new Rectangle(1, 4, 1, 0);
        assertTrue(r13.isInvalid());

        // Rectangle with valid coords
        assertFalse(r1.isInvalid());

    }


    // ----------------------------------------------------------
    /**
     * Tests to check if the method can recognize if two rectangles intersect
     */
    public void testIntersect() {

        // Intersects with itself
        assertFalse(r1.intersect(r1));

        // Should not intersect according to specs
        assertFalse(r1.intersect(r2));
        assertFalse(r2.intersect(r1));

        // Should intersect according to specs
        assertTrue(r1.intersect(r3));
        assertTrue(r3.intersect(r1));
        
        // Edge intersect
        assertTrue(r1.intersect(r4));
        assertTrue(r4.intersect(r1));
        
        assertTrue(r1.intersect(r6));
        assertTrue(r6.intersect(r1));

        // Should intersect according to specs
        assertTrue(r2.intersect(r4));
        assertTrue(r4.intersect(r2));
        
        // identical 
        assertTrue(r1.intersect(r5)); 
        
       // Partial width intersect
        assertTrue(r1.intersect(r7));
        assertTrue(r7.intersect(r1));

        // Partial height intersect
        assertTrue(r1.intersect(r8));
        assertTrue(r8.intersect(r1));
        

        Rectangle r14 = new Rectangle(10, 15, 5, 5); // Directly below r1
        Rectangle r15 = new Rectangle(5, 10, 5, 5); // Directly to the left of
                                                    // r1
        Rectangle r16 = new Rectangle(16, 10, 5, 5); // Directly to the right of
                                                     // r1
        Rectangle r17 = new Rectangle(10, 5, 5, 5); // Directly above r1

        assertFalse(r1.intersect(r14));
        assertFalse(r1.intersect(r15));
        assertFalse(r1.intersect(r16));
        assertFalse(r1.intersect(r17));
        
        //Touch corner
        Rectangle r13 = new Rectangle(15, 15, 1, 1); 
        assertFalse(r1.intersect(r13)); 
        
        //invalid rectangle
        Rectangle r18 = new Rectangle(10, 10, 0, 0);
        Rectangle r19 = new Rectangle(10, 10, 5, 0);
        Rectangle r20 = new Rectangle(10, 10, 0, 5);
        assertFalse(r1.intersect(r18));
        assertFalse(r18.intersect(r1));
        
        assertFalse(r1.intersect(r19));
        assertFalse(r19.intersect(r1));
        
        assertFalse(r1.intersect(r20));
        assertFalse(r20.intersect(r1));

    }

}
