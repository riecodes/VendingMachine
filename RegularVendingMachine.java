import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RegularVendingMachine {
    private ArrayList<Slot> slots;
    private Map<String, Item> inventory;
    private Map<Item, Integer> startingInventory;
    private Map<Item, Integer> endingInventory;
    private double salesSum;
    private Map<String, Integer> changeValue;

    public RegularVendingMachine() {
        this.slots = new ArrayList<>();
        inventory = new HashMap<>();
        startingInventory = new HashMap<>();
        endingInventory = new HashMap<>();
        salesSum = 0.0;
        changeValue = new HashMap<>();
    }
    
    public void venMachineFeatures() {
        System.out.println("Vending Machine Features:");
        System.out.println("1. Display available items");
        System.out.println("2. Purchase an item");
        System.out.println("3. Return to previous menu");
        System.out.print("Enter your choice: ");
        int choice = readValidChoice(1, 3);

        switch (choice) {
            case 1:
                displayAvailableItems();
                break;
            case 2:
                purchaseItem();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid selection! Please try again.");
                venMachineFeatures();
        }
    }

    private void displayAvailableItems() {
        System.out.println("Available Items:");
        Map<String, Item> inventory = getInventory();
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            String slot = entry.getKey();
            Item item = entry.getValue();
            System.out.println(slot + " - " + item.getItemName() + " - $" + item.getItemPrice());
        }
    }

    private void purchaseItem() {
        String slot = readValidString("Enter the slot of the item you want to purchase: ");
        double amount = readValidInteger(0, Integer.MAX_VALUE, "Enter the amount of money: ");

        Item item = getItem(slot);
        if (item != null) {
            if (amount >= item.getItemPrice()) {
                double change = amount - item.getItemPrice();
                System.out.println("You purchased: " + item.getItemName());
                System.out.println("Change: $" + change);
                item.incrementUnitsSold(); // Increment units sold count
                updateInventory(slot);
            } else {
                System.out.println("Insufficient funds. Please insert more money.");
            }
        } else {
            System.out.println("Invalid slot. Please try again.");
        }
    }


    public void restockItem() {
        performMaintenance(); // Call the existing performMaintenance() method to restock items
        System.out.println("Items restocked.");
    }

    public void addVenItem() {
        System.out.println("Enter details for the new item:");
        String itemName = readValidString("Name of item: ");
        double price = readValidDouble(0, Integer.MAX_VALUE, "Enter a price for the item: ");
        int calories = readValidInteger(0, Integer.MAX_VALUE, "Enter no. of calories of the item: ");

        Item item = new Item();        
        item.setItemName(itemName);
        item.setItemPrice(price);
        item.setItemCalories(calories);
        addItem(generateNewItemSlot(), item);

        System.out.println("New item added:");
        System.out.println("Name: " + item.getItemName());
        System.out.println("Price: " + item.getItemPrice());
        System.out.println("Calories: " + item.getItemCalories());
    }

    public void changeVenItem() {
        String itemSlot = readValidString("Enter the item slot: ");
        double newPrice = readValidDouble(0, Integer.MAX_VALUE, "Enter the new price for the item: ");
        setPrice(itemSlot, newPrice);
        System.out.println("Item price changed successfully.");
    }

    public void collectVenMoney() {
        collectPayment(); // Call the existing collectPayment() method to collect money
        System.out.println("Money collected.");
    }

    public void addVenMoney() {
        String denomination = readValidString("Enter the denomination of the coin/bill to replenish: ");
        insertCoin(denomination); // Assuming coins and bills have the same denomination values
        System.out.println("Denomination replenished.");
    }

    public void printVenTransaction() {
        Map<Item, Integer> endingInventory = getEndingInventory();
        for (Map.Entry<Item, Integer> entry : endingInventory.entrySet()) {
            Item item = entry.getKey();
            int count = entry.getValue();
            int unitsSold = item.getUnitsSold(); // Get the units sold for the item
            System.out.println(item.getItemName() + " - " + " units sold (Total: " + unitsSold + ")");
        }
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
            item.setItemPrice((int) price);

        }
    }

    public double getPrice(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            return item.getItemPrice();
        }
        return 0.0;
    }

    public int getCalories(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            return item.getItemCalories();
        }
        return 0;
    }

    public void insertCoin(String coinValue) {
        Integer count = changeValue.getOrDefault(coinValue, 0);
        changeValue.put(coinValue, count + 1);
    }

    public void insertBill(String billValue) {
        Integer count = changeValue.getOrDefault(billValue, 0);
        changeValue.put(billValue, count + 1);
    }

    public void selectItem(String itemSlot) {
        Item item = inventory.get(itemSlot);
        if (item != null) {
            double price = item.getItemPrice();
            if (salesSum >= price) {
                int startingCount = startingInventory.getOrDefault(item, 0);
                int endingCount = endingInventory.getOrDefault(item, 0);
                if (startingCount > endingCount) {
                    // Item available
                    startingInventory.put(item, startingCount - 1);
                    endingInventory.put(item, endingCount + 1);
                    salesSum += price; // Update the sales count
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
        System.out.println("Dispensing " + item.getItemName() + ".");
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
        for (Map.Entry<String, Integer> entry : changeValue.entrySet()) {
            String coinOrBill = entry.getKey();
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

    public void collectPayment() {
        salesSum = 0.0;
        System.out.println("Payment collected.");
    }

    public void replenishChange() {
        for (Map.Entry<String, Integer> entry : changeValue.entrySet()) {
            String coinOrBill = entry.getKey();
            int count = entry.getValue();
            if (count == 0) {
                changeValue.put(coinOrBill, 10); // Assuming a capacity of 10 coins/bills for each denomination
            }
        }
        System.out.println("Change replenished.");
    }

    public Map<Item, Integer> getStartingInventory() {
        return startingInventory;
    }

    public Map<Item, Integer> getEndingInventory() {
        return endingInventory;
    }

    private int readValidChoice(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
        return input;
    }

    private int readValidInteger(int min, int max, String message) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print(message);
            try {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
        return input;
    }


    private double readValidDouble(double min, double max, String message) {
        Scanner scanner = new Scanner(System.in);
        double input;
        while (true) {
            System.out.print(message);
            try {
                input = scanner.nextDouble();
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
        return input;
    }

    private String readValidString(String message) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public void updateInventory(String itemName) {
        for (Slot slot : slots) {
            ArrayList<Item> itemSlot = slot.getItemSlot();
            for (Item item : itemSlot) {
                if (item.getItemName().equals(itemName)) {
                    item.setItemQuantity(item.getItemQuantity() + 1);
                    System.out.println("Inventory updated for item: " + itemName);
                    return;
                }else {
                    System.out.println("Item not found in inventory: " + itemName);
                }
            }
        }
        
    }

    private int lastSlotNumber = 0; // Add this instance variable

    public String generateNewItemSlot() {
        lastSlotNumber++; // Increment the last slot number
        String newSlot = String.valueOf(lastSlotNumber); // Convert the slot number to a string
        System.out.println("New item slot generated: " + newSlot);
        return newSlot;
    }


    public void addSlot(Slot slot) {
        slots.add(slot);
    }
}