package disasterrelief.views;

import disasterrelief.models.*;
import disasterrelief.database.*;

import javax.swing.*;
import java.awt.*;

public class RestockUI extends JFrame {

    private JComboBox<String> cmbItems;
    private JTextField txtQty;
    private InventoryManager invManager;

	public RestockUI(InventoryManager invManager) {
    this.invManager = invManager;
    setupUI(); 
}



    private void setupUI() {
        setTitle("Restock Inventory");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblItem = new JLabel("Item:");
        lblItem.setBounds(20, 20, 100, 20);
        add(lblItem);

        cmbItems = new JComboBox<>();
        cmbItems.setBounds(100, 20, 200, 25);
        add(cmbItems);

       for (InventoryItem it : invManager.getAllItemsAsList()) {
            cmbItems.addItem(it.getName());
        }


        JLabel lblQty = new JLabel("Quantity:");
        lblQty.setBounds(20, 60, 100, 20);
        add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(100, 60, 200, 25);
        add(txtQty);

        JButton addBtn = new JButton("Update");
        addBtn.setBounds(100, 100, 90, 30);
        add(addBtn);

        addBtn.addActionListener(e -> updateStock());
    }

    private void updateStock() {
        String item = (String) cmbItems.getSelectedItem();
        int qty;

        try {
            qty = Integer.parseInt(txtQty.getText().trim());
            if (qty <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid quantity!");
            return;
        }

        invManager.restockItem(item, qty);
        JOptionPane.showMessageDialog(this, "Stock Updated!");
        dispose();
    }
}
