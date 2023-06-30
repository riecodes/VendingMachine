import java.util.Map;

public class Maintenance {
    private VendingMachine vendingMachine;

    public Maintenance(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void restockItem(String itemSlot, int quantity) {
        Item item = vendingMachine.getItem(itemSlot);
        if (item != null) {
            int startingCount = vendingMachine.getStartingInventory().getOrDefault(item, 0);
            vendingMachine.getStartingInventory().put(item, startingCount + quantity);
            System.out.println("Item restocked: " + item.getName());
            System.out.println("Quantity: " + quantity);
        } else {
            System.out.println("Invalid item slot.");
        }
    }

    public void setItemPrice(String itemSlot, double price) {
        vendingMachine.setPrice(itemSlot, price);
        System.out.println("Item Slot: " + itemSlot);
        System.out.println("Price: " + price);
    }

    public void collectPayment() {
        vendingMachine.collectPayment();
        System.out.println("Payment collected.");
    }

    public void replenishChange() {
        vendingMachine.replenishChange();
        System.out.println("Change replenished.");
    }

    public void performMaintenance() {
        restockItems();
        collectPayment();
        replenishChange();
    }

    private void restockItems() {
        Map<Item, Integer> endingInventory = vendingMachine.getEndingInventory();
        for (Map.Entry<Item, Integer> entry : endingInventory.entrySet()) {
            Item item = entry.getKey();
            int count = entry.getValue();
            vendingMachine.getStartingInventory().put(item, count);
            endingInventory.put(item, 0);
        }
        System.out.println("Items successfully restocked.");
    }
}
