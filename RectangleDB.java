import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * // On my honor:
 * // - I have not used source code obtained from another student,
 * // or any other unauthorized source, either modified or unmodified.
 * // - All source code and documentation used in my program is
 * // either my original work, or was derived by me from the
 * // source code published in the textbook for this course.
 * // - I have not discussed coding details about this project with
 * // anyone other than the instructor, ACM/UPE tutors or the TAs assigned
 * // to this course. I understand that I may discuss the concepts
 * // of this program with other students, and that another student
 * // may help me debug my program so long as neither of us writes
 * // anything during the discussion or modifies any computer file
 * // during the discussion. I have violated neither the spirit nor
 * // letter of this restriction.
 * 
 */

/**
 * The class containing the main method, the entry point of the application. It
 * will take a command line file argument which include the commands to be read
 * and creates the appropriate BST object and outputs the correct results
 * to the console as specified in the file.
 *
 * @author CS Staff
 * @version 2024-5-22
 */
public class RectangleDB {

    /**
     * The entry point of the application.
     *
     * @param args
     *            The name of the command file passed in as a command line
     *            argument.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(
                "Invalid file. No filename in command line arguments");
            return;
        }

        Database rectDB = new Database();
        CommandProcessor cmder = new CommandProcessor(rectDB);

        // The following pseudocode walks through a possible design for an
        // entrypoint for your rectangledb

        // setup a file for the file containing the commands
        // Open the file and scan through it (your may need a try catch here
        // take the first command line argument and opens that file
        // create a scanner object
        // create a command processor object
        // read the entire file and processes the commands
        // line by line
        // determines if the file has more lines to read
        // close the scanner

        // catch the exception if the file cannot be found
        // and output the correct information to the console

        File file = new File(args[0]);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                if (!command.isEmpty()) {
                    try {
                        cmder.processor(command);
                    }
                    catch (Exception e) {
                        System.out.println("Error processing command: "
                            + command);
                        e.printStackTrace();
                    } 
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + args[0]); 
        }
    }

}
