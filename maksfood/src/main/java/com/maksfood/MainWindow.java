package com.maksfood;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        // Set window properties
        setTitle("MainWindow");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create label and buttons
        JLabel label = new JLabel("Choose an option:");
        JButton button1 = new JButton("My Fridge");
        JButton button2 = new JButton("Recipes");
        JButton button3 = new JButton("Plan shopping");

        // Set button properties
        button1.setFont(new Font("Arial", Font.PLAIN, 12));
        button2.setFont(new Font("Arial", Font.PLAIN, 12));
        button3.setFont(new Font("Arial", Font.PLAIN, 12));

        button1.setPreferredSize(new Dimension(10, 10));
        button2.setPreferredSize(new Dimension(10, 10));
        button3.setPreferredSize(new Dimension(10, 10));

        // Create panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 4, 4));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        getContentPane().setBackground(new Color(82, 7, 2));

        // Add components to frame
        setLayout(new BorderLayout());
        add(label, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.CENTER);

        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}