package com.maksfood;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
// import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        // Set window properties
        setTitle("MainWindow");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100,100);

        // Create label and buttons
        JLabel label = new JLabel("Choose an option:");
        
        JButton button1 = new ColorButton("My Fridge");
        JButton button2 = new ColorButton("Recipes");
        JButton button3 = new ColorButton("Plan shopping");
        
        
        //set label properties
        label.setForeground(new Color(165, 56, 96));
        label.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
        
        // Set button properties
        button1.setPreferredSize(new Dimension(200, 60));
        button2.setPreferredSize(new Dimension(200, 60));
        button3.setPreferredSize(new Dimension(200, 60));
        
        
        //creating button container
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,100,30,100);
        c.ipadx = 20;
        c.ipady = 20;
        
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(button1,c);

        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(button2,c);

        c.gridx = 0;
        c.gridy = 3;
        buttonPanel.add(button3,c);
        
        buttonPanel.setBackground(new Color(255, 238, 219));

        
        // Create label container
        JPanel labelPanel = new JPanel(new GridLayout());
        labelPanel.add(label);
        labelPanel.setOpaque(false);


        // Creating menu container
        JPanel menuPanel = new RoundedPanel(new GridBagLayout(), 45);
        menuPanel.setOpaque(false);
        menuPanel.setBackground(new Color(255, 238, 219));
        menuPanel.setSize(900, 1000);

        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(50,10,50,10);

        d.gridx = 0;
        d.gridy = 1;
        menuPanel.add(labelPanel,d);

        d.gridx = 0;
        d.gridy = 2;
        menuPanel.add(buttonPanel,d);
        
        
        // Add components to frame
        getContentPane().setBackground(new Color(165, 56, 96));
        setLayout(new GridBagLayout());
        GridBagConstraints e = new GridBagConstraints();
        e.gridheight = 900;
        e.gridwidth = 1200;
        e.gridx = 0;
        e.gridy = 2;
        add(menuPanel,e);


        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
    }

}