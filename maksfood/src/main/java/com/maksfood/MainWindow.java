package com.maksfood;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        // Set window properties
        setTitle("MainWindow");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create label and buttons
        JLabel label = new JLabel("Choose an option:");
        label.setForeground(Color.WHITE);
        JButton button1 = new JButton("My Fridge");
        JButton button2 = new JButton("Recipes");
        JButton button3 = new JButton("Plan shopping");

        // Set button properties
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(80,7,2));
        button1.setPreferredSize(new Dimension(50, 30));

        button2.setFont(new Font("Arial", Font.BOLD, 12));
        button2.setForeground(Color.WHITE);
        button2.setBackground(new Color(80,7,2));
        button2.setPreferredSize(new Dimension(50, 30));

        button3.setFont(new Font("Arial", Font.BOLD, 12));
        button3.setForeground(Color.WHITE);
        button3.setBackground(new Color(80,7,2));
        button3.setPreferredSize(new Dimension(50, 30));

        // Create panel to hold buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(61, 11, 81, 140);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);


        getContentPane().setBackground(new Color(82, 7, 2));

        // Add components to frame
        add(label);
        add(buttonPanel);

        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}