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

class summary extends JFrame implements ActionListener {
	JFrame f4 = new JFrame("summary");
	private static String fphoneno;
	JButton remb,  buy;
	JComboBox<String> cartList;
	JPanel fpanel, productPanel;
	JTextArea productNameArea, quantityArea, listedOnArea, lastDateArea, fpriceArea, soldArea, unsoldArea;

	summary(String fphoneno) {
		fpanel = new JPanel();
		fpanel.setBounds(0, 0, 800, 50);
		fpanel.setLayout(null);
		fpanel.setBackground(new Color(76, 153, 0));
		f4.add(fpanel, BorderLayout.NORTH);

		this.fphoneno = fphoneno;

		ImageIcon image13 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\LOF.JPG");
		JLabel calo = new JLabel(image13);
		calo.setBounds(6, 6, 38, 38);
		fpanel.add(calo, BorderLayout.CENTER);

		JButton logout = new JButton("LogOut");
		logout.setBounds(690, 2, 100, 30);
		logout.setBackground(Color.RED);
		logout.setForeground(Color.WHITE);
		fpanel.add(logout);
		logout.setFocusPainted(false);
		logout.addActionListener(this);

		ImageIcon farm = new ImageIcon("LOF.JPG");
		JLabel falo = new JLabel(farm);
		falo.setBounds(6, 6, 38, 38);
		fpanel.add(falo, BorderLayout.CENTER);

		remb = new JButton("Remove Item");
		remb.setBounds(170, 375, 110, 30);
		remb.setBackground(new Color(255, 165, 47));
		remb.addActionListener(this);



		buy = new JButton("ADD A PRODUCT");
		buy.setBounds(430, 375, 150, 30);
		buy.setBackground(new Color(255, 165, 47));
		buy.addActionListener(this);

		cartList = new JComboBox<>();
		cartList.setBounds(60, 125, 90, 25);
		cartList.setBackground(new Color(255, 165, 47));
		populateCartList(fphoneno);

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

		JLabel listedOnLabel = new JLabel("Listed On");
		listedOnLabel.setFont(new Font("Osword", Font.BOLD, 15));
		listedOnLabel.setForeground(Color.BLACK);

		JLabel lastDateLabel = new JLabel("Last Date");
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

		JLabel sum = new JLabel("SUMMARY");
		sum.setBounds(10, 50, 300, 50);
		sum.setFont(new Font("Osword", Font.BOLD, 32));
		sum.setForeground(new Color(2, 1, 255));
		f4.add(sum);

		JLabel head = new JLabel("FARMANDI");
		head.setBounds(50, 5, 300, 40);
		head.setFont(new Font("Osword", Font.BOLD, 28));
		head.setForeground(new Color(255, 165, 47));
		fpanel.add(head, BorderLayout.CENTER);

		cartList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedProductName = cartList.getSelectedItem().toString();
				populateTextArea(selectedProductName);
				if (selectedProductName != null) {
					populateCartList(fphoneno);
				}
			}
		});

		f4.add(remb);
		f4.add(buy);
		f4.add(cartList);
		f4.add(productPanel);
		f4.getContentPane().setBackground(new Color(204, 255, 153));
		f4.setLocation(300, 125);
		f4.setLayout(null);
		f4.setVisible(true);
		f4.setSize(800, 500);
	}

	private void populateCartList(String fphoneno) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmandi", "root", "Rohan@1234");
			String query = "SELECT pf.productid, p.productname\n" +
					"FROM product_farmer pf\n" +
					"JOIN product p ON pf.productid = p.productid\n" +
					"WHERE pf.fphoneno = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, fphoneno); // Set the fphoneno parameter
			ResultSet resultSet = statement.executeQuery();

			cartList.removeAllItems(); // Clear existing items in the cartList

			while (resultSet.next()) {
				String productName = resultSet.getString("productname");
				cartList.addItem(productName);
			}

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
			String query = "SELECT pf.quantity_instock, pf.listedon, pf.lastdate, (pf.quantity_instock * pf.finalprice) AS quantityInStockTimesFinalPrice " +
					"FROM product_farmer pf " +
					"JOIN product p ON pf.productid = p.productid " +
					"WHERE pf.fphoneno = ? AND p.productname = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, fphoneno);
			statement.setString(2, selectedProductName);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String quantityInStock = resultSet.getString("quantity_instock");
				String listedOn = resultSet.getString("listedon");
				String lastDate = resultSet.getString("lastdate");
				String quantityInStockTimesFinalPrice = resultSet.getString("quantityInStockTimesFinalPrice");

				quantityArea.setText(quantityInStock);
				listedOnArea.setText(listedOn);
				lastDateArea.setText(lastDate);
				fpriceArea.setText(quantityInStockTimesFinalPrice);
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
			String deleteQuery = "DELETE FROM product_farmer pf " +
					"WHERE pf.fphoneno = ? AND pf.productid = (SELECT p.productid FROM product p WHERE p.productname = ?)";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setString(1, fphoneno);
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
		if (e.getActionCommand().equals("ADD A PRODUCT")) {
			f4.setVisible(false);
			new Fhomepage(fphoneno);
		} else if (e.getActionCommand().equals("Remove All Item")) {
			// Implement this method to remove all items
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

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new summary(fphoneno));
	}
}