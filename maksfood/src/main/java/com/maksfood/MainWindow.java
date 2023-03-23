package com.maksfood;

import javax.swing.*;
// import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements ActionListener{

    private JPanel currentPanel;

    public MainWindow() {

        // Set window properties
        setTitle("MainWindow");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100,70);
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
        button1.setPreferredSize(new Dimension(200, 60));
        button1.addActionListener( this );

        button2.setFont(new Font("Arial", Font.BOLD, 12));
        button2.setForeground(Color.WHITE);
        button2.setBackground(new Color(80,7,2));
        button2.setPreferredSize(new Dimension(200, 60));
        button2.addActionListener( this );

        button3.setFont(new Font("Arial", Font.BOLD, 12));
        button3.setForeground(Color.WHITE);
        button3.setBackground(new Color(80,7,2));
        button3.setPreferredSize(new Dimension(200, 60));
        button2.addActionListener( this );


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(button1,c);
        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(button2,c);
        c.gridx = 0;
        c.gridy = 3;
        buttonPanel.add(button3,c);

        //  // Set the content pane to the card panel
        getContentPane().setBackground(new Color(82, 7, 2));

        // Set the content pane to the button panel initially
        currentPanel = buttonPanel;
        getContentPane().setBackground(new Color(82, 7, 2));
        add(currentPanel);

        // Set the window to be visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a new panel depending on which button was pressed
        if (e.getActionCommand().equals("My Fridge")) {
            JPanel fridgePanel = new JPanel();
            JLabel fridgeLabel = new JLabel("My Fridge");
            fridgeLabel.setFont(new Font("Arial", Font.BOLD, 36));
            fridgePanel.add(fridgeLabel);
            currentPanel.setVisible(false);
            currentPanel = fridgePanel;
            getContentPane().add(currentPanel);
            currentPanel.setVisible(true);

        } else if (e.getActionCommand().equals("Recipes")) {
            JPanel recipesPanel = new JPanel();
            JLabel recipesLabel = new JLabel("Recipes");
            recipesLabel.setFont(new Font("Arial", Font.BOLD, 36));
            recipesPanel.add(recipesLabel);
            currentPanel.setVisible(false);
            currentPanel = recipesPanel;
            getContentPane().add(currentPanel);
            currentPanel.setVisible(true);

        } else if (e.getActionCommand().equals("Plan shopping")) {
            JPanel shoppingPanel = new JPanel();
            JLabel shoppingLabel = new JLabel("Plan shopping");
            shoppingLabel.setFont(new Font("Arial", Font.BOLD, 36));
            shoppingPanel.add(shoppingLabel);
            currentPanel.setVisible(false);
            currentPanel = shoppingPanel;
            getContentPane().add(currentPanel);
            currentPanel.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}