package com.maksfood;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;

public class Menu extends RoundedPanel implements ActionListener{

        private MainWindow window;
        public Expiration expiration;
        public DataBase DB;
        public DefaultLabel dateTimeLabel;
        public JPanel buttonPanel;
        public DefaultLabel label;
        public JPanel labelPanel;
        public ColorButton button1;
        public ColorButton button2;
        public ColorButton button3;
    
        public Menu(LayoutManager layout, int r, MainWindow w, DataBase db, DefaultLabel dL){
            super(layout, r);
            window = w;
            DB = db;
            dateTimeLabel = dL;

            label = new DefaultLabel("MAKSFOOD", 40);
            
            button1 = new ColorButton("My Fridge");
            button2 = new ColorButton("Recipes");
            button3 = new ColorButton("Plan shopping");
            
            // Set button properties
            window.setButton(button1, this);
            window.setButton(button2, this);
            window.setButton(button3, this);

            buttonPanel = addButtonPanel();

            // Create label container
            JPanel labelPanel = new JPanel(new GridLayout());
            labelPanel.add(label);
            labelPanel.setOpaque(false);
            
            
            // Creating menu container
            setOpaque(false);
            setBackground(new Color(255, 238, 219, 200));
            setSize(800, 700);
         
            GridBagConstraints d = new GridBagConstraints();
            d.insets = new Insets(50,10,50,10);
            
            d.gridx = 0;
            d.gridy = 1;
            add(labelPanel,d);
            
            d.gridx = 0;
            d.gridy = 2;
            add(buttonPanel,d);

            labelPanel.revalidate();
            labelPanel.repaint();
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }

        public JPanel addButtonPanel(){
            buttonPanel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(10,50,10,50);
            c.ipadx = 10;
            c.ipady = 10;

            dateTimeLabel = new DefaultLabel(displayExpData());

            c.gridx = 0;
            c.gridy = 1;
            buttonPanel.add(dateTimeLabel,c);
            
            c.gridx = 0;
            c.gridy = 2;
            buttonPanel.add(button1,c);
    
            c.gridx = 0;
            c.gridy = 3;
            buttonPanel.add(button2,c);
    
            c.gridx = 0;
            c.gridy = 4;
            buttonPanel.add(button3,c);

            buttonPanel.setBackground(new Color(255, 238, 219, 0));

            return buttonPanel;
        }

        public String displayExpData(){
            DefaultLabel currentStatus = new DefaultLabel("Date");
            int expiredCount = DB.getRowsCountExpiredProds(false);
            String expiredCountStr = Integer.toString(expiredCount);
            if(expiredCount == 1){
                currentStatus.setText("<html><div style='text-align: center;'>Warning!<br> " + expiredCountStr +  " product is about to expire!</div></html>");  
            }
            else if(expiredCount > 1){
                currentStatus.setText("<html><div style='text-align: center;'>Warning!<br> " + expiredCountStr +  " products are about to expire!</div></html>");
            }
            else{
                currentStatus.setText("");
            }
            return currentStatus.getText();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new panel depending on which button was pressed
            if (e.getActionCommand().equals("My Fridge")) {

                window.currentPanel.setVisible(false);
                window.currentPanel = window.fridgePanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
                window.fridgePanel.updateList();
    
            } else if (e.getActionCommand().equals("Recipes")) {
                window.recipesPanel.update_products();
                window.recipesPanel.update_fav_recipes_from_db();
                window.recipesPanel.update_favourite_recipes();
                window.currentPanel.setVisible(false);
                window.currentPanel = window.recipesPanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
    
            } else if (e.getActionCommand().equals("Plan shopping")) {

                window.currentPanel.setVisible(false);
                window.shoppingPanel.updateListsList();
                window.currentPanel = window.shoppingPanel;
                window.getContentPane().add(window.currentPanel);
                window.currentPanel.setVisible(true);
            }
        }
        }

