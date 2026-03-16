package disasterrelief.database;

import disasterrelief.models.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import disasterrelief.models.InventoryItem;

public class InventoryManager {

    private InventoryItem head;

    public InventoryManager() {
        // Initial Inventory (DSA Linked List)
        head = new InventoryItem("Food Pack", 200);
        head.next = new InventoryItem("Water Bottle", 150);
        head.next.next = new InventoryItem("Medical Kit", 100);
    }

    // Return items as list for UI use
    public List<InventoryItem> getAllItemsAsList() {
        List<InventoryItem> list = new ArrayList<>();
        InventoryItem temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        return list;
    }

    // Deduct quantity when allocating
    public boolean deduct(String item, int qty) {
        InventoryItem temp = head;
        while (temp != null) {
            if (temp.getName().equalsIgnoreCase(item)) {
                if (temp.getQty() >= qty) {
                    temp.setQty(temp.getQty() - qty);
                    return true;
                }
                return false; // not enough quantity
            }
            temp = temp.next;
        }
        return false; // item not found
    }

    // Restock item
    public boolean restockItem(String item, int qty) {
        InventoryItem temp = head;
        while (temp != null) {
            if (temp.getName().equalsIgnoreCase(item)) {
                temp.setQty(temp.getQty() + qty);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Allocate item
    public boolean allocateItem(String itemName, int quantity) {
        InventoryItem item = findItem(itemName);
        if (item == null) return false;

        if (item.getQty() >= quantity) {
            item.setQty(item.getQty() - quantity);
            return true;
        }
        return false;
    }

    // Find item in the linked list
    private InventoryItem findItem(String name) {
        for (InventoryItem it : getAllItemsAsList()) {
            if (it.getName().equalsIgnoreCase(name)) {
                return it;
            }
        }
        return null;
    }

    // Check availability with message
    public boolean checkAvailability(String itemName, int qty) {
        InventoryItem item = findItem(itemName);
        if (item == null) return false;

        if (item.getQty() == 0) {
            JOptionPane.showMessageDialog(null,
                    itemName + " is OUT OF STOCK. Please restock.");
            return false;
        }

        if (item.getQty() < qty) {
            JOptionPane.showMessageDialog(null,
                    "Not enough " + itemName + ". Available: " + item.getQty());
            return false;
        }

        return true;
    }
}
