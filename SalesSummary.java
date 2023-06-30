import java.util.HashMap;
import java.util.Map;

public class SalesSummary {
    private Map<Item, Integer> itemSales;
    private double totalSales;
    private Map<Item, Integer> startingInventory;
    private Map<Item, Integer> endingInventory;

    public SalesSummary() {
        itemSales = new HashMap<>();
        totalSales = 0.0;
        startingInventory = new HashMap<>();
        endingInventory = new HashMap<>();
    }

    public void printVenTransaction() {
        System.out.println("========================================");
        System.out.println("Regular Vending Machine Transaction Summary:");
        System.out.println("========================================");

        System.out.println("Items Sold:");
        for (Map.Entry<Item, Integer> entry : itemSales.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            int unitsSold = item.getUnitsSold(); // Get the number of units sold for this item
            System.out.println(item.getItemName() + ": " + quantity + " units sold (" + unitsSold + " total)");
        }

        System.out.println("\nTotal Sales Amount: $" + totalSales);
    }

    public void updateSales(Item item, int quantity) {
        itemSales.put(item, quantity);
    }

    public void updateTotalSales(double amount) {
        totalSales += amount;
    }

    public Map<Item, Integer> getStartingInventory() {
        return startingInventory;
    }

    public Map<Item, Integer> getEndingInventory() {
        return endingInventory;
    }

    public void setStartingInventory(Map<Item, Integer> inventory) {
        startingInventory = inventory;
    }

    public void setEndingInventory(Map<Item, Integer> inventory) {
        endingInventory = inventory;
    }
}
