package disasterrelief;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class manageFamiliesUI extends JFrame {

    JPanel panel;

    public manageFamiliesUI() {

        // FRAME SETTINGS
        setTitle("Manage Families");
        setSize(900,600);
        setLocation(290,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // MAIN PANEL
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 90, 110));   // ✅ Background matching your project's theme
        panel.setBounds(0, 0, 900, 600);
        add(panel);

        // TABLE
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setRowHeight(30);
        table.setBackground(new Color(90,156,163));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 40, 850, 450);
        panel.add(scroll);

        // DB LOAD
       try {
    Connection con = DBConnection.getConnection();
    String query = "SELECT * FROM families order by points desc";

    PreparedStatement pst = con.prepareStatement(query);
    ResultSet rs = pst.executeQuery();

    table.setModel(DbUtils.resultSetToTableModel(rs));

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
}


    JLabel label1 = new JLabel("ID");
label1.setBounds(100, 11, 80, 20);
label1.setFont(new Font("Tahoma", Font.BOLD, 14));
label1.setForeground(Color.white);
panel.add(label1);

JLabel label2 = new JLabel("Name");
label2.setBounds(260, 11, 120, 20);
label2.setFont(new Font("Tahoma", Font.BOLD, 14));
label2.setForeground(Color.white);
panel.add(label2);

JLabel label3 = new JLabel("Location");
label3.setBounds(410, 11, 120, 20);
label3.setFont(new Font("Tahoma", Font.BOLD, 14));
label3.setForeground(Color.white);
panel.add(label3); 

JLabel label4 = new JLabel("Points");
label4.setBounds(585, 11, 120, 20);
label4.setFont(new Font("Tahoma", Font.BOLD, 14));
label4.setForeground(Color.white);
panel.add(label4);

JLabel label5 = new JLabel("Queue Type");
label5.setBounds(750, 11, 150, 20);
label5.setFont(new Font("Tahoma", Font.BOLD, 14));
label5.setForeground(Color.white);
panel.add(label5);


        // BACK BUTTON
        JButton b1 = new JButton("BACK");
        b1.setBounds(400, 510, 120, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        panel.add(b1);

        b1.addActionListener(ae -> setVisible(false));

        // SHOW FRAME
        setVisible(true);
    }

    public static void main(String[] args) {
        new manageFamiliesUI();
    }
}
