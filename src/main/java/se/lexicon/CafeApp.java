package se.lexicon;

import java.util.Scanner;

public class CafeApp {
    static void main() {
        String customer = greetCustomer();
        displayMenu();
    }

    public static String greetCustomer() {
        Scanner scanner = new Scanner(System.in);

        IO.print("Welcome! What is your name? ");
        String customerName = scanner.nextLine();
        IO.println("Hi " + customerName + "! Here is our menu: \n");

        scanner.close();
        return customerName;
    }

    public static void displayMenu() {
        String item1 = "Espresso";
        double price1 = 25.00;

        String item2 = "Cappuccino";
        double price2 = 35.00;

        String item3 = "Latte";
        double price3 = 40.00;

        String item4 = "Croissant";
        double price4 = 30.00;

        String item5 = "Sandwich";
        double price5 = 55.00;

        IO.println("==============================");
        IO.println("         Lexicon Cafe");
        IO.println("==============================");
        System.out.printf("%-1d. %-15s %6.2f SEK%n",1, item1, price1);
        System.out.printf("%-1d. %-15s %6.2f SEK%n",2, item2, price2);
        System.out.printf("%-1d. %-15s %6.2f SEK%n",3, item3, price3);
        System.out.printf("%-1d. %-15s %6.2f SEK%n",4, item4, price4);
        System.out.printf("%-1d. %-15s %6.2f SEK%n",5, item5, price5);
        IO.println("==============================");
    }
}
