package se.lexicon;

import java.util.Scanner;

public class CafeApp {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        IO.print("Welcome! What is your name? ");
        String customer = scanner.nextLine();
        IO.print("Hi " + customer + "! Here is our menu:");

        scanner.close();
    }
}
