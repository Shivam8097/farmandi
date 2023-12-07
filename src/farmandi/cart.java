package farmandi;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class cart extends JFrame implements ActionListener {
	JFrame f4 = new JFrame("Cart");
	JButton addb,remb,remba,buy;

	private String cphoneno;
	JComboBox<String> cartList;
	JPanel fpanel,productPanel;
	JTextArea productNameArea, quantityArea, listedOnArea, lastDateArea, fpriceArea, soldArea, unsoldArea;

	cart(String cphoneno) {

		this.cphoneno= cphoneno;

		fpanel = new JPanel();
		fpanel.setBounds(0, 0, 800, 50);
		fpanel.setLayout(null);
		fpanel.setBackground(new Color(76, 153, 0));
		f4.add(fpanel, BorderLayout.NORTH);

		ImageIcon image12 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\LOF.JPG");
		JLabel falo = new JLabel(image12);
		falo.setBounds(6, 6, 38, 38);
		fpanel.add(falo, BorderLayout.CENTER);

		JButton logout = new JButton("LogOut");
		logout.setBounds(700, 2, 90, 30);
		logout.setBackground(Color.RED);
		logout.setForeground(Color.WHITE);
		logout.setFocusPainted(false);
		fpanel.add(logout);
		logout.addActionListener(this);

		ImageIcon farm = new ImageIcon("LOF.JPG");
		JLabel alo = new JLabel(farm);
		alo.setBounds(6,6,38,38 );
		fpanel.add(alo,BorderLayout.CENTER);

		addb = new JButton("Add a Product");
		addb.setBounds(430, 375, 150, 30);
		addb.setBackground(new Color(255, 165, 47));
		addb.addActionListener(this);

		remb = new JButton("Remove Item");
		remb.setBounds(170, 375, 110, 30);
		remb.setBackground(new Color(255, 165, 47));
		remb.addActionListener(this);


		String[] items = {"No selected","Item 1", "Item 2", "Item 3"};
		cartList = new JComboBox<>();
		cartList.setBounds(60, 125, 90, 25);
		cartList.setBackground(new Color(255, 165, 47));
		populateCartList(cphoneno);

		productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBounds(60, 155, 635, 200);
		productPanel.setBackground(new Color(255,255,204));

		JLabel productLabel = new JLabel("Product Name");
		productLabel.setFont(new Font("Osword", Font.BOLD, 15));
		productLabel.setForeground(Color.BLACK);

		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setFont(new Font("Osword", Font.BOLD, 15));
		quantityLabel.setForeground(Color.BLACK);

		JLabel listedOnLabel = new JLabel("Purchased On");
		listedOnLabel.setFont(new Font("Osword", Font.BOLD, 15));
		listedOnLabel.setForeground(Color.BLACK);

		JLabel lastDateLabel = new JLabel("Delivery Date");
		lastDateLabel.setFont(new Font("Osword", Font.BOLD, 15));
		lastDateLabel.setForeground(Color.BLACK);

		JLabel fprice = new JLabel("Final Price");
		fprice.setFont(new Font("Osword", Font.BOLD, 15));
		fprice.setForeground(Color.BLACK);

		productLabel.setBounds(20, 20, 120, 20);
		quantityLabel.setBounds(20, 55, 120, 20);
		listedOnLabel.setBounds(20, 90, 120, 20);
		lastDateLabel.setBounds(20, 125, 120, 20);
		fprice.setBounds(20, 160, 120, 20);

		productPanel.add(productLabel);
		productPanel.add(quantityLabel);
		productPanel.add(listedOnLabel);
		productPanel.add(lastDateLabel);
		productPanel.add(fprice);

		productNameArea = new JTextArea();
		productNameArea.setEditable(false);
		productNameArea.setEnabled(true);
		productNameArea.setForeground(Color.BLACK);
		productNameArea.setFont(new Font("Arial", Font.PLAIN, 14));
		productNameArea.setBorder(new LineBorder(Color.BLACK));

		quantityArea = new JTextArea();
		quantityArea.setEditable(false);
		quantityArea.setForeground(Color.BLACK);
		quantityArea.setBorder(new LineBorder(Color.BLACK));

		listedOnArea = new JTextArea();
		listedOnArea.setEditable(false);
		listedOnArea.setForeground(Color.BLACK);
		listedOnArea.setBorder(new LineBorder(Color.BLACK));

		lastDateArea = new JTextArea();
		lastDateArea.setEditable(false);
		lastDateArea.setForeground(Color.BLACK);
		lastDateArea.setBorder(new LineBorder(Color.BLACK));

		fpriceArea = new JTextArea();
		fpriceArea.setEditable(false);
		fpriceArea.setForeground(Color.BLACK);
		fpriceArea.setBorder(new LineBorder(Color.BLACK));

		productNameArea.setBounds(140, 20, 200, 20);
		quantityArea.setBounds(140, 55, 200, 20);
		listedOnArea.setBounds(140, 90, 200, 20);
		fpriceArea.setBounds(140, 160, 200, 20);
		lastDateArea.setBounds(140, 125, 200, 20);

		productPanel.add(productNameArea);
		productPanel.add(quantityArea);
		productPanel.add(listedOnArea);
		productPanel.add(lastDateArea);
		productPanel.add(fpriceArea);


		JLabel sum = new JLabel("YOUR CART");
		sum.setBounds(10,50,300,50);
		sum.setFont(new Font ("Osword",Font.BOLD,32));
		sum.setForeground(new Color(2,1,255));
		f4.add(sum);

		JLabel head = new JLabel("FARMANDI");
		head.setBounds(50,5,300,40);
		head.setFont(new Font ("Osword",Font.BOLD,28));
		head.setForeground(new Color(255,165,47));
		fpanel.add(head,BorderLayout.CENTER);


		f4.add(addb);
		f4.add(remb);
		f4.add(cartList);
		f4.add(productPanel);

		f4.setLocation(300, 125);
		f4.setLayout(null);
		f4.setVisible(true);
		f4.getContentPane().setBackground(new Color(204, 255, 153));
		f4.setSize(800, 500);
	}

	private void populateCartList(String cphoneno) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmandi", "root", "Rohan@1234");
			String query = "SELECT cp.productid, p.productname\n" +
					"FROM customer_product cp\n" +
					"JOIN product p ON cp.productid = p.productid\n" +
					"WHERE cp.cphoneno = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cphoneno); // Set the cphoneno parameter
			ResultSet resultSet = statement.executeQuery();

			cartList.removeAllItems(); // Clear existing items in the cartList

			while (resultSet.next()) {
				String productName = resultSet.getString("productname");
				cartList.addItem(productName);
			}

			cartList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selectedProductName = cartList.getSelectedItem().toString();
					populateTextArea(selectedProductName);
				}
			});

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void populateTextArea(String selectedProductName) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmandi", "root", "Rohan@1234");
			String query = "SELECT cp.quantity, cp.purchaseon, cp.delivery_date, (cp.quantity * cp.sellprice) AS quantityTimesSellPrice " +
					"FROM customer_product cp " +
					"JOIN product p ON cp.productid = p.productid " +
					"WHERE cp.cphoneno = ? AND p.productname = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cphoneno);
			statement.setString(2, selectedProductName);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String quantity = resultSet.getString("quantity");
				String purchaseon = resultSet.getString("purchaseon");
				String deliveryDate = resultSet.getString("delivery_date");
				String quantityTimesSellPrice = resultSet.getString("quantityTimesSellPrice");

				quantityArea.setText(quantity);
				listedOnArea.setText(purchaseon);
				lastDateArea.setText(deliveryDate);
				fpriceArea.setText(quantityTimesSellPrice);
				productNameArea.setText(selectedProductName);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeSelectedItem(String selectedProductName) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmandi", "root", "Rohan@1234");
			String deleteQuery = "DELETE FROM customer_product cp " +
					"WHERE cp.cphoneno = ? AND cp.productid = (SELECT p.productid FROM product p WHERE p.productname = ?)";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setString(1, cphoneno);
			deleteStatement.setString(2, selectedProductName);
			int rowsDeleted = deleteStatement.executeUpdate();
			if (rowsDeleted > 0) {
				JOptionPane.showMessageDialog(f4, "Item removed successfully.");
				cartList.removeItem(selectedProductName);
			}
			deleteStatement.close();
			connection.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add a Product")) {
			f4.setVisible(false);
			new Homepage(cphoneno);
		} else if (e.getActionCommand().equals("Remove Item")) {
			String selectedProductName = (String) cartList.getSelectedItem();
			if (selectedProductName != null) {
				removeSelectedItem(selectedProductName);
			} else {
				JOptionPane.showMessageDialog(f4, "Please select an item to remove.");
			}
		} else if (e.getActionCommand().equals("LogOut")) {
			f4.setVisible(false);
			new loginPage();
		}
	}

	public void main(String args[]) {
		String cphoneno = this.cphoneno;
		new cart(cphoneno);
	}
}