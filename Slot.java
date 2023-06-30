import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemSlot = new ArrayList <Item> ();
    private int itemQuantity;
    private int itemCapacity;

    public Slot(ArrayList<Item> itemSlot, int itemQuantity, int itemCapacity) {
        this.itemSlot = itemSlot;
        this.itemQuantity = itemQuantity;
        this.itemCapacity = itemCapacity;
    }

    public ArrayList<Item> getItemSlot() {
        return itemSlot;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public int getItemCapacity() {
        return itemCapacity;
    }

    public void setItemSlot(ArrayList<Item> items) {
        this.itemSlot = items;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemCapacity(int itemCapacity) {
        this.itemCapacity = itemCapacity;
    }
}
