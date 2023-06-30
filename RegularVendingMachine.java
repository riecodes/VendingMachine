import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegularVendingMachine {
    private Map<String, Item> inventory;
    private Map<Item, Integer> startingInventory;
    private Map<Item, Integer> endingInventory;
    private double salesSum;
    private Map<Value, Integer> changeValue;

    public VendingMachine() {
        inventory = new HashMap<>();
        startingInventory = new HashMap<>();
        endingInventory = new HashMap<>();
        salesSum = 0.0;
        changeValue = new HashMap<>();
    }
 
    public void addItem(String itemSlot, Item item) {
        inventory.put(itemSlot, item);
        startingInventory.put(item, 0);
        endingInventory.put(item, 0);
    }

    public void deleteItem(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            inventory.remove(itemSlot);
            startingInventory.remove(item);
            endingInventory.remove(item);
        }
    }

    public Item getItem(String itemSlot) {
        return inventory.get(itemSlot);
    }

    public void setPrice(String itemSlot, double price) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            item.setPrice(price);
        }
    }

    public double getPrice(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            return item.getPrice();
        }
        return 0.0;
    }

    public int getCalories(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            return item.getCalories();
        }
        return 0;
    }

    public void insertCoin(Value coinValue) {
        Integer count = changeValue.getOrDefault(coinValue, 0);
        changeValue.put(coinValue, count + 1);
    }

    public void insertBill(Value billValue) {
        Integer count = changeValue.getOrDefault(billValue, 0);
        changeValue.put(billValue, count + 1);
    }

    public void selectItem(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            double price = item.getPrice();
            if (salesSum >= price) {
                int startingCount = startingInventory.getOrDefault(item, 0);
                int endingCount = endingInventory.getOrDefault(item, 0);
                if (startingCount > endingCount) {
                    // Item available
                    startingInventory.put(item, startingCount - 1);
                    endingInventory.put(item, endingCount + 1);
                    salesSum -= price;
                    dispenseItem(item);
                    giveChange(price);
                } else {
                    // Item out of stock
                    System.out.println("Item is out of stock.");
                }
            } else {
                // Insufficient funds
                System.out.println("Insufficient funds. Please insert more coins or bills.");
            }
        }
    }

    public void cancelTransaction() {
        returnChange();
    }

    private void dispenseItem(Item item) {
        System.out.println("Dispensing " + item.getName() + ".");
    }

    private void giveChange(double price) {
        double change = salesSum - price;
        if (change > 0) {
            System.out.println("Change: " + change);
            salesSum = 0.0;
            returnChange();
        }
    }

    private void returnChange() {
        System.out.println("Returning change:");
        for (Map.Entry<Value, Integer> entry : changeValue.entrySet()) {
            Value coinOrBill = entry.getKey();
            int count = entry.getValue();
                        if (count > 0) {
                System.out.println(coinOrBill + " x " + count);
                changeValue.put(coinOrBill, 0);
            }
        }
    }

    public void performMaintenance() {
        replenishItems();
        collectPayment();
        replenishChange();
    }

    private void replenishItems() {
        for (Map.Entry<Item, Integer> entry : endingInventory.entrySet()) {
            Item item = entry.getKey();
            int count = entry.getValue();
            startingInventory.put(item, count);
            endingInventory.put(item, 0);
        }
        System.out.println("Items replenished.");
    }

    private void collectPayment() {
        salesSum = 0.0;
        System.out.println("Payment collected.");
    }

    private void replenishChange() {
        for (Map.Entry<Value, Integer> entry : changeValue.entrySet()) {
            Value coinOrBill = entry.getKey();
            int count = entry.getValue();
            if (count == 0) {
                changeValue.put(coinOrBill, 10); // Assuming a capacity of 10 coins/bills for each denomination
            }
        }
        System.out.println("Change replenished.");
    }
}