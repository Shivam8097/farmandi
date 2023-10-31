package farmandi;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class createPassword extends JFrame implements ActionListener 
{
    private JFrame passwordFrame;
    JLabel passwordLabel, confirmPasswordLabel;
    JPasswordField passwordField, confirmPasswordField;
    JButton submitPasswordButton;
    createPassword() {
        passwordFrame = new JFrame("Create Password");

        passwordLabel = new JLabel("Create Password:");
        passwordLabel.setBounds(50, 50, 150, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordFrame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 50, 150, 30);
        passwordFrame.add(passwordField);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 100, 150, 30);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordFrame.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250, 100, 150, 30);
        passwordFrame.add(confirmPasswordField);

        submitPasswordButton = new JButton("Submit Password");
        submitPasswordButton.setBackground(new Color(50, 205, 50));
        submitPasswordButton.setBounds(150, 150, 150, 30);
        submitPasswordButton.addActionListener(this);
        passwordFrame.add(submitPasswordButton);

        passwordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordFrame.getContentPane().setBackground(new Color(192, 192, 192));
        passwordFrame.setLocation(450, 225);
        passwordFrame.setSize(500, 250);
        passwordFrame.setLayout(null);
        passwordFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit Password")) {
            char[] passwordChars = passwordField.getPassword();
            char[] confirmPasswordChars = confirmPasswordField.getPassword();
            String password = new String(passwordChars);
            String confirmPassword = new String(confirmPasswordChars);
            if (password.equals(confirmPassword)) 
			{
                passwordFrame.setVisible(false);
				new farmandi.loginPage();
            } 
			else
			{
                JOptionPane.showMessageDialog(passwordFrame, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        }
    }
    public static void main(String[] args) {
        new createPassword();
    }
}