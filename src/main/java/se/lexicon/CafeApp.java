package se.lexicon;

import java.util.Scanner;

public class CafeApp {
    static void main() {
        String customer = greetCustomer();
    }

    public static String greetCustomer() {
        Scanner scanner = new Scanner(System.in);

        IO.print("Welcome! What is your name? ");
        String customerName = scanner.nextLine();
        IO.println("Hi " + customerName + "! Here is our menu: \n");

        scanner.close();
        return customerName;
    }
}
