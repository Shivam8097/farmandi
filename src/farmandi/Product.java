package farmandi;
import javax.swing.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Product extends JFrame implements ActionListener
{
    private JFrame productFrame;
    private String fphoneno;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public Product(String fphoneno) {
        productFrame = new JFrame("Product List");
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setLayout(null);
        this.fphoneno= fphoneno;

        Vector<Vector<String>> rowData = new Vector<>();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Product ID");
        columnNames.add("Product");
        columnNames.add("Shelf Life");

        Vector<String> product1 = new Vector<>();
        product1.add("1");
        product1.add("Mango");
        product1.add("10 days");
        rowData.add(product1);

        Vector<String> product2 = new Vector<>();
        product2.add("2");
        product2.add("Orange");
        product2.add("7 days");
        rowData.add(product2);

        Vector<String> product3 = new Vector<>();
        product3.add("3");
        product3.add("Onion");
        product3.add("28 days");
        rowData.add(product3);

        Vector<String> product4 = new Vector<>();
        product4.add("4");
        product4.add("Potato");
        product4.add("60 days");
        rowData.add(product4);

        Vector<String> product5 = new Vector<>();
        product5.add("5");
        product5.add("Wheat");
        product5.add("365 days");
        rowData.add(product5);

        Vector<String> product6 = new Vector<>();
        product6.add("6");
        product6.add("Garlic");
        product6.add("150 days");
        rowData.add(product6);

        tableModel = new DefaultTableModel(rowData, columnNames);
        productTable = new JTable(tableModel);
        productTable.setAutoCreateRowSorter(true);

        productTable.setBounds(10, 10, 480, 200);

        productFrame.add(productTable);

        backButton = new JButton("Back");
        backButton.setBounds(10, 220, 100, 30);
        productFrame.add(backButton);
		backButton.addActionListener(this);

        productFrame.setSize(500, 300);
        productFrame.setLocationRelativeTo(null);
        productFrame.setVisible(true);
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Back"))
		{
			productFrame.setVisible(false);
            String fphoneno = this.fphoneno;
				new Fhomepage(fphoneno);
		}
		else
		{
			
		}
		
	}
    public void main(String[] args) {
        String fphoneno = this.fphoneno;
        new Product(fphoneno);
    }
}