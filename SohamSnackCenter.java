// Snack Center Ordering System Using File Handling in Java

import java.io.*;
import java.util.*;

public class SohamSnackCenter {
    Scanner sc = new Scanner(System.in);
    String items[][] = new String[20][3];
    int item_count = 0;
    int ordered_items[][] = new int[8][2];
    String name[] = new String[10];
    int amount[] = new int[10];
    int transaction_count = 0;
    int count = 0;
    int total_amt = 0;

    public static void main(String[] args) {
        SohamSnackCenter ssc = new SohamSnackCenter();
        Scanner sc1 = new Scanner(System.in);
        ssc.load_items();
        for (;;) {
            clearScreen();
            ssc.cleardata();
            System.out.print("--------| WELCOME TO SOHAM SNACK CENTER |--------\n"
                    + "_________________________________________________\n\n"
                    + "Press 1 - To View Menu\n\n"
                    + "Press 2 - To Place Order\n\n"
                    + "Press 3 - To Open Admin Settings\n\n"
                    + "Press 4 - To Exit\n\n"
                    + "Enter your choice: ");

            int choice = sc1.nextInt();
            switch (choice) {
                case 1:
                    ssc.menu();
                    break;
                case 2:
                    ssc.order();
                    break;
                case 3:
                    ssc.admin();
                    break;
                case 4:
                    System.out.println("\n\nTHANK YOU!"
                            + "\n\nVISIT AGAIN :)\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Choice...\nEnter Again\n");
                    sc1.nextLine();
                    sc1.nextLine();
                    break;
            }
        }
    }

    /*
     * Menu method to display menu of items
     */
    public void menu() {
        clearScreen();

        System.out.println("******************MENU*****************\n\n");
        print_items();
        System.out.print("\nPress 0 to go back to the Home Screen"
                + "\nPress any other key to order "
                + "\n\nEnter your choice: ");
        int choice2 = sc.nextInt();
        if (choice2 == 0)
            return;
        else
            order();
    }

    /*
     * Order method to take order from customer and display the bill
     */
    public void order() {
        Scanner sc2 = new Scanner(System.in);
        clearScreen();
        System.out.println("******************MENU*****************\n");
        print_items();
        System.out.println("\n\n******************ORDER****************\n"
                + "_______________________________________");
        System.out.print("\nEnter your name - ");
        name[transaction_count] = sc2.nextLine();
        for (;;) {
            System.out.print("\nEnter item ID and its quantity - ");
            ordered_items[count][0] = sc2.nextInt();
            ordered_items[count][1] = sc2.nextInt();
            count++;
            System.out.print("\nPress 1 to order more items"
                    + "\nPress 2 to display your bill"
                    + "\n\nEnter your choice: ");
            int choice3 = sc2.nextInt();
            if (choice3 != 1)
                break;
        }
        clearScreen();
        System.out.println("******************BILL DETAILS*****************\n\n"
                + "Customer Name - " + name[transaction_count] + "\n\n"
                + "ID   |   Item Name            |   Price   |   Quantity\n"
                + "______________________________________________________\n");
        for (int i = 0, j = 0; i < count; i++) {
            for (; j < item_count; j++) {
                if (ordered_items[i][0] == Integer.parseInt(items[j][0]))
                    break;
            }
            System.out.printf(" %s   |   %-20s |   %s      |   %d\n\n", items[j][0], items[j][1], items[j][2],
                    ordered_items[i][1]);
            amount[transaction_count] += (Integer.valueOf(items[j][2]) * ordered_items[i][1]);
            j = 0;
        }
        total_amt += amount[transaction_count];
        count = 0;
        System.out.print("\n\nTotal amount to be paid = Rs. " + amount[transaction_count]
                + "\n\nPress any key to return to home screen...");
        transaction_count++;
        sc.nextLine();
    }

    /*
     * Admin method to display admin controls
     */
    public void admin() {
        Scanner sc2 = new Scanner(System.in);
        clearScreen();
        System.out.print("\nEnter the code to continue to admin settings - ");
        int code = sc2.nextInt();
        if (code == 1234) {
            for (;;) {
                clearScreen();
                System.out.print("*************ADMIN CONTROLS**********\n"
                        + "_____________________________________\n\n"
                        + "Press 1 - To View all list of items\n\n"
                        + "Press 2 - To View Transaction History\n\n"
                        + "Press 3 - To View Today's Income\n\n"
                        + "Press 4 - To Add New Item\n\n"
                        + "Press 5 - To Remove An Item\n\n"
                        + "Press 6 - To Exit\n\n"
                        + "Enter your choice: ");
                int choice4 = sc2.nextInt();
                clearScreen();
                switch (choice4) {
                    case 1:
                        print_items();
                        break;
                    case 2:
                        System.out.println("***********TRANSACTION HISTORY**********\n\n"
                                + "ID   |   Customer Name        |   Total Price\n"
                                + "________________________________________\n");
                        for (int i = 0; i < transaction_count; i++) {
                            System.out.printf(" %d   |   %-20s |   %d\n\n", (i + 1), name[i], amount[i]);
                        }
                        break;
                    case 3:
                        System.out.println("\nToday's total income = Rs. " + total_amt);
                        break;
                    case 4:
                        System.out.print("\nEnter item name: ");
                        items[item_count][0] = Integer.toString(item_count + 1);
                        items[item_count][1] = sc.nextLine();
                        System.out.print("\nEnter item price: ");
                        items[item_count][2] = sc2.next();
                        System.out.println("\n\nItem added successfully...");
                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter("data.txt", true));
                            out.write(items[item_count][1] + "_" + items[item_count][2] + "_");
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        item_count++;
                        break;
                    case 5:
                        print_items();
                        System.out.print("\nEnter item ID to remove: ");
                        String id = sc.next();

                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice...Enter again...");
                }
                System.out.print("\n\nPress any key to return to admin home screen...");
                sc.nextLine();
            }
        } else
            System.out.println("\n\nInvalid code...Redirecting to Home Screen...");
        sc2.nextLine();
    }

    /*
     * load_items method to load all items from file and save in array
     */
    public void load_items() {
        char filedata[] = new char[500];
        Arrays.fill(items[1], " ");
        try (FileReader fr = new FileReader("data.txt")) {
            int content;
            for (int i = 0; (content = fr.read()) != -1; i++) {
                filedata[i] = (char) content;
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0, k = 1; filedata[i] != '\u0000'; i++) {
            if (filedata[i] != '_') {
                sb.append(filedata[i]);
            } else {
                items[j][k] = String.valueOf(sb);
                if (k == 1)
                    k = 2;
                else if (k == 2) {
                    items[j][0] = Integer.toString(j + 1);
                    k = 1;
                    item_count++;
                    j++;
                }
                sb.setLength(0);
            }
        }
    }

    /*
     * print_items method to print all items with formatting
     */
    public void print_items() {
        System.out.println("ID   |   Item Name            |   Price\n"
                + "_______________________________________\n");
        for (int i = 0; i < item_count; i++)
            System.out.printf("%2s   |   %-20s |   %s\n\n", items[i][0], items[i][1], items[i][2]);
    }

    /*
     * cleardata to clear the previous transaction and proceed to next transaction
     */
    public void cleardata() {
        for (int i = 0; i < ordered_items.length; i++) {
            ordered_items[i][0] = 0;
            ordered_items[i][1] = 0;
        }
    }

    /*
     * clearscreen to clear the console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}