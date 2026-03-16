package disasterrelief.views;

import disasterrelief.models.*;
import disasterrelief.database.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

	public class MainUI extends JFrame implements ActionListener {
		JButton manageFamiliyBtn, viewReportsBtn, reliefStockBtn, logoutBtn, addFamilyButton ;
		public MainUI() {
		setTitle("Disaster Relief - Dashboard");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(245, 248, 250)); // same as login background
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 🧭 Title Label
		JLabel title = new JLabel("Disaster Relief Dashboard");
		title.setBounds(500, 40, 400, 40);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		title.setForeground(new Color(33, 47, 60));
		add(title);
		// 💼 Buttons
		addFamilyButton = createButton("Register Family", 120, 150);
		manageFamiliyBtn = createButton("Manage Families", 320, 150);
		reliefStockBtn = createButton("Relief Stock", 520, 150);
		viewReportsBtn =createButton("View Reports",720,150);
		logoutBtn = createButton("Logout", 320, 300, new Color(220, 53, 69));

		add(manageFamiliyBtn);
		add(viewReportsBtn);
		add(reliefStockBtn);
		add(addFamilyButton);
		add(logoutBtn);
	


		 

			setVisible(true);
		}

		// 🧩 Helper Method for Buttons
		private JButton createButton(String text, int x, int y) {
			return createButton(text, x, y, new Color(0, 123, 255)); // default blue
		}

		private JButton createButton(String text, int x, int y, Color bgColor) {
			JButton btn = new JButton(text);
			btn.setBounds(x, y, 160, 40);
			btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
			btn.setBackground(bgColor);
			btn.setForeground(Color.WHITE);
			btn.setFocusPainted(false);
			btn.setBorderPainted(false);
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btn.addActionListener(this);
			return btn;
		}

	   
	    public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addFamilyButton) {
				new RegisterFamilyUI();
			} else if (e.getSource() == viewReportsBtn) {
				new report();
			} else if (e.getSource() == reliefStockBtn) {
				new InventoryUI();
			}
			else if (e.getSource() == manageFamiliyBtn ) {
			   new manageFamiliesUI();
			}else if (e.getSource() == logoutBtn) {
				int confirm = JOptionPane.showConfirmDialog(this, "Do you really want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					setVisible(false);
					new LoginUI();
				}
			}
		}

		public static void main(String[] args) {
			new MainUI();
		}
	}
