package com.maksfood;

import javax.swing.*;

import java.util.Date;
import java.util.Vector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

public class FridgeAddPanel extends RoundedPanel implements ActionListener{

    public MainWindow window;
    public DataBase DB;
    public Expiration expiration;
    JTextField nameTextField;
    JTextField amountTextField;
    JTextField expDateTextField;
    JComboBox<String> dayBox;
    JComboBox<String> monthBox;
    JComboBox<String> yearBox;
    public FridgeAddPanel(LayoutManager layout, int r, MainWindow w, DataBase dataBase, Expiration exp){
        super(layout, r);
        window = w;
        expiration = exp;
        DB = dataBase;
        JLabel fridgeLabel = new DefaultLabel("Add item to fridge");
        JLabel nameLabel = new DefaultLabel("Item Name");
        JLabel amountLabel = new DefaultLabel("Amount");
        JLabel expDateLabel = new DefaultLabel("Expiry Date");


        fridgeLabel.setForeground(new Color(165, 56, 96));
        fridgeLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 40));

        nameLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 25));
        amountLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 25));
        expDateLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 25));

        ColorButton returnButton = new ColorButton("Return");
        ColorButton addButton = new ColorButton("Add");
        window.setButton(returnButton,this);
        window.setButton(addButton,this);
        
        // Combo boxes
        Vector<String> dayList = new Vector<String>();
        for(int i = 1; i <= 31; i++){
            dayList.add(Integer.toString(i));
        }
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Vector<String> yearList = new Vector<String>();
        for(int i = 2022; i <= 2040; i++){
                    yearList.add(Integer.toString(i));
                }
        dayBox = new JComboBox<String>(dayList);
        monthBox = new JComboBox<String>(months);
        yearBox = new JComboBox<String>(yearList);

        //creating button and label panels
        JPanel fridgeAddLabelPanel = new JPanel(new GridLayout());
        fridgeAddLabelPanel.setOpaque(false);
        JPanel nameLabelPanel = new JPanel(new GridLayout());
        fridgeAddLabelPanel.setOpaque(false);
        JPanel amountLabelPanel = new JPanel(new GridLayout());
        fridgeAddLabelPanel.setOpaque(false);
        JPanel expDateLabelPanel = new JPanel(new GridLayout());
        fridgeAddLabelPanel.setOpaque(false);
        JPanel returnButtonPanel = new JPanel(new GridLayout());
        JPanel addButtonPanel = new JPanel(new GridLayout());
        JPanel datePanel = new JPanel(new GridLayout());
        datePanel.setOpaque(false);

        fridgeAddLabelPanel.add(fridgeLabel);
        returnButtonPanel.add(returnButton);
        addButtonPanel.add(addButton);
        amountLabelPanel.add(amountLabel);
        nameLabelPanel.add(nameLabel);
        expDateLabelPanel.add(expDateLabel);
        fridgeAddLabelPanel.setBounds(getVisibleRect());
        nameLabelPanel.setBounds(getVisibleRect());
       
       
        setOpaque(false);
        setBackground(new Color(255, 238, 219));

        datePanel.add(dayBox);
        datePanel.add(monthBox);
        datePanel.add(yearBox);
        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(20,5,20,5);
        e.gridx = 0;
        e.gridy = 1;
        e.gridwidth = 2;
        this.add(fridgeAddLabelPanel, e);

        e.gridx = 0;
        e.gridy = 2;
        e.gridwidth =2;
        this.add(nameLabelPanel, e);

        e.gridx = 0;
        e.gridy = 4;
        e.gridwidth = 2;
        this.add(amountLabelPanel, e);

        e.gridx = 0;
        e.gridy = 6;
        e.gridwidth = 2;
        this.add(expDateLabelPanel, e);

        e.gridwidth = 1;
        e.gridx = 0;
        e.gridy = 8;
        this.add(returnButtonPanel, e);
        e.gridx = 1;
        e.gridy = 8;
        this.add(addButtonPanel, e);

        nameTextField = new JTextField("", 16);
        amountTextField = new JTextField("", 16);
        // expDateTextField = new JTextField("", 16);
        e.gridx = 0;
        e.gridy = 3;
        e.gridwidth = 2;
        this.add(nameTextField, e);
        e.gridx = 0;
        e.gridy = 5;
        this.add(amountTextField, e);
        e.gridx = 0;
        e.gridy = 7;
        this.add(datePanel, e);
        // e.gridx = 1;
        // e.gridy = 7;
        // this.add(monthBox, e);
        // e.gridx = 2;
        // e.gridy = 7;
        // this.add(yearBox, e);
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Return")) {

            window.fridgePanel.updateList();
            window.currentPanel.setVisible(false);

            window.currentPanel = window.fridgePanel;

            window.currentPanel.setVisible(true);
        }
        if (e.getActionCommand().equals("Add")) {

            if(!nameTextField.getText().equals("")){
                addEnteredElement();
                expiration.updateAboutToExpire();
            }
        }

    }
    public void addEnteredElement(){
        
        String month = Integer.toString(monthBox.getSelectedIndex()+1);
        if(monthBox.getSelectedIndex()+1 < 10){
            month = "0" + month;
        }
        String day = Integer.toString(dayBox.getSelectedIndex()+1);
        if(dayBox.getSelectedIndex()+1 < 10){
            day = "0" + day;
        }
  
        String expDate = yearBox.getSelectedItem() + "-"  + month + "-"  + day;
        System.out.println(expDate);

        
        DB.sqlUpdate("INSERT INTO maksfood.fridge VALUES(DEFAULT,'"+
        nameTextField.getText() + "','" + amountTextField.getText() + "','" + expDate + "');");
        nameTextField.setText("");
        amountTextField.setText("");
        // System.out.println(expDate);

    }

}
