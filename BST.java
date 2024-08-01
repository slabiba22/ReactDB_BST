import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Stub for binary search tree class
 * We use generics here because we want this BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author Labiba Sajjad (sLabiba22)
 * @version 2024-06-07
 * @param <T>
 *            the generic type; extends Comparable
 */
public class BST<T extends Comparable<T>> implements Iterable<BSTNode<T>> {
    /** The root. */
    private BSTNode<T> root;

    /** The size. */
    private int size;

    /**
     * Instantiates a new Binary Search Tree.
     */
    public BST() {
        root = null;
        size = 0;
    }

    // ----------------------------------------------------------


    /**
     * Size of the tree
     *
     * @return the tree size as an int
     */
    public int size() {
        return size;
    }

    // ----------------------------------------------------------


    // ----------------------------------------------------------
    /**
     * Inserts a given node with the given value
     * Taken from DSA Coursenotes Chapter 1.3
     * 
     * @param e
     *            the value to be inserted
     */
    public void insert(T e) {
        root = insertHelper(e, root);
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Remove the specified value from the tree.
     * Taken from DSA Coursenotes Chapter 1.3
     *
     * @param x
     *            the name of item to be removed
     * @return the item removed
     * 
     */
    public T remove(T x) {
        T temp = findNode(root, x);
        if (temp != null) {
            root = removeHelper(root, x);
            size--;
        }
        return temp;
    }


    // ----------------------------------------------------------
    /**
     * Returns the value for the given key
     * Taken from DSA Coursenotes Chapter 1.3
     * 
     * @param key
     *            the value to be find
     * @return the value
     */
    public List<T> find(T key) {
        List<T> results = new ArrayList<>();
        findHelper(root, key, results);
        return results;
    }


    // ----------------------------------------------------------
    /**
     * This method dumps the structure and values of the BST to console
     * dump() initiates recursive traversal
     * 
     * @return the string containing the dump
     */
    public String dump() {
        StringBuilder output = new StringBuilder("BST dump:\n");
        if (root == null) {
            output.append("Node has depth 0, Value (null)\n");
        }
        else {
            dumpRec(root, 0, output);
        }

        output.append("BST size is: ").append(size);
        return output.toString();
    }


    private void dumpRec(BSTNode<T> node, int depth, StringBuilder output) {
        if (node == null) {
            return;
        }
        dumpRec(node.getLeft(), depth + 1, output);
        output.append("Node has depth ").append(depth).append(", Value (")
            .append(node.getValue()).append(")\n");
        dumpRec(node.getRight(), depth + 1, output);
    }


    // ----------------------------------------------------------
    /**
     * Private method to insert a value into a subtree.
     * Taken from DSA Coursenotes Chapter 1.3
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BSTNode<T> insertHelper(T x, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<T>(x);
        }
        if (node.getValue().compareTo(x) >= 0) {
            node.setLeft(insertHelper(x, node.getLeft()));
        }
        else {
            node.setRight(insertHelper(x, node.getRight()));
        }
        return node;
    }


    // Taken from DSA Coursenotes Chapter 1.3
    private void findHelper(BSTNode<T> rt, T key, List<T> results) {
        if (rt == null) {
            return;
        }
        findHelper(rt.getLeft(), key, results);
        if (rt.getValue().compareTo(key) == 0) {
            results.add(rt.getValue());
        }

        findHelper(rt.getRight(), key, results);

    }


    // for remove that finds the exact node with the same key and value
    // (for duplicate rec
    private T findNode(BSTNode<T> rt, T key) {
        if (rt == null) {
            return null;
        }

        if (rt.getValue().compareTo(key) == 0 && rt.getValue().equals(key)) {
            return rt.getValue();
        }
        T found = findNode(rt.getLeft(), key);
        if (found == null) {
            found = findNode(rt.getRight(), key);
        }
        return found;

    }

    // Taken from DSA Coursenotes Chapter 1.3


    private BSTNode<T> deleteMax(BSTNode<T> rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
    }


    // returns the max node in a tree
    private BSTNode<T> getMax(BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        else if (node.getRight() == null) {
            return node;
        }
        else {
            return getMax(node.getRight());
        }
    }


    // Taken from DSA Coursenotes Chapter 1.3
    private BSTNode<T> removeHelper(BSTNode<T> rt, T key) {
        if (rt == null) {
            return null;
        }

        if (rt.getValue().equals(key)) {
            if (rt.getLeft() == null) {
                return rt.getRight();
            }
            else if (rt.getRight() == null) {
                return rt.getLeft();
            }
            else {
                BSTNode<T> temp = getMax(rt.getRight());
                rt.setValue(temp.getValue());
                rt.setRight(deleteMax(rt.getRight()));
            }
        }
        else {
            // If keys are equal but values are not, continue searching in
            // both subtrees
            rt.setLeft(removeHelper(rt.getLeft(), key));
            rt.setRight(removeHelper(rt.getRight(), key));
        }

        return rt;

    }


    /**
     * Iterator class thats goes through the tree in-order.
     * Used for intersections and remove(x y w h)
     */
    @Override
    public Iterator<BSTNode<T>> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<BSTNode<T>> {
        private List<BSTNode<T>> nodes;
        private int index;

        public BSTIterator(BSTNode<T> root) {
            nodes = new ArrayList<>();
            index = 0;
            inOrderTraversal(root);
        }


        @Override
        public boolean hasNext() {
            return index < nodes.size();
        }


        @Override
        public BSTNode<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return nodes.get(index++);

        }


        private void inOrderTraversal(BSTNode<T> node) {
            if (node != null) {
                inOrderTraversal(node.getLeft());
                nodes.add(node);
                inOrderTraversal(node.getRight());
            }
        }

    }

    // ----------------------------------------------------------
    /**
     * Returns a string representation of the tree in sorted order.
     * 
     * @return the string representation of the tree
     */

    public String toString() {
        if (root == null) {
            return "()";
        }
        else {
            return "(" + root.toString() + ")";
        }
    }

}
