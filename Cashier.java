public class Cashier {
    private Item orderedItem;
    private double paidAmount;
    private double changeAmount;
    private int orderQuantity;

    public Cashier(Item orderedItem, double paidAmount) {
        this.orderedItem = orderedItem;
        this.paidAmount = paidAmount;
    }

    public Item getItem() {
        return orderedItem;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setItem(Item orderedItem) {
        this.orderedItem = orderedItem;
    }

    public void setQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void setChangeAmount(double change) {
        this.changeAmount = change;
    }

    public void setPaidAmount(double payment) {
        this.paidAmount = payment;
    }

        public double calculateChange() {
        double totalPrice = orderedItem.getItemPrice() * orderQuantity;
        return paidAmount - totalPrice;
    }
}
