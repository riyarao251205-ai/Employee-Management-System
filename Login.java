package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton loginBtn;

    Login() {

        setTitle("Employee Management System - Login");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 248, 250));
        panel.setLayout(null);
        add(panel);

        // Title
        JLabel title = new JLabel("Login");
        title.setBounds(250, 20, 100, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title);

        // Username Label
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(150, 80, 100, 25);
        lblusername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblusername);

        // Username Field
        tfusername = new JTextField();
        tfusername.setBounds(150, 105, 250, 30);
        tfusername.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(tfusername);

        // Password Label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(150, 145, 100, 25);
        lblpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblpassword);

        // Password Field
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 170, 250, 30);
        tfpassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(tfpassword);

        // Login Button
        loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(200, 230, 150, 35);
        loginBtn.setBackground(new Color(52, 152, 219));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder());
        loginBtn.addActionListener(this);

        // Hover Effect
        loginBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(new Color(52, 152, 219));
            }
        });

        panel.add(loginBtn);

        // Optional Image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
            Image i2 = i1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel image = new JLabel(new ImageIcon(i2));
            image.setBounds(430, 90, 120, 120);
            panel.add(image);
        } catch (Exception e) {
            System.out.println("Image not found");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String username = tfusername.getText();
        String password = new String(tfpassword.getPassword());

        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields");
            return;
        }

        try {
            Conn c = new Conn();

            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement pst = c.c.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
