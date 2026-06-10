package se.lexicon;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        IO.print("Welcome! What is your name? ");
        String customer = scanner.nextLine();
        IO.print("Hi " + customer + "! Here is our menu:");

        scanner.close();
    }
}
