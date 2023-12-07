package farmandi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class createacc extends JFrame implements ActionListener
{
	private JFrame f3;
	JLabel nam,mid,las,add,cit,aadh,mob,ema,passwordLabel,confirmPasswordLabel;
	JTextField namt,midt,last,aadht,mobt,emat;
	JTextArea addt;
	JComboBox<String> dist;
	JButton sub;
	String fname,mname,lname,address,aadhar,mobile,email,password,selectedDistrict;
	JPasswordField passwordField,confirmPasswordField ;
	private Connection connection;
	createacc()
	{
		f3 = new JFrame("Account");
		f3.getContentPane().setBackground(new Color(50, 205, 50));


		/*ImageIcon image7 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\customerphotor.jpg");
       JLabel label7 = new JLabel(image7);
               label7.setBounds(475, 50, 250, 330);
               f3.add(label7);*/


        
		JLabel l21 = new JLabel("FARMANDI");
        l21.setBounds(5, 5, 250, 30);
		l21.setFont(new Font ("Osword",Font.BOLD,28));
		l21.setForeground(new Color(255,165,0));
		f3.add(l21);
		f3.add(new JLabel());

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
        
		JButton b31=new JButton("Back");	
		b31.setBounds(700, 5, 80, 30);
		b31.setBackground(new Color(50,205,50));
		b31.setForeground(Color.BLACK);
		b31.addActionListener(this);
		f3.add(b31);
		
		nam = new JLabel("<html>First Name<font color='red'>*</font>:</html>");
		nam.setBounds(50,50,150,30);
		nam.setFont(new Font ("Arial",Font.BOLD,16));
		nam.setForeground(Color.BLACK);
		f3.add(nam);
		
		mid = new JLabel("Middle Name:");
		mid.setBounds(50,100,150,30);
		mid.setFont(new Font ("Arial",Font.BOLD,16));
		mid.setForeground(Color.BLACK);
		f3.add(mid);
		
		las = new JLabel("<html>Last Name<font color='red'>*</font>:</html>");
		las.setBounds(50,150,150,30);
		las.setFont(new Font ("Arial",Font.BOLD,16));
		las.setForeground(Color.BLACK);
		f3.add(las);
		
		add = new JLabel("<html>Address<font color='red'>*</font>:</html>");
		add.setBounds(50,200,150,30);
		add.setFont(new Font ("Arial",Font.BOLD,16));
		add.setForeground(Color.BLACK);
		f3.add(add);
		
		cit = new JLabel("<html>District<font color='red'>*</font>:</html>");
		cit.setBounds(50,250,150,30);
		cit.setFont(new Font ("Arial",Font.BOLD,16));
		cit.setForeground(Color.BLACK);
		f3.add(cit);
		
		aadh = new JLabel("<html>Aadhar<font color='red'>*</font>:</html>");
		aadh.setBounds(50,300,150,30);
		aadh.setFont(new Font ("Arial",Font.BOLD,16));
		aadh.setForeground(Color.BLACK);
		f3.add(aadh);
		
		mob = new JLabel("<html>Mobile Number<font color='red'>*</font>:</html>");
		mob.setBounds(50,350,150,30);
		mob.setFont(new Font ("Arial",Font.BOLD,16));
		mob.setForeground(Color.BLACK);
		f3.add(mob);
		
		ema = new JLabel("<html>Email ID<font color='red'>*</font>:</html>");
		ema.setBounds(50,400,150,30);
		ema.setFont(new Font ("Arial",Font.BOLD,16));
		ema.setForeground(Color.BLACK);
		f3.add(ema);
		
		namt = new JTextField();
		namt.setBounds(250,50,150,30);
		f3.add(namt);

		midt = new JTextField();
		midt.setBounds(250,100,150,30);
		f3.add(midt);
		
		last = new JTextField();
		last.setBounds(250,150,150,30);
		f3.add(last);
		
		addt = new JTextArea();
		addt.setBounds(250,200,150,45);
		addt.setBackground (new Color(225,255,255));
		f3.add(addt);
		
		String district [] = {"Mumbai","Pune","Nagpur","Thane", "Nashik","Aurangabad","Solapur","Amravati","Kolhapur","Sangli","Jalgaon","Akola","Latur","Dhule","Ahmednagar","Chandrapur","Parbhani","Jalna","Bhusawal","Nanded","Satara","Beed","Yavatmal","Osmanabad","Nandurbar","Wardha","Gondia","Hingoli","Washim","Gadchiroli","Bhandara","Sindhudurg","Raigad","Ratnagiri","Palghar","Hinganghat","Pimpri-Chinchwad"};
		dist = new JComboBox<>(district);
		dist.setBounds(250,250,150,30);
		f3.add(dist);
		
		aadht = new JTextField();
		aadht.setBounds(250,300,150,30);
		f3.add(aadht);
		
		mobt = new JTextField();
		mobt.setBounds(250,350,150,30);
		f3.add(mobt);
		
		emat = new JTextField();
		emat.setBounds(250,400,150,30);
		f3.add(emat);

		passwordLabel = new JLabel("Create Password:");
		passwordLabel.setBounds(410, 400, 150, 30);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		f3.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(570, 400, 150, 30);
		f3.add(passwordField);

		confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setBounds(50, 100, 150, 30);
		confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		//f3.add(confirmPasswordLabel);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(250, 100, 150, 30);
		//f3.add(confirmPasswordField);
		
		JButton sub = new JButton("Submit");
		sub.setBackground(new Color(50,205,50));
		sub.setBounds(350,450,120,30);
		sub.addActionListener(this);
		f3.add(sub);
		
		ImageIcon logox = new ImageIcon("LO.JPG");
		JLabel log = new JLabel(logox);
		log.setBounds(0,0,800,550 );
		f3.add(log);
                
                		/*ImageIcon image11 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\cusr.jpg");
		JLabel label11 = new JLabel(image11);
		label11.setBounds(0, 0, 800,550);
		f3.add(label11);*/

                ImageIcon image11 = new ImageIcon("C:\\farmandi\\src\\IMAGES\\cusrr.jpg");
		JLabel label11 = new JLabel(image11);
		label11.setBounds(445, 130, 342, 220);
		f3.add(label11);
                
                
                
		
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f3.getContentPane().setBackground(new Color(255,255,255));
		f3.setLocation(300,125);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.setSize(800,550);
        f3.setLayout(null);
        f3.setVisible(true);
	}
		
	public void actionPerformed (ActionEvent e)
		{
			if(e.getActionCommand().equals("Back"))
			{
				f3.setVisible(false);
				new loginPage();
			}
			else if(e.getActionCommand().equals("Submit"))
			{
				if(namt.getText().isEmpty() || last.getText().isEmpty() || aadht.getText().isEmpty() || mobt.getText().isEmpty() || emat.getText().isEmpty() || passwordField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(f3, "Please fill in all mandatory fields.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					char[] passwordChars = passwordField.getPassword();

					fname = namt.getText();
					mname = midt.getText();
					lname = last.getText();
					address = addt.getText();
					aadhar = aadht.getText();
					selectedDistrict = dist.getSelectedItem().toString();
					mobile = mobt.getText();
					email = emat.getText();
					password = new String(passwordChars);

                    String insertSql = "INSERT INTO customer (cphoneno, cfname, cmname, clname, caddress, cdistrict, caadhar, cemail, cpassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

					try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
						preparedStatement.setLong(1,Long.parseLong(mobile));
						preparedStatement.setString(2, fname);
						preparedStatement.setString(3, mname);
						preparedStatement.setString(4, lname);
						preparedStatement.setString(5, address);
						preparedStatement.setString(6, selectedDistrict);
						preparedStatement.setString(7, aadhar);
						preparedStatement.setString(8, email);
						preparedStatement.setString(9, password);

						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(this, "Data inserted successfully.");
						} else {
							JOptionPane.showMessageDialog(this, "Data insertion failed.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					f3.setVisible(false);
					new loginPage();
				}
			}
			
		}
		
	public static void main(String[] args)
	{
	new createacc();
	}
}
		