package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame {

    JProgressBar progressBar;
    JLabel loadingText;

    Splash() {

        setTitle("Employee Management System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 1200, 700);
        add(image);

        // Dark Overlay
        JPanel overlay = new JPanel();
        overlay.setBounds(0, 0, 1200, 700);
        overlay.setBackground(new Color(0, 0, 0, 150));
        overlay.setLayout(null);
        image.add(overlay);

        // Heading
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(200, 120, 800, 50);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 34));
        heading.setHorizontalAlignment(JLabel.CENTER);
        overlay.add(heading);

        // Subheading
        JLabel sub = new JLabel("Welcome to Smart Employee Dashboard");
        sub.setBounds(300, 180, 600, 30);
        sub.setForeground(Color.LIGHT_GRAY);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setHorizontalAlignment(JLabel.CENTER);
        overlay.add(sub);

        // Loading Text
        loadingText = new JLabel("Loading...");
        loadingText.setBounds(550, 400, 200, 30);
        loadingText.setForeground(Color.WHITE);
        loadingText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        overlay.add(loadingText);

        // Progress Bar
        progressBar = new JProgressBar();
        progressBar.setBounds(350, 450, 500, 25);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(new Color(52, 152, 219));
        progressBar.setStringPainted(true);
        overlay.add(progressBar);

        setVisible(true);

        // Start Loading Animation
        startLoading();
    }

    // Loading Logic
    private void startLoading() {

        Timer timer = new Timer(40, new ActionListener() {
            int progress = 0;

            public void actionPerformed(ActionEvent e) {
                progress++;
                progressBar.setValue(progress);

                // Dynamic loading text
                if (progress < 30) {
                    loadingText.setText("Loading modules...");
                } else if (progress < 60) {
                    loadingText.setText("Connecting to database...");
                } else if (progress < 90) {
                    loadingText.setText("Preparing dashboard...");
                } else {
                    loadingText.setText("Launching application...");
                }

                // When done → open Login
                if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
                    setVisible(false);
                    new Login();
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        new Splash();
    }
}
