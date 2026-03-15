package disasterrelief;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * InventoryUI shows current LinkedList inventory in a JTable
 * and allows allocation & restocking.
 */
public class InventoryUI extends JFrame {

    private InventoryManager invManager;
    private Connection conn; 
    private JTable table;
    private DefaultTableModel model;

    private Choice choice;
    private JTextField jtextFiledN, jtextFiledQ, jtextfieldF, jtextfieldW, jtextfieldM;

    public InventoryUI() {

        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            conn = null;
        }

        invManager = new InventoryManager();
        // invManager.initDefaults();  // already done in your manager class

        initUI();
        refreshTable();
    }

    private void initUI() {
        setTitle("Inventory Manager");
        setSize(950, 520);
        setLayout(null);
        setLocation(265, 230);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 250));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(panel);

        JLabel title = new JLabel("Allocating Resources");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setBounds(20, 10, 300, 30);
        panel.add(title);

        // -------- LEFT SIDE FAMILY FORM --------
        JLabel label2 = new JLabel("Points :");
        label2.setBounds(25, 55, 100, 15);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds(120, 52, 180, 25);
        panel.add(choice);
        loadPoints();

        JLabel label3 = new JLabel("Name:");
        label3.setBounds(25, 95, 100, 15);
        panel.add(label3);

        jtextFiledN = new JTextField();
        jtextFiledN.setBounds(120, 92, 180, 20);
        panel.add(jtextFiledN);

        JLabel label4 = new JLabel("Queue Type:");
        label4.setBounds(25, 135, 100, 15);
        panel.add(label4);

        jtextFiledQ = new JTextField();
        jtextFiledQ.setBounds(120, 132, 180, 20);
        panel.add(jtextFiledQ);

        JLabel label5 = new JLabel("Food Need:");
        label5.setBounds(25, 175, 100, 15);
        panel.add(label5);

        jtextfieldF = new JTextField();
        jtextfieldF.setBounds(120, 172, 180, 20);
        panel.add(jtextfieldF);

        JLabel label6 = new JLabel("Water Need:");
        label6.setBounds(25, 215, 100, 15);
        panel.add(label6);

        jtextfieldW = new JTextField();
        jtextfieldW.setBounds(120, 212, 180, 20);
        panel.add(jtextfieldW);

        JLabel label7 = new JLabel("Medical Need:");
        label7.setBounds(25, 255, 100, 15);
        panel.add(label7);

        jtextfieldM = new JTextField();
        jtextfieldM.setBounds(120, 252, 180, 20);
        panel.add(jtextfieldM);

        JButton allocate = new JButton("ALLOCATE");
        allocate.setBounds(20, 295, 100, 25);
        panel.add(allocate);
        allocate.addActionListener(e -> allocateResources());

        JButton check = new JButton("CHECK");
        check.setBounds(140, 295, 120, 25);
        panel.add(check);
        check.addActionListener(ae -> loadFamily());

        // -------- RIGHT SIDE INVENTORY TABLE --------
        model = new DefaultTableModel(new Object[]{"Item", "Quantity"}, 0) {
             public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(340, 50, 560, 360);
        panel.add(jsp);

        // -------- RESTOCK BUTTON --------
        JButton restockBtn = new JButton("Restock Items");
        restockBtn.setBounds(340, 420, 130, 30);
        panel.add(restockBtn);

        restockBtn.addActionListener(e -> {
            RestockUI rui = new RestockUI(invManager);
            rui.setVisible(true);

            rui.addWindowListener(new WindowAdapter() {
               
                public void windowClosed(WindowEvent we) {
                    refreshTable();
                }
            });
        });

        // -------- BACK BUTTON --------
        JButton back = new JButton("BACK");
        back.setBounds(820, 420, 80, 30);
        panel.add(back);

        back.addActionListener(ae -> {
            dispose();
        });

        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (InventoryItem it : invManager.getAllItemsAsList()) {
            model.addRow(new Object[]{it.getName(), it.getQty()});
        }
    }

    // ------------ Load Points from DB ----------------
    private void loadPoints() {
        if (conn == null) return;
        try {
            String query = "SELECT points FROM families ORDER BY points DESC";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            choice.removeAll();
            while (rs.next()) {
                choice.add(rs.getString("points"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadFamily() {
        String points = choice.getSelectedItem();
        if (points == null || points.isEmpty()) return;

        try {
            String query = "SELECT * FROM families WHERE points = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, points);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                jtextFiledN.setText(rs.getString("name"));
                jtextFiledQ.setText(rs.getString("queue_type"));
                jtextfieldF.setText(rs.getString("food_need"));
                jtextfieldW.setText(rs.getString("water_need"));
                jtextfieldM.setText(rs.getString("medical_need"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading family: " + ex.getMessage());
        }
    }

  private void allocateResources() {
    try {

        // ---- 1. Read Inputs ----
        String familyName = jtextFiledN.getText().trim();
        String queueType = jtextFiledQ.getText().trim();

        int foodNeed = Integer.parseInt(jtextfieldF.getText().trim());
        int waterNeed = Integer.parseInt(jtextfieldW.getText().trim());
        int medicalNeed = Integer.parseInt(jtextfieldM.getText().trim());

        // ---- 2. Basic validation ----
        if (familyName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please load a family first!");
            return;
        }
		boolean foodOK = invManager.checkAvailability("Food Pack", foodNeed);
        boolean waterOK = invManager.checkAvailability("Water Bottle", waterNeed);
        boolean medOK = invManager.checkAvailability("Medical Kit", medicalNeed);
		
		 if (!foodOK || !waterOK || !medOK) {
            JOptionPane.showMessageDialog(this,
                "Allocation failed. One or more required items are OUT OF STOCK.\nPlease RESTOCK and try again.",
                "Allocation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return; // **STOP HERE** (NO partial allocation)
        }
		
        // ---- 3. Allocate from Inventory ----
        invManager.allocateItem("Food Pack", foodNeed);
        invManager.allocateItem("Water Bottle", waterNeed);
        invManager.allocateItem("Medical Kit", medicalNeed);
		
		 JOptionPane.showMessageDialog(this,
            "All resources allocated successfully to " + familyName + "!");

        // ---- 4. Insert into summary table ----
        String insertQuery = "INSERT INTO allocation_summary (family_name, queue_type, served) VALUES (?, ?, ?)";
        PreparedStatement pst1 = conn.prepareStatement(insertQuery);
        pst1.setString(1, familyName);
        pst1.setString(2, queueType);
        pst1.setInt(3, 1);
        pst1.executeUpdate();
		

        // ---- 5. Delete from families table ----
        String deleteQuery = "DELETE FROM families WHERE name = ?";
        PreparedStatement pst2 = conn.prepareStatement(deleteQuery);
        pst2.setString(1, familyName);
        pst2.executeUpdate();
		

        // ---- 6. Clear fields ----
        jtextFiledN.setText("");
        jtextFiledQ.setText("");
        jtextfieldF.setText("");
        jtextfieldW.setText("");
        jtextfieldM.setText("");

        // ---- 7. Refresh list ----
        loadPoints();
        refreshTable();

        

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}


    @Override
    public void dispose() {
        super.dispose();
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InventoryUI();
    }
}
