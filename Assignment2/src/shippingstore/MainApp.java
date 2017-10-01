package shippingstore;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class of the PackageDatabase database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Junye Wen
 */
public class MainApp {

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue doing commands until the user types '6'. A lot of user input
     * validation is done in the loop. At least enough to allow the interface
     * with PackageDatabase to be safe.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        PackageDatabase PackageDatabase = new PackageDatabase();

        String welcomeMessage = "\nWelcome to the Shipping Store database. Choose one of the following functions:\n\n"
                + "\t1. Show all existing package orders in the database\n"
                + "\t2. Add a new package order to the database.\n"
                + "\t3. Delete a package order from a database.\n"
                + "\t4. Search for a package order (given its Tracking #).\n"
                + "\t5. Show a list of orders within a given weight range.\n"
                + "\t6. Add a new user to the database."
                + "\t7. Update user info(given their id)."
                + "\t8. Complete a shipping transaction."
                + "\t9. Show completed shpping transactions."
                + "\t10. Exit program.\n";

        System.out.println(welcomeMessage);

        int selection = in.next().charAt(0);
        in.nextLine();

        while (selection != '6') {

            switch (selection) {
                case '1':
                    PackageDatabase.showPackages ();
                    break;
                case '2':
                    System.out.println("\nPlease type description of package with the following pattern:\n"
                            + "\n TRACKING #  TYPE   SPECIFICATION   CLASS   WEIGHT   VOLUME\n"
                            + "example:\nGFR23 Box Books Retail 9500.00 45\n");
                    String inTemp = in.nextLine();

                    String temp[] = inTemp.split(" ");

                    if (temp.length != 6) {
                        System.out.println("Not correct number of fields to process.");
                        break;
                    }

                    PackageDatabase.addOrder(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                    break;
                case '3':
                    PackageDatabase.showPackages();

                    System.out.println("\nPlease enter the tracking # of the package order to delete from the database.\n");
                    String orderToDelete = in.nextLine();
                    PackageDatabase.removeOrder(orderToDelete);
                    break;
                case '4':
                    System.out.println("\nEnter the Tracking # of the order you wish to see.\n");
                    String trackingNum = in.next();
                    in.nextLine();
                    PackageDatabase.searchPackage(trackingNum);
                    break;
                case '5':
                    float high = 0;
                    float low = 0;

                    System.out.println("\nEnter lower-bound weight.\n");
                    low = in.nextFloat();
                    System.out.println("\nEnter upper-bound weight.\n");
                    high = in.nextFloat();
                    in.nextLine();

                    PackageDatabase.showPackagesRange(low, high);
                    break;
                case 'h':
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;

            }

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = in.next().charAt(0);

            in.nextLine();
        }

        in.close();
        PackageDatabase.flush();

        System.out.println("Done!");

    }
}
