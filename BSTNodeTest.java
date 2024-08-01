import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024-5-22
 */
public class BSTNodeTest extends TestCase {

    private BSTNode<String> node;
    
    /**
     * Setup that is going to run before each test
     */

    public void setUp() {

        node = new BSTNode<>("root");

    }


    /**
     * Tests the getter method for value by constructor
     */
    public void testGetValue() {
        assertEquals("root", node.getValue());
    }


    /**
     * Tests the setter method for value
     */
    public void testSetValue() {
        node.setValue("newRoot");
        assertEquals("newRoot", node.getValue());
    }


    /**
     * Tests the setter and getter for Left nodes
     */
    public void testLeft() {
        BSTNode<String> leftNode = new BSTNode<>("left");
        node.setLeft(leftNode);
        assertEquals(leftNode, node.getLeft());
    }


    /**
     * Tests the setter and getter for right nodes
     */

    public void testRight() {
        BSTNode<String> rightNode = new BSTNode<>("right");
        node.setRight(rightNode);
        assertEquals(rightNode, node.getRight());
    }


    /**
     * Tests the toString
     */
    public void testToString() {
        BSTNode<String> leftNode = new BSTNode<>("left");
        BSTNode<String> rightNode = new BSTNode<>("right");
        node.setLeft(leftNode);
        node.setRight(rightNode);

        assertEquals("left, root, right", node.toString());
    }

}
