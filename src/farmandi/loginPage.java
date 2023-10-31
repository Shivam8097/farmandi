package farmandi;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class loginPage extends JFrame implements ActionListener
{
	JFrame f1=new JFrame("Framandi");
	JLabel l11=new JLabel("LOGIN ID");
	JLabel l12=new JLabel("PASSWORD");
	
	private JTextField t11=new JTextField();
	private JPasswordField p11=new JPasswordField();
	
	JRadioButton r11 = new JRadioButton("Farmer");
	JRadioButton r12 = new JRadioButton("Customer");
	
	JButton b11=new JButton("Login");
	JButton b12=new JButton("Create new Login");
	private Connection connection;
	
	loginPage()
	{
		JLabel title = new JLabel ("FARMANDI");
		
		title.setBounds(300,50,300,50);
		title.setFont(new Font ("Osword",Font.BOLD,28));
		title.setForeground(new Color(255,165,0));

		String url = "jdbc:mysql://localhost:3306/farmandi";
		String username = "root";
		String password = "Rohan@1234";

		try 
		{
			connection = DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		r11.setBounds(400,140,100,30);
		r11.setSelected(true);
		r11.setContentAreaFilled(false);
		r11.setFont(new Font ("Osword",Font.BOLD,16));
		r11.setBorderPainted(false);
		r12.setBounds(250,140,100,30);
		r12.setContentAreaFilled(false);
		r12.setFont(new Font ("Osword",Font.BOLD,16));
		r12.setBorderPainted(false);
		
		l11.setBounds(250,200,150,30);
		l11.setFont(new Font ("Arial",Font.BOLD,16));
		l11.setForeground(Color.WHITE);
		l12.setBounds(250,240,150,30);
		l12.setFont(new Font ("Arial",Font.BOLD,16));
		l12.setForeground(Color.WHITE);
		
		t11.setBounds(375,200,150,30);
		p11.setBounds(375,240,150,30);
		
		b11.setBounds(335,300,80,25);
		b12.setBounds(260,350,250,25);
		b12.setContentAreaFilled(false);
		b12.setBorderPainted(false);
		b12.setForeground(Color.WHITE);
		b12.setFont(new Font ("Osword",Font.BOLD,16));
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(r11);
		bg.add(r12);
		
		f1.add(r11);
		f1.add(r12);
		
		f1.add(l11);
		f1.add(l12);
		
		f1.add(t11);
		f1.add(p11);
		
		f1.add(b11);
		f1.add(b12);
		
		f1.add(title); 
		
		b11.addActionListener(this);
		b12.addActionListener(this);
		
		ImageIcon logo = new ImageIcon("C:\\farmandi\\src\\IMAGES\\LOGOX.JPG");
		JLabel logox = new JLabel(logo);
		logox.setBounds(15,15,125,125 );
		f1.add(logox);
		
		ImageIcon bac = new ImageIcon("C:\\farmandi\\src\\IMAGES\\3SLIDE2.JPG");
		JLabel image = new JLabel(bac);
		image.setBounds(0,0,800,500);
		f1.add(image);
		
		f1.getContentPane().setBackground(new Color(63,121,35));
		f1.setLocation(300,125);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setResizable(false);
		f1.setSize(800,500);
	}


	private boolean authenticateFarmer(String loginId, String password) {
		String sql = "SELECT fpassword FROM farmer WHERE fphoneno = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String storedPassword = resultSet.getString("fpassword");
				return password.equals(storedPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean authenticateCustomer(String loginId, String password) {
		String sql = "SELECT cpassword FROM customer WHERE cphoneno = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String storedPassword = resultSet.getString("cpassword");
				return password.equals(storedPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")) {
			String loginId = t11.getText();
			char[] pass = p11.getPassword();
			String password = new String(pass);

			if (r11.isSelected()) {
				boolean isFarmerAuthenticated = authenticateFarmer(loginId, password);
				if (isFarmerAuthenticated) {
					String fphoneno = t11.getText();
					Fhomepage homepage = new Fhomepage(fphoneno);
					f1.dispose();

				} else {
					JOptionPane.showMessageDialog(f1, "Login Id or Password is invalid.");
				}
			} else if (r12.isSelected()) {
				boolean isCustomerAuthenticated = authenticateCustomer(loginId, password);
				if (isCustomerAuthenticated) {
					String cphoneno = t11.getText();
					Homepage homepage = new Homepage(cphoneno);
					f1.dispose();
				} else {
					JOptionPane.showMessageDialog(f1, "Login Id or Password is invalid.");
				}
			} else {
				JOptionPane.showMessageDialog(f1, "Please select either 'Farmer' or 'Customer'.");
			}
		} else if (e.getActionCommand().equals("Create new Login")) {
			if (r11.isSelected()) {
				f1.setVisible(false);
				new farmandi.fcreateacc();
			} else if (r12.isSelected()) {
				f1.setVisible(false);
				new farmandi.createacc();
			} else {
				JOptionPane.showMessageDialog(f1, "Please select either 'Farmer' or 'Customer' to create a new login.");
			}
		}
	}

	public static void main(String[] args)
	{
	new loginPage();
	}
}
	
	
	
	
	