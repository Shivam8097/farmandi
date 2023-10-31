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
import java.util.List;

public class searchpage extends JFrame implements ActionListener
{
	JFrame f2;
	private String cphoneno,actionCommand;
	JTextField searchf;
	JButton searchb;
	JPanel fpanel,productPanel;
	JComboBox<String> dist;
	JComboBox<Integer> rate,quantity;
	JTextArea fpriceArea,store,prodid;
	Connection connection;

	searchpage(String cphoneno, String actionCommand)
	{
		f2 = new JFrame("Farmandi Search Page");

		this.actionCommand = actionCommand;

		this.cphoneno = cphoneno;

		fpanel = new JPanel();
		fpanel.setBounds(0, 0, 800, 50);
		fpanel.setLayout(null);	
		fpanel.setBackground(new Color(76, 153, 0));
		f2.add(fpanel, BorderLayout.NORTH);

		try {
			String url = "jdbc:mysql://localhost:3306/farmandi";
			String username = "root";
			String password = "Rohan@1234";
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JButton cart = new JButton("Cart");
		cart.setBounds(575,8,70,30);
		cart.setBackground (new Color(255,165,47));
		cart.setForeground(Color.BLACK);
		fpanel.add(cart);
		cart.addActionListener(this);
		cart.setToolTipText("cart");
		
		JButton logout = new JButton("LogOut");
		logout.setBounds(710,2,75,30);
		logout.setBackground (Color.RED);
		logout.setForeground(Color.WHITE);
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		fpanel.add(logout);
		logout.addActionListener(this);
		
		productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBounds(60, 155, 635, 200);
        productPanel.setBackground(new Color(230,221,221));

        JLabel productLabel = new JLabel("Product ID:");
		productLabel.setFont(new Font("Osword", Font.BOLD, 15));
        //productLabel.setForeground(Color.BLACK);
        
		JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Osword", Font.BOLD, 15));
        quantityLabel.setForeground(Color.BLACK);
		
		JLabel listedOnLabel = new JLabel("Rate:");
        listedOnLabel.setFont(new Font("Osword", Font.BOLD, 15));
        listedOnLabel.setForeground(Color.BLACK);
		
		JLabel lastDateLabel = new JLabel("Storing Period:");
		lastDateLabel.setFont(new Font("Osword", Font.BOLD, 15));
        lastDateLabel.setForeground(Color.BLACK);
		
		JLabel fprice = new JLabel("Final Price:");
		fprice.setFont(new Font("Osword", Font.BOLD, 15));
        fprice.setForeground(Color.BLACK);
		
		JLabel district = new JLabel("District");
		district.setFont(new Font("Osword", Font.BOLD, 15));
        //district.setForeground(Color.BLACK);

        productLabel.setBounds(10, 10, 100, 20);
        quantityLabel.setBounds(10, 40, 100, 20);
        listedOnLabel.setBounds(10, 70, 100, 20);
		district.setBounds(10,100,100,20);
        lastDateLabel.setBounds(10, 130, 150, 20);
		fprice.setBounds(10,160,100,20);

        //productPanel.add(productLabel);
        productPanel.add(quantityLabel);
        productPanel.add(listedOnLabel);
        productPanel.add(lastDateLabel);
		productPanel.add(fprice);
		//productPanel.add(district);
		
		fpriceArea = new JTextArea();
		fpriceArea.setEditable(false);
		fpriceArea.setEnabled(false);
		
		store = new JTextArea();
		store.setEditable(false);
		store.setEnabled(false);
		
		String dis [] ={null,"nasik","nagpur"};
		dist = new JComboBox(dis);
		
		Integer rateprice [] ={null,2,5};
		rate = new JComboBox(rateprice);

		Integer quantity5 [] ={null,5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
		quantity = new JComboBox(quantity5);
		
		store.setBounds(200,130,100,20);
		fpriceArea.setBounds(200,160,100,20);
		dist.setBounds(200,100,100,20);
		rate.setBounds(200,70,100,20);
		quantity.setBounds(200,40,100,20);
		
		
		productPanel.add(fpriceArea);
		//productPanel.add(dist);
		productPanel.add(rate);
		productPanel.add(quantity);
		productPanel.add(store);
		
		f2.add(productPanel);
		
		ImageIcon farm = new ImageIcon("LOF.JPG");
		JLabel falo = new JLabel(farm);
		falo.setBounds(6,6,38,38 );
		fpanel.add(falo,BorderLayout.CENTER);
		
		JLabel head = new JLabel("FARMANDI");
		head.setBounds(50,5,300,40);
		head.setFont(new Font ("Osword",Font.BOLD,28));
		head.setForeground(new Color(255,165,47));
		fpanel.add(head,BorderLayout.CENTER);
		updateFinalPriceComboBox();
		
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.getContentPane().setBackground(Color.WHITE);
		f2.setLocation(300,125);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(800,500);
        f2.setLayout(null);
        f2.setVisible(true);
		
	}
	private void updateFinalPriceComboBox() {
		String selectedProduct = actionCommand; // Use the selected product from the actionCommand

		if (selectedProduct != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT finalprice FROM product_farmer WHERE productid IN (SELECT productid FROM product WHERE productname = ?)");
				preparedStatement.setString(1, selectedProduct);
				ResultSet resultSet = preparedStatement.executeQuery();

				List<Integer> finalPrices = new ArrayList<>();
				while (resultSet.next()) {
					finalPrices.add(resultSet.getInt("finalprice"));
				}

				Integer[] finalPriceArray = finalPrices.toArray(new Integer[0]);

				DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(finalPriceArray);
				rate.setModel(model);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("LogOut"))
		{
			f2.setVisible(false);
				new loginPage();
		}
		else
		{
			
		}
		
	}
	public void main(String args[])
	{
		String chphoneno = this.cphoneno;
		new searchpage(chphoneno, actionCommand);
	}
}