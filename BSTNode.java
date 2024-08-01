// -------------------------------------------------------------------------
/**
 * Node class that has the basic getters and setters for nodes in a tree.
 * 
 * @param <T>
 * 
 * @author Labiba Sajjad slabiba22
 * @version May 30, 2024
 */
public class BSTNode<T extends Comparable<T>> {
    /** The left */
    private BSTNode<T> left;
    /** The right */
    private BSTNode<T> right;
    /** The value storing comparable rectangle node */
    private T value;

    /**
     * Instantiates a new node
     * 
     * @param value
     *            the value
     */
    public BSTNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;

    }


    // ----------------------------------------------------------
    /**
     * Returns the value store stored in the node
     * 
     * @return the value
     */
    public T getValue() {
        return value;
    }


    // ----------------------------------------------------------
    /**
     * Set the data value stored in this node.
     * 
     * @param value
     *            the new data value to set
     */
    public void setValue(T value) {
        this.value = value;
    }


    // ----------------------------------------------------------
    /**
     * Getter method that returns the left child
     * 
     * @return left child node
     */
    public BSTNode<T> getLeft() {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's left child.
     * 
     * @param left
     *            node that points to the left child.
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }


    // ----------------------------------------------------------
    /**
     * Getter method that returns the right child
     * 
     * @return right child node
     */
    public BSTNode<T> getRight() {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's right child
     * 
     * @param right
     *            node that points ot the right child
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
 

    // ----------------------------------------------------------
    /**
     * Prints the node (in-order)
     * 
     * @return a string containing the nodes
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (left != null) {
            builder.append(left.toString() + ", ");
        }
        builder.append(value.toString());
        if (right != null) {
            builder.append(", " + right.toString());
        }
        return builder.toString();
    }

}
