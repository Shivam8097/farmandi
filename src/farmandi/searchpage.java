package farmandi;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class searchpage extends JFrame implements ActionListener {
	JFrame f2;
	private String cphoneno;
	private int actionCommand;
	JTextField searchf;
	JPanel fpanel, productPanel;
	JComboBox<String> dist;
	JComboBox<Integer> rate, quantity;

	JTextArea fpriceArea,store;

	JButton ok;
	Connection connection;

	searchpage(String cphoneno, int actionCommand) {
		f2 = new JFrame("Farmandi Search Page");

		this.actionCommand = actionCommand;

		this.cphoneno = cphoneno;

		fpanel = new JPanel();
		fpanel.setBounds(0, 0, 800, 50);
		fpanel.setLayout(null);
		fpanel.setBackground(new Color(76, 153, 0));
		f2.add(fpanel, BorderLayout.NORTH);
		ImageIcon image13= new ImageIcon("C:\\farmandi\\src\\IMAGES\\LOF.JPG");
		JLabel tao = new JLabel(image13);
		tao.setBounds(6, 6, 38, 38);
		fpanel.add(tao, BorderLayout.CENTER);

		try {
			String url = "jdbc:mysql://localhost:3306/farmandi";
			String username = "root";
			String password = "Rohan@1234";
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JButton cart = new JButton(" ");
		cart.setBounds(650,8,30,30);
		cart.setBackground(new Color(255, 165, 47));
		cart.setForeground(Color.BLACK);
		fpanel.add(cart);
		cart.addActionListener(this);
		cart.setToolTipText("cart");
		cart.setOpaque(false);
		cart.setFocusPainted(false);

		ImageIcon image7 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\cartr.jpg");
		JLabel label7 = new JLabel(image7);
		label7.setBounds(650,8,30,30);
		fpanel.add(label7);

		JButton logout = new JButton("LogOut");
		logout.setBounds(690 ,2, 90, 30);
		logout.setBackground(Color.RED);
		logout.setForeground(Color.WHITE);
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		fpanel.add(logout);
		logout.addActionListener(this);

		productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBounds(60, 155, 635, 250);
		productPanel.setBackground(new Color(255,255,204));

		JLabel productLabel = new JLabel("Product ID:");
		productLabel.setFont(new Font("Osword", Font.BOLD, 15));

		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Osword", Font.BOLD, 15));
		quantityLabel.setBackground(new Color(255, 165, 47));
		quantityLabel.setForeground(Color.BLACK);

		JLabel listedOnLabel = new JLabel("Rate:");
		listedOnLabel.setFont(new Font("Osword", Font.BOLD, 15));
		listedOnLabel.setBackground(new Color(255, 165, 47));
		listedOnLabel.setForeground(Color.BLACK);

		JLabel lastDateLabel = new JLabel("Storing Period:");
		lastDateLabel.setFont(new Font("Osword", Font.BOLD, 15));
		lastDateLabel.setBackground(new Color(255, 165, 47));
		lastDateLabel.setForeground(Color.BLACK);

		JLabel fprice = new JLabel("Final Price:");
		fprice.setFont(new Font("Osword", Font.BOLD, 15));
		fprice.setBackground(new Color(255, 165, 47));
		fprice.setForeground(Color.BLACK);

		JLabel district = new JLabel("District");
		district.setFont(new Font("Osword", Font.BOLD, 15));

		ok = new JButton("BUY");
		ok.addActionListener(this);
		ok.setBackground(new Color(255, 165, 47));
		ok.setEnabled(false);

		JLabel hon = new JLabel("Product");
		hon.setFont(new Font("Osword", Font.BOLD, 28));
		hon.setForeground(new Color(0,0,0));
		hon.setBounds(10, 50, 300, 50);
		f2.add(hon, BorderLayout.NORTH);

		quantityLabel.setBounds(30, 80, 100, 20);
		listedOnLabel.setBounds(30, 40, 100, 20);
		lastDateLabel.setBounds(30, 120, 150, 20);
		fprice.setBounds(30, 160, 100, 20);
		ok.setBounds(200, 200, 100, 25);

		productPanel.add(quantityLabel);
		productPanel.add(listedOnLabel);
		productPanel.add(lastDateLabel);
		productPanel.add(fprice);
		productPanel.add(ok);

		fpriceArea = new JTextArea();
		fpriceArea.setEditable(false);
	

		store = new JTextArea();
		store.setEditable(false);

		String shelfLife = getShelfLifeForProduct(actionCommand);
		store.setText(shelfLife);

		String dis[] = {"Select", "Nasik", "Nagpur"};
		dist = new JComboBox(dis);

		Integer rateprice[] = {0, 2, 5}; // Set initial rate to 0
		rate = new JComboBox(rateprice);

		Integer quantity5[] = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100}; // Set initial quantity to 0
		quantity = new JComboBox(quantity5);

		rate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedFinalPrice = rate.getSelectedItem();
				if (selectedFinalPrice != null) {
					int finalPrice = (int) selectedFinalPrice;
					if (finalPrice > 0) {
						int maxQuantity = 100;
						int optionsCount = maxQuantity / 5;
						Integer[] quantityOptions = new Integer[optionsCount];
						for (int i = 0; i < optionsCount; i++) {
							quantityOptions[i] = (i + 1) * 5;
						}
						DefaultComboBoxModel<Integer> quantityModel = new DefaultComboBoxModel<>(quantityOptions);
						quantity.setModel(quantityModel);
					}
				}
			}
		});

		quantity.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				calculateAndDisplayFinalPrice();
				updateBuyButtonState();
			}
		});

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedFinalPrice = rate.getSelectedItem();
				Object selectedQuantity = quantity.getSelectedItem();

				if (selectedFinalPrice != null && selectedQuantity != null) {
					int finalPrice = (int) selectedFinalPrice;
					int quantityValue = (int) selectedQuantity;

					if (finalPrice > 0) {
						int productFarmerId = getProductFarmerIdForRate(finalPrice);
						if (productFarmerId > 0) {
							storeSelectedRateAndQuantity(productFarmerId);
						} else {
							JOptionPane.showMessageDialog(null, "Failed to determine product farmer ID for the selected rate.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a valid rate.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select both rate and quantity before making a purchase.");
				}
			}
		});

		store.setBounds(220, 120, 150, 20);
		fpriceArea.setBounds(220, 160, 100, 20);
		rate.setBounds(220, 40, 100, 20);
		quantity.setBounds(220, 80, 100, 20);

		productPanel.add(fpriceArea);
		productPanel.add(rate);
		productPanel.add(quantity);
		productPanel.add(store);

		f2.add(productPanel);

		ImageIcon farm = new ImageIcon("LOF.JPG");
		JLabel falo = new JLabel(farm);
		falo.setBounds(6, 6, 38, 38);
		fpanel.add(falo, BorderLayout.CENTER);

		JLabel head = new JLabel("FARMANDI");
		head.setBounds(50, 5, 300, 40);
		head.setFont(new Font("Osword", Font.BOLD, 28));
		head.setForeground(new Color(255, 165, 47));
		fpanel.add(head, BorderLayout.CENTER);
		updateFinalPriceComboBox();

		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.getContentPane().setBackground(new Color(204, 255,153));
		f2.setLocation(300, 125);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setSize(800, 500);
		f2.setLayout(null);
		f2.setVisible(true);
	}

	private void updateBuyButtonState() {
		Object selectedRate = rate.getSelectedItem();
		Object selectedQuantity = quantity.getSelectedItem();

		boolean enableBuyButton = (selectedRate != null && (int) selectedRate > 0 && selectedQuantity != null);
		ok.setEnabled(enableBuyButton);
	}

	private int getProductFarmerIdForRate(int rate) {
		int selectedProduct = actionCommand;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT product_farmer_id FROM product_farmer WHERE productid = ? AND finalprice = ?"
			);
			preparedStatement.setInt(1, selectedProduct);
			preparedStatement.setInt(2, rate);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("product_farmer_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private String getShelfLifeForProduct(int productid) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT shelflife FROM product WHERE productid = ?");
			preparedStatement.setInt(1, productid);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("shelflife");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void calculateAndDisplayFinalPrice() {
		Object selectedRate = rate.getSelectedItem();
		Object selectedQuantity = quantity.getSelectedItem();

		if (selectedRate != null && selectedQuantity != null) {
			int rateValue = (int) selectedRate;
			int quantityValue = (int) selectedQuantity;

			int finalPrice = rateValue * quantityValue;
			fpriceArea.setText(String.valueOf(finalPrice));
		} else {
			fpriceArea.setText("");
		}
	}

	private void updateFinalPriceComboBox() {
		int selectedProduct = actionCommand;

		if (selectedProduct != 0) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT finalprice FROM product_farmer WHERE productid = ?");
				preparedStatement.setInt(1, selectedProduct);
				ResultSet resultSet = preparedStatement.executeQuery();

				List<Integer> finalPrices = new ArrayList<>();
				while (resultSet.next()) {
					finalPrices.add(resultSet.getInt("finalprice"));
				}

				if (!finalPrices.isEmpty()) {
					Integer[] finalPriceArray = finalPrices.toArray(new Integer[0]);

					DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(finalPriceArray);
					rate.setModel(model);
				} else {
					rate.removeAllItems();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void storeSelectedRateAndQuantity(int productFarmerId) {
		int selectedProduct = actionCommand;
		int rateValue = (int) rate.getSelectedItem();
		Object selectedQuantity = quantity.getSelectedItem();

		if (selectedProduct != 0 && rateValue > 0 && selectedQuantity != null) {
			int productid = selectedProduct;
			int cphoneno = Integer.parseInt(this.cphoneno);
			int quantityValue = (int) selectedQuantity;
			int quotedPrice = rateValue;

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 2);
			Date deliveryDate = calendar.getTime();

			java.sql.Date sqlDeliveryDate = new java.sql.Date(deliveryDate.getTime());

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO customer_product (productid, cphoneno, product_farmer_id, purchaseon, delivery_date, quantity, quotedprice, sellprice) " +
								"VALUES (?, ?, ?, NOW(), ?, ?, ?, ?)"
				);
				preparedStatement.setInt(1, productid);
				preparedStatement.setInt(2, cphoneno);
				preparedStatement.setInt(3, productFarmerId);
				preparedStatement.setDate(4, sqlDeliveryDate); // Set the delivery date
				preparedStatement.setInt(5, quantityValue);
				preparedStatement.setInt(6, quotedPrice);
				preparedStatement.setInt(7, quotedPrice);

				int rowsInserted = preparedStatement.executeUpdate();
				if (rowsInserted > 0) {
					JOptionPane.showMessageDialog(null, "Purchase saved successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to save purchase.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred while saving the purchase.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please select a product, rate, and quantity before making a purchase.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("LogOut")) {
			f2.setVisible(false);
			new loginPage();
		}
		if (e.getActionCommand().equals(" ")) {
			f2.setVisible(false);
			new cart(cphoneno);
		}
	}

	public void main(String args[]) {
		String chphoneno = this.cphoneno;
		new searchpage(chphoneno, actionCommand);
	}
}