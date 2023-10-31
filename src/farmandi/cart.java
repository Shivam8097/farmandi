package farmandi;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

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
		
		JButton logout = new JButton("LogOut");
        logout.setBounds(710, 2, 75, 30);
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);
		logout.setFocusPainted(false);
        fpanel.add(logout);
        logout.addActionListener(this);
		
		ImageIcon farm = new ImageIcon("LOF.JPG");
		JLabel falo = new JLabel(farm);
		falo.setBounds(6,6,38,38 );
		fpanel.add(falo,BorderLayout.CENTER);
		
        addb = new JButton("Buy");
        addb.setBounds(70, 375, 100, 30);
        addb.addActionListener(this);

        remb = new JButton("Remove Item");
        remb.setBounds(170, 375, 110, 30);
        remb.addActionListener(this);
		
		remba = new JButton("Remove All Item");
        remba.setBounds(290, 375, 130, 30);
        remba.addActionListener(this);
		
		buy = new JButton("Add a Product");
        buy.setBounds(430, 375, 150, 30);
        buy.addActionListener(this);
		
        String[] items = {"No selected","Item 1", "Item 2", "Item 3"};
		cartList = new JComboBox<>(items);
		cartList.setBounds(60, 125, 90, 25);
		
		productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBounds(60, 155, 635, 200);
        productPanel.setBackground(new Color(230,221,221));

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
		
		JLabel sold = new JLabel("Sold");
		sold.setFont(new Font("Osword", Font.BOLD, 15));
        sold.setForeground(Color.BLACK);
		
		JLabel unsold = new JLabel("Unsold");
		unsold.setFont(new Font("Osword", Font.BOLD, 15));
        unsold.setForeground(Color.BLACK);

        productLabel.setBounds(10, 10, 100, 20);
        quantityLabel.setBounds(10, 40, 100, 20);
        listedOnLabel.setBounds(10, 70, 100, 20);
        lastDateLabel.setBounds(10, 100, 100, 20);
		fprice.setBounds(10,130,100,20);
		sold.setBounds(10,160,100,20);
		unsold.setBounds(230,160,100,20);

        productPanel.add(productLabel);
        productPanel.add(quantityLabel);
        productPanel.add(listedOnLabel);
        productPanel.add(lastDateLabel);
		productPanel.add(fprice);
		
        productNameArea = new JTextArea();
		productNameArea.setEditable(false);
		productNameArea.setEnabled(false);
        
		quantityArea = new JTextArea();
		quantityArea.setEditable(false);
		quantityArea.setEnabled(false);
        
		listedOnArea = new JTextArea();
        listedOnArea.setEditable(false);
		listedOnArea.setEnabled(false);
		
		lastDateArea = new JTextArea();
		lastDateArea.setEditable(false);
		lastDateArea.setEnabled(false);
		
		fpriceArea = new JTextArea();
		fpriceArea.setEditable(false);
		fpriceArea.setEnabled(false);
		
		soldArea = new JTextArea();
		soldArea.setEditable(false);
		soldArea.setEnabled(false);
		
		unsoldArea = new JTextArea();
		unsoldArea.setEditable(false);
		unsoldArea.setEnabled(false);
		
        productNameArea.setBounds(120, 10, 200, 20);
        quantityArea.setBounds(120, 40, 200, 20);
        listedOnArea.setBounds(120, 70, 200, 20);
        fpriceArea.setBounds(120,130,200,20);
		lastDateArea.setBounds(120, 100, 200, 20);
		soldArea.setBounds(120,160,100,20);
		unsoldArea.setBounds(290,160,100,20);

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
		f4.add(remba);
		f4.add(buy);
        f4.add(cartList);
		f4.add(productPanel);

        f4.setLocation(300, 125);
		f4.setLayout(null);
        f4.setVisible(true);
        f4.setSize(800, 500);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add a Product")) {
			f4.setVisible(false);
			String cphoneno = this.cphoneno;
			new Homepage(cphoneno);
            
        } else if (e.getActionCommand().equals("Remove All Item")) {
            
        }
		else if(e.getActionCommand().equals("Remove Item"))
		{
		}
		else if(e.getActionCommand().equals("LogOut"))
		{
			f4.setVisible(false);
			new farmandi.loginPage();
			
		}
    }

    public void main(String args[]) {
        String cphoneno = this.cphoneno;
		new cart(cphoneno);
    }
}
