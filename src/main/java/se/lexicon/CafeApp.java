package se.lexicon;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CafeApp {
    public static Scanner scanner = new Scanner(System.in);

    public static String item1 = "Espresso";
    public static double price1 = 25.00;

    public static String item2 = "Cappuccino";
    public static double price2 = 35.00;

    public static String item3 = "Latte";
    public static double price3 = 40.00;

    public static String item4 = "Croissant";
    public static double price4 = 30.00;

    public static String item5 = "Sandwich";
    public static double price5 = 55.00;

    static void main() {
        String customer = greetCustomer();
        displayMenu();
        Order myOrder = new Order();
        myOrder.customerName = customer;

        myOrder.isMember = readLoyaltyInput("Loyalty member? (yes/no): ");
        while (true) {
            int itemNumber = readMenuItemNumber("Enter item number (1-5, or 0 to finish): ");
            if (itemNumber == 0) {
                break;
            }

            int quantity = readQuantityInput("How many? ");

            String name = getItemName(itemNumber);
            double price = getItemPrice(itemNumber);

            myOrder.addItem(name, price, quantity);
            IO.println(name + " added.\n");
        }
        myOrder.printReceipt();
        scanner.close();
    }

    public static String greetCustomer() {
        IO.print("Welcome! What is your name? ");
        String customerName = scanner.nextLine();
        IO.println("Hi " + customerName + "! Here is our menu: \n");

        return customerName;
    }

    public static void displayMenu() {
        IO.println("====================================");
        IO.println("            Lexicon Cafe");
        IO.println("====================================");
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 1, item1, price1);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 2, item2, price2);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 3, item3, price3);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 4, item4, price4);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 5, item5, price5);
        IO.println("====================================");
    }

    public static int readMenuItemNumber(String message) {
        while (true) {
            try {
                IO.print(message);
                int number = scanner.nextInt();
                if (number >= 0 && number <= 5) {
                    return number;
                } else {
                    IO.print("Error: The number is out of range! It must be between 0 and 5. ");
                }
            } catch (InputMismatchException e) {
                IO.println("ERROR: Invalid input! Please enter number only.");
                scanner.nextLine();
            }
        }
    }

    public static int readQuantityInput(String message) {
        while (true) {
            try {
                IO.print(message);
                int quantity = scanner.nextInt();
                if (quantity >= 1) {
                    return quantity;
                } else {
                    IO.print("ERROR: Quantity must be at least 1! ");
                }
            } catch (InputMismatchException e) {
                IO.println("ERROR: Invalid input! Please enter number only.");
                scanner.nextLine();
            }
        }
    }

    public static boolean readLoyaltyInput(String message) {
        while (true) {
            IO.print(message);
            String customerLoyalty = scanner.next();
            if (customerLoyalty.equalsIgnoreCase("yes")) {

                return true;
            } else if (customerLoyalty.equalsIgnoreCase("no")) {

                return false;
            } else {
                IO.println("Error: Invalid input! Please type 'yes' or 'no'.");
            }
        }
    }

    public static String getItemName(int itemNumber) {
        switch (itemNumber) {
            case 1:
                return item1;
            case 2:
                return item2;
            case 3:
                return item3;
            case 4:
                return item4;
            case 5:
                return item5;
            default:
                return "No item with this number.";
        }
    }

    public static double getItemPrice(int itemNumber) {
        switch (itemNumber) {
            case 1:
                return price1;
            case 2:
                return price2;
            case 3:
                return price3;
            case 4:
                return price4;
            case 5:
                return price5;
            default:
                return 0.00;
        }
    }

}