import java.util.regex.Pattern;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2024-05-22
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database rectDB;
    private StringBuilder out;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor(Database dataIn) {
        rectDB = dataIn;
        out = new StringBuilder();
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions.
     * These actions are performed on specified objects and include insert,
     * remove,
     * regionsearch, search, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {

        // converts the string of the line into an
        // array of its space (" ") delimited elements
        // String[] arr = line.split("\\s{1,}");
        String[] arr = line.trim().split("\\s+");
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // if the parsed command is insert, parse the coord and call insert
        if (command.equals("insert")) {

            String name = arr[1];
            int x = Integer.parseInt(arr[2]);
            int y = Integer.parseInt(arr[3]);
            int w = Integer.parseInt(arr[4]);
            int h = Integer.parseInt(arr[5]);

            Rectangle rect = new Rectangle(x, y, w, h);
            KVPair<String, Rectangle> pair = new KVPair<>(name, rect);
            rectDB.insert(pair);

        }

        // if parsed command is remove
        else if (command.equals("remove")) {

            // for remove(name)
            if (arr.length == 2) {
                String name = arr[1];
                rectDB.remove(name);

            }
            // for remove(x, y, w, h)
            else {
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                int x = Integer.parseInt(arr[1]);
                int y = Integer.parseInt(arr[2]);
                int w = Integer.parseInt(arr[3]);
                int h = Integer.parseInt(arr[4]);

                rectDB.remove(x, y, w, h);

            }

        }
        // if the parsed command is region search, parse the coord and call
        // region search
        else if (command.equals("regionsearch")) {

            int x = Integer.parseInt(arr[1]);
            int y = Integer.parseInt(arr[2]);
            int w = Integer.parseInt(arr[3]);
            int h = Integer.parseInt(arr[4]);

            rectDB.regionsearch(x, y, w, h);

        }
        // if the parsed command is intersections call intersections
        else if (command.equals("intersections")) {
            rectDB.intersections();

        }
        // if the parsed command is search, parse the name and call search
        else if (command.equals("search")) {

            if (arr.length == 2) {
                String name = arr[1];
                rectDB.search(name);
            }
            else {
                System.out.println("Unrecognized command: " + line);
            }

        }
        // lastyly, if command is dump call dump
        else {

            rectDB.dump();

        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the message from insert for testing
     * 
     * @return String containing insert info
     */
    public String getOutput() {
        return rectDB.getOutput();
    }

}
