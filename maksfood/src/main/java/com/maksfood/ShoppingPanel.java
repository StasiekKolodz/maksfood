package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingPanel extends RoundedPanel implements ActionListener, ListSelectionListener{


    // TODO
    // implement class for Shopping List and List of shopping lists
    // refactor

    public MainWindow window;

    public JPanel shoppingLabelPanel;
    public JPanel returnButtonPanel;
    private ColorButton returnButton;
    
    public ListContainer listsList;
    public ListContainer shoppingList;
    

    public ShoppingPanel(LayoutManager layout, int r, MainWindow w){
        super(layout, r);
        window = w;
        
        DefaultLabel shoppingLabel = new DefaultLabel("Shopping");

        //creating return button
        returnButton = new ColorButton("Return");
        window.setButton(returnButton, this);

        //creating button and label panels
        shoppingLabelPanel = new JPanel(new GridBagLayout());
        shoppingLabelPanel.setOpaque(false);
        returnButtonPanel = new JPanel(new GridLayout());
        shoppingLabelPanel.add(shoppingLabel);
        returnButtonPanel.add(returnButton);
        shoppingLabelPanel.setBounds(getVisibleRect());
        setOpaque(false);
        setBackground(new Color(255, 238, 219));


        //forming both list of lists and shopping list objects
        listsList =  new ListContainer(this, this);
        shoppingList =  new ListContainer(this, this);

        // setting example content of lists 
        listsList.listModel.addElement("Tortilla");
        listsList.listModel.addElement("StudentPack");
        listsList.listModel.addElement("Harcerz");

        // setting layout of the panel
        /* 
        TODO 
        fix allignment for desired look:
                | Shopping |
            -----------------------
            List 1    |     List 2
            -----------------------
                |  Return  |
        */
        setLayout();


        // setting appearance of lists, so it corresponds to the app graphics
        /* 
        TODO 
        set the visible frames of the list
        */
        setListAppearance(listsList.list);
        setListAppearance(shoppingList.list);


    }

    public void setLayout(){

        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,50,50,50);
        e.gridx = 1;
        e.gridy = 0;
        this.add(shoppingLabelPanel, e);
        
        e.gridx = 0;
        e.gridy = 1;
        this.add(listsList.listPanel, e);

        e.gridx = 2;
        e.gridy = 1;
        this.add(shoppingList.listPanel, e);
        
        e.gridx = 1;
        e.gridy = 2;
        this.add(returnButtonPanel, e);
    }


    public void setListAppearance(JList<String> list){

        // TODO use template
        list.setForeground(new Color(165, 56, 96));
        list.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 15));
        list.setBackground(new Color(255, 238, 219));

    }
    // returns a list of items for the given list name
    private static String[] getListItems(String listName) {
        /* 
        TODO 
        add database for shopping list items
        preferably in separete ShoppingList class
        */
        if (listName.equals("Tortilla")) {
            return new String[] {"Tortilla", "Lettuce Mix", "Roasted Chicken", "Garlic Sauce", "Sriracha Sauce"};
        } else if (listName.equals("StudentPack")) {
            return new String[] {"Piwo", "Zupka chińska", "Parówki"};
        } else if (listName.equals("Harcerz")) {
            return new String[] {"Szyszki", "Mielonka", "Keczup"};
        } else {
            return new String[] {};
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if(src == returnButton){
        window.currentPanel.setVisible(false);
        window.currentPanel = window.menuPanel;
        window.currentPanel.setVisible(true);
        }
        else if(src == listsList.addButton){
            String text = listsList.textField.getText();
                if (!text.isEmpty()) {
                    listsList.listModel.addElement(text);
                    listsList.textField.setText("");
                }
        }
        else if(src == shoppingList.addButton){
            String text = shoppingList.textField.getText();
                if (!text.isEmpty()) {
                    shoppingList.listModel.addElement(text);
                    shoppingList.textField.setText("");
                }
        }
        else if(src == listsList.removeButton){
            int selectedIndex = listsList.list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedItem = listsList.listModel.getElementAt(selectedIndex);

                    // Remove the selected item from the first list
                    listsList.listModel.removeElement(selectedItem);
        }
    }
        else if(src == shoppingList.removeButton){
            int selectedIndexSh = shoppingList.list.getSelectedIndex();
                if (selectedIndexSh != -1) {
                    String selectedItemSh = shoppingList.listModel.getElementAt(selectedIndexSh);

                    // Remove the selected item from the second list
                    shoppingList.listModel.removeElement(selectedItemSh);
        }
    }
    }

    // WARNING
    // Changing list removes added items
    // No save functionality!!!
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Object src = e.getSource();
            if (src == listsList.list) {
                int selectedIndex = listsList.list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = listsList.listModel.get(selectedIndex);
                    // update list 2 with selected list items
    
                    shoppingList.listModel.clear();
                    String[] items = getListItems(selectedValue);
                    for (String item : items) {
                        shoppingList.listModel.addElement(item);
                    }
                }
            }
        }
    }
}
