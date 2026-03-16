package disasterrelief.views;

import disasterrelief.models.*;
import disasterrelief.database.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class report extends JFrame {

    JPanel panel;
    JTable table;

    public report() {

        // FRAME SETTINGS
        setTitle("Summary Table");
        setSize(900, 600);
        setLocation(290, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // MAIN PANEL
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 90, 110));
        panel.setBounds(0, 0, 900, 600);
        add(panel);

        // HEADER BAR (fixed visual issue)
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(90, 156, 163)); // same theme color
        header.setBounds(0, 0, 900, 50);
        panel.add(header);

        JLabel label1 = new JLabel("Summary Report");
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        label1.setBounds(20, 10, 300, 30);
        header.add(label1);

        // TABLE
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 13));
        table.setRowHeight(30);
        table.setBackground(new Color(90,156,163));
        

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 70, 850, 420);
        panel.add(scroll);
		
		try {
    Connection con = DBConnection.getConnection();
    String query = "SELECT * FROM allocation_summary;";
    PreparedStatement pst = con.prepareStatement(query);
    ResultSet rs = pst.executeQuery();

    table.setModel(DbUtils.resultSetToTableModel(rs));

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(
        null,
        "Database Error: " + ex.getMessage()
    );
}


       

        // BACK BUTTON
        JButton b1 = new JButton("BACK");
        b1.setBounds(390, 510, 120, 35);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(ae -> setVisible(false));

        // SHOW FRAME
        setVisible(true);
    }


    
    public static void main(String[] args) {
        new report();
    }
}
