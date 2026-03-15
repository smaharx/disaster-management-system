package disasterrelief;

import javax.swing.*;
import java.awt.*;

public class ViewInventoryUI extends JFrame {

    private InventoryManager invManager;
    private JTextArea area;

    public ViewInventoryUI(InventoryManager manager) {
        this.invManager = manager;
        setupUI();
        loadInventory();
    }

    private void setupUI() {
        setTitle("Current Inventory");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton restock = new JButton("Restock Items");
		restock.setBackground(new Color(220,53,69));
		restock.setForeground(COLOR.WHITE);
        add(restock, BorderLayout.SOUTH);

        restock.addActionListener(e ->
                new RestockUI(invManager).setVisible(true)
        );
    }

private void loadInventory() {
		area.setText(""); 
		for (InventoryItem item : invManager.getAllItemsAsList()) {
			area.append(item.getName() + " : " + item.getQty() + "\n"); 
		}
}

}
