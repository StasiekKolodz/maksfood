package com.maksfood;

import javax.swing.*;

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
        setButton(button1);
        setButton(button2);
        setButton(button3);

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

        // // Set the content pane to the button panel initially
        getContentPane().setBackground(new Color(82, 7, 2));
        add(currentPanel);

        // Set the window to be visible
        setVisible(true);
    }

    public void setButton(JButton buttonName){
        buttonName.setFont(new Font("Arial", Font.BOLD, 12));
        buttonName.setForeground(Color.WHITE);
        buttonName.setBackground(new Color(80,7,2));
        buttonName.setPreferredSize(new Dimension(200, 60));
        buttonName.addActionListener( this );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a new panel depending on which button was pressed
        if (e.getActionCommand().equals("My Fridge")) {
            FridgePanel fridgePanel = new FridgePanel();
            currentPanel.setVisible(false);
            currentPanel = fridgePanel;
            getContentPane().add(currentPanel);
            currentPanel.setVisible(true);

        } else if (e.getActionCommand().equals("Recipes")) {
            RecipesPanel recipesPanel = new RecipesPanel();
            currentPanel.setVisible(false);
            currentPanel = recipesPanel;
            getContentPane().add(currentPanel);
            currentPanel.setVisible(true);

        } else if (e.getActionCommand().equals("Plan shopping")) {
            ShoppingPanel shoppingPanel = new ShoppingPanel();
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