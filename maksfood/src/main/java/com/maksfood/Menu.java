package com.maksfood;

import java.awt.*;
import java.awt.event.ActionListener;

// import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;

public class Menu extends RoundedPanel implements ActionListener{

        private MainWindow window;
    
        public Menu(LayoutManager layout, int r, MainWindow w){
            super(layout, r);
            window = w;
            JLabel label = new JLabel("Choose an option:");
            
            ColorButton button1 = new ColorButton("My Fridge");
            ColorButton button2 = new ColorButton("Recipes");
            ColorButton button3 = new ColorButton("Plan shopping");
            
            //set label properties
            label.setForeground(new Color(165, 56, 96));
            label.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));
            
            
            // Set button properties
            window.setButton(button1, this);
            window.setButton(button2, this);
            window.setButton(button3, this);
            
            
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
            setOpaque(false);
            setBackground(new Color(255, 238, 219));
            setSize(900, 1000);
            
            GridBagConstraints d = new GridBagConstraints();
            d.insets = new Insets(50,10,50,10);
            
            d.gridx = 0;
            d.gridy = 1;
            add(labelPanel,d);
            
            d.gridx = 0;
            d.gridy = 2;
            add(buttonPanel,d);
        }
    
        
    
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new panel depending on which button was pressed
            if (e.getActionCommand().equals("My Fridge")) {

                window.currentPanel.setVisible(false);
                window.currentPanel = window.fridgePanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
    
            } else if (e.getActionCommand().equals("Recipes")) {

                window.currentPanel.setVisible(false);
                window.currentPanel = window.recipesPanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
    
            } else if (e.getActionCommand().equals("Plan shopping")) {

                window.currentPanel.setVisible(false);
                window.currentPanel = window.shoppingPanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
            }
        }
        }

