package se.lexicon;

public class Order {
    public String customerName;
    public String item;
    public int quantity;
    public double unitPrice;
    public boolean isMember;
    public double loyaltyDiscount = 0.15;
    public double vat = 0.12;
    public double bigOrderDiscount = 0.10;
    public double bigOrderMinimum = 150.00;

    public double calculateBasePrice() {
        return unitPrice * quantity;
    }

    public double calculateBaseVAT() {
        return unitPrice * quantity * vat;
    }

    public double calculateTotalPrice() {
        return unitPrice * quantity * (1 + vat);
    }

    public double calculateLoyaltyDiscount() {
        return unitPrice * quantity * (-1 * loyaltyDiscount);
    }

    public double calculateLoyaltyVAT() {
        return unitPrice * quantity * (1-loyaltyDiscount) * vat;
    }

    public double calculateLoyaltyTotalPrice() {
        return unitPrice * quantity * (1-loyaltyDiscount) * (1 + vat);
    }

    public double calculateBigOrderDiscount() {
        return unitPrice * quantity * (-1 * bigOrderDiscount);
    }

    public double calculateBigOrderVAT() {
        return unitPrice * quantity * (1-bigOrderDiscount) * vat;
    }

    public double calculateBigOrderTotalPrice() {
        return unitPrice * quantity * (1-bigOrderDiscount) * (1 + vat);
    }

    public void printReceipt() {
        IO.println();
        IO.println("==============================");
        IO.println("         Lexicon Cafe");
        IO.println("==============================");
        System.out.printf("%-10s : %-10s %n", "Customer", customerName);
        System.out.printf("%-10s : %-10s x %-2s %n", "Item", item, quantity);
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
        IO.println("------------------------------");
        if (isMember) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateLoyaltyTotalPrice());
        } else if (calculateBasePrice() > bigOrderMinimum) {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateBigOrderTotalPrice());
        } else {
            System.out.printf("%-10s : %-6.2f SEK%n", "TOTAL", calculateTotalPrice());
        }
        IO.println("==============================");
        IO.println("    Thank you, " + customerName + "!");
        IO.println("    See you next time!");
        IO.println("==============================");
    }
}

