package com.maksfood;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingPanel extends RoundedPanel implements ActionListener{


    // TODO
    // implement class for Shopping List and List of shopping lists
    // refactor

    public MainWindow window;
    public JPanel shoppingLabelPanel;
    public JPanel returnButtonPanel;
    private ColorButton addButton;
    private ColorButton addItemButton;
    private ColorButton returnButton;
    private JTextField textListField;
    private JTextField textShoppingField;
    public JPanel listsListPanel;
    public JPanel shoppingListPanel;
    
    public JList<String> listsList;
    public DefaultListModel<String> listsListModel;
    public DefaultListModel<String> shoppingListModel;
    public JList<String> shoppingList;
    

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


        //forming both list of lists and shopping list
        /* 
        TODO 
        refactor the function, so it sets only one list, which is passed as an argument
        */
        formLists();


        // setting add button both for list of lists and shopping list
        /* 
        TODO 
        refactor the function, so it sets only one button, a list is passed as an argument
        */
        setAddButton(this);


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
        setListAppearance(listsList);
        setListAppearance(shoppingList);


    }

    public void setLayout(){

        GridBagConstraints e = new GridBagConstraints();
        e.insets = new Insets(50,10,50,10);
        e.gridx = 1;
        e.gridy = 1;
        this.add(shoppingLabelPanel, e);
        
        e.gridx = 0;
        e.gridy = 2;
        this.add(listsListPanel, e);

        e.gridx = 2;
        e.gridy = 2;
        this.add(shoppingListPanel, e);
        
        e.gridx = 1;
        e.gridy = 3;
        this.add(returnButtonPanel, e);
    }

    public void formLists(){
        listsListPanel = new JPanel();
        listsListPanel.setLayout(new BorderLayout());

        shoppingListPanel = new JPanel();
        shoppingListPanel.setLayout(new BorderLayout());

        textListField = new JTextField();
        textShoppingField = new JTextField();

        listsListModel = new DefaultListModel<>();

        /* 
        TODO 
        add database for shopping lists collection
        */
        listsListModel.addElement("Tortilla");
        listsListModel.addElement("StudentPack");
        listsListModel.addElement("Harcerz");

        shoppingListModel = new DefaultListModel<>();
        
        listsList = new JList<>(listsListModel);
        shoppingList = new JList<>(shoppingListModel);

        /* 
        TODO 
        refactor the function
        */
        listsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = listsList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedValue = listsListModel.get(selectedIndex);
                        // update list 2 with selected list items
                        shoppingListModel.clear();
                        for (String item : getListItems(selectedValue)) {
                            shoppingListModel.addElement(item);
                        }
                    }
                }
            }
        });

        listsListPanel.add(listsList, BorderLayout.NORTH);
        listsListPanel.add(textListField, BorderLayout.SOUTH);

        shoppingListPanel.add(shoppingList, BorderLayout.NORTH);
        shoppingListPanel.add(textShoppingField, BorderLayout.SOUTH);
    }

    public void setAddButton(ActionListener l){
        addButton = new ColorButton("Add item", 15);
        addItemButton = new ColorButton("Add item", 15);
        listsListPanel.add(addButton, BorderLayout.EAST);
        shoppingListPanel.add(addItemButton, BorderLayout.WEST);
        addButton.addActionListener(l);
        addItemButton.addActionListener(l);
    }


    public void setListAppearance(JList list){
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
        else if(src == addButton){
            String text = textListField.getText();
                if (!text.isEmpty()) {
                    listsListModel.addElement(text);
                    textListField.setText("");
                }
        }
        else if(src == addItemButton){
            String text = textShoppingField.getText();
                if (!text.isEmpty()) {
                    shoppingListModel.addElement(text);
                    textShoppingField.setText("");
                }
        }
    }


}
