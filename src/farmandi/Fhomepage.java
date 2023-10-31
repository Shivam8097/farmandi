package farmandi;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class Fhomepage extends JFrame implements ActionListener {
    JFrame f2;
    private static String fphoneno;
    JTextField searchf;
    JButton searchb;
    JPanel fpanel, spanel;
    JTextField prodt, shlt, quant, prit;

    private Connection connection;

    Fhomepage(String fphoneno) {
        f2 = new JFrame("Farmandi Home Page");

        this.fphoneno = fphoneno;

        String url = "jdbc:mysql://localhost:3306/farmandi";
        String username = "root";
        String password = "Rohan@1234";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fpanel = new JPanel();
        fpanel.setBounds(0, 0, 800, 50);
        fpanel.setLayout(null);
        fpanel.setBackground(new Color(76, 153, 0));
        f2.add(fpanel, BorderLayout.NORTH);

        JLabel wel = new JLabel("Welcome ");
        wel.setFont(new Font("Osword", Font.BOLD, 28));
        wel.setForeground(new Color(255, 165, 47));
        wel.setBounds(25, 35, 350, 80);
        fpanel.add(wel, BorderLayout.NORTH);

        spanel = new JPanel();
        spanel.setBounds(60, 115, 635, 275);
        spanel.setLayout(null);
        spanel.setBackground(new Color(255, 255, 255));
        f2.add(spanel, BorderLayout.CENTER);

        JLabel mos = new JLabel("ADD A PRODUCT");
        mos.setFont(new Font("Osword", Font.BOLD, 25));
        mos.setForeground(new Color(255, 165, 47));
        mos.setBounds(5, 5, 350, 25);
        spanel.add(mos, BorderLayout.NORTH);

        JLabel prodname = new JLabel("PRODUCT NAME:");
        prodname.setFont(new Font("Osword", Font.BOLD, 15));
        prodname.setForeground(Color.BLACK);
        prodname.setBounds(40, 55, 200, 15);
        spanel.add(prodname, BorderLayout.NORTH);

        prodt = new JTextField();
        prodt.setBounds(350, 55, 150, 25);
        spanel.add(prodt);

        JLabel shl = new JLabel("Phone Number");
        shl.setFont(new Font("Osword", Font.BOLD, 15));
        shl.setForeground(Color.BLACK);
        shl.setBounds(40, 105, 200, 15);
        spanel.add(shl, BorderLayout.EAST);

        shlt = new JTextField();
        shlt.setBounds(350, 105, 150, 25);
        spanel.add(shlt);

        JLabel quan = new JLabel("QUANTITY(in quintal):");
        quan.setFont(new Font("Osword", Font.BOLD, 15));
        quan.setForeground(Color.BLACK);
        quan.setBounds(40, 135, 200, 15);
        spanel.add(quan, BorderLayout.NORTH);

        quant = new JTextField();
        quant.setBounds(350, 135, 150, 25);
        spanel.add(quant);

        JLabel pri = new JLabel("QUOTED PRICE:");
        pri.setFont(new Font("Osword", Font.BOLD, 15));
        pri.setForeground(Color.BLACK);
        pri.setBounds(40, 165, 200, 15);
        spanel.add(pri, BorderLayout.NORTH);

        prit = new JTextField();
        prit.setBounds(350, 165, 150, 25);
        spanel.add(prit);

        JButton list = new JButton("PRODUCT LIST");
        list.setBounds(505, 55, 125, 25);
        list.setBackground(new Color(255, 255, 185));
        list.setForeground(Color.BLACK);
        spanel.add(list);
        list.addActionListener(this);

        JButton lis = new JButton("CONFORM");
        lis.setBounds(305, 200, 125, 25);
        lis.setBackground(new Color(255, 255, 185));
        lis.setForeground(Color.BLACK);
        lis.addActionListener(this);
        spanel.add(lis);

        JButton sumary = new JButton("Sell History");
        sumary.setBounds(575, 8, 100, 30);
        sumary.setBackground(new Color(255, 255, 185));
        sumary.setForeground(Color.BLACK);
        fpanel.add(sumary);
        sumary.addActionListener(this);
        sumary.setToolTipText("Sell History");

        JButton logout = new JButton("LogOut");
        logout.setBounds(710, 2, 75, 30);
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);
        fpanel.add(logout);
        logout.setFocusPainted(false);
        logout.addActionListener(this);

        JLabel l21 = new JLabel("Welcome Rohan !");
        l21.setBounds(10, 40, 500, 100);
        l21.setFont(new Font("Osword", Font.BOLD, 28));
        f2.add(l21);

        ImageIcon farm = new ImageIcon("LOF.JPG");
        JLabel falo = new JLabel(farm);
        falo.setBounds(6, 6, 38, 38);
        fpanel.add(falo, BorderLayout.CENTER);

        JLabel head = new JLabel("FARMANDI");
        head.setBounds(50, 5, 300, 40);
        head.setFont(new Font("Osword", Font.BOLD, 28));
        head.setForeground(new Color(255, 165, 47));
        fpanel.add(head, BorderLayout.CENTER);

        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setBackground(new Color(204, 255, 153));
        f2.setLocation(350, 125);
        f2.setSize(800, 500);
        f2.setLayout(null);
        f2.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LogOut")) {
            f2.setVisible(false);
            new loginPage();
        } else if (e.getActionCommand().equals("Sell History")) {
            f2.dispose();
            new farmandi.summary(fphoneno);
        }
        else if (e.getActionCommand().equals("PRODUCT LIST")) {
            f2.setVisible(false);
            new farmandi.Product(fphoneno);
        }
        else if (e.getActionCommand().equals("CONFORM")) {
            String productName = prodt.getText();
            String quantity = quant.getText();
            String quotedPrice = prit.getText();
            String farmerPhone = this.fphoneno;

            String productFarmerSql = "INSERT INTO product_farmer (productid, fphoneno, listedon, lastdate, quantity_instock, quotedprice) VALUES (?, ?, ?, ?, ?, ?)";

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            Date listedOnDate = calendar.getTime();

            calendar.add(Calendar.DAY_OF_YEAR, 5);
            Date lastDate = calendar.getTime();

            try (PreparedStatement productFarmerPreparedStatement = connection.prepareStatement(productFarmerSql)) {
                productFarmerPreparedStatement.setInt(1, Integer.parseInt(productName));
                productFarmerPreparedStatement.setString(2, farmerPhone);
                productFarmerPreparedStatement.setDate(3, new java.sql.Date(listedOnDate.getTime()));
                productFarmerPreparedStatement.setDate(4, new java.sql.Date(lastDate.getTime()));
                productFarmerPreparedStatement.setInt(5, Integer.parseInt(quantity) * 100);
                productFarmerPreparedStatement.setDouble(6, Double.parseDouble(quotedPrice));
                //productFarmerPreparedStatement.setDouble(7, quotedPrice - (0.1 * quotedPrice));


                int rowsInserted = productFarmerPreparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Product and Farmer association added successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Product and Farmer association insertion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main (String[] args) {
        new Fhomepage(fphoneno);
    }
}