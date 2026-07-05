package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public String customerName;
    public boolean isMember;
    public double loyaltyDiscount = 0.15;
    public double vat = 0.12;
    public double bigOrderDiscount = 0.10;
    public double bigOrderMinimum = 150.00;
    public List<LineItem> myList = new ArrayList<>();

    public void addItem(String itemName, double unitPrice, int quantity) {
        LineItem lineItem = new LineItem(itemName, unitPrice, quantity);
        myList.add(lineItem);
    }

    public double calculateBasePrice() {
        return myList.stream().mapToDouble(lineItem -> lineItem.lineTotal()).sum();
    }

    public double calculateBaseVAT() {
        return calculateBasePrice() * vat;
    }

    public double calculateTotalPrice() {
        return calculateBasePrice() * (1 + vat);
    }

    public double calculateLoyaltyDiscount() {
        return calculateBasePrice() * (-1 * loyaltyDiscount);
    }

    public double calculateLoyaltyVAT() {
        return calculateBasePrice() * (1 - loyaltyDiscount) * vat;
    }

    public double calculateLoyaltyTotalPrice() {
        return calculateBasePrice() * (1 - loyaltyDiscount) * (1 + vat);
    }

    public double calculateBigOrderDiscount() {
        return calculateBasePrice() * (-1 * bigOrderDiscount);
    }

    public double calculateBigOrderVAT() {
        return calculateBasePrice() * (1 - bigOrderDiscount) * vat;
    }

    public double calculateBigOrderTotalPrice() {
        return calculateBasePrice() * (1 - bigOrderDiscount) * (1 + vat);
    }

    public void printReceipt() {
        IO.println();
        IO.println("====================================");
        IO.println("            Lexicon Cafe");
        IO.println("====================================");
        System.out.printf("%-10s : %-10s %n", "Customer", customerName);
        IO.println("------------------------------------");
        for (LineItem line : myList) {
            System.out.printf("  %-15s x%-4d %6.2f SEK%n", line.itemName, line.quantity, line.lineTotal());
        }
        IO.println("------------------------------------");
        System.out.printf("%-10s : %-6.2f SEK%n", "Subtotal", calculateBasePrice());
        if (isMember) {
            System.out.printf("%-10s : %-6.2f SEK%n", "Discount", calculateLoyaltyDiscount());
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateLoyaltyVAT());
        } else if (calculateBasePrice() > bigOrderMinimum) {
            System.out.printf("%-10s : %-6.2f SEK%n", "Discount", calculateBigOrderDiscount());
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateBigOrderVAT());
        } else {
            System.out.printf("%-10s : %-6.2f SEK%n", "VAT", calculateBaseVAT());
        }
        IO.println("------------------------------------");
        if (isMember) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateLoyaltyTotalPrice());
        } else if (calculateBasePrice() > bigOrderMinimum) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateBigOrderTotalPrice());
        } else {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateTotalPrice());
        }
        IO.println("====================================");
        IO.println("    Thank you, " + customerName + "!");
        IO.println("    See you next time!");
        IO.println("====================================");
    }
}

