package disasterrelief;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginUI extends JFrame implements ActionListener {

    JTextField textField;
    JButton jb1, jb2;
    JPasswordField jpasswordField;

    LoginUI() {
        setTitle("Disaster Relief - Login");

        // 🏷 Username Label
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(40, 20, 100, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(new Color(25, 25, 25));
        add(nameLabel);

        // 🔒 Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Segoe UI", Font.BOLD, 16));
        password.setForeground(new Color(25, 25, 25));
        add(password);

        // ✏️ Username Field
        textField = new JTextField();
        textField.setBounds(150, 20, 180, 30);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textField.setBackground(new Color(230, 240, 250));
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        add(textField);

        // 🔐 Password Field
        jpasswordField = new JPasswordField();
        jpasswordField.setBounds(150, 70, 180, 30);
        jpasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        jpasswordField.setBackground(new Color(230, 240, 250));
        jpasswordField.setForeground(Color.BLACK);
        jpasswordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        add(jpasswordField);

        // 🖼️ Login Icon (optional)
        try {
            ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setBounds(430, 30, 180, 180);
            add(imageLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Image not found");
            errorLabel.setBounds(400, 100, 200, 30);
            add(errorLabel);
        }

        // 🔘 Login Button
        jb1 = new JButton("Login");
        jb1.setBounds(40, 140, 130, 35);
        jb1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        jb1.setBackground(new Color(0, 123, 255)); // Blue
        jb1.setForeground(Color.WHITE);
        jb1.setFocusPainted(false);
        jb1.setBorderPainted(false);
        jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jb1.addActionListener(this);
        add(jb1);

        // ❌ Cancel Button
        jb2 = new JButton("Cancel");
        jb2.setBounds(190, 140, 130, 35);
        jb2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        jb2.setBackground(new Color(220, 53, 69)); // Red
        jb2.setForeground(Color.WHITE);
        jb2.setFocusPainted(false);
        jb2.setBorderPainted(false);
        jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jb2.addActionListener(this);
        add(jb2);

        // 🎨 Background
        getContentPane().setBackground(new Color(245, 248, 250)); // very light gray-blue
        setSize(700, 280);
        setLocation(350, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == jb1) {
    try {
        Connection con = DBConnection.getConnection();   // Correct way
        String user = textField.getText();
        String pass = new String(jpasswordField.getPassword());

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, user);
        pst.setString(2, pass);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            setVisible(false);
            new MainUI();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }

        rs.close();
        pst.close();
        con.close();  // close connection

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
 else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}
