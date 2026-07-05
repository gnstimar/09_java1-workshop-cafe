package se.lexicon;

public class LineItem {
    public String itemName;
    public double unitPrice;
    public int quantity;

    public double lineTotal() {
        return unitPrice * quantity;
    }

    public LineItem(String itemName, double unitPrice, int quantity) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
