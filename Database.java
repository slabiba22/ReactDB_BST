import java.util.*;

/**
 * This class is responsible for interfacing between the command processor and
 * the BST. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * BST method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2024-05-22
 */
public class Database {

    // this is the BST object that we are using a
    // string for the name of the rectangle and then
    // a rectangle object, these are stored in a BSTNode,
    // see the Rectangle class for more information
    private BST<KVPair<String, Rectangle>> tree;

    // This is an Iterator object over the BST to loop through it from outside
    // the class.
    // You will need to define an extra Iterator for the intersections method.
    // private Iterator<KVPair<String, Rectangle>> itr1;
    private Iterator<BSTNode<KVPair<String, Rectangle>>> itr1;
    private StringBuilder out;

    /**
     * The constructor for this class initializes a BST object
     * with a KVPair of Strings and Rectangles
     */
    public Database() {
        tree = new BST<KVPair<String, Rectangle>>();
        out = new StringBuilder();
    }


    /**
     * Inserts the KVPair in the BST if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted BST appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
      
        // makes a rectangle with the coord given
        Rectangle r1 = pair.getValue();
        // gets the name of the rec
        String name = pair.getKey();

        // check if the rec is valid by name, coord, and bounds
        if (!isValidName(name) || r1.isInvalid() || r1.getxCoordinate() + r1
            .getWidth() > 1024 || r1.getyCoordinate() + r1.getHeight() > 1024) {

            out.append("Rectangle rejected: ").append(name).append(" ").append(
                r1).append("\n");

            System.out.println("Rectangle rejected: " + name + " " + r1);
        }
        // send it to BST method insert
        else {
            tree.insert(pair);
            out.append("Rectangle accepted: ").append(name).append(" ").append(
                r1).append("\n");
            System.out.println("Rectangle accepted: " + name + " " + r1);

        }

    }


    /**
     * Checks if the provided name is valid.
     * 
     * @param name
     *            the name to be checked
     * @return true if the name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     * @return the string containing the info and status of the given rect
     */
    public String remove(String name) {
        
        KVPair<String, Rectangle> searchKey = new KVPair<>(name, null);
        
        // list of all the rectangles found with the same name
        List<KVPair<String, Rectangle>> result = tree.find(searchKey);
        StringBuilder out1 = new StringBuilder();

        if (result.isEmpty()) {

            out1.append("Rectangle not found: (" + name + ")");
            System.out.println(out1.toString());

        }
        else {
            // removes the first rec in the list
            KVPair<String, Rectangle> removed = result.get(0);

            tree.remove(removed);

            out1.append("Rectangle removed: (").append(name).append(", ")
                .append(removed.getValue()).append(")");

            System.out.println("Rectangle removed: (" + removed + ")");
        }

        return out1.toString();

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     * @return the string containing the info and status of the given rect
     */
    public String remove(int x, int y, int w, int h) {
        StringBuilder result = new StringBuilder();
        Rectangle rec = new Rectangle(x, y, w, h);
        
        // check if it is a valid rectangle
        if (rec.isInvalid() || rec.getxCoordinate() + rec.getWidth() > 1024
            || rec.getyCoordinate() + rec.getHeight() > 1024) {
            result.append("Rectangle rejected: (").append(rec).append(")");
            System.out.println(result.toString());
            return result.toString();
        }

        
        itr1 = tree.iterator();
        KVPair<String, Rectangle> pairToRemove = null;

        // Iterator goes through all the rectangles in the tree
        while (itr1.hasNext()) {
            KVPair<String, Rectangle> outerPair = itr1.next().getValue();
            // if the coord of a rectangle in the tree matches with the 
            // coord given take that pair
            if (outerPair.getValue().equals(rec)) {
                pairToRemove = outerPair;
                break; // Remove the first matching rectangle
            }
        }

        // if a matching rec is found remove it
        if (pairToRemove != null) {
            tree.remove(pairToRemove);
            result.append("Rectangle removed: (").append(pairToRemove.getKey())
                .append(" ,").append(rec);
            System.out.println(result.toString());
        }
        else {
            result.append("Rectangle not found: (").append(rec).append(")");
            System.out.println(result.toString());
        }
        return result.toString();

    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {

        // check if the region coords are valid
        if (w <= 0 || h <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return;
        }

        
        Rectangle searchRegion = new Rectangle(x, y, w, h);
        StringBuilder out1 = new StringBuilder(
            "Rectangles intersecting region (").append(x).append(", ").append(y)
                .append(", ").append(w).append(", ").append(h).append("):\n");

        // List for all the pairs found within the region
        List<KVPair<String, Rectangle>> interRec = new ArrayList<>();
        itr1 = tree.iterator();

        while (itr1.hasNext())

        {
            BSTNode<KVPair<String, Rectangle>> node = itr1.next();
            Rectangle rect = node.getValue().getValue();

            // check which rectangles intersect with the region
            if (rect.intersect(searchRegion)) {
                interRec.add(node.getValue());
            }

        }
        // When no rectangles found
        if (interRec.isEmpty()) {
            out1.append("");
        }

        else {
            interRec.sort(null);

            // go through the lists and print
            for (KVPair<String, Rectangle> pair : interRec) {
                out1.append("(").append(pair.getKey()).append(", ").append(pair
                    .getValue()).append(")\n");
            }
        }

        System.out.println(out1.toString().trim());

    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the BST class
     * as the BST needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {
        StringBuilder out1 = new StringBuilder("Intersection pairs:\n");

        // iterator that starts from the beginning of the tree
        Iterator<BSTNode<KVPair<String, Rectangle>>> outerItr = tree.iterator();

        while (outerItr.hasNext()) {
            KVPair<String, Rectangle> outerRect = outerItr.next().getValue();

            // Makes sure inner itr is at the same positon as the outer itr
            Iterator<BSTNode<KVPair<String, Rectangle>>> innerItr = tree
                .iterator();
            while (innerItr.hasNext()) {
                KVPair<String, Rectangle> current = innerItr.next().getValue();
                if (current.equals(outerRect)) {
                    break;
                }
            }

            while (innerItr.hasNext()) {
                KVPair<String, Rectangle> innerRect = innerItr.next()
                    .getValue();

                // first pair
                if (outerRect.getValue().intersect(innerRect.getValue())) {
                    out1.append(outerRect.getKey()).append(" ").append(outerRect
                        .getValue()).append(" | ").append(innerRect.getKey())
                        .append(" ").append(innerRect.getValue()).append("\n");

                    // Reverse papir
                    out1.append(innerRect.getKey()).append(" ").append(innerRect
                        .getValue()).append(" | ").append(outerRect.getKey())
                        .append(" ").append(outerRect.getValue()).append("\n");
                }
            }
        }

        System.out.println(out1.toString().trim());
    }


    /**
     * Prints out all the rectangles with the specified name in the BST.
     * This method will delegate the searching to the BST class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     * @return string that has the info of the rect status and name
     */
    public String search(String name) {

        StringBuilder out1 = new StringBuilder();

        KVPair<String, Rectangle> searcher = new KVPair<>(name, null);
        
        // list to store the pair found with the find method in BST
        List<KVPair<String, Rectangle>> result = tree.find(searcher);

        
        // if list is empty
        if (result.isEmpty()) {
            System.out.println("Rectangle not found: " + name);
            return "Rectangle not found: " + name;

        }

        out1.append("Rectangles found matching \"").append(name).append(
            "\":\n");
        System.out.println("Rectangles found matching: " + name + "\":");

        // go through list and print details
        for (KVPair<String, Rectangle> pair : result) {
            Rectangle r1 = pair.getValue();
            out1.append("(").append(name).append(", ").append(r1).append(")\n");
            System.out.println("(" + name + ", " + r1 + ")");
        }
        return out1.toString().trim();

    }


    /**
     * Prints out a dump of the BST which includes information about the
     * size of the BST and shows all of the contents of the BST. This
     * will all be delegated to the BST.
     * 
     * @return String containing the nodes in the tree
     */
    public String dump() {

        String h = tree.dump();
        System.out.println(h);
        return (h);
    }


    // ----------------------------------------------------------
    /**
     * For insert testing
     * 
     * @return returns the messages after insert.
     */
    public String getOutput() {
        return out.toString().trim();
    }

}
