public class Item {
    private String itemName;
    private double itemPrice;
    private int itemCalories;
    private int itemQuantity;
    private boolean itemAvailability;
    private int unitsSold;

    public Item() {
        this.itemName = "Empty";
        this.itemPrice = -1;
        this.itemCalories = -1;
        this.itemQuantity = -1;
        this.itemAvailability = false;
    }

    public Item(String itemName, int itemPrice, int itemCalories, int itemQuantity, boolean itemAvailability) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalories = itemCalories;
        this.itemQuantity = itemQuantity;
        this.itemAvailability = itemAvailability;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemCalories() {
        return itemCalories;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public boolean getItemAvailability() {
        return itemAvailability;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemCalories(int itemCalories) {
        this.itemCalories = itemCalories;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemAvailability(boolean itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    public boolean isAvailable() {
        return itemAvailability;
    }

    public void itemRestock(int capacity) {
        if (itemQuantity < capacity) {
            int addedItems = capacity - itemQuantity;
            System.out.println("Restocking " + addedItems + " " + itemName + "(s)...");
            itemQuantity = capacity;
            System.out.println("Restocking complete! The vending machine now has " + itemQuantity + " " + itemName + "(s).");
        } else {
            System.out.println("No restocking needed! The vending machine already has the maximum capacity of " + itemQuantity + " " + itemName + "(s).");
        }
    }

    public double calculateTotalPrice(int quantity) {
        return itemPrice * quantity;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void incrementUnitsSold() {
        unitsSold++;
    }
}
