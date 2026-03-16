package disasterrelief.models;

public class InventoryItem {
    private String name;
    private int qty;
    public InventoryItem next;

    public InventoryItem(String name, int qty) {
        this.name = name;
        this.qty = qty;
        this.next = null;
    }

    public String getName() { return name; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}
