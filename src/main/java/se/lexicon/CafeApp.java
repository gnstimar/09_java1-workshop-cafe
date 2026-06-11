package se.lexicon;

import java.util.Scanner;

public class CafeApp {
    public static Scanner scanner = new Scanner(System.in);
    public static int order;
    public static int quantity;
    public static boolean loyalty = false;
    public static double loyaltyDiscount = 0.15;
    public static double vat = 0.12;
    public static double bigOrderDiscount = 0.10;
    public static double bigOrderMinimum = 150.00;
    public static int dailyTotalCustomer = 0;
    public static double dailyTotalSales = 0;

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
        while (true) {
            IO.print("Next customer name (or 'done' to close): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            IO.println("Hi " + name + "! Here is our menu: \n");
            displayMenu();
            dailyTotalSales += takeOrder();
            IO.println("---       NEXT ORDER       ---");
            dailyTotalCustomer += 1;
            scanner.nextLine();
        }
        endOfDayReport();
        scanner.close();
    }

    public static void displayMenu() {
        IO.println("==============================");
        IO.println("         Lexicon Cafe");
        IO.println("==============================");
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 1, item1, price1);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 2, item2, price2);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 3, item3, price3);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 4, item4, price4);
        System.out.printf("%-1d. %-15s %6.2f SEK%n", 5, item5, price5);
        IO.println("==============================");
    }

    public static double takeOrder() {
        IO.print("Enter item number (1-5): ");
        order = scanner.nextInt();

        while (order < 1 || order > 5) {
            IO.print("You gave a wrong number. It must be between 1-5! Enter item number again! ");
            order = scanner.nextInt();
        }

        IO.print("How many? ");
        quantity = scanner.nextInt();

        IO.print("Loyalty member? (yes/no): ");
        String customerLoyalty = scanner.next();
        if (customerLoyalty.equalsIgnoreCase("yes")) {
            loyalty = true;
        }

        return calculateBasePrice(order, quantity);
    }

    public static void endOfDayReport() {
        IO.println("==============================");
        IO.println("       END OF DAY REPORT");
        IO.println("==============================");
        System.out.printf("%-15s : %-5s %n", "Customers served", dailyTotalCustomer);
        System.out.printf("%-15s : %-6.2f SEK%n", "Total revenue", dailyTotalSales);
        IO.println("==============================");
    }

    public static String getItemName(int itemNumber) {
        switch (itemNumber) {
            case 1: return item1;
            case 2: return item2;
            case 3: return item3;
            case 4: return item4;
            case 5: return item5;
            default: return "No item with this number.";
        }
    }

    public static double getItemPrice(int itemNumber) {
        switch (itemNumber) {
            case 1: return price1;
            case 2: return price2;
            case 3: return price3;
            case 4: return price4;
            case 5: return price5;
            default: return 0.00;
        }
    }

    public static double calculateBasePrice(int order, int quantity) {
        return getItemPrice(order) * quantity;
    }

    public static double calculateBaseVAT(int order, int quantity) {
        return getItemPrice(order) * quantity * vat;
    }

    public static double calculateTotalPrice(int order, int quantity) {
        return getItemPrice(order) * quantity * (1 + vat);
    }

    public static double calculateLoyaltyDiscount(int order, int quantity) {
        return getItemPrice(order) * quantity * (-1 * loyaltyDiscount);
    }

    public static double calculateLoyaltyVAT(int order, int quantity) {
        return getItemPrice(order) * quantity * (1-loyaltyDiscount) * vat;
    }

    public static double calculateLoyaltyTotalPrice(int order, int quantity) {
        return getItemPrice(order) * quantity * (1-loyaltyDiscount) * (1 + vat);
    }

    public static double calculateBigOrderDiscount(int order, int quantity) {
        return getItemPrice(order) * quantity * (-1 * bigOrderDiscount);
    }

    public static double calculateBigOrderVAT(int order, int quantity) {
        return getItemPrice(order) * quantity * (1-bigOrderDiscount) * vat;
    }

    public static double calculateBigOrderTotalPrice(int order, int quantity) {
        return getItemPrice(order) * quantity * (1-bigOrderDiscount) * (1 + vat);
    }

    public static void printReceipt(String customerName, int order, int quantity) {
        IO.println();
        IO.println("==============================");
        IO.println("         Lexicon Cafe");
        IO.println("==============================");
        System.out.printf("%-10s : %-10s %n", "Customer", customerName);
        System.out.printf("%-10s : %-10s x %-2s %n", "Item", getItemName(order), quantity);
        System.out.printf("%-10s : %-6.2f SEK%n", "Subtotal", calculateBasePrice(order, quantity));
        if (loyalty) {
            System.out.printf("%-10s : %-6.2f SEK%n", "Discount", calculateLoyaltyDiscount(order, quantity));
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateLoyaltyVAT(order, quantity));
        } else if (calculateBasePrice(order, quantity) > bigOrderMinimum) {
            System.out.printf("%-10s : %-6.2f SEK%n", "Discount", calculateBigOrderDiscount(order, quantity));
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateBigOrderVAT(order, quantity));
        } else {
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateBaseVAT(order, quantity));
        }
        IO.println("------------------------------");
        if (loyalty) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateLoyaltyTotalPrice(order, quantity));
        } else if (calculateBasePrice(order, quantity) > bigOrderMinimum) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateBigOrderTotalPrice(order, quantity));
        } else {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateTotalPrice(order, quantity));
        }
        IO.println("==============================");
        IO.println("    Thank you, " + customerName + "!");
        IO.println("    See you next time!");
        IO.println("==============================");
    }
}
