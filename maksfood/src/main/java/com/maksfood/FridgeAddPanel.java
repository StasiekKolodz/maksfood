package com.maksfood;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import java.util.Vector;

public class FridgeAddPanel extends RoundedPanel implements ActionListener{

    public MainWindow window;
    public DataBase DB;
    JTextField nameTextField;
    JTextField amountTextField;
    JTextField expDateTextField;

    public FridgeAddPanel(LayoutManager layout, int r, MainWindow w, DataBase dataBase){
        super(layout, r);
        window = w;
        DB = dataBase;
        JLabel fridgeLabel = new JLabel("Add item to fridge");
        JLabel nameLabel = new JLabel("Item Name");
        JLabel amountLabel = new JLabel("Amount");
        JLabel expDateLabel = new JLabel("Expiry Date");


        fridgeLabel.setForeground(new Color(165, 56, 96));
        fridgeLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 30));

        nameLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        amountLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        expDateLabel.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));

        ColorButton returnButton = new ColorButton("Return");
        ColorButton addButton = new ColorButton("Add");
        // ColorButton deleteButton = new ColorButton("Delete");
        window.setButton(returnButton,this);
        // window.setButton(deleteButton,this);
        window.setButton(addButton,this);
        
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
        // JPanel deleteButtonPanel = new JPanel(new GridLayout());
        fridgeAddLabelPanel.add(fridgeLabel);
        returnButtonPanel.add(returnButton);
        addButtonPanel.add(addButton);
        amountLabelPanel.add(amountLabel);
        nameLabelPanel.add(nameLabel);
        expDateLabelPanel.add(expDateLabel);
        // deleteButtonPanel.add(deleteButton);
        fridgeAddLabelPanel.setBounds(getVisibleRect());
        nameLabelPanel.setBounds(getVisibleRect());
       
       
        setOpaque(false);
        setBackground(new Color(255, 238, 219));

        //adding elements to RecipesPanel
        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(20,5,20,5);
        e.gridx = 0;
        e.gridy = 1;
        this.add(fridgeAddLabelPanel, e);

        e.gridx = 0;
        e.gridy = 2;
        this.add(nameLabelPanel, e);

        e.gridx = 0;
        e.gridy = 4;
        this.add(amountLabelPanel, e);

        e.gridx = 0;
        e.gridy = 6;
        this.add(expDateLabelPanel, e);


        e.gridx = 0;
        e.gridy = 8;
        this.add(returnButtonPanel, e);
        e.gridx = 1;
        e.gridy = 8;
        this.add(addButtonPanel, e);

        nameTextField = new JTextField("", 16);
        amountTextField = new JTextField("", 16);
        expDateTextField = new JTextField("", 16);
        e.gridx = 0;
        e.gridy = 3;
        this.add(nameTextField, e);
        e.gridx = 0;
        e.gridy = 5;
        this.add(amountTextField, e);
        e.gridx = 0;
        e.gridy = 7;
        this.add(expDateTextField, e);


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
            }
        }

    }
    public void addEnteredElement(){
        DB.sqlUpdate("INSERT INTO maksfood.fridge VALUES(DEFAULT,'"+
        nameTextField.getText() + "','" + amountTextField.getText() + "','" + expDateTextField.getText() + "');");
        nameTextField.setText("");
        amountTextField.setText("");
        expDateTextField.setText("");
    }

}