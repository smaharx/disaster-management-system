package disasterrelief.views;

import disasterrelief.models.*;
import disasterrelief.database.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterFamilyUI extends JFrame {
    Queue<Family> normalQueue = new LinkedList<>();
    PriorityQueue<Family> priorityQueue = new PriorityQueue<>(
        (a, b) -> b.totalPoints - a.totalPoints // higher points first
    );

    public RegisterFamilyUI() {
        setTitle("Family Registration");
        setSize(650, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // ✅ Center frame on screen

        JButton registerBtn = new JButton("Register Family");
        registerBtn.setBounds(200, 150, 200, 40);
		registerBtn.setBackground(new Color(0,123,255));
		registerBtn.setForeground(Color.WHITE);
        add(registerBtn);

        JButton Backbtn = new JButton("Back");
        Backbtn.setBounds(200, 210, 200, 40);
		Backbtn.setBackground(new Color(220,53,69));
		Backbtn.setForeground(Color.WHITE);
        add(Backbtn);

        registerBtn.addActionListener(e -> openFamilyRegistration());
        Backbtn.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    private void openFamilyRegistration() {
        JDialog dialog = new JDialog(this, "Register Family", true);
        dialog.setSize(600, 700);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setLocationRelativeTo(this); // Center dialog relative to frame

        // Font & Dimension for inputs
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(200, 30);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(fieldSize);
        nameField.setFont(font);

        JTextField locationField = new JTextField();
        locationField.setPreferredSize(fieldSize);
        locationField.setFont(font);

        dialog.add(new JLabel("Family Name:"));
        dialog.add(nameField);

        dialog.add(new JLabel("Location:"));
        dialog.add(locationField);

        // 5 priority questions
        String[] priorityQuestions = {
            "House severely damaged?",
            "Multiple family members affected?",
            "Elderly/children/disabled?",
            "Lack access to food/water?",
            "Received help before?"
        };

        JRadioButton[][] priorityAnswers = new JRadioButton[5][2];
        for (int i = 0; i < 5; i++) {
            dialog.add(new JLabel(priorityQuestions[i]));
            JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ButtonGroup group = new ButtonGroup();

            priorityAnswers[i][0] = new JRadioButton("No (0)");
            priorityAnswers[i][1] = new JRadioButton("Yes (1)");

            // ✅ Set size and font for radio buttons
            for (JRadioButton rb : priorityAnswers[i]) {
                rb.setFont(font);
                rb.setPreferredSize(new Dimension(100, 25));
            }

            group.add(priorityAnswers[i][0]);
            group.add(priorityAnswers[i][1]);
            radioPanel.add(priorityAnswers[i][0]);
            radioPanel.add(priorityAnswers[i][1]);
            dialog.add(radioPanel);
        }

        // ===== Resource Need Section =====

        // ----- FOOD -----
        JLabel foodAccessLbl = new JLabel("Do you have access to food?");
        JComboBox<String> foodAccess = new JComboBox<>(new String[]{"Yes", "No"});
        foodAccess.setPreferredSize(fieldSize);
        foodAccess.setFont(font);
        dialog.add(foodAccessLbl);
        dialog.add(foodAccess);

        JLabel foodExtraLbl = new JLabel("if Yes! Do you need extra food?");
        JComboBox<String> foodExtra = new JComboBox<>(new String[]{"No", "Yes"});
        foodExtra.setPreferredSize(fieldSize);
        foodExtra.setFont(font);
        dialog.add(foodExtraLbl);
        dialog.add(foodExtra);

        JLabel foodNeedLbl = new JLabel("If No/Yes, how many packs needed?");
        JTextField foodNeedField = new JTextField("0");
        foodNeedField.setPreferredSize(fieldSize);
        foodNeedField.setFont(font);
        dialog.add(foodNeedLbl);
        dialog.add(foodNeedField);

        // ----- WATER -----
        JLabel waterAccessLbl = new JLabel("Do you have access to water?");
        JComboBox<String> waterAccess = new JComboBox<>(new String[]{"Yes", "No"});
        waterAccess.setPreferredSize(fieldSize);
        waterAccess.setFont(font);
        dialog.add(waterAccessLbl);
        dialog.add(waterAccess);

        JLabel waterExtraLbl = new JLabel("If Yes! Do you need extra water?");
        JComboBox<String> waterExtra = new JComboBox<>(new String[]{"No", "Yes"});
        waterExtra.setPreferredSize(fieldSize);
        waterExtra.setFont(font);
        dialog.add(waterExtraLbl);
        dialog.add(waterExtra);

        JLabel waterNeedLbl = new JLabel("If No/Yes, how many bottles needed?");
        JTextField waterNeedField = new JTextField("0");
        waterNeedField.setPreferredSize(fieldSize);
        waterNeedField.setFont(font);
        dialog.add(waterNeedLbl);
        dialog.add(waterNeedField);

        // ----- MEDICAL -----
        JLabel medicalAccessLbl = new JLabel("Do you have access to medical aid?");
        JComboBox<String> medicalAccess = new JComboBox<>(new String[]{"Yes", "No"});
        medicalAccess.setPreferredSize(fieldSize);
        medicalAccess.setFont(font);
        dialog.add(medicalAccessLbl);
        dialog.add(medicalAccess);

        JLabel medicalExtraLbl = new JLabel("If Yes! Do you need extra medical help?");
        JComboBox<String> medicalExtra = new JComboBox<>(new String[]{"No", "Yes"});
        medicalExtra.setPreferredSize(fieldSize);
        medicalExtra.setFont(font);
        dialog.add(medicalExtraLbl);
        dialog.add(medicalExtra);

        JLabel medicalNeedLbl = new JLabel("If No/Yes, how many kits needed?");
        JTextField medicalNeedField = new JTextField("0");
        medicalNeedField.setPreferredSize(fieldSize);
        medicalNeedField.setFont(font);
        dialog.add(medicalNeedLbl);
        dialog.add(medicalNeedField);

        // Submit button
        JButton submitButton = new JButton("Submit");
		submitButton.setBounds(220, 10, 150, 40);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		submitButton.setBackground(new Color(0,123,255));
		submitButton.setForeground(Color.WHITE);
        dialog.add(new JLabel());
        dialog.add(submitButton);
		
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(100,10,150,40);
        backbtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		backbtn.setBackground(new Color(220,53,69));
		backbtn.setForeground(Color.WHITE);
        dialog.add(new JLabel());
        dialog.add(backbtn);
		backbtn.addActionListener(e -> dialog.dispose());


        submitButton.addActionListener(ev -> {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();

            if (name.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                return;
            }

            int totalPoints = 0;
    for (int i = 0; i < 5; i++) {
        if (priorityAnswers[i][1].isSelected()) {
            totalPoints += 3;
        }
    }

    // Calculate resource needs
    int foodNeed = 0, waterNeed = 0, medicalNeed = 0;

    try {
        if (true) {
            foodNeed = Integer.parseInt(foodNeedField.getText().trim());
            waterNeed = Integer.parseInt(waterNeedField.getText().trim());
            medicalNeed = Integer.parseInt(medicalNeedField.getText().trim());
        
    }
	}catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(dialog, "Please enter valid numeric values for needs!");
        return;
    }

    // Determine queue type
    String queueType = (totalPoints >= 12) ? "Priority" : "Normal";

    // Create Family object
    Family family = new Family(name, location, totalPoints, foodNeed, waterNeed, medicalNeed);

    // Add to memory queues
    if (queueType.equals("Priority")) {
        priorityQueue.add(family);
    } else {
        normalQueue.add(family);
    }
	
	Connection conn = DBConnection.getConnection();
if (conn == null) {
    JOptionPane.showMessageDialog(dialog, "Database connection failed!");
    return;
}
    // Store in database
try {
    String query = "INSERT INTO families (name, location, points, queue_type, food_need, water_need, medical_need) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setString(1, name);
    ps.setString(2, location);
    ps.setInt(3, totalPoints);
    ps.setString(4, queueType);
    ps.setInt(5, foodNeed);
    ps.setInt(6, waterNeed);
    ps.setInt(7, medicalNeed);
    ps.executeUpdate();
    conn.close();
} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(dialog, "Error saving to database: " + ex.getMessage());
    return;
}


    JOptionPane.showMessageDialog(dialog, name + " added successfully to  queue!");
    dialog.dispose();
});

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterFamilyUI();
    }
}
